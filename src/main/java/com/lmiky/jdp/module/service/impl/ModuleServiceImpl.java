package com.lmiky.jdp.module.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.module.pojo.Function;
import com.lmiky.jdp.module.pojo.Module;
import com.lmiky.jdp.module.service.ModuleService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.service.impl.BaseServiceImpl;

/**
 * @author lmiky
 * @date 2013-12-31
 */
@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl implements ModuleService {

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.module.service.ModuleService#listFunctionByModulePath(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Function> listFunctionByModulePath(String modulePath, String moduleType) throws ServiceException {
		List<Function> functions = new ArrayList<Function>();
		if(Module.MODULE_TYPE_SYSTEM.equals(moduleType)) {
			functions = list(Function.class);
		} else if(Module.MODULE_TYPE_GROUP.equals(moduleType)) {
			String hql = "select Function from Function Function where Function.module.group.path = '" + modulePath + "'";
			functions = list(hql);
		} else if(Module.MODULE_TYPE_MODULE.equals(moduleType)) {
			String hql = "select Function from Function Function where Function.module.path = '" + modulePath + "'";
			functions = list(hql);
		} else if(Module.MODULE_TYPE_FUNCTION.equals(moduleType)) {
			String hql = "select Function from Function Function where Function.authorityCode = '" + modulePath + "'";
			functions = list(hql);
		}
		return functions;
	}

}
