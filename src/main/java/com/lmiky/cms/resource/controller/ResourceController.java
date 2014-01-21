package com.lmiky.cms.resource.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmiky.cms.directory.pojo.CmsDirectory;
import com.lmiky.cms.resource.pojo.CmsResource;
import com.lmiky.cms.resource.pojo.CmsResourceContent;
import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.form.controller.FormController;
import com.lmiky.jdp.form.model.ValidateError;
import com.lmiky.jdp.form.util.ValidateUtils;
import com.lmiky.jdp.user.pojo.User;

/**
 * 资源
 * @author lmiky
 * @date 2014-1-5
 */
@Controller
@RequestMapping("/cms/resource")
public class ResourceController extends FormController<CmsResource> {

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#getAddAuthorityCode()
	 */
	@Override
	protected String getAddAuthorityCode() {
		return "cms_resource_add";
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#getModifyAuthorityCode()
	 */
	@Override
	protected String getModifyAuthorityCode() {
		return "cms_resource_modify";
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#getDeleteAuthorityCode()
	 */
	@Override
	protected String getDeleteAuthorityCode() {
		return "cms_resource_delete";
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.base.controller.BaseController#getLoadAuthorityCode()
	 */
	@Override
	protected String getLoadAuthorityCode() {
		return "cms_resource_load";
	}

	/**
	 * @author lmiky
	 * @date 2014-1-5
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

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.web.controller.BaseController#getDefaultSort(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected List<Sort> getDefaultSort(ModelMap modelMap, HttpServletRequest request) {
		List<Sort> sorts = super.getDefaultSort(modelMap, request);
		sorts.add(0, new Sort("createTime", Sort.SORT_TYPE_DESC, CmsResource.class));
		return sorts;
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.view.controller.ViewController#generatePropertyFilters(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	protected List<PropertyFilter> generatePropertyFilters(ModelMap modelMap, HttpServletRequest request) {
		List<PropertyFilter> filters = super.generatePropertyFilters(modelMap, request);
		String directoryId = request.getParameter("directoryId");
		if(!StringUtils.isBlank(directoryId)) {
			filters.add(new PropertyFilter("directory.id", Long.parseLong(directoryId), PropertyCompareType.EQ, CmsResource.class));
		}
		return filters;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.view.controller.ViewController#appendListAttribute(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void appendListAttribute(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		super.appendListAttribute(modelMap, request, resopnse);
		// 获取所属栏目
		Long directoryId = Long.parseLong(request.getParameter("directoryId"));
		CmsDirectory directory = service.find(CmsDirectory.class, directoryId);
		if (directory == null) {
			throw new Exception(String.format("directory[id: %d] is not found", directoryId));
		}
		modelMap.put("directory", directory);
	}

	/**
	 * @author lmiky
	 * @date 2014-1-5
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/load.shtml")
	public String load(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse,
			@RequestParam(value = "id", required = false) Long id) throws Exception {
		return executeLoad(modelMap, request, resopnse, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#generateNewPojo(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected CmsResource generateNewPojo(ModelMap modelMap, HttpServletRequest request) throws Exception {
		CmsResource resource = super.generateNewPojo(modelMap, request);
		resource.setCreateTime(new Date());
		User user = new User();
		user.setId(getLoginUserId(modelMap, request));
		resource.setCreator(user);
		return resource;
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#setPojoProperties(com.lmiky.jdp.database.pojo.BasePojo, org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void setPojoProperties(CmsResource pojo, ModelMap modelMap, HttpServletRequest request) throws Exception {
		super.setPojoProperties(pojo, modelMap, request);
		// 文章内容
		Set<CmsResourceContent> contents = pojo.getResourceContents();
		CmsResourceContent content = null;
		if (contents == null) {
			contents = new HashSet<CmsResourceContent>();
			content = new CmsResourceContent();
			contents.add(content);
		} else {
			content = contents.iterator().next();
		}
		content.setContent(request.getParameter("content"));
		content.setCmsResource(pojo);
		pojo.setResourceContents(contents);
		CmsDirectory directory = new CmsDirectory();
		directory.setId(Long.parseLong(request.getParameter("directoryId")));
		pojo.setDirectory(directory);
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#appendLoadAttribute(org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String, com.lmiky.jdp.database.pojo.BasePojo)
	 */
	@Override
	protected void appendLoadAttribute(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, String openMode, CmsResource pojo)
			throws Exception {
		super.appendLoadAttribute(modelMap, request, resopnse, openMode, pojo);
		// 获取所属栏目
		Long directoryId = Long.parseLong(request.getParameter("directoryId"));
		CmsDirectory directory = service.find(CmsDirectory.class, directoryId);
		if (directory == null) {
			throw new Exception(String.format("directory[id: %d] is not found", directoryId));
		}
		modelMap.put("directory", directory);
	}

	/**
	 * @author lmiky
	 * @date 2014-1-5
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save.shtml")
	public String save(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse,
			@RequestParam(value = "id", required = false) Long id) throws Exception {
		return executeSave(modelMap, request, resopnse, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#validateInput(com.lmiky.jdp.database.pojo.BasePojo, java.lang.String, org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	public List<ValidateError> validateInput(CmsResource pojo, String openMode, ModelMap modelMap, HttpServletRequest request) throws Exception {
		List<ValidateError> validateErrors = new ArrayList<ValidateError>();
		ValidateUtils.validateRequired(request, "title", "标题", validateErrors);
		return validateErrors;
	}

	/**
	 * @author lmiky
	 * @date 2014-1-5
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete.shtml")
	public String delete(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse,
			@RequestParam(value = "id", required = false) Long id) throws Exception {
		return executeDelete(modelMap, request, resopnse, id);
	}

	/**
	 * @author lmiky
	 * @date 2014-1-5
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/batchDelete.shtml")
	public String batchDelete(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse,
			@RequestParam(value = "batchDeleteId", required = false) Long[] ids) throws Exception {
		return executeBatchDelete(modelMap, request, resopnse, ids);
	}
}
