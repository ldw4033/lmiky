package com.lmiky.love.company.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BaseSortPojo;

/**
 * 公司行业
 * @author lmiky
 * @date 2013-10-26
 */
@Entity
@Table(name = "love_company_profession")
public class CompanyProfession extends BaseSortPojo {
	private static final long serialVersionUID = -2245952503825266991L;
	private String name;
	
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
}
