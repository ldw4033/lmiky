package com.lmiky.cms.resource.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lmiky.cms.directory.pojo.CmsDirectory;
import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 资源
 * @author lmiky
 * @date 2014-1-5
 */ 
@Entity
@Table(name="cms_resource")
public class CmsResource extends BasePojo {
	private static final long serialVersionUID = 7980282407055179730L;
	private String title;
	private String subtitle;
	private String author;
	private Date createTime;
	private Date pubTime;
	private CmsResourceContent resourceContent;
	private CmsDirectory directory;
	
	/**
	 * @return the title
	 */
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the subtitle
	 */
	@Column(name="subtitle")
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * @return the author
	 */
	@Column(name="author")
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the createTime
	 */
	@Column(name="create_time")
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
	 * @return the pubTime
	 */
	@Column(name="pub_time")
	public Date getPubTime() {
		return pubTime;
	}
	/**
	 * @param pubTime the pubTime to set
	 */
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	/**
	 * @return the resourceContent
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="content_id")
	public CmsResourceContent getResourceContent() {
		return resourceContent;
	}
	/**
	 * @param resourceContent the resourceContent to set
	 */
	public void setResourceContent(CmsResourceContent resourceContent) {
		this.resourceContent = resourceContent;
	}
	
	/**
	 * 获取内容
	 * @author lmiky
	 * @date 2014-1-5
	 * @return
	 */
	@Transient
	public String getContent() {
		if(resourceContent != null) {
			return resourceContent.getContent();
		}
		return "";
	}
	/**
	 * @return the directory
	 */
	@ManyToOne(fetch=FetchType.LAZY)  
    @JoinColumn(name="directory_id")  
	public CmsDirectory getDirectory() {
		return directory;
	}
	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(CmsDirectory directory) {
		this.directory = directory;
	}
}
