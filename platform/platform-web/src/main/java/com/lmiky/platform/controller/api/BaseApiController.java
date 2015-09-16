package com.lmiky.platform.controller.api;

import com.lmiky.platform.constants.Constants;
import com.lmiky.platform.controller.api.editor.CustomDateEditor;
import com.lmiky.platform.controller.api.view.BaseCodeDataView;
import com.lmiky.platform.controller.html.BaseController;
import com.lmiky.platform.exception.BaseCodeException;
import com.lmiky.platform.logger.util.LoggerUtils;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * API控制器
 * @author lmiky
 * @date 2014年11月3日 下午2:20:50
 */
public abstract class BaseApiController extends BaseController {

    static CustomDateEditor customDateEditor;

    static {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(Constants.CONTEXT_KEY_FORMAT_DATETIME_VALUE);
        dateTimeFormat.setLenient(false);
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.CONTEXT_KEY_FORMAT_DATE_VALUE);
        dateFormat.setLenient(false);
        SimpleDateFormat timeFormat = new SimpleDateFormat(Constants.CONTEXT_KEY_FORMAT_TIME_VALUE);
        timeFormat.setLenient(false);
        customDateEditor = new CustomDateEditor(true, dateTimeFormat, dateFormat, timeFormat);
    }

    /**
     * 绑定参数，json实体类的参数转换
     *
     * @param binder 绑定参数
     * @author lmiky
     * @date 2015年8月20日 上午10:35:08
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    /**
     * 异常处理
     *
     * @param request       request
     * @param exception     错误
     * @param handlerMethod 方法处理器
     * @return 如果是ajax请求，返回构建的数据实体类，否则返回null
     * @author lmiky
     * @throws Exception
     * @date 2015年8月12日 下午7:33:41
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception exception, HandlerMethod handlerMethod) throws Exception {
        Method method = handlerMethod.getMethod();
        if (method == null) {
            return null;
        }
        // 判断是否ajax请求
        ResponseBody responseBodyAnn = AnnotationUtils.findAnnotation(method, ResponseBody.class);
        if (responseBodyAnn != null) {
            return handleAjaxRequestException(request, exception, handlerMethod);
        }
        return handleViewRequestException(request, exception, handlerMethod);
    }

    /**
     * 处理ajax请求异常
     *
     * @param request       request
     * @param exception     错误
     * @param handlerMethod 方法处理器
     * @return 返回构建的数据实体类
     * @author lmiky
     * @date 2015年8月13日 上午11:53:01
     */
    public BaseCodeDataView handleAjaxRequestException(HttpServletRequest request, Exception exception,
                                                     HandlerMethod handlerMethod) {
        // 带结果码异常
        if (exception instanceof BaseCodeException) {
            return BaseCodeDataView.buildVo(((BaseCodeException) exception).getCode());
        }
        LoggerUtils.error("handle controller(" + handlerMethod.getMethod().getName() + ") exception !", exception);
        return BaseCodeDataView.buildErrorVo();
    }

    /**
     * 处理视图请求异常
     *
     * @param request       request
     * @param exception     错误
     * @param handlerMethod 方法处理器
     * @return 返回视图信息
     * @author lmiky
     * @throws Exception
     * @date 2015年8月13日 上午11:53:59
     */
    public Object handleViewRequestException(HttpServletRequest request, Exception exception,
                                             HandlerMethod handlerMethod) throws Exception {
        LoggerUtils.error("handle controller(" + handlerMethod.getMethod().getName() + ") exception !", exception);
        throw exception;
    }

}