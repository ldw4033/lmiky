package com.lmiky.jdp.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.authority.pojo.Authority;
import com.lmiky.jdp.authority.service.AuthorityService;
import com.lmiky.jdp.database.dao.BaseDAO;
import com.lmiky.jdp.database.exception.DatabaseException;
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
	public List<Role> listAuthorizedOperator(String functionPath) throws ServiceException {
		try {
			String hql = "from Role Role where exists (select 1 from Authority Authority where Authority.functionPath = '" + functionPath
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
	public List<Role> listUnauthorizedOperator(String functionPath) throws ServiceException {
		try {
			String hql = "from Role Role where not exists (select 1 from Authority Authority where Authority.functionPath = '" + functionPath
					+ "' and Authority.operator = Role.id)";
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
	public void authorize(String functionPath, List<Authority> authorities) throws ServiceException {
		try {
			// 删除旧的数据
			String hql = "delete from Authority Authority  where Authority.functionPath = '" + functionPath + "'";
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

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.authority.service.AuthorityService#checkAuthority(java.lang.String, java.lang.Long)
	 */
	@Transactional(readOnly = true)
	public boolean checkAuthority(String functionPath, Long userId) throws ServiceException {
		try {
			StringBuffer hql = new StringBuffer();
			// 检查角色
			hql.append("select 1 from User User, Authority  Authority join User.roles Role where User.id = ").append(userId)
					.append(" and Role.id = Authority.operator and Authority.functionPath = '").append(functionPath).append("'");
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
