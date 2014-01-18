package com.lmiky.jdp.tree.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lmiky.jdp.sort.pojo.BaseSortPojo;

/**
 * 树
 * @author lmiky
 * @date 2014-1-2
 */
@SuppressWarnings("serial")
@Entity 
@Table(name="tree")
@Inheritance(strategy=InheritanceType.JOINED) 
public class BaseTreePojo extends BaseSortPojo {
	
	private String name;
	private BaseTreePojo parent;
	private Integer leaf = 0;
	
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
	 * @return the parent
	 */
	@ManyToOne(fetch=FetchType.LAZY)  
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
