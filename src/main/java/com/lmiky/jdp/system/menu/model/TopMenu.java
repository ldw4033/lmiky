package com.lmiky.jdp.system.menu.model;

import java.io.Serializable;
import java.util.List;

/**
 * 顶层菜单
 * @author lmiky
 * @date 2013-6-16
 */
public class TopMenu implements Serializable {
	private static final long serialVersionUID = 3860497925965363378L;
	private String id;
	private String label;
	private List<LeftMenu> leftMenus;
	
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
	 * @return the leftMenus
	 */
	public List<LeftMenu> getLeftMenus() {
		return leftMenus;
	}
	/**
	 * @param leftMenus the leftMenus to set
	 */
	public void setLeftMenus(List<LeftMenu> leftMenus) {
		this.leftMenus = leftMenus;
	}
}
