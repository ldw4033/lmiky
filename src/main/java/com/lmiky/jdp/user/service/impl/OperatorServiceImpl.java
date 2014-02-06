package com.lmiky.jdp.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.user.pojo.Operator;
import com.lmiky.jdp.user.pojo.Role;
import com.lmiky.jdp.user.service.OperatorService;

/**
 * 
 * @author lmiky
 * @date 2014-2-6
 */
@Service("operatorService")
public class OperatorServiceImpl extends UserServiceImpl implements OperatorService {

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.user.service.UserService#listUserRoles(java.lang.Long)
	 */
	@Transactional(readOnly=true)
	public List<Role> listUserRoles(Long userId) throws ServiceException {
		List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
		propertyFilters.add(new PropertyFilter("users.id", userId, PropertyCompareType.EQ, true, Operator.class));
		List<Sort> sorts = new ArrayList<Sort>();
		sorts.add(new Sort("name", Sort.SORT_TYPE_ASC, Role.class));
		return list(Role.class, propertyFilters, sorts);
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.user.service.UserService#listNoUserRoles(java.lang.Long)
	 */
	@Transactional(readOnly=true)
	public List<Role> listNoUserRoles(Long userId) throws ServiceException {
		String hql = "from Role Role where not exists (select 1 from Role r join r.users u where u.id = " + userId + " and Role.id = r.id) ORDER BY Role.name ";
		return list(hql);
	}

}
