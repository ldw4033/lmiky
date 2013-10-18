package com.lmiky.jdp.init.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.authority.pojo.Authority;
import com.lmiky.jdp.init.parser.ModuleParser;
import com.lmiky.jdp.init.service.InitService;
import com.lmiky.jdp.module.pojo.Function;
import com.lmiky.jdp.module.pojo.Module;
import com.lmiky.jdp.module.pojo.ModuleGroup;
import com.lmiky.jdp.service.impl.BaseServiceImpl;
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
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.init.service.InitService#init(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional(rollbackFor={Exception.class})
	public void init(String adminName, String adminLoginName, String adminPassword) throws Exception {
		//用户
		User user = new User();
		user.setName(adminName);
		user.setLoginName(adminLoginName);
		user.setPassword(Encoder.md5(adminPassword));
		user.setValid(User.VALID_YES);
		Date currentDate = new Date();
		user.setCreateTime(currentDate);
		user.setLastSetPasswordTime(currentDate);
		delete(User.class);
		save(user);
		
		//权限：拥有系统管理员的权限
		Authority authority = new Authority();
		authority.setFunctionId(new Integer(Function.DEFAULT_FUNCTIONID_ADMIN).longValue());
		authority.setModuleId(Module.MODULE_ID_SYSTEM);
		authority.setModuleType(Authority.MODULETYPE_SYSTEM);
		authority.setOperator(user.getId());
		authority.setOperatorType(Authority.OPERATORTYPE_USER);
		delete(Authority.class);
		save(authority);
		
		//模块
		List<ModuleGroup> moduleGroups = moduleParser.parse();
		delete(ModuleGroup.class);
		save(moduleGroups);
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
	@Resource(name="moduleParser")
	public void setModuleParser(ModuleParser moduleParser) {
		this.moduleParser = moduleParser;
	}
}
