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
import com.lmiky.jdp.user.pojo.User;
import com.lmiky.jdp.user.pojo.UserGroup;

/**
 * @author lmiky
 * @date 2013-5-20
 */
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
	private BaseDAO baseDAO;
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listAuthorizedUser(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<User> listAuthorizedUser(String moduleType, Long moduleId, Long functionId) throws ServiceException {
		try {
			String hql = "from User User where exists (select 1 from Authority Authority where Authority.moduleType ='" + moduleType
					+ "' and Authority.operatorType = '" + Authority.OPERATORTYPE_USER + "' and Authority.moduleId = " + moduleId
					+ " and Authority.functionId = " + functionId + " and Authority.operator = User.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listUnauthorizedUser(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<User> listUnauthorizedUser(String moduleType, Long moduleId, Long functionId) throws ServiceException {
		try {
			String hql = "from User User where not exists (select 1 from Authority Authority where Authority.moduleType ='" + moduleType
					+ "' and Authority.operatorType = '" + Authority.OPERATORTYPE_USER + "' and Authority.moduleId = " + moduleId
					+ " and Authority.functionId = " + functionId + " and Authority.operator = User.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listAuthorizedUserGroup(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserGroup> listAuthorizedUserGroup(String moduleType, Long moduleId, Long functionId) throws ServiceException {
		try {
			String hql = "from UserGroup UserGroup where exists (select 1 from Authority Authority where Authority.moduleType ='" + moduleType
					+ "' and Authority.operatorType = '" + Authority.OPERATORTYPE_USERGROUP + "' and Authority.moduleId = " + moduleId
					+ " and Authority.functionId = " + functionId + " and Authority.operator = UserGroup.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listUnauthorizedUserGroup(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UserGroup> listUnauthorizedUserGroup(String moduleType, Long moduleId, Long functionId) throws ServiceException {
		try {
			String hql = "from UserGroup UserGroup where not exists (select 1 from Authority Authority where Authority.moduleType ='" + moduleType
					+ "' and Authority.operatorType = '" + Authority.OPERATORTYPE_USERGROUP + "' and Authority.moduleId = " + moduleId
					+ " and Authority.functionId = " + functionId + " and Authority.operator = UserGroup.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

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
			hql.append("select count(*) from User User, Authority  Authority join User.roles Role where User.id = ").append(userId).append(" and Role.id = Authority.operator and (");
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
			int count = baseDAO.count(hql.toString());
			if(count > 0) {
				return true;
			}
			//检查用户组
			hql = new StringBuffer();
			hql.append("select count(*) from User User, Authority  Authority join User.groups UserGroup where User.id = ").append(userId).append(" and UserGroup.id = Authority.operator and (");
			hql.append(" (Authority.moduleType = '").append(Authority.MODULETYPE_SYSTEM).append("' ");	//系统
			hql.append(" and Authority.moduleId = ").append(0).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_USERGROUP).append("' ");
			hql.append(" and Authority.functionId = ").append(Function.DEFAULT_FUNCTIONID_ADMIN).append(")");
			hql.append(" or ");
			hql.append(" (Authority.moduleType = '").append(Authority.MODULETYPE_GROUP).append("' ");//模块组
			hql.append(" and Authority.moduleId = ").append(moduleGroupId).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_USERGROUP).append("' ");
			hql.append(" and Authority.functionId = ").append(Function.DEFAULT_FUNCTIONID_ADMIN).append(")");
			hql.append(" or ");
			hql.append(" ( Authority.moduleType = '").append(Authority.MODULETYPE_MODULE).append("' ");//模块
			hql.append(" and Authority.moduleId = ").append(moduleId).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_USERGROUP).append("' ");
			hql.append(" and Authority.functionId = ").append(Function.DEFAULT_FUNCTIONID_ADMIN).append(")");
			hql.append(" or ");
			hql.append(" ( Authority.moduleType = '").append(Authority.MODULETYPE_FUNCTION).append("' ");//功能
			hql.append(" and Authority.moduleId = ").append(moduleId).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_USERGROUP).append("' ");
			hql.append(" and Authority.functionId = ").append(functionId).append(")");
			hql.append(") ");
			count = baseDAO.count(hql.toString());
			if(count > 0) {
				return true;
			}
			//检查用户
			hql = new StringBuffer();
			hql.append("select count(*) from User User, Authority  Authority where User.id = ").append(userId).append(" and User.id = Authority.operator and (");
			hql.append(" (Authority.moduleType = '").append(Authority.MODULETYPE_SYSTEM).append("' ");
			hql.append(" and Authority.moduleId = ").append(0).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_USER).append("' ");
			hql.append(" and Authority.functionId = ").append(Function.DEFAULT_FUNCTIONID_ADMIN).append(")");
			hql.append(" or ");
			hql.append(" (Authority.moduleType = '").append(Authority.MODULETYPE_GROUP).append("' ");
			hql.append(" and Authority.moduleId = ").append(moduleGroupId).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_USER).append("' ");
			hql.append(" and Authority.functionId = ").append(Function.DEFAULT_FUNCTIONID_ADMIN).append(")");
			hql.append(" or ");
			hql.append(" ( Authority.moduleType = '").append(Authority.MODULETYPE_MODULE).append("' ");
			hql.append(" and Authority.moduleId = ").append(moduleId).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_USER).append("' ");
			hql.append(" and Authority.functionId = ").append(Function.DEFAULT_FUNCTIONID_ADMIN).append(")");
			hql.append(" or ");
			hql.append(" ( Authority.moduleType = '").append(Authority.MODULETYPE_FUNCTION).append("' ");
			hql.append(" and Authority.moduleId = ").append(moduleId).append(" and Authority.operatorType = '").append(Authority.OPERATORTYPE_USER).append("' ");
			hql.append(" and Authority.functionId = ").append(functionId).append(")");
			hql.append(") ");
			count = baseDAO.count(hql.toString());
			if(count > 0) {
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
