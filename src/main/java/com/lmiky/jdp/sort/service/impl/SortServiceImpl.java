package com.lmiky.jdp.sort.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.database.exception.DatabaseException;
import com.lmiky.jdp.database.pojo.BasePojo;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.service.impl.BaseServiceImpl;
import com.lmiky.jdp.sort.pojo.BaseSortPojo;
import com.lmiky.jdp.sort.service.SortService;

/**
 * 排序
 * @author lmiky
 * @date 2014-1-17
 */
@Service("sortService")
public class SortServiceImpl extends BaseServiceImpl implements SortService {

	/*
	 * (non-Javadoc)
	 * @see com.lmiky.jdp.sort.SortService#sort(java.lang.Class, java.lang.String[])
	 */
	@Transactional(rollbackFor = { Exception.class })
	public <T extends BaseSortPojo> void sort(Class<T> sortPojoClass, String[] sortedIds) throws ServiceException {
		try {
			// 清除原先的排序
			this.update(sortPojoClass, null, null, BaseSortPojo.POJO_FIELD_NAME_SORT, BaseSortPojo.DEFAULT_SORT);
			int sortValue = sortedIds.length;
			for (String id : sortedIds) {
				this.update(sortPojoClass, BasePojo.POJO_FIELD_NAME_ID, Long.valueOf(id), BaseSortPojo.POJO_FIELD_NAME_SORT, sortValue);
				sortValue--;
			}
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
