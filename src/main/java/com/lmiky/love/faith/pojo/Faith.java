package com.lmiky.love.faith.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BaseSortPojo;

/**
 * 宗教信仰
 * @author lmiky
 * @date 2013-10-24
 */
@Entity
@Table(name = "love_faith")
public class Faith extends BaseSortPojo {
	private static final long serialVersionUID = -1794102813777194120L;
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
