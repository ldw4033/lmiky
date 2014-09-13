package com.lmiky.jdp.database.dao.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Table;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.lmiky.jdp.database.dao.BaseDAO;
import com.lmiky.jdp.database.exception.DatabaseException;
import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 基础dao
 * @author lmiky
 * @date 2013-4-15
 */
@Repository("baseDAO")
public class BaseDAOImpl implements BaseDAO {

	// 参数字段
	/**
	 * 过滤条件
	 */
	protected static final String PARAM_NAME_FILTERS = "filters";
	/**
	 * 排序
	 */
	protected static final String PARAM_NAME_SORTS = "sorts";
	/**
	 * 分页：起始位置
	 */
	protected static final String PARAM_NAME_PAGE_FIRST = "pageFirst";
	/**
	 * 分页：查询记录数
	 */
	protected static final String PARAM_NAME_PAGE_SIZE = "pageSize";

	// 映射文件命名空间
	/**
	 * 公共映射
	 */
	protected static final String MAPPER_NAMESPACE_COMMON = "common";

	// sql方法名
	/**
	 * sql方法名：查询
	 */
	protected static final String SQLNAME_FIND = "find";

	/**
	 * sql方法名：添加
	 */
	protected static final String SQLNAME_ADD = "add";

	/**
	 * sql方法名：修改
	 */
	protected static final String SQLNAME_UPDATE = "update";
	
	/**
	 * sql方法名：删除
	 */
	protected static final String SQLNAME_DELETE = "delete";
	
	/**
	 * sql方法名：根据ID批量删除
	 */
	protected static final String SQLNAME_DELETE_BATCH_BY_IDS = "batchDeleteByIds";

	/**
	 * sql方法名：获取列表
	 */
	protected static final String SQLNAME_LIST = "list";

	/**
	 * sql方法名：统计
	 */
	protected static final String SQLNAME_COUNT = "count";

	// 查询方法名后缀
	/**
	 * 查询方法名后缀：查询
	 */
	protected static final String SQLNAME_SUFFIX_FIND = "." + SQLNAME_FIND;
	/**
	 * 查询方法名后缀：添加
	 */
	protected static final String SQLNAME_SUFFIX_ADD = "." + SQLNAME_ADD;
	/**
	 * 查询方法名后缀：添加
	 */
	protected static final String SQLNAME_SUFFIX_UPDATE = "." + SQLNAME_UPDATE;
	/**
	 * 查询方法名后缀：删除
	 */
	protected static final String SQLNAME_SUFFIX_DELETE = "." + SQLNAME_DELETE;
	/**
	 * 查询方法名后缀：根据ID批量删除
	 */
	protected static final String SQLNAME_SUFFIX_DELETE_BATCH_BY_IDS = "." + SQLNAME_DELETE_BATCH_BY_IDS;
	/**
	 * 查询方法名后缀：获取列表
	 */
	protected static final String SQLNAME_SUFFIX_LIST = "." + SQLNAME_LIST;
	/**
	 * 查询方法名后缀：统计
	 */
	protected static final String SQLNAME_SUFFIX_COUNT = "." + SQLNAME_COUNT;

	protected SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 对象对应的数据库表名
	 */
	protected Map<Class<?>, String> pojoTableNames = new HashMap<Class<?>, String>();

	/**
	 * 获取实体类的表名
	 * @author lmiky
	 * @date 2014年8月25日 下午10:12:01
	 * @param pojoClass
	 * @return
	 * @throws DatabaseException
	 */
	protected String getPojoTabelName(Class<?> pojoClass) throws DatabaseException {
		// 先读缓存
		String cacheTableName = pojoTableNames.get(pojoClass);
		if (cacheTableName != null) {
			return cacheTableName;
		}
		// 根据反射获取
		Table annotation = pojoClass.getAnnotation(Table.class);
		// 没有对应的注解
		if (annotation == null) {
			throw new DatabaseException(pojoClass.getName() + " is not a db pojo!");
		}
		cacheTableName = annotation.name();
		// 放入缓存
		pojoTableNames.put(pojoClass, cacheTableName);
		return cacheTableName;
	}

	/**
	 * 生成参数
	 * @author lmiky
	 * @date 2014年9月9日 上午10:23:01
	 * @param pojoClass
	 * @return
	 */
	protected <T extends BasePojo> Map<String, Object> generateParameterMap(Class<T> pojoClass) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", getPojoTabelName(pojoClass));
		params.put("pojoName", pojoClass.getSimpleName());
		return params;
	}

	/**
	 * 设置排序参数
	 * @author lmiky
	 * @date 2014年9月13日 下午5:33:48
	 * @param parameterMap
	 * @param sorts
	 */
	protected void setSortParameter(Map<String, Object> parameterMap, List<Sort> sorts) {
		parameterMap.put(PARAM_NAME_SORTS, sorts);
	}
	/**
	 * 设置排序参数
	 * @author lmiky
	 * @date 2014年9月13日 下午5:33:58
	 * @param parameterMap
	 * @param sort
	 */
	protected void setSortParameter(Map<String, Object> parameterMap, Sort sort) {
		List<Sort> sorts = new ArrayList<Sort>();
		sorts.add(sort);
		setSortParameter(parameterMap, sorts);
	}
	
	/**
	 * 设置分页参数
	 * @author lmiky
	 * @date 2014年9月13日 下午5:37:58
	 * @param parameterMap
	 * @param pageFirst	起始位置，如果不指定，则设置为null
	 * @param pageSize 获取记录数，如果不指定，则设置为null
	 */
	protected void setPageParameter(Map<String, Object> parameterMap, Integer pageFirst, Integer pageSize) {
		parameterMap.put(PARAM_NAME_PAGE_FIRST, pageFirst);
		parameterMap.put(PARAM_NAME_PAGE_SIZE, pageSize);
	}
	
	/**
	 * 设置排序参数
	 * @author lmiky
	 * @date 2014年9月13日 下午5:34:02
	 * @param parameterMap
	 * @param propertyName
	 * @param sortType
	 * @param sortClass
	 */
	protected void setSortParameter(Map<String, Object> parameterMap, String propertyName, String sortType, Class<?> sortClass) {
		Sort sort = new Sort();
		sort.setPropertyName(propertyName);
		sort.setSortType(sortType);
		sort.setSortClass(sortClass);
		setSortParameter(parameterMap, sort);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, java.lang.Long)
	 */
	@Override
	public <T extends BasePojo> T find(Class<T> pojoClass, Long id) throws DatabaseException {
		try {
			PropertyFilter propertyFilter = new PropertyFilter();
			propertyFilter.setCompareClass(pojoClass);
			propertyFilter.setCompareType(PropertyCompareType.EQ);
			propertyFilter.setPropertyName(BasePojo.POJO_FIELD_NAME_ID + "");
			propertyFilter.setPropertyValue(id);
			return find(pojoClass, propertyFilter);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, String propertyName, Object propertyValue) throws DatabaseException {
		try {
			PropertyFilter propertyFilter = new PropertyFilter();
			propertyFilter.setCompareClass(pojoClass);
			propertyFilter.setCompareType(PropertyCompareType.EQ);
			propertyFilter.setPropertyName(propertyName);
			propertyFilter.setPropertyValue(propertyValue);
			return find(pojoClass, propertyFilter);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, java.util.List)
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		try {
			Map<String, Object> params = generateParameterMap(pojoClass);
			params.put(PARAM_NAME_FILTERS, propertyFilters);
			return sqlSessionTemplate.selectOne(pojoClass.getName() + SQLNAME_SUFFIX_FIND, params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		try {
			return find(pojoClass, Arrays.asList(propertyFilters));
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#save(com.lmiky.jdp.database.pojo.BasePojo)
	 */
	@Override
	public <T extends BasePojo> void save(T pojo) throws DatabaseException {
		try {
			if (pojo.getId() == null) {
				add(pojo);
			} else {
				update(pojo);
			}
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#save(java.util.List)
	 */
	@Override
	public <T extends BasePojo> void save(List<T> pojos) throws DatabaseException {
		try {
			for (T pojo : pojos) {
				save(pojo);
			}
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#add(com.lmiky.jdp.database.pojo.BasePojo)
	 */
	@Override
	public <T extends BasePojo> void add(T pojo) throws DatabaseException {
		try {
			sqlSessionTemplate.insert(pojo.getClass().getName() + SQLNAME_SUFFIX_ADD, pojo);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#add(java.util.List)
	 */
	@Override
	public <T extends BasePojo> void add(List<T> pojos) throws DatabaseException {
		try {
			for (T pojo : pojos) {
				add(pojo);
			}
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#update(com.lmiky.jdp.database.pojo.BasePojo)
	 */
	@Override
	public <T extends BasePojo> void update(T pojo) throws DatabaseException {
		try {
			sqlSessionTemplate.update(pojo.getClass().getName() + SQLNAME_SUFFIX_UPDATE, pojo);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#update(java.util.List)
	 */
	@Override
	public <T extends BasePojo> void update(List<T> pojos) throws DatabaseException {
		try {
			for (T pojo : pojos) {
				update(pojo);
			}
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#update(java.lang.Class, java.lang.Long, java.lang.String, java.lang.Object)
	 */
	@Override
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, String propertyName, Object propertyValue) throws DatabaseException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(propertyName, propertyValue);
			return update(pojoClass, id, params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#update(java.lang.Class, java.lang.Long, java.util.Map)
	 */
	@Override
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, Map<String, Object> params) throws DatabaseException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put(BasePojo.POJO_FIELD_NAME_ID, id);
			return update(pojoClass, condition, params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#update(java.lang.Class, java.util.Map, java.util.Map)
	 */
	@Override
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Map<String, Object> condition, Map<String, Object> updateValue) throws DatabaseException {
		try {
			Map<String, Object> params = generateParameterMap(pojoClass);
			params.put("condition", condition);
			params.put("updateValue", updateValue);
			return sqlSessionTemplate.update(MAPPER_NAMESPACE_COMMON + SQLNAME_SUFFIX_UPDATE, params) > 0;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#update(java.lang.Class, java.lang.String, java.lang.Object, java.lang.String, java.lang.Object)
	 */
	@Override
	public <T extends BasePojo> boolean update(Class<T> pojoClass, String conditionFieldName, Object conditionFieldValue, String updateFieldName, Object updateFieldValue) throws DatabaseException {
		try {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put(conditionFieldName, conditionFieldValue);
			Map<String, Object> updateValue = new HashMap<String, Object>();
			condition.put(updateFieldName, updateFieldValue);
			return update(pojoClass, condition, updateValue);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(com.lmiky.jdp.database.pojo.BasePojo)
	 */
	@Override
	public <T extends BasePojo> void delete(T pojo) throws DatabaseException {
		try {
			delete(pojo.getClass(), pojo.getId());
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.util.List)
	 */
	@Override
	public <T extends BasePojo> void delete(List<T> pojos) throws DatabaseException {
		try {
			if (pojos.isEmpty()) {
				return;
			}
			Long[] ids = new Long[pojos.size()];
			for (int i = 0; i < pojos.size(); i++) {
				ids[i] = pojos.get(i).getId();
			}
			delete(pojos.get(0).getClass(), ids);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.Class, java.lang.Long)
	 */
	@Override
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long id) throws DatabaseException {
		try {
			PropertyFilter propertyFilter = new PropertyFilter();
			propertyFilter.setCompareClass(pojoClass);
			propertyFilter.setCompareType(PropertyCompareType.EQ);
			propertyFilter.setPropertyName(BasePojo.POJO_FIELD_NAME_ID + "");
			propertyFilter.setPropertyValue(id);
			delete(pojoClass, propertyFilter);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.Class, java.lang.Long[])
	 */
	@Override
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long[] ids) throws DatabaseException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tableName", getPojoTabelName(pojoClass));
			params.put("ids", ids);
			sqlSessionTemplate.delete(MAPPER_NAMESPACE_COMMON + SQLNAME_SUFFIX_DELETE_BATCH_BY_IDS, params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	@Override
	public <T extends BasePojo> int delete(Class<T> pojoClass, String propertyName, Object propertyValue) throws DatabaseException {
		try {
			PropertyFilter propertyFilter = new PropertyFilter();
			propertyFilter.setCompareClass(pojoClass);
			propertyFilter.setCompareType(PropertyCompareType.EQ);
			propertyFilter.setPropertyName(propertyName);
			propertyFilter.setPropertyValue(propertyValue);
			return delete(pojoClass, propertyFilter);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.Class, java.util.List)
	 */
	@Override
	public <T extends BasePojo> int delete(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		try {
			Map<String, Object> params = generateParameterMap(pojoClass);
			params.put(PARAM_NAME_FILTERS, propertyFilters);
			return sqlSessionTemplate.delete(MAPPER_NAMESPACE_COMMON + SQLNAME_SUFFIX_DELETE, params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Override
	public <T extends BasePojo> int delete(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		try {
			return delete(pojoClass, Arrays.asList(propertyFilters));
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class)
	 */
	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass) throws DatabaseException {
		try {
			return list(pojoClass, 0, Integer.MAX_VALUE);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter, com.lmiky.jdp.database.model.Sort)
	 */
	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter propertyFilter, Sort sort) throws DatabaseException {
		try {
			List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
			if (propertyFilter != null) {
				propertyFilters.add(propertyFilter);
			}
			List<Sort> sorts = new ArrayList<Sort>();
			if (sort != null) {
				sorts.add(sort);
			}
			return list(pojoClass, propertyFilters, sorts);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, java.util.List, java.util.List)
	 */
	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts) throws DatabaseException {
		try {
			return list(pojoClass, propertyFilters, sorts, 0, Integer.MAX_VALUE);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		try {
			return list(pojoClass, 0, Integer.MAX_VALUE, propertyFilters);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, com.lmiky.jdp.database.model.Sort[])
	 */
	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort... sorts) throws DatabaseException {
		try {
			return list(pojoClass, 0, Integer.MAX_VALUE, sorts);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, int, int)
	 */
	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize) throws DatabaseException {
		try {
			return list(pojoClass, null, null, pageFirst, pageSize);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, java.util.List, java.util.List, int, int)
	 */
	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts, int pageFirst, int pageSize) throws DatabaseException {
		try {
			Map<String, Object> params = generateParameterMap(pojoClass);
			params.put(PARAM_NAME_FILTERS, propertyFilters);
			setSortParameter(params, sorts);
			setPageParameter(params, pageFirst, pageSize);
			return sqlSessionTemplate.selectList(pojoClass.getName() + SQLNAME_SUFFIX_LIST, params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, int, int, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, PropertyFilter... propertyFilters) throws DatabaseException {
		try {
			return list(pojoClass, Arrays.asList(propertyFilters), null, pageFirst, pageSize);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, int, int, com.lmiky.jdp.database.model.Sort[])
	 */
	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, Sort... sorts) throws DatabaseException {
		try {
			return list(pojoClass, null, Arrays.asList(sorts), pageFirst, pageSize);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#count(java.lang.Class)
	 */
	@Override
	public <T extends BasePojo> int count(Class<T> pojoClass) throws DatabaseException {
		try {
			return count(pojoClass, new ArrayList<PropertyFilter>());
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#count(java.lang.Class, java.util.List)
	 */
	@Override
	public <T extends BasePojo> int count(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		try {
			Map<String, Object> params = generateParameterMap(pojoClass);
			if (propertyFilters != null) {
				params.put(PARAM_NAME_FILTERS, propertyFilters);
			}
			return sqlSessionTemplate.selectOne(MAPPER_NAMESPACE_COMMON + SQLNAME_SUFFIX_COUNT, params);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#count(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	@Override
	public <T extends BasePojo> int count(Class<T> pojoClass, String propertyName, Object propertyValue) throws DatabaseException {
		try {
			PropertyFilter propertyFilter = new PropertyFilter();
			propertyFilter.setCompareClass(pojoClass);
			propertyFilter.setCompareType(PropertyCompareType.EQ);
			propertyFilter.setPropertyName(propertyName);
			propertyFilter.setPropertyValue(propertyValue);
			return count(pojoClass, propertyFilter);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#count(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Override
	public <T extends BasePojo> int count(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		try {
			return count(pojoClass, Arrays.asList(propertyFilters));
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#exist(java.lang.Class, java.util.List)
	 */
	@Override
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		try {
			return count(pojoClass, propertyFilters) > 0;
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#exist(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Override
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		try {
			return exist(pojoClass, Arrays.asList(propertyFilters));
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#exist(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	@Override
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, String propertyName, Object propertyValue) throws DatabaseException {
		try {
			PropertyFilter propertyFilter = new PropertyFilter();
			propertyFilter.setCompareClass(pojoClass);
			propertyFilter.setCompareType(PropertyCompareType.EQ);
			propertyFilter.setPropertyName(propertyName);
			propertyFilter.setPropertyValue(propertyValue);
			return exist(pojoClass, propertyFilter);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * @return the sqlSessionTemplate
	 */
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	/**
	 * @param sqlSessionTemplate the sqlSessionTemplate to set
	 */
	@Resource(name = "sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}