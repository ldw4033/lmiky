package com.lmiky.test.jdp.database.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.lmiky.jdp.database.dao.BaseDAO;
import com.lmiky.jdp.module.pojo.Module;
import com.lmiky.test.BaseTest;

/**
 * DAO测试
 * @author lmiky
 * @date 2013-4-15
 */
public class DAOTest extends BaseTest{
	private BaseDAO baseDAO;
	
	@Test
	public void testGetDAO() {
		System.out.println(baseDAO);
	}
	
	@Test
	public void testFind() {
		Module module = baseDAO.find(Module.class, 290l);
		System.out.println(module);
		if(module != null) {
			System.out.println(module.getName());
			System.out.println(module.getGroup());
			if(module.getGroup() != null) {
				System.out.println(module.getGroup().getName());
			}
		}
	}

	/**
	 * @param dao the dao to set
	 */
	@Resource(name="baseDAO")
	public void setDao(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
}
