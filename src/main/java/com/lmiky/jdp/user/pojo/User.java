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
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 用户
 * @author lmiky
 * @date 2013-4-23
 */
@Entity
@Table(name="user")
public class User extends BasePojo {
	private static final long serialVersionUID = 6477794886740573968L;
	//是否可用
	public static final int VALID_YES = 0;
	public static final int VALID_NO = 1;
	
	private String loginName;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String photo;
	private Date createTime;
	private Date lastSetPasswordTime;
	private Integer valid;
	private String description;
	private Set<Role> roles;

	/**
	 * @return the loginName
	 */
	@Column(name = "loginName")
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

	/**
	 * @return the email
	 */
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the photo
	 */
	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return the createTime
	 */
	@Column(name = "createTime", updatable = false)
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the lastSetPasswordTime
	 */
	@Column(name = "lastSetPasswordTime")
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
	 * @return the description
	 */
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the roles
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST})
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "userId", updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "roleId", updatable = false) })
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