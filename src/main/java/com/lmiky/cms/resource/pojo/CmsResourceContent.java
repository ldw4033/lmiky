package com.lmiky.cms.resource.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 资源内容
 * @author lmiky
 * @date 2014-1-5
 */ 
@Entity
@Table(name="cms_resource_content")
public class CmsResourceContent extends BasePojo {
	private static final long serialVersionUID = 6064841502608057587L;
	private String content;

	/**
	 * @return the content
	 */
	@Lob
	@Column(name="content")
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
