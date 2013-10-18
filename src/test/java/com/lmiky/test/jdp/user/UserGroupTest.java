package com.lmiky.test.jdp.user;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.user.pojo.User;
import com.lmiky.jdp.user.service.UserGroupService;
import com.lmiky.test.BaseTest;

/**
 * @author lmiky
 * @date 2013-5-13
 */
public class UserGroupTest extends BaseTest {
	private UserGroupService userGroupService;

	@Test
	public void testGetGroupUser() throws ServiceException {
		List<User> users = userGroupService.listGroupUser(1l);
		System.out.println(users.get(0));
	}
	
	@Test
	public void testGetNoGroupUser() throws ServiceException {
		List<User> users = userGroupService.listNoGroupUser(1l);
		System.out.println(users.size());
	}
	
	
	/**
	 * @return the baseService
	 */
	public BaseService getBaseService() {
		return userGroupService;
	}

	/**
	 * @param baseService the baseService to set
	 */
	@Resource(name="userGroupService")
	public void setBaseService(UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}
}
