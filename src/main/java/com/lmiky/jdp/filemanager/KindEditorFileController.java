package com.lmiky.jdp.filemanager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.WebUtils;

import com.lmiky.jdp.base.controller.BaseController;
import com.lmiky.jdp.constants.Constants;
import com.lmiky.jdp.json.util.JsonUtils;
import com.lmiky.jdp.util.PropertiesUtils;
import com.lmiky.jdp.util.UUIDGenerator;
import com.lmiky.jdp.web.util.ResponseUtils;

/**
 * kindEditor文件管理
 * @author lmiky
 * @date 2014-1-21
 */
@Controller
@RequestMapping("/kindEditorFile")
public class KindEditorFileController extends BaseController {
	//是否成功键名
	public static final String KEY_NAME_ERROR = "error";
	//提示
	public static final String KEY_NAME_MESSAGE = "message";
	//文件路径
	public static final String KEY_NAME_FILE_URL = "url";
	
	//结果码
	public static final int VALUE_NAME_ERROR_SUCCESS = 0;
	public static final int VALUE_NAME_ERROR_ERROR = 1;

	/**
	 * 上传文件
	 * @author lmiky
	 * @date 2014-1-21
	 * @param modelMap
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/upload.shtml")
	public void upload(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(KEY_NAME_FILE_URL, "");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile("imgFile");
		if (multipartFile == null) {
			result.put(KEY_NAME_ERROR, VALUE_NAME_ERROR_ERROR);
			result.put(KEY_NAME_MESSAGE, "请选择上传文件！");
		} else {
			String oldFileName = multipartFile.getOriginalFilename();
			if (StringUtils.isBlank(oldFileName)) {
				result.put(KEY_NAME_ERROR, VALUE_NAME_ERROR_ERROR);
				result.put(KEY_NAME_MESSAGE, "请选择上传文件！");
			} else {
				String filePrefix = UUIDGenerator.generateString();
				String fileSuffix = StringUtils.substringAfterLast(oldFileName, ".");
				String newFileName = filePrefix + "." + fileSuffix;
				//检查文件格式
				boolean isAllowFileExtension = true;
				String fileExtension = request.getParameter("fileExtension");	//允许的文件格式，多个格式之间以“,”分隔
		        if(!StringUtils.isBlank(fileExtension)) {
		        	if(("," + fileExtension + ",").indexOf("," + fileSuffix + ",") == -1) {
		        		result.put(KEY_NAME_ERROR, VALUE_NAME_ERROR_ERROR);
		    			result.put(KEY_NAME_MESSAGE, "文件格式错误！");
		    			isAllowFileExtension = false;
		        	}
		        }
		        if(isAllowFileExtension) {
					String filePath = PropertiesUtils.getStringContextValue(Constants.SYSTEM_FILE_UPLOAD_PATH);
					String realPath = WebUtils.getRealPath(request.getSession().getServletContext(), filePath);
					File newFile = new File(realPath + "/" + newFileName);
					IOUtils.copy(multipartFile.getInputStream(), org.apache.commons.io.FileUtils.openOutputStream(newFile));
					result.put(KEY_NAME_ERROR, VALUE_NAME_ERROR_SUCCESS);
					result.put(KEY_NAME_MESSAGE, "上传成功！");
					result.put(KEY_NAME_FILE_URL, request.getContextPath() + filePath + "/" + newFileName);
		        }
			}
		}
		ResponseUtils.write(response, JsonUtils.toJson(result));
	}
	
	
	
}
