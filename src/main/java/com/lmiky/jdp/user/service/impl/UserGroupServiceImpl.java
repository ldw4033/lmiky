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
import com.lmiky.jdp.user.pojo.User;
import com.lmiky.jdp.user.pojo.UserGroup;
import com.lmiky.jdp.user.service.UserGroupService;

/**
 * @author lmiky
 * @date 2013-5-13
 */
@Service("userGroupService")
public class UserGroupServiceImpl extends BaseServiceImpl implements UserGroupService {

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.user.service.UserGroupService#listGroupUser(java.lang.Long)
	 */
	@Transactional(readOnly=true)
	public List<User> listGroupUser(Long groupId) throws ServiceException {
		List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
		propertyFilters.add(new PropertyFilter("groups.id", groupId, PropertyCompareType.EQ, true, UserGroup.class));
		List<Sort> sorts = new ArrayList<Sort>();
		sorts.add(new Sort("name", Sort.SORT_TYPE_ASC, User.class));
		return list(User.class, propertyFilters, sorts);
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.user.service.UserGroupService#listNoGroupUser(java.lang.Long)
	 */
	@Transactional(readOnly=true)
	public List<User> listNoGroupUser(Long groupId) throws ServiceException {
		String hql = "from User User where not exists (select 1 from User u join u.groups g where g.id = " + groupId + " and User.id = u.id) ORDER BY User.name ";
		return list(hql);
	}

}
