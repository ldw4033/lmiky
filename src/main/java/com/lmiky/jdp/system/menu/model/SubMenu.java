package com.lmiky.jdp.system.menu.model;

import java.io.Serializable;

/**
 * 子菜单菜单
 * @author lmiky
 * @date 2013-6-16
 */
public class SubMenu implements Serializable {
	private static final long serialVersionUID = -248788134293255417L;
	public static final String TYPE_LINK = "link";
	public static final String TYPE_DIALOG = "dialog";
	public static final String TYPE_IFRAME = "iframe";
	
	private String id;
	private String label;
	private String url;
	private String type;
	private String modulePath;
	private String authority;
	private Integer width;
	private Integer height;
	
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the modulePath
	 */
	public String getModulePath() {
		return modulePath;
	}
	/**
	 * @param modulePath the modulePath to set
	 */
	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}
	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}
	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
}