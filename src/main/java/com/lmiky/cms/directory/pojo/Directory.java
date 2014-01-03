package com.lmiky.cms.directory.pojo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.lmiky.jdp.tree.pojo.BaseTreePojo;

/**
 * 目录
 * @author lmiky
 * @date 2014-1-2
 */
@Entity
@Table(name="directory")
@PrimaryKeyJoinColumn(name="id")
public class Directory extends BaseTreePojo {
	private static final long serialVersionUID = -9175148643428532655L;
	
}
