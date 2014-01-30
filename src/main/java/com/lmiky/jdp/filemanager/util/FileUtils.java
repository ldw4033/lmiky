package com.lmiky.jdp.filemanager.util;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.WebUtils;

import com.lmiky.jdp.constants.Constants;
import com.lmiky.jdp.filemanager.exception.FileUploadException;
import com.lmiky.jdp.util.PropertiesUtils;
import com.lmiky.jdp.util.UUIDGenerator;

/**
 * 文件工具
 * @author lmiky
 * @date 2014-1-27
 */
public class FileUtils {

	/**
	 * 上传文件 imgFile: 文件form名称
	 * @author lmiky
	 * @date 2014-1-27
	 * @param modelMap
	 * @param request
	 * @param response
	 * @param formFieldName 表单中文件字段名称
	 * @throws FileUploadException
	 */
	public static String upload(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, String formFieldName) throws FileUploadException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile(formFieldName);
		if (multipartFile == null) {
			throw new FileUploadException("请选择上传文件！", FileUploadException.CODE_FILE_EMPTY);
		}
		String oldFileName = multipartFile.getOriginalFilename();
		if (StringUtils.isBlank(oldFileName)) {
			throw new FileUploadException("请选择上传文件！", FileUploadException.CODE_FILE_EMPTY);
		}
		String filePrefix = UUIDGenerator.generateString();
		String fileSuffix = StringUtils.substringAfterLast(oldFileName, ".");
		String newFileName = filePrefix + "." + fileSuffix;
		// 检查文件格式
		String fileExtension = request.getParameter("fileExtension"); // 允许的文件格式，多个格式之间以“,”分隔
		if (!StringUtils.isBlank(fileExtension)) {
			if (("," + fileExtension + ",").indexOf("," + fileSuffix + ",") == -1) {
				throw new FileUploadException("文件格式错误！", FileUploadException.CODE_FORMAT_ERROR);
			}
		}
		try {
			String filePath = PropertiesUtils.getStringContextValue(Constants.SYSTEM_FILE_PATH);
			String realPath = WebUtils.getRealPath(request.getSession().getServletContext(), filePath);
			File newFile = new File(realPath + "/" + newFileName);
			IOUtils.copy(multipartFile.getInputStream(), org.apache.commons.io.FileUtils.openOutputStream(newFile));
			return filePath + "/" + newFileName;
		} catch (Exception e) {
			throw new FileUploadException(e.getMessage());
		}
	}

	/**
	 * 默认表单文件字段名称为imgFile
	 * @author lmiky
	 * @date 2014-1-27
	 * @param modelMap
	 * @param request
	 * @param response
	 * @throws FileUploadException
	 */
	public static String upload(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws FileUploadException {
		return upload(modelMap, request, response, "imgFile");
	}
}
