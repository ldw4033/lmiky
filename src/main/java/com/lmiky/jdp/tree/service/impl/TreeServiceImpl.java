package com.lmiky.jdp.tree.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmiky.jdp.database.pojo.BasePojo;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.jdp.service.impl.BaseServiceImpl;
import com.lmiky.jdp.tree.pojo.BaseTreePojo;

/**
 * 树
 * @author lmiky
 * @date 2014-1-5
 */
@Service("treeService")
public class TreeServiceImpl extends BaseServiceImpl {

	/* (non-Javadoc)
	 * @see com.lmiky.jdp.service.impl.BaseServiceImpl#save(com.lmiky.jdp.database.pojo.BasePojo)
	 */
	@Override
	@Transactional(rollbackFor={Exception.class})
	public <T extends BasePojo> void save(T pojo) throws ServiceException {
		//如果是树
		if(pojo instanceof BaseTreePojo) {
			BaseTreePojo parent = ((BaseTreePojo) pojo).getParent();
			//非顶层
			if(parent != null) {
				//修改父节点叶子状态
				if(parent.getLeaf() == BaseTreePojo.LEAF_YES) {
					parent.setLeaf(BaseTreePojo.LEAF_NO);
					super.save(parent);
				}
			}
		}
		super.save(pojo);
	}

}
