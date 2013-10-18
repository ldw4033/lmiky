package com.lmiky.jdp.session.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Session信息
 * @author lmiky
 * @date 2013-4-24
 */
public class SessionInfo implements Serializable {
	private static final long serialVersionUID = 6373381257099639245L;
	private String sessionId;
	private Long userId; //用户ID
	private String loginName; //登录用户名
	private String password; //密码
	private String userName;	//用户姓名
	private Map<String, Boolean> authoritys = new HashMap<String, Boolean>();
	
	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the authoritys
	 */
	public Map<String, Boolean> getAuthoritys() {
		return authoritys;
	}

	/**
	 * 是否拥有权限
	 * @author lmiky
	 * @date 2013-5-24
	 * @param authorityKey
	 * @return 如果未设值，则返回null
	 */
	public Boolean isAuthority(String authorityKey) {
		return authoritys.get(authorityKey);
	}
	
	/**
	 * 设置是否拥有权限
	 * @author lmiky
	 * @date 2013-5-24
	 * @param authorityKey
	 * @param isAuthority
	 */
	public void setAuthority(String authorityKey, boolean isAuthority) {
		authoritys.put(authorityKey, isAuthority);
	}
}
