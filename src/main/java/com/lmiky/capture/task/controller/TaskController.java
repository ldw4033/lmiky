package com.lmiky.capture.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lmiky.capture.task.pojo.CaptureTask;
import com.lmiky.jdp.form.controller.FormController;


/**
 * 抓取任务
 * @author lmiky
 * @date 2013-11-12
 */
@Controller
@RequestMapping("/capture/task")
public class TaskController extends FormController<CaptureTask> {
	
	/**
	 * @author lmiky
	 * @date 2013-11-12
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
}
