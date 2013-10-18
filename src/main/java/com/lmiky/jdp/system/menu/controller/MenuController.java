package com.lmiky.jdp.system.menu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmiky.jdp.base.controller.BaseController;
import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.json.util.JsonUtils;
import com.lmiky.jdp.session.exception.SessionException;
import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.system.menu.model.SubMenu;
import com.lmiky.jdp.system.menu.pojo.MyFavoriteMenu;
import com.lmiky.jdp.system.menu.service.MenuService;
import com.lmiky.jdp.util.PropertiesUtils;
import com.lmiky.jdp.web.util.ResponseUtils;

/**
 * 菜单
 * @author lmiky
 * @date 2013-6-16
 */
@Controller
@RequestMapping("/sso/system/menu")
public class MenuController extends BaseController {
	public static final String CODE_SUCCESS = "0";
	public static final String CODE_FAIL = "-1";
	
	private MenuService menuService;
	private Integer latelyOperateMenuNum = PropertiesUtils.getIntContextValue("menu.latelyOperateMenuNum");

	/**
	 * 加载菜单列表
	 * @author lmiky
	 * @date 2013-6-16
	 * @param modelMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/load.shtml")
	public String load(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//判断是否有登陆
			SessionInfo sessionInfo = getSessionInfo(modelMap, request);
			modelMap.put("menus", menuService.getTopMenus(sessionInfo));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", sessionInfo.getUserId());
			//最近操作菜单
			//如果按时间降序排序，mysql会被distinct干扰,无法获取想要的结果，只能按id降序排序
			List<String> subMenuIds = service.executeQuery("select distinct LatelyOperateMenu.menuId from LatelyOperateMenu LatelyOperateMenu where LatelyOperateMenu.userId = :userId order by LatelyOperateMenu.id desc", params, 0, latelyOperateMenuNum.intValue());
			List<SubMenu> opeMenus = new ArrayList<SubMenu>();
			for(String subMenuId : subMenuIds) {
				SubMenu subMenu = menuService.getSubMenus(subMenuId, sessionInfo);
				if(subMenu != null) {
					opeMenus.add(subMenu);
				}
			}
			modelMap.put("latelyOperateMenus", opeMenus);
			//我的收藏菜单
			subMenuIds = service.executeQuery("select distinct MyFavoriteMenu.menuId from MyFavoriteMenu MyFavoriteMenu where MyFavoriteMenu.userId = :userId order by MyFavoriteMenu.id desc", params);
			List<SubMenu> favoriteMenus = new ArrayList<SubMenu>();
			for(String subMenuId : subMenuIds) {
				SubMenu subMenu = menuService.getSubMenus(subMenuId, sessionInfo);
				if(subMenu != null) {
					favoriteMenus.add(subMenu);
				}
			}
			modelMap.put("favoriteMenus", favoriteMenus);
			return "index";
		} catch(Exception e) {
			return transactException(e, modelMap, request, response);
		}
	}
	
	/**
	 * 添加到收藏夹
	 * @author lmiky
	 * @date 2013-6-17
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param menuId
	 * @throws Exception
	 */
	@RequestMapping("/addMyFavorite.shtml")
	public void addMyFavorite(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "menuId", required = true) String menuId) throws Exception {
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("code", CODE_FAIL);
		try {
			SessionInfo sessionInfo = getSessionInfo(modelMap, request);
			if(service.exist(MyFavoriteMenu.class, new PropertyFilter("userId", sessionInfo.getUserId(), PropertyCompareType.EQ, MyFavoriteMenu.class), new PropertyFilter("menuId", menuId, PropertyCompareType.EQ, MyFavoriteMenu.class))) {
				retMap.put("msg", "该菜单已经在您的收藏夹中！");
			} else {
				MyFavoriteMenu myFavoriteMenu = new MyFavoriteMenu();
				myFavoriteMenu.setMenuId(menuId);
				myFavoriteMenu.setUserId(sessionInfo.getUserId());
				myFavoriteMenu.setAddTime(new Date());
				service.save(myFavoriteMenu);
				retMap.put("code", CODE_SUCCESS);
				retMap.put("msg", "收藏成功！");
			}
			ResponseUtils.write(response, JsonUtils.toJson(retMap));
		} catch(Exception e) {
			logException(e, modelMap, request, response);
			if(e instanceof SessionException) {
				retMap.put("msg", "添加失败，登录超时！");
			} else {
				retMap.put("msg", "添加失败，系统错误！");
			}
			ResponseUtils.write(response, JsonUtils.toJson(retMap));
		}
	}
	
	/**
	 * 移除收藏夹
	 * @author lmiky
	 * @date 2013-6-17
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param menuId
	 * @throws Exception
	 */
	@RequestMapping("/removeMyFavorite.shtml")
	public void removeMyFavorite(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "menuId", required = true) String menuId) throws Exception {
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("code", CODE_FAIL);
		try {
			SessionInfo sessionInfo = getSessionInfo(modelMap, request);
			MyFavoriteMenu myFavoriteMenu = service.find(MyFavoriteMenu.class, new PropertyFilter("userId", sessionInfo.getUserId(), PropertyCompareType.EQ, MyFavoriteMenu.class), new PropertyFilter("menuId", menuId, PropertyCompareType.EQ, MyFavoriteMenu.class));
			if(myFavoriteMenu == null) {
				retMap.put("msg", "该菜单不在您的收藏夹中！");
			} else {
				service.delete(myFavoriteMenu);
				retMap.put("code", CODE_SUCCESS);
				retMap.put("msg", "取消成功！");
			}
			ResponseUtils.write(response, JsonUtils.toJson(retMap));
		} catch(Exception e) {
			logException(e, modelMap, request, response);
			if(e instanceof SessionException) {
				retMap.put("msg", "移除失败，登录超时！");
			} else {
				retMap.put("msg", "移除失败，系统错误！");
			}
			ResponseUtils.write(response, JsonUtils.toJson(retMap));
		}
	}

	/**
	 * @return the menuService
	 */
	public MenuService getMenuService() {
		return menuService;
	}

	/**
	 * @param menuService the menuService to set
	 */
	@Resource(name="menuService")
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * @return the latelyOperateMenuNum
	 */
	public Integer getLatelyOperateMenuNum() {
		return latelyOperateMenuNum;
	}

}
