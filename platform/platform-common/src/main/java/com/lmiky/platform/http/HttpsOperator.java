package com.lmiky.platform.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * HTTPS请求工具
 * @author lmiky
 * @date 2015年10月26日 下午1:41:04
 */
public class HttpsOperator extends HttpOperator {
	
	/**
	 * @return
	 * @author lmiky
	 * @date 2015年10月26日 下午1:57:45
	 */
	public static HttpsOperator getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		protected static final HttpsOperator instance = new HttpsOperator();
	}

	/* (non-Javadoc)
	 * @see com.lmiky.platform.http.HttpOperator#createDefaultClient()
	 */
	public CloseableHttpClient createDefaultClient() throws Exception {
		HttpClientBuilder httpClientBuilder = createHttpClientBuilder();
		setSSL(httpClientBuilder);
		return httpClientBuilder.build();
	}
	
	/**
	 * 设置SSL
	 * @param httpClientBuilder
	 * @throws Exception
	 * @author lmiky
	 * @date 2015年10月26日 下午2:42:55
	 */
	public void setSSL(HttpClientBuilder httpClientBuilder) throws Exception {
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			// 信任所有
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				return true;
			}
		}).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		httpClientBuilder.setSSLSocketFactory(sslsf);
	}
}
