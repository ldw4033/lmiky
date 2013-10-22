package com.lmiky.jdp.user.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.form.controller.FormController;
import com.lmiky.jdp.form.model.ValidateError;
import com.lmiky.jdp.form.util.ValidateUtils;
import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.user.pojo.User;
import com.lmiky.jdp.user.pojo.UserGroup;
import com.lmiky.jdp.user.service.UserGroupService;

/**
 * 用户组
 * @author lmiky
 * @date 2013-5-13
 */
@Controller
@RequestMapping("/usergroup")
public class UserGroupController extends FormController<UserGroup> {

	/**
	 * @author lmiky
	 * @date 2013-5-13
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list.shtml")
	public String list(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		return executeList(modelMap, request, resopnse);
	}
	
	/**
	 * @author lmiky
	 * @date 2013-5-13
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/load.shtml")
	public String load(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, @RequestParam(value = "id", required = false) Long id) throws Exception {
		return executeLoad(modelMap, request, resopnse, id, "jdp_user_usergroup_add", "jdp_user_usergroup_modify");
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#appendLoadAttribute(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, com.lmiky.jdp.database.pojo.BasePojo)
	 */
	@Override
	protected void appendLoadAttribute(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, String openMode, UserGroup pojo) throws Exception {
		super.appendLoadAttribute(modelMap, request, resopnse, openMode, pojo);
		UserGroupService userGroupService = (UserGroupService)service;
		if(OPEN_MODE_EDIT.equals(openMode) || OPEN_MODE_READ.equals(openMode)) {
			modelMap.put("groupUsers", userGroupService.listGroupUser(pojo.getId()));
		}
		if(OPEN_MODE_EDIT.equals(openMode)) {
			modelMap.put("noGroupUsers", userGroupService.listNoGroupUser(pojo.getId()));
		} else if(OPEN_MODE_CTEATE.equals(openMode)) {
			List<Sort> sorts = new ArrayList<Sort>();
			sorts.add(new Sort("name", Sort.SORT_TYPE_ASC, User.class));
			modelMap.put("noGroupUsers", userGroupService.list(User.class, null, sorts));
		}
	}

	/**
	 * @author lmiky
	 * @date 2013-5-13
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save.shtml")
	public String save(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, @RequestParam(value = "id", required = false) Long id) throws Exception {
		return executeSave(modelMap, request, resopnse, id, "jdp_user_usergroup_add", "jdp_user_usergroup_modify");
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#validateInput(com.lmiky.jdp.database.pojo.BasePojo, java.lang.String, org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public List<ValidateError> validateInput(UserGroup pojo, String openMode, ModelMap modelMap, HttpServletRequest request) throws Exception {
		List<ValidateError> errors = super.validateInput(pojo, openMode, modelMap, request);
		ValidateUtils.validateRequired(request, "name", "名称", errors);
		return errors;
	}

	
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#setPojoProperties(com.lmiky.jdp.database.pojo.BasePojo, org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void setPojoProperties(UserGroup pojo, ModelMap modelMap, HttpServletRequest request) throws Exception {
		super.setPojoProperties(pojo, modelMap, request);
		String[] selectUsers = request.getParameterValues("selectedUsers");
		if(selectUsers != null && selectUsers.length > 0) {
			Set<User> users = new HashSet<User>();
			for(String userId : selectUsers) {
				User user = new User();
				user.setId(Long.parseLong(userId));
				users.add(user);
			}
			pojo.setUsers(users);
		}
	}
	
	/**
	 * 方法说明
	 * @author lmiky
	 * @date 2013-5-14
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete.shtml")
	public String delete(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, @RequestParam(value = "id", required = false) Long id) throws Exception {
		return executeDelete(modelMap, request, resopnse, id, "jdp_user_usergroup_delete");
	}

	/**
	 * 批量删除
	 * @author lmiky
	 * @date 2013-6-24
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/batchDelete.shtml")
	public String batchDelete(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, @RequestParam(value = "batchDeleteId", required = false) Long[] ids) throws Exception {
		return executeBatchDelete(modelMap, request, resopnse, ids, "jdp_user_usergroup_delete");
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.base.controller.BaseController#getService()
	 */
	@Resource(name="userGroupService")
	public void setService(BaseService service) {
		this.service = service;
	}
}
