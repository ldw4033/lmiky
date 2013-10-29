package com.lmiky.love.job.position.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BaseSortPojo;

/**
 * 工作职位
 * @author lmiky
 * @date 2013-10-26
 */
@Entity
@Table(name = "love_job_position")
public class JobPosition extends BaseSortPojo {
	private static final long serialVersionUID = -5586015063891652592L;
	private String name;

	/**
	 * @return the name
	 */
	@Column(name = "name")
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
