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
	private static final long serialVersionUID = -8619159895800318250L;
	
	private String sessionId;
	private Long userId; //用户ID
	private String loginName; //登录用户名
	private String password; //密码
	private String userName;	//用户姓名
	private Map<String, Boolean> authoritys = new HashMap<String, Boolean>();
	private Map<String, Boolean> menuFavoriteInfo = new HashMap<String, Boolean>();
	private String latelyOperateMenuId;	//最后操作的菜单ID
	
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
	/**
	 * @return the menuFavoriteInfo
	 */
	public Map<String, Boolean> getMenuFavoriteInfo() {
		return menuFavoriteInfo;
	}
	/**
	 * 是否在收藏夹中
	 * @author lmiky
	 * @date 2013-12-8
	 * @param key
	 * @return 如果未设值，则返回null
	 */
	public Boolean isInMenuFavorite(String key) {
		return menuFavoriteInfo.get(key);
	}
	
	/**
	 * 设置收藏信息
	 * @author lmiky
	 * @date 2013-12-8
	 * @param key
	 * @param value
	 */
	public void setMenuFavoriteInfo(String key, Boolean value) {
		menuFavoriteInfo.put(key, value);
	}
	/**
	 * @return the latelyOperateMenuId
	 */
	public String getLatelyOperateMenuId() {
		return latelyOperateMenuId;
	}
	/**
	 * @param latelyOperateMenuId the latelyOperateMenuId to set
	 */
	public void setLatelyOperateMenuId(String latelyOperateMenuId) {
		this.latelyOperateMenuId = latelyOperateMenuId;
	}
}
