package com.lmiky.platform.http.client;

import java.io.IOException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.lmiky.platform.util.BundleUtils;

public class PoolHttpClient extends CustomCloseHttpClient {
	/**
	 * 最大连接数
	 */
	public final static int DEFAULT_MAX_TOTAL_CONNECTIONS = 400;
	/**
	 * 每个路由最大连接数
	 */
	public final static int DEFAULT_MAX_ROUTE_CONNECTIONS = 300;

	private PoolingHttpClientConnectionManager cm = null;

	protected PoolHttpClient() {
		int httpPoolMaxTotalConnections = 0;
		try {
			httpPoolMaxTotalConnections = BundleUtils.getIntContextValue("http.pool.max.total.connections");
		} catch(Throwable e) {
			httpPoolMaxTotalConnections = DEFAULT_MAX_TOTAL_CONNECTIONS;
		}
		int httpPoolMaxRouteConnections = 0;
		try {
			httpPoolMaxRouteConnections = BundleUtils.getIntContextValue("http.pool.max.route.connections");
		} catch(Throwable e) {
			httpPoolMaxRouteConnections = DEFAULT_MAX_ROUTE_CONNECTIONS;
		}
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(httpPoolMaxTotalConnections);
		cm.setDefaultMaxPerRoute(httpPoolMaxRouteConnections);
		setClient(HttpClients.custom().setConnectionManager(cm).build());
		setAutoClose(false);
	}

	public static PoolHttpClient getInstance() {
		return SingletonHolder.instance;
	}

	private static class SingletonHolder {
		protected static final PoolHttpClient instance = new PoolHttpClient();
	}

	/**
	 * 从连接池获取连接
	 * @return 连接
	 * @author lmiky
	 * @date 2016年4月21日 下午8:19:34
	 */
	public CloseableHttpClient pool() {
		return getClient();
	}
	
	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		throw new RuntimeException("pool client can not support close!");
	}
	
	/**
	 * 停掉连接池
	 * @throws IOException
	 * @author lmiky
	 * @date 2016年4月21日 下午8:32:39
	 */
	public void closePool() throws IOException {
		client.close();
	}
}
