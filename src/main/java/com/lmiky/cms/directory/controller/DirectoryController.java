package com.lmiky.cms.directory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
}