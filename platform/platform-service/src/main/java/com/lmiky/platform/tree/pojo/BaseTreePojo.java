package com.lmiky.platform.tree.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.lmiky.platform.sort.pojo.BaseSortPojo;

/**
 * 树
 * @author lmiky
 * @date 2014-1-2
 */
@Entity
@Table(name="t_tree")
@Inheritance(strategy=InheritanceType.JOINED)
public class BaseTreePojo extends BaseSortPojo {
    private static final long serialVersionUID = 1L;
    private String name;
	private Integer leaf = 0;
	private Long parentId;
    private BaseTreePojo parent;
	private List<BaseTreePojo> children;

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
	/**
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
	/**
     * @return the parent
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parentId")
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
	 * @return the children
	 */
	@OneToMany(mappedBy="parent", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@OrderBy(BaseSortPojo.POJO_FIELD_NAME_SORT + " desc")
	public List<BaseTreePojo> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<BaseTreePojo> children) {
		this.children = children;
	}
}
