package com.lmiky.jdp.system.menu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.system.menu.controller.MenuController;
import com.lmiky.jdp.system.menu.model.LeftMenu;
import com.lmiky.jdp.system.menu.model.SubMenu;
import com.lmiky.jdp.system.menu.model.TopMenu;
import com.lmiky.jdp.system.menu.service.MenuService;
import com.lmiky.jdp.util.Environment;
import com.lmiky.jdp.util.PropertiesUtils;

/**
 * 菜单工具类
 * @author lmiky
 * @date 2013-12-8
 */
public class MenuUtils {
	public static final String SESSION_KEY_MYFAVORITE = "menu_myFavorite_";
	private static Integer latelyOperateMenuNum = PropertiesUtils.getIntContextValue("menu.latelyOperateMenuNum");

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

	/**
	 * 获取顶级菜单
	 * @author lmiky
	 * @date 2014-6-28
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param sessionInfo
	 * @param topMenuId
	 * @return
	 * @throws Exception
	 */
	public static TopMenu getTopMenu(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, SessionInfo sessionInfo, String topMenuId)
			throws Exception {
		TopMenu topMenu = null;
		List<TopMenu> topMenus = getMenuService().getTopMenus(sessionInfo);
		for (TopMenu tm : topMenus) {
			if (topMenuId.equals(tm.getId())) {
				topMenu = tm;
			}
		}
		// 个人主页
		if (topMenu == null) {
			topMenu = new TopMenu();
			topMenu.setId(MenuController.TOP_MENU_ID_MYINDEX);
			topMenu.setLabel(MenuController.TOP_MENU_LABEL_MYINDEX);
		}
		return topMenu;
	}

	/**
	 * 获取左菜单列表
	 * @author lmiky
	 * @date 2014-6-28
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param sessionInfo
	 * @param topMenuId
	 * @return
	 * @throws Exception
	 */
	public static List<LeftMenu> getLeftMenus(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, SessionInfo sessionInfo,
			String topMenuId) throws Exception {
		List<LeftMenu> leftMenuList = new ArrayList<LeftMenu>();
		if (MenuController.TOP_MENU_ID_MYINDEX.equals(topMenuId)) {
			leftMenuList = getMyIndexMenus(modelMap, request, response, sessionInfo, topMenuId);
		} else {
			leftMenuList = getMenuService().getLeftMenus(topMenuId, sessionInfo);
		}
		return leftMenuList;
	}

	/**
	 * 获取左菜单列表
	 * @author lmiky
	 * @date 2014-6-28
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param sessionInfo
	 * @param topMenu 如果为空，则获取个人主页菜单列表
	 * @return
	 * @throws Exception
	 */
	public static List<LeftMenu> getLeftMenus(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, SessionInfo sessionInfo,
			TopMenu topMenu) throws Exception {
		List<LeftMenu> leftMenuList = new ArrayList<LeftMenu>();
		if (topMenu == null || MenuController.TOP_MENU_ID_MYINDEX.equals(topMenu.getId())) {
			leftMenuList = getMyIndexMenus(modelMap, request, response, sessionInfo, MenuController.TOP_MENU_ID_MYINDEX);
		} else {
			leftMenuList = topMenu.getLeftMenus();
		}
		return leftMenuList;
	}

	/**
	 * 获取个人主页菜单列表
	 * @author lmiky
	 * @date 2014-6-28
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param sessionInfo
	 * @param topMenuId
	 * @return
	 * @throws Exception
	 */
	public static List<LeftMenu> getMyIndexMenus(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, SessionInfo sessionInfo,
			String topMenuId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", sessionInfo.getUserId());

		List<LeftMenu> leftMenuList = new ArrayList<LeftMenu>();
		LeftMenu leftMenu = new LeftMenu();
		leftMenu.setId(MenuController.LEFTMENU_ID_LATELYOPE);
		leftMenu.setLabel(MenuController.MENU_LABEL_LEFTMENU_LATELYOPE);
		// 最近操作菜单
		// 如果按时间降序排序，mysql会被distinct干扰,无法获取想要的结果，只能按id降序排序
		List<String> subMenuIds = getBaseService()
				.executeQuery(
						"select distinct LatelyOperateMenu.menuId from LatelyOperateMenu LatelyOperateMenu where LatelyOperateMenu.userId = :userId order by LatelyOperateMenu.id desc",
						params, 0, latelyOperateMenuNum.intValue());
		List<SubMenu> opeMenus = new ArrayList<SubMenu>();
		for (String subMenuId : subMenuIds) {
			SubMenu subMenu = getMenuService().getSubMenu(subMenuId, sessionInfo);
			if (subMenu != null) {
				opeMenus.add(subMenu);
			}
		}
		leftMenu.setSubMenus(opeMenus);
		leftMenuList.add(leftMenu);
		// 我的收藏菜单
		leftMenu = new LeftMenu();
		leftMenu.setId(MenuController.LEFTMENU_ID_MYFAVORITE);
		leftMenu.setLabel(MenuController.MENU_LABEL_LEFTMENU_MYFAVORITE);
		subMenuIds = getBaseService()
				.executeQuery(
						"select distinct MyFavoriteMenu.menuId from MyFavoriteMenu MyFavoriteMenu where MyFavoriteMenu.userId = :userId order by MyFavoriteMenu.id desc",
						params);
		List<SubMenu> favoriteMenus = new ArrayList<SubMenu>();
		for (String subMenuId : subMenuIds) {
			SubMenu subMenu = getMenuService().getSubMenu(subMenuId, sessionInfo);
			if (subMenu != null) {
				favoriteMenus.add(subMenu);
			}
		}
		leftMenu.setSubMenus(favoriteMenus);
		leftMenuList.add(leftMenu);
		return leftMenuList;
	}

	/**
	 * 获取我的最近操作link菜单
	 * @author lmiky
	 * @date 2014-6-28
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param sessionInfo
	 * @return
	 * @throws Exception
	 */
	public static SubMenu getMyLatelyOpeLinkMenu(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, SessionInfo sessionInfo) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", sessionInfo.getUserId());
		// 最近操作菜单
		// 如果按时间降序排序，mysql会被distinct干扰,无法获取想要的结果，只能按id降序排序
		List<String> subMenuIds = getBaseService()
				.executeQuery(
						"select distinct LatelyOperateMenu.menuId from LatelyOperateMenu LatelyOperateMenu where LatelyOperateMenu.userId = :userId order by LatelyOperateMenu.id desc",
						params, 0, latelyOperateMenuNum.intValue());
		for (String subMenuId : subMenuIds) {
			SubMenu subMenu = getMenuService().getSubMenu(subMenuId, sessionInfo);
			if (subMenu != null && SubMenu.TYPE_LINK.equals(subMenu.getType())) {
				return subMenu;
			}
		}
		return null;
	}
	
	/**
	 * 获取进入系统欢迎菜单
	 * @author lmiky
	 * @date 2014-6-28
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param sessionInfo
	 * @return
	 * @throws Exception
	 */
	public static SubMenu getMyWelcomeMenu(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, SessionInfo sessionInfo) throws Exception {
		//最近操作菜单
		SubMenu subMenu = getMyLatelyOpeLinkMenu(modelMap, request, response, sessionInfo);
		if(subMenu != null) {
			return subMenu;
		}
		//收藏夹
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", sessionInfo.getUserId());
		List<String> subMenuIds = getBaseService()
				.executeQuery(
						"select distinct MyFavoriteMenu.menuId from MyFavoriteMenu MyFavoriteMenu where MyFavoriteMenu.userId = :userId order by MyFavoriteMenu.id desc",
						params);
		MenuService menuService = getMenuService();
		for (String subMenuId : subMenuIds) {
			SubMenu sm = menuService.getSubMenu(subMenuId, sessionInfo);
			if (sm != null && SubMenu.TYPE_LINK.equals(sm.getType())) {
				return sm;
			}
		}
		//拥有权限的菜单
		List<TopMenu> topMenus = menuService.getTopMenus(sessionInfo);
		for(TopMenu topMenu : topMenus) {
			for(LeftMenu leftMenu : topMenu.getLeftMenus()) {
				for(SubMenu sm : leftMenu.getSubMenus()) {
					if (sm != null && SubMenu.TYPE_LINK.equals(sm.getType())) {
						return sm;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取菜单业务
	 * @author lmiky
	 * @date 2014-6-28
	 * @return
	 */
	private static MenuService getMenuService() {
		return (MenuService) Environment.getBean("menuService");
	}

	/**
	 * 获取基本业务
	 * @author lmiky
	 * @date 2014-6-28
	 * @return
	 */
	private static BaseService getBaseService() {
		return (BaseService) Environment.getBean("baseService");
	}
}
