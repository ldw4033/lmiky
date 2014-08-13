package com.lmiky.jdp.sort.service;

import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.sort.pojo.BaseSortPojo;

/**
 * 排序
 * @author lmiky
 * @date 2014-1-17
 */
public interface SortService extends BaseService {

	/**
	 * 排序，如果该实体类其他数据没在ID列表中，那么会被设置为默认的排序
	 * @author lmiky
	 * @date 2014-1-17
	 * @param sortPojoClass
	 * @param sortedIds 排完序的ID列表，越前面就排的越高
	 * @throws ServiceException
	 */
	public <T extends BaseSortPojo> void sort(Class<T> sortPojoClass, String[] sortedIds) throws ServiceException;
	
}
