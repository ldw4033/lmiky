package com.lmiky.jdp.system.menu.service;

import java.util.List;

import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.system.menu.model.SubMenu;
import com.lmiky.jdp.system.menu.model.TopMenu;

/**
 * 菜单
 * @author lmiky
 * @date 2013-6-16
 */
public interface MenuService {
	/**
	 * 初始化
	 * @author lmiky
	 * @date 2013-6-16
	 * @throws Exception
	 */
	public void init() throws Exception;
	
	/**
	 * 解析获取拥有权限的菜单列表
	 * @author lmiky
	 * @date 2013-6-16
	 * @param sessionInfo
	 * @return
	 * @throws Exception
	 */
	public List<TopMenu> getTopMenus(SessionInfo sessionInfo) throws Exception;
	
	/**
	 * 根据ID获取子菜单
	 * @author lmiky
	 * @date 2013-6-16
	 * @param subMenuId
	 * @param sessionInfo 如果不为空，则检查权限，没有权限则返回空值
	 * @return
	 * @throws Exception
	 */
	public SubMenu getSubMenus(String subMenuId, SessionInfo sessionInfo) throws Exception;
}
