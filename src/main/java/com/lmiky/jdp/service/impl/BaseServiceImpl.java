package com.lmiky.jdp.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.cms.resource.pojo.CmsResource;
import com.lmiky.jdp.database.dao.BaseDAO;
import com.lmiky.jdp.database.exception.DatabaseException;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.database.pojo.BasePojo;
import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;

/**
 * 业务基类
 * @author lmiky
 * @date 2013-4-15
 */
@Service("baseService")
public class BaseServiceImpl implements BaseService {
	protected BaseDAO baseDAO;

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#find(java.lang.Class, java.io.Serializable)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> T find(Class<T> pojoClass, Long id) throws ServiceException {
		try {
			return getDAO().find(pojoClass, id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#find(java.lang.Class, java.util.List)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> T find(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws ServiceException {
		try {
			return getDAO().find(pojoClass, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#find(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> T find(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException {
		try {
			return getDAO().find(pojoClass, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#find(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public <T extends BasePojo> T find(String hql) throws ServiceException {
		try {
			return (T)getDAO().find(hql);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#find(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public <T extends BasePojo> T find(String hql, Map<String, Object> params) throws ServiceException {
		try {
			return (T)getDAO().find(hql, params);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#save(com.lmiky.jdp.database.pojo.BasePojo)
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> void save(T pojo) throws ServiceException {
		try {
			if(pojo.getId() != null && pojo instanceof CmsResource) {	//对象已存在
//				PropertyFilter propertyFilter = new PropertyFilter();
//				propertyFilter.setCompareClass(CmsResourcePictureSnapshot.class);
//				propertyFilter.setCompareType(PropertyCompareType.EQ);
//				propertyFilter.setPropertyName("cmsResource.id");
//				propertyFilter.setPropertyValue(pojo.getId());
//				//删除旧的图片快照
//				delete(CmsResourcePictureSnapshot.class, propertyFilter);
				this.executeUpdate("delete from CmsResourcePictureSnapshot where cmsResource.id = " + pojo.getId());
			}
			getDAO().save(pojo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#save(java.util.List)
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> void save(List<T> pojos) throws ServiceException {
		try {
			getDAO().save(pojos);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#update(java.lang.Class, java.lang.Long, java.lang.String, java.lang.Object)
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, String propertyName, Object propertyValue) throws ServiceException {
		try {
			return getDAO().update(pojoClass, id, propertyName, propertyValue);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#update(java.lang.Class, java.lang.Long, java.util.Map)
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, Map<String, Object> params) throws ServiceException {
		try {
			return getDAO().update(pojoClass, id, params);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#delete(com.lmiky.jdp.database.pojo.BasePojo)
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> void delete(T pojo) throws ServiceException {
		try {
			getDAO().delete(pojo);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#delete(java.util.List)
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> void delete(List<T> pojos) throws ServiceException {
		try {
			getDAO().delete(pojos);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#delete(java.lang.Class, java.io.Serializable)
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long id) throws ServiceException {
		try {
			getDAO().delete(pojoClass, id);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#delete(java.lang.Class, java.lang.Long[])
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long[] ids) throws ServiceException {
		try {
			getDAO().delete(pojoClass, ids);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#delete(java.lang.Class, java.util.List)
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> int delete(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws ServiceException {
		try {
			return getDAO().delete(pojoClass, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#delete(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> int delete(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException {
		try {
			return getDAO().delete(pojoClass, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#delete(java.lang.String)
	 */
	@Transactional(rollbackFor={Exception.class})
	public int delete(String hql) throws ServiceException {
		try {
			return getDAO().delete(hql);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#delete(java.lang.String, java.util.Map)
	 */
	@Transactional(rollbackFor={Exception.class})
	public int delete(String hql, Map<String, Object> params) throws ServiceException {
		try {
			return getDAO().delete(hql, params);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(Class<T> pojoClass) throws ServiceException {
		try {
			return getDAO().list(pojoClass);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter, com.lmiky.jdp.database.model.Sort)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter propertyFilter, Sort sort) throws ServiceException {
		try {
			return getDAO().list(pojoClass, propertyFilter, sort);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, java.util.List, java.util.List)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts) throws ServiceException {
		try {
			return getDAO().list(pojoClass, propertyFilters, sorts);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException {
		try {
			return getDAO().list(pojoClass, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, com.lmiky.jdp.database.model.Sort[])
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort... sorts) throws ServiceException {
		try {
			return getDAO().list(pojoClass, sorts);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, int, int)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize) throws ServiceException {
		try {
			return getDAO().list(pojoClass, pageFirst, pageSize);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, java.util.List, java.util.List, int, int)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts, int pageFirst, int pageSize) throws ServiceException {
		try {
			return getDAO().list(pojoClass, propertyFilters, sorts, pageFirst, pageSize);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, int, int, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, PropertyFilter... propertyFilters) throws ServiceException {
		try {
			return getDAO().list(pojoClass, pageFirst, pageSize, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, int, int, com.lmiky.jdp.database.model.Sort[])
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, Sort... sorts) throws ServiceException {
		try {
			return getDAO().list(pojoClass, pageFirst, pageSize, sorts);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.String)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(String hql) throws ServiceException {
		try {
			return getDAO().list(hql);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.String, java.util.Map)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(String hql, Map<String, Object> params) throws ServiceException {
		try {
			return getDAO().list(hql, params);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.String, int, int)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(String hql, int pageFirst, int pageSize) throws ServiceException {
		try {
			return getDAO().list(hql, pageFirst, pageSize);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#list(java.lang.String, java.util.Map, int, int)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> List<T> list(String hql, Map<String, Object> params, int pageFirst, int pageSize) throws ServiceException {
		try {
			return getDAO().list(hql, params, pageFirst, pageSize);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#count(java.lang.Class)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> int count(Class<T> pojoClass) throws ServiceException {
		try {
			return getDAO().count(pojoClass);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#count(java.lang.Class, java.util.List)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> int count(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws ServiceException {
		try {
			return getDAO().count(pojoClass, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#count(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> int count(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException {
		try {
			return getDAO().count(pojoClass, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#count(java.lang.String)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> int count(String hql) throws ServiceException {
		try {
			return getDAO().count(hql);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#count(java.lang.String, java.util.Map)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> int count(String hql, Map<String, Object> params) throws ServiceException {
		try {
			return getDAO().count(hql, params);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#exist(java.lang.Class, java.util.List)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws ServiceException {
		try {
			return getDAO().exist(pojoClass, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#exist(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException {
		try {
			return getDAO().exist(pojoClass, propertyFilters);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#exist(java.lang.String)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> boolean exist(String hql) throws ServiceException {
		try {
			return getDAO().exist(hql);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#exist(java.lang.String, java.util.Map)
	 */
	@Transactional(readOnly=true)
	public <T extends BasePojo> boolean exist(String hql, Map<String, Object> params) throws ServiceException {
		try {
			return getDAO().exist(hql, params);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#executeQuery(java.lang.String)
	 */
	@Transactional(readOnly=true)
	public <X> List<X> executeQuery(String hql) throws ServiceException {
		try {
			return getDAO().executeQuery(hql);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#executeQuery(java.lang.String, int, int)
	 */
	@Transactional(readOnly=true)
	public <X> List<X> executeQuery(String hql, int pageFirst, int pageSize) throws ServiceException {
		try {
			return getDAO().executeQuery(hql, pageFirst, pageSize);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#executeQuery(java.lang.String, java.util.Map)
	 */
	@Transactional(readOnly=true)
	public <X> List<X> executeQuery(String hql, Map<String, Object> params) throws ServiceException {
		try {
			return getDAO().executeQuery(hql, params);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#executeQuery(java.lang.String, java.util.Map, int, int)
	 */
	@Transactional(readOnly=true)
	public <X> List<X> executeQuery(String hql, Map<String, Object> params, int pageFirst, int pageSize) throws ServiceException {
		try {
			return getDAO().executeQuery(hql, params, pageFirst, pageSize);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#executeUpdate(java.lang.String)
	 */
	@Transactional(rollbackFor={Exception.class})
	public int executeUpdate(String hql) throws ServiceException {
		try {
			return getDAO().executeUpdate(hql);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.BaseService#executeUpdate(java.lang.String, java.util.Map)
	 */
	@Transactional(rollbackFor={Exception.class})
	public int executeUpdate(String hql, Map<String, Object> params) throws ServiceException {
		try {
			return getDAO().executeUpdate(hql, params);
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * 获取DAO对象
	 * @author lmiky
	 * @date 2013-4-15
	 * @return
	 */
	public BaseDAO getDAO() {
		return baseDAO;
	}

	/**
	 * 注入DAO
	 * @author lmiky
	 * @date 2013-4-15
	 * @param dao
	 */
	@Resource(name="baseDAO")
	public void setDAO(BaseDAO dao) {
		this.baseDAO = dao;
	}
}