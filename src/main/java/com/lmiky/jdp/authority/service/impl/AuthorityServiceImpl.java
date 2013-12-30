package com.lmiky.jdp.authority.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.authority.pojo.Authority;
import com.lmiky.jdp.authority.pojo.AuthorityConfig;
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

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listAuthorizedOperator(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Role> listAuthorizedOperator(String authorityCode) throws ServiceException {
		try {
			String hql = "from Role Role where exists (select 1 from Authority Authority where Authority.authorityCode = '" + authorityCode
					+ "' and Authority.operator = Role.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listUnauthorizedOperator(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Role> listUnauthorizedOperator(String authorityCode) throws ServiceException {
		try {
			String hql = "from Role Role where not exists (select 1 from Authority Authority where Authority.authorityCode = '" + authorityCode
					+ "' and Authority.operator = Role.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listAuthorizedOperator(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Role> listAuthorizedOperator(String moduleType, String moduleCode) throws ServiceException {
		try {
			String hql = "from Role Role where exists (select 1 from AuthorityConfig AuthorityConfig where AuthorityConfig.moduleType = '" + moduleType + "' and AuthorityConfig.moduleCode='" + moduleCode
					+ "' and AuthorityConfig.operator = Role.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#listUnauthorizedOperator(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Role> listUnauthorizedOperator(String moduleType, String moduleCode) throws ServiceException {
		try {
			String hql = "from Role Role where not exists (select 1 from AuthorityConfig AuthorityConfig where AuthorityConfig.moduleType = '" + moduleType + "' and AuthorityConfig.moduleCode='" + moduleCode
					+ "' and AuthorityConfig.operator = Role.id)";
			return baseDAO.list(hql);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#authorize(java.lang.String, java.util.List)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void authorize(String authorityCode, List<Authority> authorities) throws ServiceException {
		try {
			// 删除旧的数据
			String hql = "delete from Authority Authority  where Authority.authorityCode = '" + authorityCode + "'";
			baseDAO.delete(hql);
			if (authorities == null || authorities.isEmpty()) {
				return;
			}
			for (Authority a : authorities) {
				baseDAO.save(a);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#authorize(java.util.List, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void authorize(List<AuthorityConfig> authorityConfigs, String authorityCode) throws ServiceException {
		//删除配置
		String hql = "delete from  AuthorityConfig AuthorityConfig";
		AuthorityConfig authorityConfig = null;
		if(!authorityConfigs.isEmpty()) {
			authorityConfig = authorityConfigs.get(0);
			hql += " where AuthorityConfig.moduleType = '" + authorityConfig.getModuleType() + "' and AuthorityConfig.moduleCode='" + authorityConfig.getModuleCode() + "'";
		}
		baseDAO.delete(hql);
		baseDAO.save(authorityConfigs);
		
		//修改配置
		//hql = "delete from Authority Authority  where Authority.authorityCode = '" + authorityCode + "'";
		//baseDAO.delete(hql);
		if(authorityConfig != null) {
			String moduleType = authorityConfig.getModuleType();
			String moduleCode = authorityConfig.getModuleCode();
			List<Function> functions = new ArrayList<Function>();
			//选择整个系统
			if(AuthorityConfig.MODULETYPE_SYSTEM.equals(moduleType)) {
				functions = baseDAO.list(Function.class);
			} else if(AuthorityConfig.MODULETYPE_GROUP.equals(moduleType)) {
				functions = baseDAO.list("select Function from Function Function join Function.modules module join module.group group where group.path = '" + moduleCode + "'");
			}
			//授权
			List<Authority> authorities = new ArrayList<Authority>();
			Authority authority = null;
			for(AuthorityConfig ac : authorityConfigs) {
				for(Function function : functions) {
					authority = new Authority();
					authority.setFunctionPath(function.getAuthorityCode());
					authority.setOperator(ac.getOperator());
					authorities.add(authority);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#checkAuthority(java.lang.String, java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public boolean checkAuthority(String authorityCode, Long userId) throws ServiceException {
		try {
			StringBuffer hql = new StringBuffer();
			// 检查角色
			hql.append("select 1 from User User, Authority  Authority join User.roles Role where User.id = ").append(userId)
					.append(" and Role.id = Authority.operator and Authority.authorityCode = '").append(authorityCode).append("'");
			List<Object[]> result = baseDAO.executeQuery(hql.toString());
			if (result.size() > 0) {
				return true;
			}
		} catch (DatabaseException e) {
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
	@Resource(name = "baseDAO")
	public void setDAO(BaseDAO dao) {
		this.baseDAO = dao;
	}

}
