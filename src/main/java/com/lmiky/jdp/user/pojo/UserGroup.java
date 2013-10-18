package com.lmiky.jdp.user.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 用户组
 * @author lmiky
 * @date 2013-5-12
 */
@Entity
@Table(name="user_group")
public class UserGroup extends BasePojo {
	private static final long serialVersionUID = 8388285707130870602L;
	private String name;
	private Set<User> users;

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
	 * @return the users
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST})
	@JoinTable(name = "user_usergroup", joinColumns = { @JoinColumn(name = "userGroupId", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "userId", updatable = false) })
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
