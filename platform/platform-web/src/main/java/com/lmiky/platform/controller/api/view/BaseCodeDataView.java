package com.lmiky.platform.controller.api.view;

import com.lmiky.platform.constants.Constants;
import com.lmiky.platform.controller.view.BaseCode;
import com.lmiky.platform.logger.util.LoggerUtils;
import com.lmiky.platform.util.BundleUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据封装，base.
 *
 * @author lmiky
 * @date 2015年8月12日 下午4:52:27
 */
public class BaseCodeDataView implements Serializable {
    private static final long serialVersionUID = 1L;


    // 键值
    public static final String KEY_NAME_CODE = "code";
    public static final String KEY_NAME_MSG = "msg";
    public static final String KEY_NAME_DATA = "data";

    private int code = BaseCode.CODE_SUCCESS;
    private String msg = "";
    private Map<String, Object> data;

    /**
     *
     */
    private BaseCodeDataView() {

    }

    /**
     * @param code 结果码
     * @param msg  消息
     */
    private BaseCodeDataView(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 添加数据
     *
     * @param dataKey   数据键
     * @param dataValue 数据值
     * @author lmiky
     * @date 2015年8月12日 下午5:36:37
     */
    public void addDate(String dataKey, Object dataValue) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(dataKey, dataValue);
    }

    /**
     * 添加数据
     *
     * @param dataMap   数据键
     * @author lmiky
     * @date 2015年8月12日 下午5:36:37
     */
    public void addDate(Map<String, Object> dataMap) {
        if (data == null) {
            data = new HashMap<>();
        }
        if (dataMap != null) {
            data.putAll(dataMap);
        }
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
        setMsg(getCodeMsg(code));
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    private void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * 构建结果码消息
     *
     * @param code 结果码
     * @return 消息
     * @author lmiky
     * @date 2015年8月13日 下午3:07:52
     */
    public static final String getCodeMsg(int code) {
        String msg = "";
        try {
            msg = BundleUtils.getStringValue(Constants.PROPERTIES_KEY_CODE_MSG_FILE, code + "");
        } catch (Exception e) {
            LoggerUtils.error("构建结果码消息失败!", e);
        }
        return msg;
    }

    /**
     * 构建对象
     *
     * @param code 结果码
     * @return 消息体
     * @author lmiky
     * @date 2015年8月12日 下午6:29:06
     */
    public static BaseCodeDataView buildView(int code) {
        BaseCodeDataView view = new BaseCodeDataView();
        view.setCode(code);
        view.setMsg(getCodeMsg(code));
        return view;
    }

    /**
     * 构建对象
     *
     * @param code 结果码
     * @param msg  提示消息
     * @return 消息体
     * @author lmiky
     * @date 2015年8月12日 下午6:29:06
     */
    public static BaseCodeDataView buildView(int code, String msg) {
        BaseCodeDataView view = new BaseCodeDataView();
        view.setCode(code);
        view.setMsg(msg);
        return view;
    }

    /**
     * 构建成功对象
     *
     * @return 消息体
     * @author lmiky
     * @date 2015年8月12日 下午6:28:57
     */
    public static BaseCodeDataView buildSuccessView() {
        return buildView(BaseCode.CODE_SUCCESS);
    }

    /**
     * 构建对象
     *
     * @param dataKey   数据键
     * @param dataValue 数据值
     * @return 返回VO对象
     * @author lmiky
     * @date 2015年8月12日 下午5:40:51
     */
    public static BaseCodeDataView buildSuccessView(String dataKey, Object dataValue) {
        BaseCodeDataView view = buildSuccessView();
        view.addDate(dataKey, dataValue);
        return view;
    }

    /**
     * 构建对象
     *
     * @param data 数据集
     * @return 消息体
     * @author lmiky
     * @date 2015年8月12日 下午5:41:54
     */
    public static BaseCodeDataView buildSuccessView(Map<String, Object> data) {
        BaseCodeDataView view = buildSuccessView();
        view.setData(data);
        return view;
    }

    /**
     * 构建错误对象
     *
     * @return 消息体
     * @author lmiky
     * @date 2015年8月12日 下午6:30:28
     */
    public static BaseCodeDataView buildErrorView() {
        return buildView(BaseCode.CODE_ERROR);
    }
}
