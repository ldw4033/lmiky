package com.lmiky.jdp.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.authority.pojo.Authority;
import com.lmiky.jdp.authority.service.AuthorityService;
import com.lmiky.jdp.database.dao.BaseDAO;
import com.lmiky.jdp.database.exception.DatabaseException;
import com.lmiky.jdp.module.pojo.Function;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.user.pojo.Role;

/**
 * @author lmiky
 * @date 2013-5-20
 */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
	private BaseDAO baseDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listAuthorizedRole(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Role> listAuthorizedRole(String moduleType, Long moduleId, Long functionId) throws ServiceException {
		try {
			String hql = "from Role Role where exists (select 1 from Authority Authority where Authority.moduleType ='" + moduleType
					+ "' and Authority.operatorType = '" + Authority.OPERATORTYPE_ROLE + "' and Authority.moduleId = " + moduleId
					+ " and Authority.functionId = " + functionId + " and Authority.operator = Role.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listUnauthorizedRole(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Role> listUnauthorizedRole(String moduleType, Long moduleId, Long functionId) throws ServiceException {
		try {
			String hql = "from Role Role where not exists (select 1 from Authority Authority where Authority.moduleType ='" + moduleType
					+ "' and Authority.operatorType = '" + Authority.OPERATORTYPE_ROLE + "' and Authority.moduleId = " + moduleId
					+ " and Authority.functionId = " + functionId + " and Authority.operator = Role.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#save(java.util.List)
	 */
	@Override
	@Transactional(rollbackFor={Exception.class})
	public void save(List<Authority> authorities) throws ServiceException {
		try {
			if (authorities == null || authorities.isEmpty()) {
				return;
			}
			Authority authority = authorities.get(0);
			String hql = "delete from Authority Authority  where Authority.moduleType ='" + authority.getModuleType()
					+ "' and Authority.operatorType = '" + authority.getOperatorType() + "' and Authority.moduleId = " + authority.getModuleId() + " and Authority.functionId = " + authority.getFunctionId();
			baseDAO.delete(hql);
			for(Authority a : authorities) {
				baseDAO.save(a);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#checkAuthority(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public boolean checkAuthority(Long moduleGroupId, Long moduleId, Long functionId, Long userId) throws ServiceException {
		try {
			StringBuffer hql = new StringBuffer();
			//检查角色
			hql.append("select 1 from User User, Authority  Authority join User.roles Role where User.id = ").append(userId).append(" and Role.id = Authority.operator and (");
			hql.append(" (Authority.moduleType = '").append(Authority.MODULETYPE_SYSTEM).append("' ");
			hql.append(" and Authority.moduleId = ").append(0).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_ROLE).append("' ");
			hql.append(" and Authority.functionId = ").append(Function.DEFAULT_FUNCTIONID_ADMIN).append(")");
			hql.append(" or ");
			hql.append(" (Authority.moduleType = '").append(Authority.MODULETYPE_GROUP).append("' ");
			hql.append(" and Authority.moduleId = ").append(moduleGroupId).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_ROLE).append("' ");
			hql.append(" and Authority.functionId = ").append(Function.DEFAULT_FUNCTIONID_ADMIN).append(")");
			hql.append(" or ");
			hql.append(" ( Authority.moduleType = '").append(Authority.MODULETYPE_MODULE).append("' ");
			hql.append(" and Authority.moduleId = ").append(moduleId).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_ROLE).append("' ");
			hql.append(" and Authority.functionId = ").append(Function.DEFAULT_FUNCTIONID_ADMIN).append(")");
			hql.append(" or ");
			hql.append(" ( Authority.moduleType = '").append(Authority.MODULETYPE_FUNCTION).append("' ");
			hql.append(" and Authority.moduleId = ").append(moduleId).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_ROLE).append("' ");
			hql.append(" and Authority.functionId = ").append(functionId).append(")");
			hql.append(") ");
			List<Object[]> result= baseDAO.executeQuery(hql.toString());
			if(result.size() > 0) {
				return true;
			}
		} catch(DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
		return false;
	}
	
	/**
	 * 注入DAO
	 * @author lmiky
	 * @date 2013-5-24
	 * @param dao
	 */
	@Resource(name="baseDAO")
	public void setDAO(BaseDAO dao) {
		this.baseDAO = dao;
	}
}
