package com.lmiky.jdp.service;

import java.util.List;
import java.util.Map;

import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.database.pojo.BasePojo;
import com.lmiky.jdp.service.exception.ServiceException;

/**
 * 业务接口
 * @author lmiky
 * @date 2013-4-15
 */
public interface BaseService {

	/**
	 * 根据主键获取对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, Long id) throws ServiceException;
	
	/**
	 * 根据属性获取对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws ServiceException;
	
	/**
	 * 根据属性获取对象
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> T find(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException;
	
	/**
	 * 根据HQL获取对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> T find(String hql) throws ServiceException;
	
	/**
	 * 根据HQL获取对象
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params 参数
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> T find(String hql, Map<String, Object> params) throws ServiceException;
	
	/**
	 * 保存对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojo
	 * @throws ServiceException
	 */
	public <T extends BasePojo> void save(T pojo) throws ServiceException;
	
	/**
	 * 保存对象
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojos
	 * @throws ServiceException
	 */
	public <T extends BasePojo> void save(List<T> pojos) throws ServiceException;
	
	/**
	 * 删除对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojo
	 * @throws ServiceException
	 */
	public <T extends BasePojo> void delete(T pojo) throws ServiceException;
	
	/**
	 * 批量删除
	 * @author lmiky
	 * @date 2013-6-24
	 * @param pojos
	 * @throws ServiceException
	 */
	public <T extends BasePojo> void delete(List<T> pojos) throws ServiceException;
	
	/**
	 * 删除对象
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param id
	 * @throws ServiceException
	 */
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long id) throws ServiceException;
	
	/**
	 * 批量删除对象
	 * @author lmiky
	 * @date 2013-6-24
	 * @param pojoClass
	 * @param ids
	 * @throws ServiceException
	 */
	public <T extends BasePojo> void delete(Class<T> pojoClass, Long[] ids) throws ServiceException;
	
	/**
	 * 批量删除
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters 过滤条件
	 * @return	 删除数量
	 * @throws ServiceException
	 */
	public <T extends BasePojo> int delete(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws ServiceException;
	
	/**
	 * 批量删除
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> int delete(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException;
	
	/**
	 * 根据HQL批量删除
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @return
	 * @throws ServiceException
	 */
	public int delete(String hql) throws ServiceException;
	
	/**
	 * 根据HQL批量删除
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public int delete(String hql, Map<String, Object> params) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2014-1-3
	 * @param pojoClass
	 * @param propertyFilter
	 * @param sort
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter propertyFilter, Sort sort) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters	过滤条件
	 * @param sorts	排序
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param sorts
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort... sorts) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param pageFirst 从第几条记录开始查询
	 * @param pageSize	查询几条记录
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize) throws ServiceException;
	
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
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass,  List<PropertyFilter> propertyFilters, List<Sort> sorts, int pageFirst, int pageSize) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param pageFirst
	 * @param pageSize
	 * @param propertyFilters
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, PropertyFilter... propertyFilters) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param pageFirst
	 * @param pageSize
	 * @param sorts
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, Sort... sorts) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(String hql) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(String hql, Map<String, Object> params) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @param pageFirst	从第几条记录开始查询
	 * @param pageSize	查询几条记录
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(String hql, int pageFirst, int pageSize) throws ServiceException;
	
	/**
	 * 查询对象列表
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @param pageFirst
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> List<T> list(String hql, Map<String, Object> params, int pageFirst, int pageSize) throws ServiceException;
	
	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> int count(Class<T> pojoClass) throws ServiceException;
	
	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters	过滤条件
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> int count(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws ServiceException;
	
	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-5-29
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> int count(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException;
	
	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-4-16
	 * @param hql
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> int count(String hql) throws ServiceException;

	/**
	 * 计算数量
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> int count(String hql, Map<String, Object> params) throws ServiceException;
	
	/**
	 * 判断是否存在
	 * @author lmiky
	 * @date 2013-4-16
	 * @param pojoClass
	 * @param propertyFilters	过滤条件
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, List<PropertyFilter> propertyFilters) throws ServiceException;
	
	/**
	 * 判断是否存在
	 * @author lmiky
	 * @date 2013-5-30
	 * @param pojoClass
	 * @param propertyFilters
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> boolean exist(Class<T> pojoClass, PropertyFilter... propertyFilters) throws ServiceException;
	
	/**
	 * 判断是否存在
	 * @author lmiky
	 * @date 2013-5-30
	 * @param hql
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> boolean exist(String hql) throws ServiceException;
	
	/**
	 * 判断是否存在
	 * @author lmiky
	 * @date 2013-5-30
	 * @param hql
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public <T extends BasePojo> boolean exist(String hql, Map<String, Object> params) throws ServiceException;
	
	/**
	 * 执行hql查询
	 * @author lmiky
	 * @date 2013-5-24
	 * @param hql
	 * @return
	 * @throws ServiceException
	 */
	public <X> List<X> executeQuery(String hql) throws ServiceException;
	
	/**
	 * 执行hql查询
	 * @author lmiky
	 * @date 2013-6-16
	 * @param hql
	 * @param pageFirst
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public <X> List<X> executeQuery(String hql, int pageFirst, int pageSize) throws ServiceException;
	
	/**
	 * 执行hql查询
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public <X> List<X> executeQuery(String hql, Map<String, Object> params) throws ServiceException;
	
	/**
	 * 执行hql查询
	 * @author lmiky
	 * @date 2013-6-16
	 * @param hql
	 * @param params
	 * @param pageFirst
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public <X> List<X> executeQuery(String hql, Map<String, Object> params, int pageFirst, int pageSize) throws ServiceException;
	
	/**
	 * 执行hql更新
	 * @author lmiky
	 * @date 2013-5-24
	 * @param hql
	 * @return
	 * @throws ServiceException
	 */
	public int executeUpdate(String hql) throws ServiceException;
	
	/**
	 * 执行hql更新
	 * @author lmiky
	 * @date 2013-5-29
	 * @param hql
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public int executeUpdate(String hql, Map<String, Object> params) throws ServiceException;
}
