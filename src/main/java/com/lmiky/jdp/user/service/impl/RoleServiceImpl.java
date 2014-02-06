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
import com.lmiky.jdp.user.pojo.Operator;
import com.lmiky.jdp.user.pojo.Role;
import com.lmiky.jdp.user.service.RoleService;

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
	public List<Operator> listRoleUser(Long roleId) throws ServiceException {
		List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
		propertyFilters.add(new PropertyFilter("roles.id", roleId, PropertyCompareType.EQ, true, Role.class));
		List<Sort> sorts = new ArrayList<Sort>();
		sorts.add(new Sort("name", Sort.SORT_TYPE_ASC, Operator.class));
		return list(Operator.class, propertyFilters, sorts);
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.user.service.RoleService#listNoRoleUser(java.lang.Long)
	 */
	@Transactional(readOnly=true)
	public List<Operator> listNoRoleUser(Long roleId) throws ServiceException {
		String hql = "from Operator Operator where not exists (select 1 from Operator u join u.roles r where r.id = " + roleId + " and Operator.id = u.id) ORDER BY Operator.name ";
		return list(hql);
	}

}
