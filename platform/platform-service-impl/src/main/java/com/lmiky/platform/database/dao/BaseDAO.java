package com.lmiky.platform.database.dao;

import com.lmiky.platform.database.model.PropertyFilter;
import com.lmiky.platform.database.model.Sort;
import com.lmiky.platform.database.pojo.BasePojo;

import java.util.List;
import java.util.Map;

/**
 * DAO接口
 *
 * @author lmiky
 * @date 2013-4-15
 */
public interface BaseDAO {

    /**
     * 是否数据库类
     *
     * @author lmiky
     * @date 2014年10月16日 上午11:21:52
     * @param pojoClass
     * @return
     */
    public <T extends BasePojo> boolean isDBPojo(Class<T> pojoClass);

    /**
     * 根据主键获取对象
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @param id
     * @return
     */
    public <T extends BasePojo> T find(Class<T> pojoClass, Long id);

    /**
     * 根据属性获取对象
     *
     * @author lmiky
     * @date 2014-8-13 下午5:32:32
     * @param pojoClass
     * @param propertyName
     * @param propertyValue
     * @return
     */
    public <T extends BasePojo> T find(Class<T> pojoClass, String propertyName, Object propertyValue);

    /**
     * 根据属性获取对象
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @param propertyFilters
     * @return
     */
    public <T extends BasePojo> T find(Class<T> pojoClass, List<PropertyFilter> propertyFilters);

    /**
     * 根据属性获取对象
     *
     * @author lmiky
     * @date 2013-5-29
     * @param pojoClass
     * @param propertyFilters
     * @return
     */
    public <T extends BasePojo> T find(Class<T> pojoClass, PropertyFilter... propertyFilters);

    /**
     * 根据属性获取对象
     *
     * @author lmiky
     * @date 2014年12月4日 下午3:52:01
     * @param pojoClass
     * @param params
     * @return
     */
    public <T extends BasePojo> T find(Class<T> pojoClass, Map<String, Object> params);

    /**
     * 保存对象
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojo
     */
    public <T extends BasePojo> void save(T pojo);

    /**
     * 保存对象
     *
     * @author lmiky
     * @date 2013-5-29
     * @param pojos
     */
    public <T extends BasePojo> void save(List<T> pojos);

    /**
     * 添加对象
     *
     * @author lmiky
     * @date 2013-8-16
     * @param pojo
     */
    public <T extends BasePojo> void add(T pojo);

    /**
     * 添加对象
     *
     * @author lmiky
     * @date 2013-8-16
     * @param pojos
     */
    public <T extends BasePojo> void add(List<T> pojos);

    /**
     * 修改对象
     *
     * @author lmiky
     * @date 2013-8-16
     * @param pojo
     */
    public <T extends BasePojo> void update(T pojo);

    /**
     * 修改对象
     *
     * @author lmiky
     * @date 2013-8-16
     * @param pojos
     */
    public <T extends BasePojo> void update(List<T> pojos);

    /**
     * 修改单个属性
     *
     * @author lmiky
     * @date 2014-1-26
     * @param pojoClass
     * @param id
     * @param propertyName
     * @param propertyValue
     * @return
     */
    public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, String propertyName, Object propertyValue);

    /**
     * 修改属性
     *
     * @author lmiky
     * @date 2014-1-26
     * @param pojoClass
     * @param id
     * @param params
     * @return
     */
    public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, Map<String, Object> params);

    /**
     * 根据条件修改
     *
     * @author lmiky
     * @date 2014年8月13日 下午9:48:37
     * @param pojoClass
     * @param condition
     * @param updateValue
     * @return
     */
    public <T extends BasePojo> boolean update(Class<T> pojoClass, Map<String, Object> condition,
            Map<String, Object> updateValue);

    /**
     * 根据条件修改
     *
     * @author lmiky
     * @date 2014年8月13日 下午9:56:15
     * @param pojoClass
     * @param conditionFieldName
     * @param conditionFieldValue
     * @param updateFieldName
     * @param updateFieldValue
     * @return
     */
    public <T extends BasePojo> boolean update(Class<T> pojoClass, String conditionFieldName,
            Object conditionFieldValue, String updateFieldName, Object updateFieldValue);

    /**
     * 根据条件修改
     *
     * @author lmiky
     * @date 2014年10月16日 下午3:29:02
     * @param pojoClass
     * @param propertyFilters
     * @param updateValue
     * @return
     */
    public <T extends BasePojo> boolean update(Class<T> pojoClass, List<PropertyFilter> propertyFilters,
            Map<String, Object> updateValue);

    /**
     * 删除对象
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojo
     */
    public <T extends BasePojo> void delete(T pojo);

    /**
     * 批量删除对象s
     *
     * @author lmiky
     * @date 2013-6-24
     * @param pojos
     */
    public <T extends BasePojo> void delete(List<T> pojos);

    /**
     * 删除对象
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @param id
     */
    public <T extends BasePojo> void delete(Class<T> pojoClass, Long id);

    /**
     * 批量删除对象
     *
     * @author lmiky
     * @date 2013-6-24
     * @param pojoClass
     * @param ids
     */
    public <T extends BasePojo> void delete(Class<T> pojoClass, Long[] ids);

    /**
     * 批量删除
     *
     * @author lmiky
     * @date 2014-8-13 下午5:16:48
     * @param pojoClass
     * @param propertyName
     * @param propertyValue
     * @return
     */
    public <T extends BasePojo> int delete(Class<T> pojoClass, String propertyName, Object propertyValue);

    /**
     * 批量删除
     *
     * @author lmiky
     * @date 2014-8-13 下午5:16:48
     * @param pojoClass
     * @param condition
     * @return
     */
    public <T extends BasePojo> int delete(Class<T> pojoClass, Map<String, Object> condition);

    /**
     * 批量删除
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @param propertyFilters 过滤条件
     * @return 删除数量
     */
    public <T extends BasePojo> int delete(Class<T> pojoClass, List<PropertyFilter> propertyFilters);

    /**
     * 批量删除
     *
     * @author lmiky
     * @date 2013-5-29
     * @param pojoClass
     * @param propertyFilters
     * @return
     */
    public <T extends BasePojo> int delete(Class<T> pojoClass, PropertyFilter... propertyFilters);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2014年12月10日 下午5:48:53
     * @param pojoClass
     * @param propertyName
     * @param propertyValue
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, String propertyName, Object propertyValue);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2014年12月10日 下午5:49:21
     * @param pojoClass
     * @param params
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, Map<String, Object> params);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2015年1月24日 下午2:42:26
     * @param pojoClass
     * @param sort
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort sort);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2014-1-3
     * @param pojoClass
     * @param propertyFilter
     * @param sort
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter propertyFilter, Sort sort);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2015年1月24日 下午2:42:55
     * @param pojoClass
     * @param sorts
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<Sort> sorts);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @param propertyFilters 过滤条件
     * @param sorts 排序
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2013-5-29
     * @param pojoClass
     * @param propertyFilters
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter... propertyFilters);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2013-5-29
     * @param pojoClass
     * @param sorts
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort... sorts);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @param pageFirst 从第几条记录开始查询
     * @param pageSize 查询几条记录
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @param propertyFilters 过滤条件
     * @param sorts 排序
     * @param pageFirst 从第几条记录开始查询
     * @param pageSize 查询几条记录
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters,
            List<Sort> sorts, int pageFirst, int pageSize);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2013-5-29
     * @param pojoClass
     * @param pageFirst
     * @param pageSize
     * @param propertyFilters
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize,
            PropertyFilter... propertyFilters);

    /**
     * 查询对象列表
     *
     * @author lmiky
     * @date 2013-5-29
     * @param pojoClass
     * @param pageFirst
     * @param pageSize
     * @param sorts
     * @return
     */
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, Sort... sorts);

    /**
     * 计算数量
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @return
     */
    public <T extends BasePojo> int count(Class<T> pojoClass);

    /**
     * 计算数量
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @param propertyFilters 过滤条件
     * @return
     */
    public <T extends BasePojo> int count(Class<T> pojoClass, List<PropertyFilter> propertyFilters);

    /**
     * 计算数量
     *
     * @author lmiky
     * @date 2014-8-13 下午5:38:22
     * @param pojoClass
     * @param propertyName
     * @param propertyValue
     * @return
     */
    public <T extends BasePojo> int count(Class<T> pojoClass, String propertyName, Object propertyValue);

    /**
     * 计算数量
     *
     * @author lmiky
     * @date 2013-5-29
     * @param pojoClass
     * @param propertyFilters
     * @return
     */
    public <T extends BasePojo> int count(Class<T> pojoClass, PropertyFilter... propertyFilters);

    /**
     * 计算数量
     *
     * @author lmiky
     * @date 2014年12月8日 下午4:56:08
     * @param pojoClass
     * @param params
     * @return
     */
    public <T extends BasePojo> int count(Class<T> pojoClass, Map<String, Object> params);

    /**
     * 判断是否存在
     *
     * @author lmiky
     * @date 2013-4-16
     * @param pojoClass
     * @param propertyFilters 过滤条件
     * @return
     */
    public <T extends BasePojo> boolean exist(Class<T> pojoClass, List<PropertyFilter> propertyFilters);

    /**
     * 判断是否存在
     *
     * @author lmiky
     * @date 2013-5-30
     * @param pojoClass
     * @param propertyFilters
     * @return
     */
    public <T extends BasePojo> boolean exist(Class<T> pojoClass, PropertyFilter... propertyFilters);

    /**
     * 判断是否存在
     *
     * @author lmiky
     * @date 2014-8-13 下午5:39:36
     * @param pojoClass
     * @param propertyName
     * @param propertyValue
     * @return
     */
    public <T extends BasePojo> boolean exist(Class<T> pojoClass, String propertyName, Object propertyValue);

    /**
     * 判断是否存在
     *
     * @author lmiky
     * @date 2014年12月8日 下午4:58:33
     * @param pojoClass
     * @param params
     * @return
     */
    public <T extends BasePojo> boolean exist(Class<T> pojoClass, Map<String, Object> params);
}