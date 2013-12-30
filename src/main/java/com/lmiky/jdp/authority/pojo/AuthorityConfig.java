package com.lmiky.jdp.authority.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 权限
 * @author lmiky
 * @date 2013-5-12
 */
@Entity
@Table(name="authority_config")
public class AuthorityConfig extends BasePojo {
	private static final long serialVersionUID = 7444847264582278124L;
	
	public static final String MODULETYPE_SYSTEM = "system";
	public static final String MODULETYPE_GROUP = "group";
	public static final String MODULETYPE_MODULE = "module";
	public static final String MODULETYPE_FUNCTION = "function";
	
	private Long operator;
	private String moduleType;
	private String moduleCode;
	
	/**
	 * @return the operator
	 */
	@Column(name="operator")
	public Long getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}
	/**
	 * @return the moduleType
	 */
	@Column(name="module_type")
	public String getModuleType() {
		return moduleType;
	}
	/**
	 * @param moduleType the moduleType to set
	 */
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	/**
	 * @return the moduleCode
	 */
	@Column(name="module_code")
	public String getModuleCode() {
		return moduleCode;
	}
	/**
	 * @param moduleCode the moduleCode to set
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
}
