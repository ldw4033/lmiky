package com.lmiky.love.job.position.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmiky.jdp.form.controller.FormController;
import com.lmiky.jdp.form.model.ValidateError;
import com.lmiky.jdp.form.util.ValidateUtils;
import com.lmiky.love.job.position.pojo.JobPosition;

/**
 * 公司行业
 * @author lmiky
 * @date 2013-10-26
 */
@Controller
@RequestMapping("/love/job/position")
public class PositionController extends FormController<JobPosition> {

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#getAddAuthorityCode()
	 */
	@Override
	protected String getAddAuthorityCode() {
		return "love_job_position_add";
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#getModifyAuthorityCode()
	 */
	@Override
	protected String getModifyAuthorityCode() {
		return "love_job_position_modify";
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#getDeleteAuthorityCode()
	 */
	@Override
	protected String getDeleteAuthorityCode() {
		return "love_job_position_delete";
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.base.controller.BaseController#getLoadAuthorityCode()
	 */
	@Override
	protected String getLoadAuthorityCode() {
		return "love_job_position_load";
	}
	
	/**
	 * @author lmiky
	 * @date 2013-10-26
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
	 * @date 2013-10-26
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

	/**
	 * @author lmiky
	 * @date 2013-10-26
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

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#validateInput(com.lmiky.jdp.database.pojo.BasePojo, java.lang.String, org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public List<ValidateError> validateInput(JobPosition pojo, String openMode, ModelMap modelMap, HttpServletRequest request) throws Exception {
		List<ValidateError> validateErrors = super.validateInput(pojo, openMode, modelMap, request);
		ValidateUtils.validateRequired(request, "name", "名称", validateErrors);
		return validateErrors;
	}
	
	/**
	 * 删除
	 * @author lmiky
	 * @date 2013-10-26
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete.shtml")
	public String delete(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, @RequestParam(value = "id", required = false) Long id) throws Exception {
		return executeDelete(modelMap, request, resopnse, id);
	}
	
	/**
	 * 批量删除
	 * @author lmiky
	 * @date 2013-6-26
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/batchDelete.shtml")
	public String batchDelete(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, @RequestParam(value = "batchDeleteId", required = false) Long[] ids) throws Exception {
		return executeBatchDelete(modelMap, request, resopnse, ids);
	}
	
}
