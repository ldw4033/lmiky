package com.lmiky.jdp.module.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 功能
 * @author lmiky
 * @date 2013-5-12
 */
@Entity
@Table(name="function")
public class Function extends BasePojo {
	private static final long serialVersionUID = 8941182016977264129L;
	
	//默认功能ID
	public static final int DEFAULT_FUNCTIONID_ADMIN = -1;		//管理员
	public static final int DEFAULT_FUNCTIONID_LOAD = -2;		//查询
	
	//默认功能编码
	public static final String DEFAULT_AUTHORITYCODE_ADMIN = "admin";		//管理员
	public static final String DEFAULT_AUTHORITYCODE_LOAD = "load";		//查询
	
	//默认排序值
	public static final int SORT_DEFAULT = 0;
	
	private String name;
	private Module module;
	private String authorityCode;
	private Integer sort = SORT_DEFAULT;	//排序,从小到大排序
	
	/**
	 * @return the name
	 */
	@Column(name="name")
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the module
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="moduleId", updatable = false)
	public Module getModule() {
		return module;
	}
	/**
	 * @param module the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
	}
	/**
	 * @return the authorityCode
	 */
	@Column(name="authorityCode")
	public String getAuthorityCode() {
		return authorityCode;
	}
	/**
	 * @param authorityCode the authorityCode to set
	 */
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}
	/**
	 * @return the sort
	 */
	@Column(name="sort")
	public Integer getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
