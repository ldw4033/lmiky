package com.lmiky.jdp.user.service;

import java.util.List;

import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.user.pojo.User;

/**
 * 角色业务
 * @author lmiky
 * @date 2013-5-14
 */
public interface RoleService extends BaseService {
	
	/**
	 * 获取角色用户
	 * @author lmiky
	 * @date 2013-5-14
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	public List<User> listRoleUser(Long roleId) throws ServiceException;
	
	/**
	 * 获取非角色用户
	 * @author lmiky
	 * @date 2013-5-14
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	public List<User> listNoRoleUser(Long roleId) throws ServiceException;
}
