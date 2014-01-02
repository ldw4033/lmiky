package com.lmiky.cms.directory.pojo;

import com.lmiky.jdp.tree.pojo.BaseTreePojo;

/**
 * 目录
 * @author lmiky
 * @date 2014-1-2
 */
public class Directory extends BaseTreePojo {
	private static final long serialVersionUID = 4386184813739538591L;
	
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
