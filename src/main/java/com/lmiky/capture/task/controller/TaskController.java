package com.lmiky.capture.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lmiky.capture.task.pojo.CaptureTask;
import com.lmiky.jdp.form.controller.FormController;
import com.lmiky.jdp.util.HttpUtils;


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
	
	/**
	 * @author lmiky
	 * @date 2013-11-17
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
		return executeLoad(modelMap, request, resopnse, id, "capture_task_add", "capture_task_modify");
	}
	
	/**
	 * 方法说明
	 * @author lmiky
	 * @date 2013-11-17
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/urlContent.shtml")
	public String urlContent(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse,
			@RequestParam(value = "url", required = true) String url) throws Exception {
		String content = HttpUtils.get(url);
		modelMap.put("content", content == null ? "" : content);
		return "capture/task/urlContent";
	}
}
