package com.lmiky.jdp.system.menu.model;

import java.io.Serializable;
import java.util.List;

/**
 * 左菜单菜单
 * @author lmiky
 * @date 2013-6-16
 */
public class LeftMenu implements Serializable {
	private static final long serialVersionUID = 613083126910346194L;
	private String id;
	private String label;
	private List<SubMenu> subMenus;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the subMenus
	 */
	public List<SubMenu> getSubMenus() {
		return subMenus;
	}
	/**
	 * @param subMenus the subMenus to set
	 */
	public void setSubMenus(List<SubMenu> subMenus) {
		this.subMenus = subMenus;
	}
}
