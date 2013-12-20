package com.lmiky.jdp.authority.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lmiky.jdp.authority.pojo.Authority;
import com.lmiky.jdp.base.controller.BaseController;
import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.module.controller.ModuleController;
import com.lmiky.jdp.module.pojo.Function;
import com.lmiky.jdp.module.pojo.ModuleGroup;
import com.lmiky.jdp.session.model.SessionInfo;

/**
 * 授权
 * @author lmiky
 * @date 2013-5-16
 */
@Controller
@RequestMapping("/authority")
public class AuthorityController extends BaseController {
	/**
	 * @author lmiky
	 * @date 2013-5-16
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/load.shtml")
	public String load(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		try {
			return executeLoad(modelMap, request, resopnse);
		} catch(Exception e) {
			return transactException(e, modelMap, request, resopnse);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.base.controller.BaseController#processLoad(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.util.Map)
	 */
	@Override
	protected String processLoad(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, Map<String, Object> loadParams) throws Exception {
		super.processLoad(modelMap, request, resopnse, loadParams);
		//获取模块组
		List<ModuleGroup> moduleGroups = service.list(ModuleGroup.class);
		modelMap.put("moduleGroups", moduleGroups);
		return "jdp/authority/load";
	}


	/**
	 * 获取授权用户
	 * @author lmiky
	 * @date 2013-5-20
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listUser.shtml")
	public String listUser(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		try {
			//判断是否有登陆
			SessionInfo sessionInfo = getSessionInfo(modelMap, request);
			//检查单点登陆
			checkSso(sessionInfo, modelMap, request);
			//检查权限
			checkAuthority(modelMap, request, sessionInfo, getModule(modelMap, request), Function.DEFAULT_FUNCTIONID_LOAD);
			String moduleType = request.getParameter("moduleType");
			String moduleId = request.getParameter("moduleId");
			String functionId = request.getParameter("functionId");
			Long authorityModuleId = 0l;
			Long authorityFunctionId = 0l;
			if(Authority.MODULETYPE_SYSTEM.equals(moduleType)) {
				authorityModuleId = Long.parseLong(moduleId.substring(ModuleController.TREE_LIST_ID_PREFIX_SYSTEM.length()));
				authorityFunctionId = Long.parseLong(functionId);
			} else if(Authority.MODULETYPE_GROUP.equals(moduleType)) {
				authorityModuleId = Long.parseLong(moduleId.substring(ModuleController.TREE_LIST_ID_PREFIX_GROUP.length()));
				authorityFunctionId = Long.parseLong(functionId);
			} else if(Authority.MODULETYPE_MODULE.equals(moduleType)) {
				authorityModuleId = Long.parseLong(moduleId.substring(ModuleController.TREE_LIST_ID_PREFIX_MODULE.length()));
				authorityFunctionId = Long.parseLong(functionId);
			} else if(Authority.MODULETYPE_FUNCTION.equals(moduleType)) {
				authorityModuleId = Long.parseLong(moduleId.substring(ModuleController.TREE_LIST_ID_PREFIX_MODULE.length()));
				authorityFunctionId = Long.parseLong(functionId.substring(ModuleController.TREE_LIST_ID_PREFIX_FUNCTION.length()));
			}
			String operatorType = request.getParameter("operatorType");
			if(Authority.OPERATORTYPE_ROLE.equals(operatorType)) {
				modelMap.put("authorizedList", authorityService.listAuthorizedRole(moduleType, authorityModuleId, authorityFunctionId));
				modelMap.put("unauthorizedList", authorityService.listUnauthorizedRole(moduleType, authorityModuleId, authorityFunctionId));
			}
			modelMap.put("moduleType", moduleType);
			modelMap.put("moduleId", authorityModuleId);
			modelMap.put("functionId", authorityFunctionId);
			modelMap.put("operatorType", operatorType);
			return "jdp/authority/authorize";
		} catch(Exception e) {
			return transactException(e, modelMap, request, resopnse);
		}
	}
	
	/**
	 * @author lmiky
	 * @date 2013-5-16
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/authorize.shtml")
	public String authorize(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		try {
			//判断是否有登陆
			SessionInfo sessionInfo = getSessionInfo(modelMap, request);
			//检查单点登陆
			checkSso(sessionInfo, modelMap, request);
			//检查权限
			checkAuthority(modelMap, request, sessionInfo, getModule(modelMap, request), "jdp_authority");
			String moduleType = request.getParameter("moduleType");
			Long moduleId = Long.parseLong(request.getParameter("moduleId"));
			Long functionId = Long.parseLong(request.getParameter("functionId"));
			String operatorType = request.getParameter("operatorType");
			
			String[] selectUsers = request.getParameterValues("selectedUsers");
			if(selectUsers != null && selectUsers.length > 0) {
				List<Authority> authorities = new ArrayList<Authority>();
				for(String userId : selectUsers) {
					Authority authority = new Authority();
					authority.setOperator(Long.parseLong(userId));
					authority.setFunctionId(functionId);
					authority.setModuleId(moduleId);
					authority.setModuleType(moduleType);
					authority.setOperatorType(operatorType);
					authorities.add(authority);
				}
				authorityService.save(authorities);
			} else {
				List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
				propertyFilters.add(new PropertyFilter("functionId", functionId, PropertyCompareType.EQ, Authority.class));
				propertyFilters.add(new PropertyFilter("moduleId", moduleId, PropertyCompareType.EQ, Authority.class));
				propertyFilters.add(new PropertyFilter("moduleType", moduleType, PropertyCompareType.EQ, Authority.class));
				propertyFilters.add(new PropertyFilter("operatorType", operatorType, PropertyCompareType.EQ, Authority.class));
				service.delete(Authority.class, propertyFilters);
			}
			
			
			if(Authority.OPERATORTYPE_ROLE.equals(operatorType)) {
				modelMap.put("authorizedList", authorityService.listAuthorizedRole(moduleType, moduleId, functionId));
				modelMap.put("unauthorizedList", authorityService.listUnauthorizedRole(moduleType, moduleId, functionId));
			}
			modelMap.put("moduleType", moduleType);
			modelMap.put("moduleId", moduleId);
			modelMap.put("functionId", functionId);
			modelMap.put("operatorType", operatorType);
			putMessage(modelMap, "保存成功!");
			return "jdp/authority/authorize";
		} catch(Exception e) {
			return transactException(e, modelMap, request, resopnse);
		}
	}
	
}
