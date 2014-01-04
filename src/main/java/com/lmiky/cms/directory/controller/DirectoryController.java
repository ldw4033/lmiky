package com.lmiky.cms.directory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmiky.cms.directory.pojo.Directory;
import com.lmiky.jdp.tree.controller.TreeController;

/**
 * 栏目
 * @author lmiky
 * @date 2014-1-3
 */
@Controller
@RequestMapping("/cms/directory")
public class DirectoryController extends TreeController<Directory> {

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.base.controller.BaseController#getLoadAuthorityCode()
	 */
	@Override
	protected String getLoadAuthorityCode() {
		return "cms_directory_load";
	}

	/**
	 * @author lmiky
	 * @date 2014-1-3
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadTree.shtml")
	public String loadTree(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		return executeLoadTree(modelMap, request, resopnse);
	}
	
	/**
	 * @author lmiky
	 * @date 2014-1-4
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/treeList.shtml")
	public String treeList(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, @RequestParam(value = "id", required = false) Long id) throws Exception {
		return executeTreeList(modelMap, request, resopnse, id);
	}
}