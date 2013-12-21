package com.lmiky.jdp.init.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.authority.pojo.Authority;
import com.lmiky.jdp.init.parser.ModuleParser;
import com.lmiky.jdp.init.service.InitService;
import com.lmiky.jdp.module.pojo.Function;
import com.lmiky.jdp.module.pojo.Module;
import com.lmiky.jdp.module.pojo.ModuleGroup;
import com.lmiky.jdp.service.impl.BaseServiceImpl;
import com.lmiky.jdp.system.menu.pojo.LatelyOperateMenu;
import com.lmiky.jdp.system.menu.pojo.MyFavoriteMenu;
import com.lmiky.jdp.system.menu.service.MenuService;
import com.lmiky.jdp.user.pojo.Role;
import com.lmiky.jdp.user.pojo.User;
import com.lmiky.jdp.util.Encoder;

/**
 * 初始化业务实现类
 * @author lmiky
 * @date 2013-10-13
 */
@Service("initService")
public class InitServiceImpl extends BaseServiceImpl implements InitService {
	private ModuleParser moduleParser;
	private MenuService menuService;

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.init.service.InitService#init(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional(rollbackFor = { Exception.class })
	public void init(String adminName, String adminLoginName, String adminPassword) throws Exception {
		// 最近操作菜单
		delete(LatelyOperateMenu.class);
		// 收藏夹
		delete(MyFavoriteMenu.class);

		// 角色
		delete(Role.class);
		Role role = new Role();
		role.setName(adminName);
		save(role);

		// 用户
		User user = new User();
		user.setName(adminName);
		user.setLoginName(adminLoginName);
		user.setPassword(Encoder.md5(adminPassword));
		user.setValid(User.VALID_YES);
		Date currentDate = new Date();
		user.setCreateTime(currentDate);
		user.setLastSetPasswordTime(currentDate);
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		delete(User.class);
		save(user);

		// 权限：拥有系统管理员的权限
		Authority authority = new Authority();
		authority.setFunctionId(new Integer(Function.DEFAULT_FUNCTIONID_ADMIN).longValue());
		authority.setModuleId(Module.MODULE_ID_SYSTEM);
		authority.setModuleType(Authority.MODULETYPE_SYSTEM);
		authority.setOperator(role.getId());
		authority.setOperatorType(Authority.OPERATORTYPE_ROLE);
		delete(Authority.class);
		save(authority);

		// 模块
		List<ModuleGroup> moduleGroups = moduleParser.parse();
		delete(ModuleGroup.class);
		save(moduleGroups);

	}

	@Transactional(rollbackFor = { Exception.class })
	public void updateModule() throws Exception {
	}

	/**
	 * @return the moduleParser
	 */
	public ModuleParser getModuleParser() {
		return moduleParser;
	}

	/**
	 * @param moduleParser the moduleParser to set
	 */
	@Resource(name = "moduleParser")
	public void setModuleParser(ModuleParser moduleParser) {
		this.moduleParser = moduleParser;
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
	@Resource(name = "menuService")
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
