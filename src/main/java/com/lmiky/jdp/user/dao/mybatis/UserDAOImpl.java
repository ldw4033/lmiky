package com.lmiky.jdp.user.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lmiky.jdp.database.dao.mybatis.BaseDAOImpl;
import com.lmiky.jdp.database.exception.DatabaseException;
import com.lmiky.jdp.system.menu.pojo.LatelyOperateMenu;
import com.lmiky.jdp.user.dao.UserDAO;
import com.lmiky.jdp.user.pojo.Operator;
import com.lmiky.jdp.user.pojo.Role;
import com.lmiky.jdp.user.pojo.User;

/**
 * @author lmiky
 * @date 2014年8月13日 下午10:52:30
 */
@Repository("userDAO")
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.user.dao.UserDAO#listNoUserRoles(java.lang.Long)
	 */
	@Override
	public List<Role> listNoUserRoles(Long userId) throws DatabaseException {
		try {
			Map<String, Object> params = generateParameterMap(LatelyOperateMenu.class);
			params.put("userId", userId);
			return sqlSessionTemplate.selectList(Role.class.getName() + ".listNoUserRoles", params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.user.dao.UserDAO#listNoRoleUser(java.lang.Long)
	 */
	@Override
	public List<Operator> listNoRoleUser(Long roleId) throws DatabaseException {
		try {
			Map<String, Object> params = generateParameterMap(LatelyOperateMenu.class);
			params.put("roleId", roleId);
			return sqlSessionTemplate.selectList(Operator.class.getName() + ".listNoRoleUser", params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.user.dao.UserDAO#deleteUserRole(java.lang.Long)
	 */
	@Override
	public void deleteUserRole(Long userId) throws DatabaseException {
		try {
			sqlSessionTemplate.delete(User.class.getName() + ".deleteUserRole", userId);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.user.dao.UserDAO#addUserRole(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void addUserRole(Long userId, Long roleId) throws DatabaseException {
		try {
			Map<String, Object> params = generateParameterMap(User.class);
			params.put("userId", userId);
			params.put("roleId", roleId);
			sqlSessionTemplate.delete(User.class.getName() + ".addUserRole", params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
