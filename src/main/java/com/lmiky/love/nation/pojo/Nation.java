package com.lmiky.love.nation.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BaseSortPojo;

/**
 * 民族
 * @author lmiky
 * @date 2013-10-24
 */
@Entity
@Table(name = "love_nation")
public class Nation extends BaseSortPojo {
	private static final long serialVersionUID = 2826967689728779571L;
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
