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
	private static final long serialVersionUID = 7444847264582278124L;
	
	private Long operator;
	private String functionPath;
	
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
	 * @return the functionPath
	 */
	@Column(name="function_path")
	public String getFunctionPath() {
		return functionPath;
	}
	/**
	 * @param functionPath the functionPath to set
	 */
	public void setFunctionPath(String functionPath) {
		this.functionPath = functionPath;
	}
}
