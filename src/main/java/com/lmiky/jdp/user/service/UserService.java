package com.lmiky.jdp.user.service;

import java.util.List;

import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.user.pojo.Role;
import com.lmiky.jdp.user.pojo.User;

/**
 * 用户管理
 * @author lmiky
 * @date 2013-4-24
 */
public interface UserService extends BaseService {
	
	/**
	 * 根据登陆账号获取用户
	 * @author lmiky
	 * @date 2013-4-24
	 * @param loginName
	 * @return
	 * @throws ServiceException
	 */
	public User findByLoginName(String loginName) throws ServiceException;
	
	/**
	 * 列出用户所拥有的角色
	 * @author lmiky
	 * @date 2013-12-10
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listUserRoles(Long userId) throws ServiceException;
	
	/**
	 * 列出非用户所拥有的角色
	 * @author lmiky
	 * @date 2013-12-10
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public List<Role> listNoUserRoles(Long userId) throws ServiceException;
}
