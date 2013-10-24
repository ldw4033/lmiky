package com.lmiky.jdp.database.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 基本排序实体类
 * @author lmiky
 * @date 2013-10-24
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseSortPojo extends BasePojo {
	public static int DEFAULT_SORT = 0;
	
	private Integer Sort = DEFAULT_SORT;

	/**
	 * @return the sort
	 */
	@Column(name="Sort")
	public Integer getSort() {
		return Sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		Sort = sort;
	}
}
