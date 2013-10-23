package com.lmiky.jdp.area.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmiky.jdp.area.pojo.Country;
import com.lmiky.jdp.area.pojo.Province;
import com.lmiky.jdp.form.controller.FormController;
import com.lmiky.jdp.form.model.ValidateError;
import com.lmiky.jdp.form.util.ValidateUtils;

/**
 * 省份
 * @author lmiky
 * @date 2013-10-22
 */
@Controller
@RequestMapping("/province")
public class ProvinceController extends FormController<Province> {

	/**
	 * @author lmiky
	 * @date 2013-10-22
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/load.shtml")
	public String load(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, @RequestParam(value = "id", required = false) Long id) throws Exception {
		return executeLoad(modelMap, request, resopnse, id, "jdp_area_manage", "jdp_area_manage");
	}
	
	/**
	 * 方法说明
	 * @author lmiky
	 * @date 2013-10-22
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
		modelMap.put("flag", "refresh");
		return executeSave(modelMap, request, resopnse, id, "jdp_area_manage", "jdp_area_manage");
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#setPojoProperties(com.lmiky.jdp.database.pojo.BasePojo, org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void setPojoProperties(Province pojo, ModelMap modelMap, HttpServletRequest request) throws Exception {
		super.setPojoProperties(pojo, modelMap, request);
		Long countryId = Long.parseLong(request.getParameter("countryId"));
		Country country = new Country();
		country.setId(countryId);
		pojo.setCountry(country);
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.form.controller.FormController#validateInput(com.lmiky.jdp.database.pojo.BasePojo, java.lang.String, org.springframework.ui.ModelMap, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public List<ValidateError> validateInput(Province pojo, String openMode, ModelMap modelMap, HttpServletRequest request) throws Exception {
		List<ValidateError> validateErrors = super.validateInput(pojo, openMode, modelMap, request);
		ValidateUtils.validateRequired(request, "name", "名称", validateErrors);
		return validateErrors;
	}
	
	/**
	 * 删除
	 * @author lmiky
	 * @date 2013-10-23
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete.shtml")
	public String delete(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, @RequestParam(value = "id", required = false) Long id) throws Exception {
		modelMap.put("flag", "refresh");
		executeDelete(modelMap, request, resopnse, id, "jdp_area_manage");
		return executeLoad(modelMap, request, resopnse, null, "jdp_area_manage", "jdp_area_manage");
	}
}
