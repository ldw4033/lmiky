package com.lmiky.jdp.web.ui.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;


import com.lmiky.jdp.authority.service.AuthorityService;
import com.lmiky.jdp.module.pojo.Function;
import com.lmiky.jdp.module.pojo.Module;
import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.session.model.SessionInfo;
import com.lmiky.jdp.util.SpringUtils;
import com.lmiky.jdp.web.util.WebUtils;

/**
 * 检查权限
 * @author lmiky
 * @date 2013-6-6
 */
public class CheckAuthorityTag extends BaseTag {
	private static final long serialVersionUID = 24362296371705163L;
	
	private AuthorityService authorityService;
	private BaseService baseService;
	private String modulePath;
	private String authorityCode;

	public CheckAuthorityTag() {
		authorityService = (AuthorityService) SpringUtils.getBean("authorityService");
		baseService = (BaseService) SpringUtils.getBean("baseService");
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.web.ui.taglib.BaseTag#doStartTag()
	 */
	@Override
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request = getRequest();
			SessionInfo sessionInfo = WebUtils.getSessionInfo(request);
			if (sessionInfo == null) {
				return SKIP_BODY;
			}
			Module module = WebUtils.getModule(baseService, modulePath);
			if(Function.DEFAULT_AUTHORITYCODE_ADMIN.equals(authorityCode)) {
				if(!WebUtils.checkAuthority(authorityService, sessionInfo, module, Function.DEFAULT_FUNCTIONID_ADMIN)) {	//管理员
					return SKIP_BODY;
				}
			} else if(Function.DEFAULT_AUTHORITYCODE_LOAD.equals(authorityCode)) {
				if(!WebUtils.checkAuthority(authorityService, sessionInfo, module, Function.DEFAULT_FUNCTIONID_LOAD)) {	//查询
					return SKIP_BODY;
				}
			} else if(!WebUtils.checkAuthority(baseService, authorityService, sessionInfo, module, authorityCode)) {	//其他
				return SKIP_BODY;
			}
			return EVAL_BODY_INCLUDE;
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
	}

	/**
	 * @return the modulePath
	 */
	public String getModulePath() {
		return modulePath;
	}

	/**
	 * @param modulePath the modulePath to set
	 */
	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	/**
	 * @return the authorityCode
	 */
	public String getAuthorityCode() {
		return authorityCode;
	}

	/**
	 * @param authorityCode the authorityCode to set
	 */
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}


}
