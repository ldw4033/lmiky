package com.lmiky.jdp.web.ui.taglib;

import javax.servlet.jsp.JspException;

import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.system.menu.pojo.MyFavoriteMenu;
import com.lmiky.jdp.web.util.WebUtils;

/**
 * 判断是否在我的收藏菜单中
 * @author lmiky
 * @date 2013-6-17
 */ 
public class MyFavoriteMenuInTag extends MyFavoriteMenuTag {
	private static final long serialVersionUID = -4711526415451705329L;

	public MyFavoriteMenuInTag() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.web.ui.taglib.BaseTag#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		try {
			SessionInfo sessionInfo = WebUtils.getSessionInfo(getRequest());
			if (sessionInfo == null) {
				return SKIP_BODY;
			}
			if(!baseService.exist(MyFavoriteMenu.class, new PropertyFilter("userId", sessionInfo.getUserId(), PropertyCompareType.EQ, MyFavoriteMenu.class), new PropertyFilter("menuId", getMenuId(), PropertyCompareType.EQ, MyFavoriteMenu.class))) {
				return SKIP_BODY;
			}
			return EVAL_BODY_INCLUDE;
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
	}

}