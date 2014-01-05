package com.lmiky.test.cms.resource;


import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.lmiky.cms.directory.pojo.CmsDirectory;
import com.lmiky.cms.resource.pojo.CmsResource;
import com.lmiky.cms.resource.pojo.CmsResourceContent;
import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.test.BaseTest;

/**
 * @author lmiky
 * @date 2013-5-7
 */
public class CmsResourceTest extends BaseTest {
	private BaseService baseService;

	@Test
	public void testSave() throws ServiceException {
		CmsResource resource = new CmsResource();
		resource.setTitle("标题常长长长长长长长长长长长长长长");
		resource.setSubtitle("副标题长长长长长长长长长长长长长长长长长长");
		resource.setAuthor("林大家");
		resource.setCreateTime(new Date());
		CmsResourceContent cmsResourceContent = new CmsResourceContent();
		cmsResourceContent.setContent("内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容");
		resource.setResourceContent(cmsResourceContent);
		CmsDirectory directory = new CmsDirectory();
		directory.setId(1l);
		resource.setDirectory(directory);
		baseService.save(resource);
	}
	
	@Test
	public void testSave2() throws ServiceException {
		for(int i=0; i< 100; i++) {
			testSave();
		}
	}

	/**
	 * @param baseService the baseService to set
	 */
	@Resource(name="baseService")
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
}
