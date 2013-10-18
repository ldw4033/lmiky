package com.lmiky.jdp.module.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.lmiky.jdp.json.util.JsonUtils;
import com.lmiky.jdp.module.controller.ModuleController;
import com.lmiky.jdp.module.pojo.Function;

/**
 * 模块列表
 * @author lmiky
 * @date 2013-5-19
 */
@Component(value="functionListJsonView")
public class FunctionListJsonView extends AbstractView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Function> functions = (List<Function>)model.get("pojos");
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//默认增加查询功能菜单
		dataMap.put("id", ModuleController.TREE_LIST_ID_PREFIX_FUNCTION + Function.DEFAULT_FUNCTIONID_LOAD);
		dataMap.put("name", "查询");
		dataMap.put("moduleType", ModuleController.MODULE_TYPE_FUNCTION);
		dataMap.put("isParent", false);
		dataList.add(dataMap);
		for(Function function : functions) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("id", ModuleController.TREE_LIST_ID_PREFIX_FUNCTION + function.getId());
			dataMap.put("name", function.getName());
			dataMap.put("moduleType", ModuleController.MODULE_TYPE_FUNCTION);
			dataMap.put("isParent", false);
			dataList.add(dataMap);
		}
		String json = JsonUtils.toJson(dataList);
		response.getWriter().write(json);
	}

}
