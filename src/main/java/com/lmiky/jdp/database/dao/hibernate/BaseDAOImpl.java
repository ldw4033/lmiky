package com.lmiky.jdp.database.dao.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lmiky.jdp.database.dao.BaseDAO;
import com.lmiky.jdp.database.exception.DatabaseException;
import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * 类说明
 * @author lmiky
 * @date 2013-4-15
 */
@Repository("baseDAO")
public class BaseDAOImpl implements BaseDAO {
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T find(Class<T> pojoClass, Long id) throws DatabaseException {
		try {
			return (T) getSession().get(pojoClass, id);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T find(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		try {
			// 单个实例或者null；当返回的实例大于一个的时候的抛出NonUniqueResultException
			return (T) generateQuery(pojoClass, propertyFilters, null).uniqueResult();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		return find(pojoClass, Arrays.asList(propertyFilters));
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T find(String hql) throws DatabaseException {
		try {
			return (T) generateQuery(hql).uniqueResult();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T find(String hql, Map<String, Object> params) throws DatabaseException {
		try {
			return (T) generateQuery(hql, params).uniqueResult();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#save(com.lmiky.jdp.database.pojo.BasePojo)
	 */
	public <T extends BasePojo> void save(T pojo) throws DatabaseException {
		try {
			getSession().saveOrUpdate(pojo);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#save(java.util.List)
	 */
	public <T extends BasePojo> void save(List<T> pojos) throws DatabaseException {
		try {
			Session session = getSession();
			for(T t : pojos) {
				session.saveOrUpdate(t);
			}
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(com.lmiky.jdp.database.pojo.BasePojo)
	 */
	public <T extends BasePojo> void delete(T pojo) throws DatabaseException {
		try {
			getSession().delete(pojo);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.util.List)
	 */
	public <T extends BasePojo> void delete(List<T> pojos) throws DatabaseException {
		for(T pojo : pojos) {
			delete(pojo);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.Class, java.io.Serializable)
	 */
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long id) throws DatabaseException {
		try {
			T pojo = find(pojoClass, id);
			delete(pojo);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.Class, java.lang.Long[])
	 */
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long[] ids) throws DatabaseException {
		try {
			if(ids == null || ids.length == 0) {
				return;
			}
			String pojoSimpleName = pojoClass.getSimpleName();
			StringBuilder hql = new StringBuilder("delete from ").append(pojoSimpleName).append(" ").append(pojoSimpleName) .append(" where 1=2 ");
			for(Long id : ids) {
				hql.append(" or id = ").append(id);
			}
			delete(hql.toString());
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.Class, java.util.List)
	 */
	public <T extends BasePojo> int delete(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		try {
			String hql = generateHql(pojoClass, propertyFilters, null);
			return generateQuery(hql.replaceFirst("from", "delete"), getFilterValues(propertyFilters)).executeUpdate();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	public <T extends BasePojo> int delete(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		return delete(pojoClass, Arrays.asList(propertyFilters));
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.String)
	 */
	public int delete(String hql) throws DatabaseException {
		return delete(hql, null);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#delete(java.lang.String, java.util.Map)
	 */
	public int delete(String hql, Map<String, Object> params) throws DatabaseException {
		try {
			return generateQuery(hql, params).executeUpdate();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class)
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass) throws DatabaseException {
		try {
			return list(pojoClass, 0, 0);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, java.util.List, java.util.List)
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts) throws DatabaseException {
		try {
			return list(pojoClass, propertyFilters, sorts, 0, 0);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		return list(pojoClass, Arrays.asList(propertyFilters), null);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, com.lmiky.jdp.database.model.Sort[])
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort... sorts) throws DatabaseException {
		return list(pojoClass, null, Arrays.asList(sorts));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, int, int)
	 */
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
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts, int pageFirst, int pageSize)
			throws DatabaseException {
		try {
			return list(generateQuery(pojoClass, propertyFilters, sorts), pageFirst, pageSize);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, int, int, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, PropertyFilter... propertyFilters) throws DatabaseException {
		return list(pojoClass, Arrays.asList(propertyFilters), null, pageFirst, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.Class, int, int, com.lmiky.jdp.database.model.Sort[])
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, Sort... sorts) throws DatabaseException {
		return list(pojoClass, null, Arrays.asList(sorts), pageFirst, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.String)
	 */
	public <T extends BasePojo> List<T> list(String hql) throws DatabaseException {
		try {
			return list(hql, null);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.String, java.util.Map)
	 */
	public <T extends BasePojo> List<T> list(String hql, Map<String, Object> params) throws DatabaseException {
		try {
			return list(generateQuery(hql, params), 0, 0);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.String, int, int)
	 */
	public <T extends BasePojo> List<T> list(String hql, int pageFirst, int pageSize) throws DatabaseException {
		return list(hql, null, pageFirst, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#list(java.lang.String, java.util.Map, int, int)
	 */
	public <T extends BasePojo> List<T> list(String hql, Map<String, Object> params, int pageFirst, int pageSize) throws DatabaseException {
		try {
			return list(generateQuery(hql, params), pageFirst, pageSize);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * 查询列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param query
	 * @return
	 */
	protected <T extends BasePojo> List<T> list(Query query) {
		try {
			return list(query, 0, 0);
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * 查询列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param query
	 * @param pageFirst
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T extends BasePojo> List<T> list(Query query, int pageFirst, int pageSize) {
		try {
			query.setFirstResult(pageFirst);
			query.setMaxResults(pageSize);
			return (List<T>) query.list();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#count(java.lang.Class)
	 */
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
	public <T extends BasePojo> int count(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		try {
			return ((Long)generateQuery(generateCountHql(pojoClass, propertyFilters), getFilterValues(propertyFilters)).uniqueResult()).intValue();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public <T extends BasePojo> int count(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		return count(pojoClass, Arrays.asList(propertyFilters));
	}

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#count(java.lang.String)
	 */
	public <T extends BasePojo> int count(String hql) throws DatabaseException {
		return count(hql, null);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#count(java.lang.String, java.util.Map)
	 */
	public <T extends BasePojo> int count(String hql, Map<String, Object> params) throws DatabaseException {
		try {
			return ((Long) generateQuery(generateCountHql(hql), params).uniqueResult()).intValue();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#exist(java.lang.Class, java.util.List)
	 */
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		if(count(pojoClass, propertyFilters) > 0) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#exist(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		return exist(pojoClass, Arrays.asList(propertyFilters));
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#exist(java.lang.String)
	 */
	public <T extends BasePojo> boolean exist(String hql) throws DatabaseException {
		return exist(hql, null);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#exist(java.lang.String, java.util.Map)
	 */
	public <T extends BasePojo> boolean exist(String hql, Map<String, Object> params) throws DatabaseException {
		if(count(hql, params) > 0) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#executeQuery(java.lang.String)
	 */
	public <X> List<X> executeQuery(String hql) throws DatabaseException {
		return executeQuery(hql, null);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#executeQuery(java.lang.String, int, int)
	 */
	public <X> List<X> executeQuery(String hql, int pageFirst, int pageSize) throws DatabaseException {
		return executeQuery(hql, null, pageFirst, pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#executeQuery(java.lang.String, java.util.Map)
	 */
	public <X> List<X> executeQuery(String hql, Map<String, Object> params) throws DatabaseException {
		return executeQuery(hql, params, 0, 0);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#executeQuery(java.lang.String, java.util.Map, int, int)
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> executeQuery(String hql, Map<String, Object> params, int pageFirst, int pageSize) throws DatabaseException {
		try {
			Query query = generateQuery(hql, params);
			query.setFirstResult(pageFirst);
			query.setMaxResults(pageSize);
			return query.list();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#executeUpdate(java.lang.String)
	 */
	public int executeUpdate(String hql) throws DatabaseException {
		return executeUpdate(hql, null);
	}
	
	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#executeUpdate(java.lang.String, java.util.Map)
	 */
	public int executeUpdate(String hql, Map<String, Object> params) throws DatabaseException {
		try {
			return generateQuery(hql, params).executeUpdate();
		} catch (Exception e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	/**
	 * 生成HQL
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters 过滤条件
	 * @param sorts 排序
	 * @return
	 */
	protected <T extends BasePojo> String generateHql(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts) {
		String pojoSimpleName = pojoClass.getSimpleName();
		StringBuilder hql = new StringBuilder("from " + pojoSimpleName + " " + pojoSimpleName + " ");
		boolean hasCollectionFilter = false;	//是否含有集合条件
		if (propertyFilters != null && !propertyFilters.isEmpty()) {
			List<String> joinClassNames = new ArrayList<String>();
			for(PropertyFilter propertyFilter : propertyFilters) {
				if(propertyFilter.isCollectionField()) {
					String compareClassSimpleName = propertyFilter.getCompareClass().getSimpleName();
					//已关联
					if(joinClassNames.contains(compareClassSimpleName)) {
						continue;
					}
					String comparePropertyName = propertyFilter.getPropertyName().substring(0, propertyFilter.getPropertyName().indexOf("."));
					hql.append(" join ").append(pojoSimpleName).append(".").append(comparePropertyName).append(" ").append(compareClassSimpleName).append(" ");
					joinClassNames.add(compareClassSimpleName);
					if(!hasCollectionFilter) {
						hasCollectionFilter = true;
					}
				}
			}
		}
		//如果有集合条件
		if(hasCollectionFilter) {
			hql.insert(0, "select distinct " + pojoSimpleName + " ");
		}
		return prepareHql(hql.toString(), propertyFilters, sorts);
	}

	/**
	 * 生成计数HQL
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 */
	protected <T extends BasePojo> String generateCountHql(Class<T> pojoClass, List<PropertyFilter> propertyFilters) {
		return "select count(*) " + removeSelect(removeOrders(generateHql(pojoClass, propertyFilters, null)));
	}
	
	/**
	 * 讲hql转为select count(*) 语句
	 * @author lmiky
	 * @date 2013-5-30
	 * @param hql
	 * @return
	 */
	protected <T extends BasePojo> String generateCountHql(String hql) {
		return "select count(*) " + removeSelect(removeOrders(hql));
	}
	
	/**
	 * 清除select HQL
	 * @author lmiky
	 * @date 2013-5-30
	 * @param hql
	 * @return
	 */
	protected String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}

	/**
	 * 清除order by HQL
	 * @author lmiky
	 * @date 2013-5-30
	 * @param hql
	 * @return
	 */
	protected String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * 设置HQL
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @param propertyFilters 过滤条件
	 * @param sorts 排序
	 * @return
	 */
	protected String prepareHql(String hql, List<PropertyFilter> propertyFilters, List<Sort> sorts) {
		StringBuffer hqlBuf = new StringBuffer(hql);
		if (propertyFilters != null && !propertyFilters.isEmpty()) {
			if (hql.toLowerCase().indexOf("where") == -1) {
				hqlBuf.append(" where 1=1 ");
			}
			for (PropertyFilter filter : propertyFilters) {
				String propertyName = filter.getPropertyName();
				if(filter.isCollectionField()) {
					propertyName = propertyName.substring(propertyName.indexOf(".") + 1);
				}
				hqlBuf.append(" and ").append(filter.getCompareClass().getSimpleName()).append(".").append(propertyName);
				if (filter.getCompareType() == PropertyCompareType.EQ) {
					hqlBuf.append(" = ? ");
				} else if (filter.getCompareType() == PropertyCompareType.NE) {
					hqlBuf.append(" != ? ");
				} else if (filter.getCompareType() == PropertyCompareType.GT) {
					hqlBuf.append(" > ? ");
				} else if (filter.getCompareType() == PropertyCompareType.GE) {
					hqlBuf.append(" >= ? ");
				} else if (filter.getCompareType() == PropertyCompareType.LT) {
					hqlBuf.append(" < ? ");
				} else if (filter.getCompareType() == PropertyCompareType.LE) {
					hqlBuf.append(" <= ? ");
				} else if (filter.getCompareType() == PropertyCompareType.LIKE) {
					hqlBuf.append(" like '%").append(filter.getPropertyValue()).append("%' ");
				} else if (filter.getCompareType() == PropertyCompareType.LLIKE) {
					hqlBuf.append(" like '%").append(filter.getPropertyValue()).append("' ");
				} else if (filter.getCompareType() == PropertyCompareType.RLIKE) {
					hqlBuf.append(" like '").append(filter.getPropertyValue()).append("%' ");
				} else if (filter.getCompareType() == PropertyCompareType.NLIKE) {
					hqlBuf.append(" not like '%").append(filter.getPropertyValue()).append("%' ");
				} else if (filter.getCompareType() == PropertyCompareType.NLLIKE) {
					hqlBuf.append(" not like '%").append(filter.getPropertyValue()).append("' ");
				} else if (filter.getCompareType() == PropertyCompareType.NRLIKE) {
					hqlBuf.append(" not like '").append(filter.getPropertyValue()).append("%' ");
				} else if (filter.getCompareType() == PropertyCompareType.NNULL) {
					hqlBuf.append(" is not null ");
				} else if (filter.getCompareType() == PropertyCompareType.NULL) {
					hqlBuf.append(" is null ");
				}
			}
		}

		if (sorts != null && !sorts.isEmpty()) {
			// 判断HQL中是否已经有排序
			boolean isSorted = false;
			Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(hql);
			if (m.find()) {
				isSorted = true;
			}
			for (Sort sort : sorts) {
				if (!isSorted) {
					hqlBuf.append(" order by ").append(sort.getSortClass().getSimpleName()).append(".").append(sort.getPropertyName()).append(" ")
							.append(sort.getSortType());
					isSorted = true;
				} else {
					hqlBuf.append(", ").append(sort.getSortClass().getSimpleName()).append(".").append(sort.getPropertyName()).append(" ")
							.append(sort.getSortType());
				}
			}
		}
		return hqlBuf.toString();
	}

	/**
	 * 获取过滤条件值
	 * @author lmiky
	 * @date 2013-4-16
	 * @param propertyFilters
	 * @return
	 */
	protected List<Object> getFilterValues(List<PropertyFilter> propertyFilters) {
		List<Object> values = new ArrayList<Object>();
		if (propertyFilters != null && !propertyFilters.isEmpty()) {
			for (PropertyFilter filter : propertyFilters) {
				// like或null直接在HQL拼写的时候写入
				if (filter.getPropertyValue() != null && filter.getCompareType() != PropertyCompareType.LIKE
						&& filter.getCompareType() != PropertyCompareType.LLIKE && filter.getCompareType() != PropertyCompareType.RLIKE
						&& filter.getCompareType() != PropertyCompareType.NLIKE && filter.getCompareType() != PropertyCompareType.NLLIKE
						&& filter.getCompareType() != PropertyCompareType.NRLIKE && filter.getCompareType() != PropertyCompareType.NNULL
						&& filter.getCompareType() != PropertyCompareType.NULL) {
					values.add(filter.getPropertyValue());
				}
			}
		}
		return values;
	}

	/**
	 * 生成查询对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters
	 * @param sorts
	 * @return
	 */
	protected <T extends BasePojo> Query generateQuery(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts) {
		return generateQuery(generateHql(pojoClass, propertyFilters, sorts), getFilterValues(propertyFilters));
	}

	/**
	 * 生成查询对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @return
	 */
	protected Query generateQuery(String hql) {
		return generateQuery(hql, new ArrayList<Object>());
	}

	/**
	 * 生成查询对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @param propertyValues
	 * @return
	 */
	protected Query generateQuery(String hql, List<Object> propertyValues) {
		Query query = getSession().createQuery(hql);
		if (propertyValues != null && !propertyValues.isEmpty()) {
			for (int i = 0; i < propertyValues.size(); i++) {
				query.setParameter(i, propertyValues.get(i));
			}
		}
		return query;
	}
	
	/**
	 * 生成查询对象
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params 键值对参数
	 * @return
	 */
	protected Query generateQuery(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			query.setProperties(params);
		}
		return query;
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取数据库Session
	 * @author
	 * @date 2013-4-15
	 * @return
	 */
	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}
}
