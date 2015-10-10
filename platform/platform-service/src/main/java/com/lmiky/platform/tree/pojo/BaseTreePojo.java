package com.lmiky.platform.tree.pojo;

import java.util.List;

import javax.persistence.CascadeType;
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

import lombok.Getter;
import lombok.Setter;

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
    @Getter
	@Setter
    private String name;
    @Getter
	@Setter
	private Integer leaf = 0;
    @Getter
	@Setter
	private Long parentId;
    @Setter
    private BaseTreePojo parent;
    @Setter
	private List<BaseTreePojo> children;

	/**
     * @return the parent
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="parentId")
    public BaseTreePojo getParent() {
        return parent;
    }
	/**
	 * @return the children
	 */
	@OneToMany(mappedBy="parent", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@OrderBy(BaseSortPojo.POJO_FIELD_NAME_SORT + " desc")
	public List<BaseTreePojo> getChildren() {
		return children;
	}
}
