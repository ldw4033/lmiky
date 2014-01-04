package com.lmiky.jdp.tree.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

import com.lmiky.jdp.constants.Constants;
import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.database.pojo.BaseSortPojo;
import com.lmiky.jdp.form.controller.FormController;
import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.tree.pojo.BaseTreePojo;

/**
 * 树
 * @author lmiky
 * @date 2013-4-15
 */
public abstract class TreeController<T extends BaseTreePojo> extends FormController<T> {
	public static final int ROOT_NODE_ID = 0;
	
	/**
	 * 加载树
	 * @author lmiky
	 * @date 2014-1-3
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @return
	 * @throws Exception
	 */
	public String executeLoadTree(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		try {
			// 判断是否有登陆
			SessionInfo sessionInfo = getSessionInfo(modelMap, request);
			// 检查单点登陆
			checkSso(sessionInfo, modelMap, request);
			//检查权限
			checkAuthority(modelMap, request, sessionInfo, getLoadAuthorityCode());
			modelMap.put("roots", service.list(pojoClass, new PropertyFilter("parent.id", null, PropertyCompareType.NULL, pojoClass), new Sort(BaseSortPojo.POJO_FIELD_NAME_SORT, Sort.SORT_TYPE_ASC, pojoClass)));
			appendListAttribute(modelMap, request, resopnse);
			String modulePath = getModulePath(modelMap, request);
			modelMap.put(Constants.HTTP_PARAM_MODULE_PATH, modulePath);
			return getExecuteListRet(modelMap, request, modulePath);
		} catch (Exception e) {
			return transactException(e, modelMap, request, resopnse);
		}
	}
	
	/**
	 * 执行树列表加载
	 * @author lmiky
	 * @date 2014-1-4
	 * @param modelMap
	 * @param request
	 * @param resopnse
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public String executeTreeList(ModelMap modelMap, HttpServletRequest request, HttpServletResponse resopnse, Long parentId) throws Exception {
		try {
			// 判断是否有登陆
			SessionInfo sessionInfo = getSessionInfo(modelMap, request);
			// 检查单点登陆
			checkSso(sessionInfo, modelMap, request);
			//检查权限
			checkAuthority(modelMap, request, sessionInfo, getLoadAuthorityCode());
			if(parentId == null) {
				modelMap.put("nodes", service.list(pojoClass, new PropertyFilter("parent.id", null, PropertyCompareType.NULL, pojoClass), new Sort(BaseSortPojo.POJO_FIELD_NAME_SORT, Sort.SORT_TYPE_ASC, pojoClass)));
			} else {
				modelMap.put("nodes", service.list(pojoClass, new PropertyFilter("parent.id", parentId, PropertyCompareType.EQ, pojoClass), new Sort(BaseSortPojo.POJO_FIELD_NAME_SORT, Sort.SORT_TYPE_ASC, pojoClass)));
			}
			return "treeListJsonView";
		} catch (Exception e) {
			logException(e, modelMap, request, resopnse);
		}
		return null;
	}
}