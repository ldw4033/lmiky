package com.lmiky.jdp.web.model;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 类说明
 * @author lmiky
 * @date 2013-4-24
 */
public class ContinuationRequest extends HttpServletRequestWrapper implements HttpServletRequest {
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private HttpServletRequest request;
	
	/**
	 * @param request
	 * @param parameters
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ContinuationRequest(HttpServletRequest request, Map parameters) {
		super(request);
		this.request = request;
		this.parameters = parameters;
		//追加request的参数
		Enumeration parameterNames = request.getParameterNames();
		while(parameterNames!=null && parameterNames.hasMoreElements()) {
			String parameterName = (String)parameterNames.nextElement();
			this.parameters.put(parameterName, request.getParameterValues(parameterName));
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getParameterMap()
	 */
	@SuppressWarnings("rawtypes")
	public Map getParameterMap() {
		return parameters;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getParameterNames()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Enumeration getParameterNames() {
		Set names = parameters.keySet();
		Hashtable hashtable = new Hashtable();
		if(names!=null && !names.isEmpty()) {
			for(Iterator iterator = names.iterator(); iterator.hasNext();) {
				String name = (String)iterator.next();
				hashtable.put(name, name);
			}
		}
		return hashtable.elements();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
	 */
	public String[] getParameterValues(String arg0) {
		return (String[])parameters.get(arg0);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String name) {
		if(parameters == null) {
			return null;
		}
		String[] param = (String[])parameters.get(name);
		if(param == null) {
			return null;
		}
		return param[0];
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeaderNames()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration getHeaderNames() {
		return request.getHeaderNames();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeaders(java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration getHeaders(String name) {
		return request.getHeaders(name);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequestWrapper#getHeader(java.lang.String)
	 */
	@Override
	public String getHeader(String name) {
		return request.getHeader(name);
	}
}