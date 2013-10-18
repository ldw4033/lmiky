package com.lmiky.jdp.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.service.impl.BaseServiceImpl;
import com.lmiky.jdp.user.pojo.Role;
import com.lmiky.jdp.user.pojo.User;
import com.lmiky.jdp.user.service.RoleService;;

/**
 * @author lmiky
 * @date 2013-5-14
 */
/**
 * 说明
 * @author lmiky
 * @date 2013-5-14
 */ 
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.user.service.RoleService#listRoleUser(java.lang.Long)
	 */
	@Transactional(readOnly=true)
	public List<User> listRoleUser(Long roleId) throws ServiceException {
		List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
		propertyFilters.add(new PropertyFilter("roles.id", roleId, PropertyCompareType.EQ, true, Role.class));
		List<Sort> sorts = new ArrayList<Sort>();
		sorts.add(new Sort("name", Sort.SORT_TYPE_ASC, User.class));
		return list(User.class, propertyFilters, sorts);
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.user.service.RoleService#listNoRoleUser(java.lang.Long)
	 */
	@Transactional(readOnly=true)
	public List<User> listNoRoleUser(Long roleId) throws ServiceException {
		String hql = "from User User where not exists (select 1 from User u join u.roles r where r.id = " + roleId + " and User.id = u.id) ORDER BY User.name ";
		return list(hql);
	}

}
