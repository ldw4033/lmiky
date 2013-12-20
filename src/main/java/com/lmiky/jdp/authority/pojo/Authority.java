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
@Table(name="authority")
public class Authority extends BasePojo {
	private static final long serialVersionUID = 3538567387722410381L;
	//操作员类别
	public static final String OPERATORTYPE_ROLE = "role";							//角色
	
	//模块类别
	public static final String MODULETYPE_SYSTEM= "system";			//系统
	public static final String MODULETYPE_GROUP= "group";			//模块组
	public static final String MODULETYPE_MODULE = "module";		//模块
	public static final String MODULETYPE_FUNCTION = "function";	//功能
	
	private Long operator;
	private String operatorType;
	private Long moduleId;
	private String moduleType;
	private Long functionId;
	
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
	 * @return the operatorType
	 */
	@Column(name="operatorType")
	public String getOperatorType() {
		return operatorType;
	}
	/**
	 * @param operatorType the operatorType to set
	 */
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	/**
	 * @return the moduleId
	 */
	@Column(name="moduleId")
	public Long getModuleId() {
		return moduleId;
	}
	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * @return the moduleType
	 */
	@Column(name="moduleType")
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
	 * @return the functionId
	 */
	@Column(name="functionId")
	public Long getFunctionId() {
		return functionId;
	}
	/**
	 * @param functionId the functionId to set
	 */
	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}
}
