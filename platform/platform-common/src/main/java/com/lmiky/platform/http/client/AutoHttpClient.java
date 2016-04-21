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
 * 自动关闭HTTP客户端
 * @author lmiky
 * @date 2015年10月26日 下午3:34:57
 */
@SuppressWarnings("deprecation")
public class AutoHttpClient extends CloseableHttpClient {
	private CloseableHttpClient target;
	private boolean autoClose = true;	//是否自动关闭
	
	public AutoHttpClient() {
		
	}

	public AutoHttpClient(boolean autoClose) {
		this.target = HttpOperator.getInstance().createDefaultClient();
		this.autoClose = autoClose;
	}
	
	public AutoHttpClient(CloseableHttpClient target,  boolean autoClose) {
		this.target = target;
		this.autoClose = autoClose;
	}

	/**
	 * @return the target
	 */
	public CloseableHttpClient getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(CloseableHttpClient target) {
		this.target = target;
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

	@Override
	public HttpParams getParams() {
		// TODO Auto-generated method stub
		return target.getParams();
	}

	@Override
	public ClientConnectionManager getConnectionManager() {
		// TODO Auto-generated method stub
		return target.getConnectionManager();
	}

	@Override
	public void close() throws IOException {
		target.close();
	}

	@Override
	protected CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
		throw new RuntimeException("auth http client not support doExecute method!");
	}
}
