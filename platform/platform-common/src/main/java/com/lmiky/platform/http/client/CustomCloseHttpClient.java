package com.lmiky.platform.http.client;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import com.lmiky.platform.http.HttpOperator;

/**
 * 自定义关闭HTTP客户端
 * @author lmiky
 * @date 2015年10月26日 下午3:34:57
 */
@SuppressWarnings("deprecation")
public class CustomCloseHttpClient extends CloseableHttpClient {
	protected CloseableHttpClient client;
	protected boolean autoClose = true;	//是否自动关闭
	
	public CustomCloseHttpClient() {
		
	}

	public CustomCloseHttpClient(boolean autoClose) throws Exception {
		this.client = HttpOperator.getInstance().createDefaultClient();
		this.autoClose = autoClose;
	}
	
	public CustomCloseHttpClient(CloseableHttpClient client,  boolean autoClose) {
		this.client = client;
		this.autoClose = autoClose;
	}

	
	/**
	 * @return the client
	 */
	public CloseableHttpClient getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(CloseableHttpClient client) {
		this.client = client;
	}

	/**
	 * @return the autoClose
	 */
	public boolean isAutoClose() {
		return autoClose;
	}

	/**
	 * @param autoClose the autoClose to set
	 */
	public void setAutoClose(boolean autoClose) {
		this.autoClose = autoClose;
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#getParams()
	 */
	@Override
	public HttpParams getParams() {
		// TODO Auto-generated method stub
		return client.getParams();
	}

	/* (non-Javadoc)
	 * @see org.apache.http.client.HttpClient#getConnectionManager()
	 */
	@Override
	public ClientConnectionManager getConnectionManager() {
		// TODO Auto-generated method stub
		return client.getConnectionManager();
	}

	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		client.close();
	}

	@Override
	protected CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
		throw new RuntimeException("auth http client not support doExecute method!");
	}
}
