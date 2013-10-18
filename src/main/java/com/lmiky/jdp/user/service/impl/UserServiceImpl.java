package com.lmiky.jdp.user.service.impl;

import org.springframework.stereotype.Service;

import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.service.impl.BaseServiceImpl;
import com.lmiky.jdp.user.pojo.User;
import com.lmiky.jdp.user.service.UserService;

/**
 * 用户管理业务实现类
 * @author lmiky
 * @date 2013-4-24
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.user.service.UserService#findByLoginName(java.lang.String)
	 */
	public User findByLoginName(String loginName) throws ServiceException {
		return find(User.class, new PropertyFilter("loginName", loginName, PropertyCompareType.EQ, User.class));
	}

}
