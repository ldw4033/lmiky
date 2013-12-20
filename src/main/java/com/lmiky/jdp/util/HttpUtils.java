package com.lmiky.jdp.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * HTTP
 * @author lmiky
 * @date 2013-11-17
 */
public class HttpUtils {
	
	/**
	 * 方读取网址内容
	 * @author lmiky
	 * @date 2013-11-17
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String get(String url) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			//执行get请求  
		    HttpResponse response = httpClient.execute(httpGet);  
		    //获取响应实体  
		    int status = response.getStatusLine().getStatusCode();
            if (status >= HttpStatus.SC_OK && status < HttpStatus.SC_MULTIPLE_CHOICES) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
		} finally {
			httpClient.close();
        }
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(get("http://www.google.com/"));
	}
}
