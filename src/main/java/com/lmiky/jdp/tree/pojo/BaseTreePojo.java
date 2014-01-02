package com.lmiky.jdp.tree.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.lmiky.jdp.database.pojo.BaseSortPojo;

/**
 * 树
 * @author lmiky
 * @date 2014-1-2
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseTreePojo extends BaseSortPojo {
	public static final int LEAF_YES = 0;
	public static final int LEAF_NO = 1;
	
	private BaseTreePojo parent;
	private Integer leaf = LEAF_YES;
	
	/**
	 * @return the parent
	 */
	@ManyToOne(fetch=FetchType.LAZY , cascade={CascadeType.ALL} )  
    @JoinColumn(name="parent_id")
	public BaseTreePojo getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(BaseTreePojo parent) {
		this.parent = parent;
	}
	/**
	 * @return the leaf
	 */
	@Column(name="leaf")
	public Integer getLeaf() {
		return leaf;
	}
	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
}
