package com.lmiky.jdp.user.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 用户
 * @author lmiky
 * @date 2013-4-23
 */
@Entity
@Table(name="t_user")
@PrimaryKeyJoinColumn(name="id")
public class User extends Person {
	private static final long serialVersionUID = 2172716215766952681L;
	//是否可用
	public static final int VALID_YES = 0;
	public static final int VALID_NO = 1;
	
	private String loginName;
	private String password;
	private Date lastSetPasswordTime;
	private Integer valid;
	private Set<Role> roles;

	/**
	 * @return the loginName
	 */
	@Column(name = "login_name")
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the password
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the lastSetPasswordTime
	 */
	@Column(name = "last_set_password_time")
	public Date getLastSetPasswordTime() {
		return lastSetPasswordTime;
	}

	/**
	 * @param lastSetPasswordTime the lastSetPasswordTime to set
	 */
	public void setLastSetPasswordTime(Date lastSetPasswordTime) {
		this.lastSetPasswordTime = lastSetPasswordTime;
	}

	/**
	 * @return the valid
	 */
	@Column(name = "valid")
	public Integer getValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}

	/**
	 * @return the roles
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST})
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "user_id", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}