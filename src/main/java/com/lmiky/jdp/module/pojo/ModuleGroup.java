package com.lmiky.jdp.module.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 模块组
 * @author lmiky
 * @date 2013-5-12
 */
@Entity
@Table(name="module_group")
public class ModuleGroup extends BasePojo {
	private static final long serialVersionUID = 1705712097333979440L;
	private String name;
	private Set<Module> modules;

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
	 * @return the modules
	 */
	@OneToMany(mappedBy="group", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@OrderBy("id asc")
	public Set<Module> getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
}
