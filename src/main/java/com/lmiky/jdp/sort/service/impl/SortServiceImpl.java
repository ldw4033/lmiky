package com.lmiky.jdp.sort.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.database.exception.DatabaseException;
import com.lmiky.jdp.database.pojo.BasePojo;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.service.impl.BaseServiceImpl;
import com.lmiky.jdp.sort.SortService;
import com.lmiky.jdp.sort.pojo.BaseSortPojo;

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
			String className = sortPojoClass.getSimpleName();
			StringBuilder hql = new StringBuilder();
			// 清除原先的排序
			hql.append("update ").append(className).append(" set ").append(BaseSortPojo.POJO_FIELD_NAME_SORT).append(" = ").append(BaseSortPojo.DEFAULT_SORT);
			baseDAO.executeUpdate(hql.toString());
			int sortValue = sortedIds.length;
			for (String id : sortedIds) {
				hql.delete(0, hql.length());
				hql.append("update ").append(className).append(" set ").append(BaseSortPojo.POJO_FIELD_NAME_SORT).append(" = ").append(sortValue).append(" where ").append(BasePojo.POJO_FIELD_NAME_ID).append(" = ").append(id);
				baseDAO.executeUpdate(hql.toString());
				sortValue--;
			}
		} catch (DatabaseException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
