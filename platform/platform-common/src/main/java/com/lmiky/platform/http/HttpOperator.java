package com.lmiky.platform.http;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import com.lmiky.platform.http.client.CustomCloseHttpClient;
import com.lmiky.platform.util.BundleUtils;

/**
 * HTTP请求工具
 * @author lmiky
 * @date 2013-11-17
 */
public class HttpOperator {

	// 编码
	public static final String CHARSET_UTF8 = "UTF-8";
	public static final String CHARSET_GBK = "GBK";
	public static final String CHARSET_GB2312 = "GB2312";
	public static final String CHARSET_DEFAULT = CHARSET_UTF8;

	/**
	 * 连接超时时间
	 */
	public final static int DEFAULT_CONNECT_TIMEOUT = 10000;
	/**
	 * 读取超时时间
	 */
	public final static int DEFAULT_SOCKET_TIMEOUT = 10000;

	protected RequestConfig defaultRequestConfig = null;

	protected HttpOperator() {
		int httpSocketTimeout = 0;
		try {
			httpSocketTimeout = BundleUtils.getIntContextValue("http.socket.timeout");
		} catch(Throwable e) {
			httpSocketTimeout = DEFAULT_SOCKET_TIMEOUT;
		}
		int httpConnectTimeout = 0;
		try {
			httpConnectTimeout = BundleUtils.getIntContextValue("http.connect.timeout");
		} catch(Throwable e) {
			httpConnectTimeout = DEFAULT_CONNECT_TIMEOUT;
		}
		defaultRequestConfig = RequestConfig.custom().setSocketTimeout(httpSocketTimeout).setConnectTimeout(httpConnectTimeout).build();
	}
	
	public static HttpOperator getInstance() {
		return SingletonHolder.instance;
	}

	private static class SingletonHolder {
		protected static final HttpOperator instance = new HttpOperator();
	}

	/**
	 * 请求反馈处理类
	 * @author lmiky
	 * @date 2014-8-7 下午2:51:15
	 */
	public static ResponseHandler<String> stringResponseHandler = new ResponseHandler<String>() {

		/*
		 * (non-Javadoc)
		 * @see org.apache.http.client.ResponseHandler#handleResponse(org.apache.http.HttpResponse)
		 */
		@Override
		public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			try {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					String content = entity != null ? EntityUtils.toString(entity, CHARSET_DEFAULT) : null;
					// CloseableHttpClient.execute() 240行会自动EntityUtils.consume(entity);
					// EntityUtils.consume(entity);
					return content;
				} else {
					throw new ClientProtocolException("HTTP请求非预期结果码: " + status);
				}
			} finally {
				if(response != null && response instanceof CloseableHttpResponse) {
					((CloseableHttpResponse)response).close();
				}
			}
		}
	};

	/**
	 * 创建客户端构造器
	 * @return
	 * @throws Exception
	 * @author lmiky
	 * @date 2015年10月26日 下午2:40:39
	 */
	public HttpClientBuilder createHttpClientBuilder() throws Exception {
		return HttpClients.custom();
	}

	/**
	 * 创建
	 * @param httpClientBuilder
	 * @return
	 * @throws Exception
	 * @author lmiky
	 * @date 2015年10月26日 下午2:49:36
	 */
	public CloseableHttpClient build(HttpClientBuilder httpClientBuilder) throws Exception {
		return httpClientBuilder.build();
	}

	/**
	 * 创建连接客户端
	 * @throws Exception
	 * @author lmiky
	 * @date 2014-8-7 上午11:07:10
	 * @return
	 */
	public CloseableHttpClient createDefaultClient() throws Exception {
		return HttpClients.createDefault();
	}

	/**
	 * 设置Cookie 存储
	 * @param httpClientBuilder
	 * @param cookieStore
	 * @throws Exception
	 * @author lmiky
	 * @date 2015年10月26日 下午2:42:55
	 */
	public void setCookieStore(HttpClientBuilder httpClientBuilder, CookieStore cookieStore) throws Exception {
		if (cookieStore == null) {
			cookieStore = new BasicCookieStore();
		}
		httpClientBuilder.setDefaultCookieStore(cookieStore);
	}

	/**
	 * 关闭连接
	 * @author lmiky
	 * @date 2014-8-7 上午11:07:04
	 * @param httpClient
	 * @throws IOException
	 */
	public void close(HttpClient httpClient) throws IOException {
		// if(httpRequestBase != null) {
		// httpRequestBase.releaseConnection();
		// }
		if (httpClient != null) {
			try {
				if (httpClient instanceof CloseableHttpClient) {
					CloseableHttpClient closeableHttpClient = null;
					if (closeableHttpClient instanceof CustomCloseHttpClient) {
						CustomCloseHttpClient autoHttpClient = (CustomCloseHttpClient) httpClient;
						closeableHttpClient = autoHttpClient.getClient();
					} else {
						closeableHttpClient = (CloseableHttpClient) httpClient;
					}
					if (closeableHttpClient != null) {
						closeableHttpClient.close();
					}
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}

	/**
	 * 执行请求
	 * @author lmiky
	 * @date 2014-8-7 下午2:27:42
	 * @param httpRequestBase
	 * @return
	 * @throws Exception
	 */
	public String execute(HttpRequestBase httpRequestBase) throws Exception {
		return execute(httpRequestBase, null);
	}

	/**
	 * 执行请求
	 * @param httpRequestBase 请求方法
	 * @param httpClient 请求客户端
	 * @return 请求内容
	 * @throws Exception
	 * @author lmiky
	 * @date 2015年10月26日 上午11:59:08
	 */
	public String execute(HttpRequestBase httpRequestBase, HttpClient httpClient) throws Exception {
		try {
			if (httpClient == null) {
				httpClient = createDefaultClient();
			}
			// 构建请求内容
			if (httpClient instanceof CustomCloseHttpClient) {
				return ((CustomCloseHttpClient) httpClient).getClient().execute(httpRequestBase, stringResponseHandler);
			} else {
				return httpClient.execute(httpRequestBase, stringResponseHandler);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// 关闭资源
			if (httpClient instanceof CustomCloseHttpClient) {
				CustomCloseHttpClient autoHttpClient = (CustomCloseHttpClient) httpClient;
				if (autoHttpClient.isAutoClose()) {
					close(autoHttpClient);
				}
			} else {
				close(httpClient);
			}
		}
	}

	/**
	 * 构建参数列表
	 * @author lmiky
	 * @date 2014-8-7 下午3:17:06
	 * @param params
	 * @return
	 */
	private static List<NameValuePair> getParamsList(Map<String, String> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (CollectionUtils.isEmpty(params)) {
			return nvps;
		}
		for (Map.Entry<String, String> map : params.entrySet()) {
			nvps.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}
		return nvps;
	}

	/**
	 * 方读取网址内容
	 * @author lmiky
	 * @date 2013-11-17
	 * @param url
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public String get(String url) throws Exception {
		return get(url, null, null, null);
	}

	/**
	 * 方读取网址内容
	 * @author lmiky
	 * @date 2013-11-17
	 * @param url
	 * @param httpClient
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public String get(String url, HttpClient httpClient) throws Exception {
		return get(url, null, null, httpClient);
	}

	/**
	 * get
	 * @author lmiky
	 * @date 2014-8-7 下午3:13:45
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String get(String url, Map<String, String> params) throws Exception {
		return get(url, params, null, null);
	}

	/**
	 * get
	 * @author lmiky
	 * @date 2014-8-7 下午3:13:45
	 * @param url
	 * @param params
	 * @param httpClient
	 * @return
	 * @throws Exception
	 */
	public String get(String url, Map<String, String> params, HttpClient httpClient) throws Exception {
		return get(url, params, null, httpClient);
	}

	/**
	 * get
	 * @author lmiky
	 * @date 2014-8-7 下午3:14:26
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String get(String url, Map<String, String> params, String charset) throws Exception {
		return get(url, params, charset, null);
	}

	/**
	 * get
	 * @author lmiky
	 * @date 2014-8-7 下午3:14:26
	 * @param url
	 * @param params
	 * @param charset
	 * @param httpClient
	 * @return
	 * @throws Exception
	 */
	public String get(String url, Map<String, String> params, String charset, HttpClient httpClient) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		// 设置编码
		if (StringUtils.isBlank(charset)) {
			charset = CHARSET_DEFAULT;
		}
		// 构建参数
		List<NameValuePair> nvps = getParamsList(params);
		if (!CollectionUtils.isEmpty(nvps)) {
			String formatParams = URLEncodedUtils.format(nvps, charset);
			if (url.indexOf("?") < 0) {
				url = url + "?" + formatParams;
			} else {
				url = url + "&" + formatParams;
			}
		}
		HttpGet httpGet = new HttpGet(url);
		return execute(httpGet, httpClient);
	}

	/**
	 * post
	 * @author lmiky
	 * @date 2014-8-7 下午3:23:38
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public String post(String url, Map<String, String> params) throws Exception {
		return post(url, params, null, null);
	}

	/**
	 * post
	 * @author lmiky
	 * @date 2014-8-7 下午3:23:38
	 * @param url
	 * @param params
	 * @param httpClient
	 * @return
	 * @throws Exception
	 */
	public String post(String url, Map<String, String> params, HttpClient httpClient) throws Exception {
		return post(url, params, null, httpClient);
	}

	/**
	 * post
	 * @author lmiky
	 * @date 2014-8-7 下午3:23:43
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String post(String url, Map<String, String> params, String charset) throws Exception {
		return post(url, params, charset, null);
	}

	/**
	 * post
	 * @author lmiky
	 * @date 2014-8-7 下午3:23:43
	 * @param url
	 * @param params
	 * @param charset
	 * @param httpClient
	 * @return
	 * @throws Exception
	 */
	public String post(String url, Map<String, String> params, String charset, HttpClient httpClient) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		// 设置编码
		if (StringUtils.isBlank(charset)) {
			charset = CHARSET_DEFAULT;
		}
		try {
			// 模拟表单
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(getParamsList(params), charset);
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(formEntity);
			return execute(httpPost, httpClient);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 启用默认的编码post内容体
	 * @author lmiky
	 * @date 2014-8-7 上午11:10:00
	 * @param url
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String post(String url, String content) throws Exception {
		return post(url, CHARSET_DEFAULT, content, null);
	}

	/**
	 * 启用默认的编码post内容体
	 * @author lmiky
	 * @date 2014-8-7 上午11:10:00
	 * @param url
	 * @param content
	 * @param httpClient
	 * @return
	 * @throws Exception
	 */
	public String post(String url, String content, HttpClient httpClient) throws Exception {
		return post(url, CHARSET_DEFAULT, content, httpClient);
	}

	/**
	 * post内容体
	 * @author lmiky
	 * @date 2014-8-7 上午11:09:50
	 * @param url
	 * @param charset
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String post(String url, String charset, String content) throws Exception {
		return post(url, charset, content, null);
	}

	/**
	 * post内容体
	 * @author lmiky
	 * @date 2014-8-7 上午11:09:50
	 * @param url
	 * @param charset
	 * @param content
	 * @param httpClient
	 * @return
	 * @throws Exception
	 */
	public String post(String url, String charset, String content, HttpClient httpClient) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			HttpPost httpPost = new HttpPost(url);
			// 构建请求内容
			StringEntity entity = new StringEntity(content, StringUtils.isBlank(charset) ? CHARSET_DEFAULT : charset);
			httpPost.setEntity(entity);
			return execute(httpPost, httpClient);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 以将文件写入流的方式传输文件
	 * @author lmiky
	 * @date 2014-8-7 下午2:31:16
	 * @param url
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public String postFile(String url, File file) throws Exception {
		return postFile(url, file, null);
	}

	/**
	 * 以将文件写入流的方式传输文件
	 * @author lmiky
	 * @date 2014-8-7 下午2:31:16
	 * @param url
	 * @param file
	 * @param httpClient
	 * @return
	 * @throws Exception
	 */
	public String postFile(String url, File file, HttpClient httpClient) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		if (file == null) {
			return null;
		}
		HttpPost httpPost = new HttpPost(url);
		// 构建请求内容
		// InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(file), -1, ContentType.APPLICATION_OCTET_STREAM); //-1表示未知文件大小
		// reqEntity.setChunked(true);
		FileEntity fileEntity = new FileEntity(file, ContentType.DEFAULT_BINARY);
		fileEntity.setChunked(true);
		httpPost.setEntity(fileEntity);
		return execute(httpPost, httpClient);
	}

	/**
	 * 模拟表单，以key-value的方式传输文件
	 * @author lmiky
	 * @date 2014-8-7 下午3:35:58
	 * @param url
	 * @param file
	 * @param httpKey
	 * @return
	 * @throws Exception
	 */
	public String postFileInKey(String url, File file, String httpKey) throws Exception {
		return postFileInKey(url, file, httpKey, null);
	}

	/**
	 * 模拟表单，以key-value的方式传输文件
	 * @author lmiky
	 * @date 2014-8-7 下午3:35:58
	 * @param url
	 * @param file
	 * @param httpKey
	 * @param httpClient
	 * @return
	 * @throws Exception
	 */
	public String postFileInKey(String url, File file, String httpKey, HttpClient httpClient) throws Exception {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		if (file == null) {
			return null;
		}
		HttpPost httpPost = new HttpPost(url);
		// 构建表单字段
		FileBody bin = new FileBody(file);
		HttpEntity reqEntity = MultipartEntityBuilder.create().addPart(httpKey, bin).build();
		httpPost.setEntity(reqEntity);
		return execute(httpPost);
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(get("http://www.baidu.com/"));
		// String data =
		// "backEndUrl=http%3A%2F%2F218.17.162.151%3A8081%2Fopetv-queen%2Fapi%2FaccountPayList%2Fchinapay%2Fumpm%2Fnotify.do&charset=UTF-8&merId=898440348120476&orderAmount=10&orderCurrency=156&orderDescription=100030%E4%BA%8E2014%2F08%2F07+10%3A22%3A41%E7%94%B3%E8%AF%B7%E7%BC%B4%E8%B4%B9%E5%85%85%E5%80%BC&orderNumber=1408071022010212&orderTime=20140807102241&signMethod=MD5&signature=1bf1024473b7aee7db5ed2afe30fd824&transType=01&version=1.0.0";
		// String respString = post("https://mgate.unionpay.com/gateway/merchant/trade", data);
		// System.out.println(respString);
		// File file = new File("d:/13617.jpg");
		// System.out.println(postFile("http://localhost:8080/lmiky/file/upload.shtml", file, "file"));
		HttpOperator instance = HttpOperator.getInstance();
		System.out.println(instance.post("http://wx.21f2f.com/api/weixin/message.do?signature=signature&timestamp=timestamp&nonce=nonce", "test微信提交"));
	}
}
