package com.lmiky.jdp.system.menu.util;

import javax.servlet.http.HttpServletRequest;
import com.lmiky.jdp.session.model.SessionInfo;

/**
 * 菜单工具类
 * @author lmiky
 * @date 2013-12-8
 */
public class MenuUtils {
	public static final String SESSION_KEY_MYFAVORITE = "menu_myFavorite_";
	
	/**
	 * 添加收藏夹信息
	 * @author lmiky
	 * @date 2013-12-8
	 * @param request
	 * @param sessionInfo
	 * @param menuId
	 */
	public static void addMyFavorite(HttpServletRequest request, SessionInfo sessionInfo, String menuId) {
		setMyFavorite(request, sessionInfo, menuId, true);
	}
	
	/**
	 * 移除收藏夹信息
	 * @author lmiky
	 * @date 2013-12-8
	 * @param request
	 * @param sessionInfo
	 * @param menuId
	 */
	public static void removeMyFavorite(HttpServletRequest request, SessionInfo sessionInfo, String menuId) {
		setMyFavorite(request, sessionInfo, menuId, false);
	}
	
	/**
	 * 设置收藏夹信息
	 * @author lmiky
	 * @date 2013-12-8
	 * @param request
	 * @param sessionInfo
	 * @param menuId
	 * @param value
	 */
	public static void setMyFavorite(HttpServletRequest request, SessionInfo sessionInfo, String menuId, Boolean value) {
		sessionInfo.setMenuFavoriteInfo(SESSION_KEY_MYFAVORITE + menuId, value);
	}
	
}
