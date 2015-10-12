package com.lmiky.platform.apitest.controller;

import com.lmiky.platform.controller.api.BaseApiController;
import com.lmiky.platform.controller.api.view.CodeDataView;
import com.lmiky.platform.logger.util.LoggerUtils;
import com.lmiky.platform.tree.pojo.BaseTreePojo;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lmiky
 * @date 2015年5月11日 下午2:58:27
 */
@RestController
@RequestMapping("/api/test")
public class TestApiController extends BaseApiController {

    /**
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @author lmiky
     * @date 2015年9月16日 上午11:46:49
     */
    @RequestMapping("/test.shtml")
    @ResponseBody
    public CodeDataView test(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        LoggerUtils.info(String.format("IP[%s]进入到测试接口中", request.getRemoteAddr()));
        CodeDataView view = CodeDataView.buildSuccessView("sessionId", request.getSession().getId());
        view.addDate("serverId", 2);
        return view;
    }

    /**
     * 激活
     *
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
    @RequestMapping("/testlist.shtml")
    @ResponseBody
    public CodeDataView testList(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        LoggerUtils.info(String.format("IP[%s]进入到测试接口中", request.getRemoteAddr()));
        List<BaseTreePojo> trees = service.list(BaseTreePojo.class);
        return CodeDataView.buildSuccessView("trees", trees);
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
    @RequestMapping("/testfind.shtml")
    @ResponseBody
    public CodeDataView testFind(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
            Long id) {
        LoggerUtils.info(String.format("IP[%s]进入到测试接口中", request.getRemoteAddr()));
        BaseTreePojo tree = service.find(BaseTreePojo.class, id);
        return CodeDataView.buildSuccessView("tree", tree);
    }

    /**
     * @param modelMap
     * @param request
     * @param response
     * @param date
     * @return
     * @author lmiky
     * @date 2015年9月16日 下午2:06:47
     */
    @RequestMapping("/testdateparam.shtml")
    @ResponseBody
    public CodeDataView testDateParam(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
            Date date) {
        LoggerUtils.info(String.format("IP[%s]进入到测试接口中", request.getRemoteAddr()));
        LoggerUtils.info(date);
        return CodeDataView.buildSuccessView("date", date);
    }
}
