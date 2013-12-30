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
	@RequestMapping("/listOperator.shtml")
	public String listOperator(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		try {
			//判断是否有登陆
			SessionInfo sessionInfo = getSessionInfo(modelMap, request);
			//检查单点登陆
			checkSso(sessionInfo, modelMap, request);
			//检查权限
			checkAuthority(modelMap, request, sessionInfo, getModule(modelMap, request), Function.DEFAULT_FUNCTIONID_LOAD);
			String functionPath = request.getParameter("functionPath");
			modelMap.put("functionPath", functionPath);
			modelMap.put("authorizedList", authorityService.listAuthorizedOperator(functionPath));
			modelMap.put("unauthorizedList", authorityService.listUnauthorizedOperator(functionPath));
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
			
			String functionPath = request.getParameter("functionPath");
			modelMap.put("functionPath", functionPath);
			
			String[] selectOperators = request.getParameterValues("selectedOperators");
			List<Authority> authorities = new ArrayList<Authority>();
			if(selectOperators != null && selectOperators.length > 0) {
				for(String operatorId : selectOperators) {
					Authority authority = new Authority();
					authority.setOperator(Long.parseLong(operatorId));
					authority.setFunctionPath(functionPath);
					authorities.add(authority);
				}
			}
			authorityService.authorize(functionPath, authorities);
			
			modelMap.put("authorizedList", authorityService.listAuthorizedOperator(functionPath));
			modelMap.put("unauthorizedList", authorityService.listUnauthorizedOperator(functionPath));
			
			putMessage(modelMap, "保存成功!");
			return "jdp/authority/authorize";
		} catch(Exception e) {
			return transactException(e, modelMap, request, resopnse);
		}
	}
	
}
