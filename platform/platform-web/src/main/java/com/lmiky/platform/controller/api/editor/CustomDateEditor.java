package com.lmiky.platform.controller.api.editor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * 自定义日期格式转换器
 *
 * @author lmiky
 * @date 2015年8月25日 下午3:38:38
 */
public class CustomDateEditor extends PropertyEditorSupport {
    protected static final Log LOGGER = LogFactory.getLog(CustomDateEditor.class);

    private final DateFormat[] dateFormats;
    private final boolean allowEmpty;
    private final int exactDateLength;

    /**
     * 初始化
     *
     * @param allowEmpty  是否允许为空
     * @param dateFormats 日期转换器列表
     */
    public CustomDateEditor(boolean allowEmpty, DateFormat... dateFormats) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
        this.dateFormats = dateFormats;
    }

    /**
     * 初始化
     *
     * @param allowEmpty      是否允许为空
     * @param exactDateLength 数据长度
     * @param dateFormats     日期转换器列表
     */
    public CustomDateEditor(boolean allowEmpty, int exactDateLength, DateFormat... dateFormats) {
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
        this.dateFormats = dateFormats;
    }

    /*
     * (non-Javadoc)
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
            throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength
                + "characters long");
        } else {
            boolean success = false; // 是否成功
            Exception e = null; // 错误
            for (DateFormat dateFormat : dateFormats) {
                try {
                    setValue(dateFormat.parse(text));
                    success = true;
                } catch (ParseException ex) {
                    success = false;
                    e = ex;
                }
                if (success) {
                    break;
                }
            }
            if (!success && e != null) {
                throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see java.beans.PropertyEditorSupport#getAsText()
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        if (value == null) {
            return "";
        }
        for (DateFormat dateFormat : dateFormats) {
            try {
                return dateFormat.format(value);
            } catch (Exception e) {
                LOGGER.debug(String.format("转换日期格式错误: %s", value));
            }
        }
        return "";
    }
}