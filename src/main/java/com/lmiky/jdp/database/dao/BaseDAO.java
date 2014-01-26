package com.lmiky.jdp.database.dao;

import java.util.List;
import java.util.Map;

import com.lmiky.jdp.database.exception.DatabaseException;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.database.pojo.BasePojo;

/**
 * DAO接口
 * @author lmiky
 * @date 2013-4-15
 */
public interface BaseDAO {
	
	/**
	 * 根据主键获取对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, Long id) throws DatabaseException;
	
	/**
	 * 根据属性获取对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException;
	
	/**
	 * 根据属性获取对象
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException;
	
	/**
	 * 根据HQL获取对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> T find(String hql) throws DatabaseException;
	
	/**
	 * 根据HQL获取对象
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params 参数
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> T find(String hql, Map<String, Object> params) throws DatabaseException;
	
	/**
	 * 保存对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojo
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> void save(T pojo) throws DatabaseException;
	
	/**
	 * 保存对象
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojos
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> void save(List<T> pojos) throws DatabaseException;
	
	/**
	 * 修改单个属性
	 * @author lmiky
	 * @date 2014-1-26
	 * @param pojoClass
	 * @param id
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, String propertyName, Object propertyValue) throws DatabaseException;
	
	/**
	 * 修改属性
	 * @author lmiky
	 * @date 2014-1-26
	 * @param pojoClass
	 * @param id
	 * @param params
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, Map<String, Object> params) throws DatabaseException;
	
	/**
	 * 删除对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojo
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> void delete(T pojo) throws DatabaseException;
	
	/**
	 * 批量删除对象s
	 * @author lmiky
	 * @date 2013-6-24
	 * @param pojos
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> void delete(List<T> pojos) throws DatabaseException;
	
	/**
	 * 删除对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param id
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long id) throws DatabaseException;
	
	/**
	 * 批量删除对象
	 * @author lmiky
	 * @date 2013-6-24
	 * @param pojoClass
	 * @param ids
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long[] ids) throws DatabaseException;
	
	/**
	 * 批量删除
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters 过滤条件
	 * @return	 删除数量
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> int delete(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException;
	
	/**
	 * 批量删除
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> int delete(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException;
	
	/**
	 * 根据HQL批量删除
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @return
	 * @throws DatabaseException
	 */
	public int delete(String hql) throws DatabaseException;
	
	/**
	 * 根据HQL批量删除
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws DatabaseException
	 */
	public int delete(String hql, Map<String, Object> params) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2014-1-3
	 * @param pojoClass
	 * @param propertyFilter
	 * @param sort
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter propertyFilter, Sort sort) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters	过滤条件
	 * @param sorts	排序
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param sorts
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort... sorts) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param pageFirst 从第几条记录开始查询
	 * @param pageSize	查询几条记录
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters	过滤条件
	 * @param sorts	排序
	 * @param pageFirst	 从第几条记录开始查询
	 * @param pageSize	查询几条记录
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass,  List<PropertyFilter> propertyFilters, List<Sort> sorts, int pageFirst, int pageSize) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param pageFirst
	 * @param pageSize
	 * @param propertyFilters
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, PropertyFilter... propertyFilters) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param pageFirst
	 * @param pageSize
	 * @param sorts
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, Sort... sorts) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(String hql) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(String hql, Map<String, Object> params) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @param pageFirst	从第几条记录开始查询
	 * @param pageSize	查询几条记录
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(String hql, int pageFirst, int pageSize) throws DatabaseException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @param pageFirst
	 * @param pageSize
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> List<T> list(String hql, Map<String, Object> params, int pageFirst, int pageSize) throws DatabaseException;
	
	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> int count(Class<T> pojoClass) throws DatabaseException;
	
	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters	过滤条件
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> int count(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException;
	
	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> int count(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException;
	
	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> int count(String hql) throws DatabaseException;
	
	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> int count(String hql, Map<String, Object> params) throws DatabaseException;
	
	/**
	 * 判断是否存在
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters	过滤条件
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws DatabaseException;
	
	/**
	 * 判断是否存在
	 * @author lmiky
	 * @date 2013-5-30
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, PropertyFilter... propertyFilters) throws DatabaseException;
	
	/**
	 * 判断是否存在
	 * @author lmiky
	 * @date 2013-5-30
	 * @param hql
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> boolean exist(String hql) throws DatabaseException;
	
	/**
	 * 判断是否存在
	 * @author lmiky
	 * @date 2013-5-30
	 * @param hql
	 * @param params
	 * @return
	 * @throws DatabaseException
	 */
	public <T extends BasePojo> boolean exist(String hql, Map<String, Object> params) throws DatabaseException;
	
	/**
	 * 执行hql查询
	 * @author lmiky
	 * @date 2013-5-30
	 * @param hql
	 * @return
	 * @throws DatabaseException
	 */
	public <X> List<X> executeQuery(String hql) throws DatabaseException;
	
	/**
	 * 执行hql查询
	 * @author lmiky
	 * @date 2013-6-16
	 * @param hql
	 * @param pageFirst
	 * @param pageSize
	 * @return
	 * @throws DatabaseException
	 */
	public <X> List<X> executeQuery(String hql, int pageFirst, int pageSize) throws DatabaseException;
	
	/**
	 * 执行hql查询
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws DatabaseException
	 */
	public <X> List<X> executeQuery(String hql, Map<String, Object> params) throws DatabaseException;
	
	/**
	 * 执行hql查询
	 * @author lmiky
	 * @date 2013-6-16
	 * @param hql
	 * @param params
	 * @param pageFirst
	 * @param pageSize
	 * @return
	 * @throws DatabaseException
	 */
	public <X> List<X> executeQuery(String hql, Map<String, Object> params, int pageFirst, int pageSize) throws DatabaseException;
	
	/**
	 * 执行hql更新
	 * @author lmiky
	 * @date 2013-5-24
	 * @param hql
	 * @return
	 * @throws DatabaseException
	 */
	public int executeUpdate(String hql) throws DatabaseException;
	
	/**
	 * 执行hql更新
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws DatabaseException
	 */
	public int executeUpdate(String hql, Map<String, Object> params) throws DatabaseException;
}
