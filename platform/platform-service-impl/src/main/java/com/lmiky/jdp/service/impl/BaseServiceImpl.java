package com.lmiky.jdp.service.impl;

import com.lmiky.jdp.database.dao.BaseDAO;
import com.lmiky.platform.database.model.PropertyFilter;
import com.lmiky.platform.database.model.Sort;
import com.lmiky.platform.database.pojo.BasePojo;
import com.lmiky.platform.service.BaseService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 业务基类
 *
 * @author lmiky
 * @date 2013-4-15
 */
@Service("baseService")
public class BaseServiceImpl implements BaseService {
    protected BaseDAO baseDAO;

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#isDBPojo(java.lang.Class)
     */
    public <T extends BasePojo> boolean isDBPojo(Class<T> pojoClass) {
        return getDAO().isDBPojo(pojoClass);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#find(java.lang.Class, java.io.Serializable)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> T find(Class<T> pojoClass, Long id) {
        return getDAO().find(pojoClass, id);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#find(java.lang.Class, java.lang.String, java.lang.Object)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> T find(Class<T> pojoClass, String propertyName, Object propertyValue) {
        return getDAO().find(pojoClass, propertyName, propertyValue);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#find(java.lang.Class, java.util.List)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> T find(Class<T> pojoClass, List<PropertyFilter> propertyFilters) {
        return getDAO().find(pojoClass, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#find(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> T find(Class<T> pojoClass, PropertyFilter... propertyFilters) {
        return getDAO().find(pojoClass, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#find(java.lang.Class, java.util.Map)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> T find(Class<T> pojoClass, Map<String, Object> params) {
        return getDAO().find(pojoClass, params);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#save(com.lmiky.jdp.database.pojo.BasePojo)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void save(T pojo) {
        if (pojo.getId() == null) {
            add(pojo);
        } else {
            update(pojo);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#save(java.util.List)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void save(List<T> pojos) {
        for (T t : pojos) {
            save(t);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#add(com.lmiky.jdp.database.pojo.BasePojo)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void add(T pojo) {
        getDAO().add(pojo);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#add(java.util.List)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void add(List<T> pojos) {
        for (T t : pojos) {
            add(t);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#update(com.lmiky.jdp.database.pojo.BasePojo)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void update(T pojo) {
        getDAO().update(pojo);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#update(java.util.List)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void update(List<T> pojos) {
        for (T t : pojos) {
            update(t);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#update(java.lang.Class, java.lang.Long, java.lang.String,
     * java.lang.Object)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, String propertyName, Object propertyValue) {
        return getDAO().update(pojoClass, id, propertyName, propertyValue);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#update(java.lang.Class, java.lang.Long, java.util.Map)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> boolean update(Class<T> pojoClass, Long id, Map<String, Object> params) {
        return getDAO().update(pojoClass, id, params);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#update(java.lang.Class, java.util.Map, java.util.Map)
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> boolean update(Class<T> pojoClass, Map<String, Object> condition,
            Map<String, Object> updateValue) {
        return getDAO().update(pojoClass, condition, updateValue);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#update(java.lang.Class, java.lang.String, java.lang.Object,
     * java.lang.String, java.lang.Object)
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> boolean update(Class<T> pojoClass, String conditionFieldName,
            Object conditionFieldValue, String updateFieldName, Object updateFieldValue) {
        return getDAO().update(pojoClass, conditionFieldName, conditionFieldValue, updateFieldName, updateFieldValue);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#update(java.lang.Class, java.util.List, java.util.Map)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> boolean update(Class<T> pojoClass, List<PropertyFilter> propertyFilters,
            Map<String, Object> updateValue) {
        return getDAO().update(pojoClass, propertyFilters, updateValue);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#delete(com.lmiky.jdp.database.pojo.BasePojo)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void delete(T pojo) {
        getDAO().delete(pojo);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#delete(java.util.List)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void delete(List<T> pojos) {
        for (T pojo : pojos) {
            delete(pojo);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#delete(java.lang.Class, java.io.Serializable)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void delete(Class<T> pojoClass, Long id) {
        getDAO().delete(pojoClass, id);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#delete(java.lang.Class, java.lang.Long[])
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> void delete(Class<T> pojoClass, Long[] ids) {
        getDAO().delete(pojoClass, ids);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#delete(java.lang.Class, java.lang.String, java.lang.Object)
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> int delete(Class<T> pojoClass, String propertyName, Object propertyValue) {
        return getDAO().delete(pojoClass, propertyName, propertyValue);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.platform.service.BaseService#delete(java.lang.Class, java.util.Map)
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> int delete(Class<T> pojoClass, Map<String, Object> condition) {
        return getDAO().delete(pojoClass, condition);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#delete(java.lang.Class, java.util.List)
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> int delete(Class<T> pojoClass, List<PropertyFilter> propertyFilters) {
        return getDAO().delete(pojoClass, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#delete(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
     */
    @Transactional(rollbackFor = { Exception.class })
    public <T extends BasePojo> int delete(Class<T> pojoClass, PropertyFilter... propertyFilters) {
        return getDAO().delete(pojoClass, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass) {
        return getDAO().list(pojoClass);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, java.lang.String, java.lang.Object)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, String propertyName, Object propertyValue) {
        return getDAO().list(pojoClass, propertyName, propertyValue);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, java.util.Map)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, Map<String, Object> params) {
        return getDAO().list(pojoClass, params);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, com.lmiky.jdp.database.model.Sort)
     */
    @Override
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort sort) {
        return getDAO().list(pojoClass, sort);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter,
     * com.lmiky.jdp.database.model.Sort)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter propertyFilter, Sort sort) {
        return getDAO().list(pojoClass, propertyFilter, sort);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, java.util.List)
     */
    @Override
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<Sort> sorts) {
        return getDAO().list(pojoClass, sorts);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, java.util.List, java.util.List)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters, List<Sort> sorts) {
        return getDAO().list(pojoClass, propertyFilters, sorts);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, PropertyFilter... propertyFilters) {
        return getDAO().list(pojoClass, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, com.lmiky.jdp.database.model.Sort[])
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, Sort... sorts) {
        return getDAO().list(pojoClass, sorts);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, int, int)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize) {
        return getDAO().list(pojoClass, pageFirst, pageSize);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, java.util.List, java.util.List, int, int)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, List<PropertyFilter> propertyFilters,
            List<Sort> sorts, int pageFirst, int pageSize) {
        return getDAO().list(pojoClass, propertyFilters, sorts, pageFirst, pageSize);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, int, int,
     * com.lmiky.jdp.database.model.PropertyFilter[])
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize,
            PropertyFilter... propertyFilters) {
        return getDAO().list(pojoClass, pageFirst, pageSize, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#list(java.lang.Class, int, int, com.lmiky.jdp.database.model.Sort[])
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> List<T> list(Class<T> pojoClass, int pageFirst, int pageSize, Sort... sorts) {
        return getDAO().list(pojoClass, pageFirst, pageSize, sorts);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#count(java.lang.Class)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> int count(Class<T> pojoClass) {
        return getDAO().count(pojoClass);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#count(java.lang.Class, java.lang.String, java.lang.Object)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> int count(Class<T> pojoClass, String propertyName, Object propertyValue) {
        return getDAO().count(pojoClass, propertyName, propertyValue);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#count(java.lang.Class, java.util.List)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> int count(Class<T> pojoClass, List<PropertyFilter> propertyFilters) {
        return getDAO().count(pojoClass, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#count(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> int count(Class<T> pojoClass, PropertyFilter... propertyFilters) {
        return getDAO().count(pojoClass, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#count(java.lang.Class, java.util.Map)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> int count(Class<T> pojoClass, Map<String, Object> params) {
        return getDAO().count(pojoClass, params);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#exist(java.lang.Class, java.util.List)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> boolean exist(Class<T> pojoClass, List<PropertyFilter> propertyFilters) {
        return getDAO().exist(pojoClass, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#exist(java.lang.Class, com.lmiky.jdp.database.model.PropertyFilter[])
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> boolean exist(Class<T> pojoClass, PropertyFilter... propertyFilters) {
        return getDAO().exist(pojoClass, propertyFilters);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#exist(java.lang.Class, java.util.Map)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> boolean exist(Class<T> pojoClass, Map<String, Object> params) {
        return getDAO().exist(pojoClass, params);
    }

    /*
     * (non-Javadoc)
     * @see com.lmiky.jdp.service.BaseService#exist(java.lang.Class, java.lang.String, java.lang.Object)
     */
    @Transactional(readOnly = true)
    public <T extends BasePojo> boolean exist(Class<T> pojoClass, String propertyName, Object propertyValue) {
        return getDAO().exist(pojoClass, propertyName, propertyValue);
    }

    /**
     * 获取DAO对象
     *
     * @author lmiky
     * @date 2013-4-15
     * @return
     */
    public BaseDAO getDAO() {
        return baseDAO;
    }

    /**
     * 注入DAO
     *
     * @author lmiky
     * @date 2013-4-15
     * @param dao
     */
    @Resource(name = "baseDAO")
    public void setDAO(BaseDAO dao) {
        this.baseDAO = dao;
    }

}