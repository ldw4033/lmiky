package com.lmiky.jdp.database.dao.mybatis;

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
 * @date 2014年8月26日 下午3:39:28
 */
@Repository("baseDAO")
public class BaseDAOImpl implements BaseDAO {
	//sql方法名
	/**
	 * sql方法名：查询
	 */
	protected static final String SQLNAME_FIND = "find";
	
	//查询方法名后缀
	/**
	 * 查询方法名后缀：查询
	 */
	private static final String SQLNAME_SUFFIX_FIND = "." + SQLNAME_FIND;
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	protected Map<Class<?>, String> pojoTableNames = new HashMap<Class<?>, String>();
	
	/**
	 * 获取实体类的表名
	 * @author lmiky
	 * @date 2014年8月25日 下午10:12:01
	 * @param pojoClass
	 * @return
	 * @throws DatabaseException
	 */
	protected <T extends BasePojo> String getPojoTabelName(Class<T> pojoClass) throws DatabaseException {
		//先读缓存
		String cacheTableName = pojoTableNames.get(pojoClass);
		if(cacheTableName != null) {
			return cacheTableName;
		}
		//根据反射获取
		Table annotation = pojoClass.getAnnotation(Table.class);
		//没有对应的注解
		if(annotation == null) {
			throw new DatabaseException(pojoClass.getName() + " is not a db pojo!");
		}
		cacheTableName = annotation.name();
		//放入缓存
		pojoTableNames.put(pojoClass, cacheTableName);
		return cacheTableName;
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, java.lang.Long)
	 */
	@Override
	public <T extends BasePojo> T find(Class<T> pojoClass, Long id) throws DatabaseException {
//		return find(pojoClass, BasePojo.POJO_FIELD_NAME_ID, id);
		return sqlSessionTemplate.selectOne(pojoClass.getName() + SQLNAME_SUFFIX_FIND, id);
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, java.lang.String, java.lang.Object)
	 */
	@Override
	public <T extends BasePojo> T find(Class<T> pojoClass, String propertyName, Object propertyValue) throws DatabaseException {
		PropertyFilter propertyFilter = new PropertyFilter();
		propertyFilter.setCompareClass(pojoClass);
		propertyFilter.setPropertyName(propertyName);
		propertyFilter.setPropertyValue(propertyValue);
		propertyFilter.setCompareType(PropertyCompareType.EQ);
		return find(pojoClass, propertyFilter);
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, java.util.List)
	 */
	@Override
	public <T extends BasePojo> T find(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		return sqlSessionTemplate.selectOne(pojoClass.getName() + SQLNAME_SUFFIX_FIND, propertyFilters);
	}

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.database.dao.BaseDAO#find(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
	 */
	@Override
	public <T extends BasePojo> T find(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		return find(pojoClass, Arrays.asList(propertyFilters));
	}

	@Override
	public <T extends BasePojo> void save(T pojo) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends BasePojo> void add(T pojo) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends BasePojo> void update(T pojo) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, String propertyName, Object propertyValue) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, Map<String, Object> params) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Map<String, Object> condition, Map<String, Object> updateValue) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends BasePojo> boolean update(Class<T> pojoClass, String conditionFieldName, Object conditionFieldValue, String updateFieldName, Object updateFieldValue) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends BasePojo> void delete(T pojo) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends BasePojo> void delete(List<T> pojos) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long id) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long[] ids) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public <T extends BasePojo> int delete(Class<T> pojoClass, String propertyName, Object propertyValue) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends BasePojo> int delete(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends BasePojo> int delete(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter propertyFilter, Sort sort) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort... sorts) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts, int pageFirst, int pageSize) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, PropertyFilter... propertyFilters) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, Sort... sorts) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends BasePojo> int count(Class<T> pojoClass) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends BasePojo> int count(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends BasePojo> int count(Class<T> pojoClass, String propertyName, Object propertyValue) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends BasePojo> int count(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, String propertyName, Object propertyValue) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
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
	@Resource(name="sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public <T extends BasePojo> void save(List<T> pojos) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends BasePojo> void add(List<T> pojos) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends BasePojo> void update(List<T> pojos) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}
}
