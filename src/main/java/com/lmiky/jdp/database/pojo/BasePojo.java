package com.lmiky.jdp.database.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 基本实体类
 * @author lmiky
 * @date 2013-4-15
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BasePojo implements Serializable {
	private Long id;

	/**
	 * @return the id
	 */
	 @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
