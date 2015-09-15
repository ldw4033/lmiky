package com.lmiky.platform.apitest.controller;

import com.lmiky.platform.controller.BaseApiController;
import com.lmiky.platform.controller.view.BaseCodeDataListView;
import com.lmiky.platform.controller.view.BaseCodeDataView;
import com.lmiky.platform.controller.view.BaseCodeView;
import com.lmiky.platform.controller.view.BaseJsonView;
import com.lmiky.platform.logger.util.LoggerUtils;
import com.lmiky.platform.service.BaseService;
import com.lmiky.platform.tree.pojo.BaseTreePojo;
import com.lmiky.platform.util.Environment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lmiky
 * @date 2015年5月11日 下午2:58:27
 */
@Controller
@RequestMapping("/api/test")
public class TestApiController extends BaseApiController {
	private BaseService baseService;

	/**
	 * 激活
	 * @author lmiky
	 * @date 2015年5月11日 下午5:24:25
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param playerCode 15为IEMI+15位IMSI(3位MCC+2位MNC+10位MSIN)+12位MAC地址
	 * @param gameCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/test.shtml")
	public String test(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			LoggerUtils.info(String.format("IP[%s]进入到测试接口中", request.getRemoteAddr()));
			System.out.println(baseService);
			System.out.println(Environment.getBean("baseService"));
		} catch (Exception e) {
			transactException(modelMap, request, response, e);
		}
		return BaseJsonView.getViewName(BaseCodeView.class);
	}

	/**
	 * 激活
	 * @author lmiky
	 * @date 2015年5月11日 下午5:24:25
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param playerCode 15为IEMI+15位IMSI(3位MCC+2位MNC+10位MSIN)+12位MAC地址
	 * @param gameCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/testList.shtml")
	public String testList(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			LoggerUtils.info(String.format("IP[%s]进入到测试接口中", request.getRemoteAddr()));
			List<BaseTreePojo> trees = baseService.list(BaseTreePojo.class);
			BaseCodeDataListView.addData(modelMap, "trees", trees);
		} catch (Exception e) {
			transactException(modelMap, request, response, e);
		}
		return BaseJsonView.getViewName(BaseCodeDataListView.class);
	}

	/**
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lmiky
	 * @date 2015年9月15日 下午5:23:58
	 */
	@RequestMapping("/testFind.shtml")
    public String testFind(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
        try {
            LoggerUtils.info(String.format("IP[%s]进入到测试接口中", request.getRemoteAddr()));
            BaseTreePojo tree = baseService.find(BaseTreePojo.class, id);
            BaseCodeDataView.addData(modelMap, "tree", tree);
        } catch (Exception e) {
            transactException(modelMap, request, response, e);
        }
        return BaseJsonView.getViewName(BaseCodeDataView.class);
    }

	/**
	 * @return the baseService
	 */
	public BaseService getBaseService() {
		return baseService;
	}

	/**
	 * @param baseService the baseService to set
	 */
	@Resource(name="baseService")
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

}
