package com.lmiky.jdp.user.service;

import java.util.List;

import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.user.pojo.User;

/**
 * 用户组业务
 * @author lmiky
 * @date 2013-5-13
 */
public interface UserGroupService extends BaseService {
	
	/**
	 * 获取组用户
	 * @author lmiky
	 * @date 2013-5-13
	 * @param groupId
	 * @return
	 * @throws ServiceException
	 */
	public List<User> listGroupUser(Long groupId) throws ServiceException;
	
	/**
	 * 获取非组用户
	 * @author lmiky
	 * @date 2013-5-13
	 * @param groupId
	 * @return
	 * @throws ServiceException
	 */
	public List<User> listNoGroupUser(Long groupId) throws ServiceException;
}
