package com.lmiky.test.jdp.database.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.lmiky.jdp.database.dao.BaseDAO;
import com.lmiky.jdp.database.model.PropertyCompareType;
import com.lmiky.jdp.database.model.PropertyFilter;
import com.lmiky.jdp.database.model.Sort;
import com.lmiky.jdp.module.pojo.Module;
import com.lmiky.jdp.module.pojo.ModuleGroup;
import com.lmiky.jdp.user.pojo.Operator;
import com.lmiky.jdp.user.pojo.User;
import com.lmiky.test.BaseTest;
import com.lmiky.tiger.goods.pojo.Goods;

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
	public void testFindById() {
		long begin = System.currentTimeMillis();
		Operator operator = baseDAO.find(Operator.class, 21l);
		System.out.println(operator);
		if(operator != null) {
			System.out.println(operator.getName());
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
	
	@Test
	public void testFindByProperty() {
		long begin = System.currentTimeMillis();
		Module module = baseDAO.find(Module.class, "path", "jdp/area");
		System.out.println(module);
		if(module != null) {
			System.out.println(module.getName());
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
	
	@Test
	public void testFindByProperties() {
		long begin = System.currentTimeMillis();
		List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
		PropertyFilter propertyFilter = new PropertyFilter();
		propertyFilter.setCompareClass(Module.class);
		propertyFilter.setCompareType(PropertyCompareType.LLIKE);
		propertyFilter.setPropertyName("path");
		propertyFilter.setPropertyValue("jdp");
		propertyFilters.add(propertyFilter);
		PropertyFilter propertyFilter2 = new PropertyFilter();
		propertyFilter2.setCompareClass(Module.class);
		propertyFilter2.setCompareType(PropertyCompareType.LIKE);
		propertyFilter2.setPropertyName("name");
		propertyFilter2.setPropertyValue("地区");
		propertyFilters.add(propertyFilter2);
		Module module = baseDAO.find(Module.class, propertyFilters);
		System.out.println(module);
		if(module != null) {
			System.out.println(module.getId());
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}

	@Test
	public void testFind() {
		long begin = System.currentTimeMillis();
		Module module = baseDAO.find(Module.class, 290l);
		System.out.println(module);
		if(module != null) {
			System.out.println(module.getName());
			ModuleGroup group = module.getGroup();
			System.out.println(group);
//			if(module.getGroup() != null) {
//				System.out.println(group.getName());
//				Set<Module> modules = module.getGroup().getModules();
//				if(modules != null) {
//					System.out.println(modules.size());
//					System.out.println(modules.iterator().next().getName());
//				}
//			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
	}
	
	@Test
	public void testDeleteByPojo() {
		Module module = baseDAO.find(Module.class, 299l);
		baseDAO.delete(module);
	}
	
	@Test
	public void testDeleteById() {
		baseDAO.delete(Goods.class, 2417l);
	}
	
	@Test
	public void testDeleteByIds() {
		baseDAO.delete(Goods.class, new Long[] {1009l, 1008l});
	}
	
	@Test
	public void testDelete() {
		List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
		PropertyFilter propertyFilter = new PropertyFilter();
		propertyFilter.setCompareClass(Goods.class);
		propertyFilter.setCompareType(PropertyCompareType.LLIKE);
		propertyFilter.setPropertyName("title");
		propertyFilter.setPropertyValue("测试添加1");
		propertyFilters.add(propertyFilter);
		PropertyFilter propertyFilter2 = new PropertyFilter();
		propertyFilter2.setCompareClass(Goods.class);
		propertyFilter2.setCompareType(PropertyCompareType.GT);
		propertyFilter2.setPropertyName("id");
		propertyFilter2.setPropertyValue(2400l);
		propertyFilters.add(propertyFilter2);
		baseDAO.delete(Goods.class, propertyFilters);
	}
	
	@Test
	public void testAdd() {
		Module module = new Module();
		module.setName("test");
		module.setPath("test");
		ModuleGroup group = new ModuleGroup();
		group.setId(132l);
		module.setGroup(group);
		baseDAO.add(module);
		System.out.println(module.getId());
	}
	
	@Test
	public void testAddExtend() throws IllegalAccessException, InvocationTargetException {
		Operator operator = new Operator();
		operator.setCreateTime(new Date());
		operator.setDescription("");
		operator.setEmail("email");
		operator.setLastSetPasswordTime(new Date());
		operator.setLoginName("test" + System.currentTimeMillis());
		operator.setName(operator.getLoginName());
		operator.setPassword("test");
		operator.setPhone("123");
//		operator.setPhoto("photo");
		operator.setValid(1);
		User user = new User();
		BeanUtils.copyProperties(user, operator);
		System.out.println(user.getClass().getName());
		baseDAO.add(user);
		System.out.println(user.getId());
		operator.setId(user.getId());
		System.out.println(operator.getId());
		baseDAO.add(operator);
	}
	
	@Test
	public void testUpdate() {
		System.out.println(baseDAO.update(Goods.class, 1920l, "title", "test"));
	}
	
	@Test
	public void testUpdate2() {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("title", "测试添加");
		condition.put("sale_price", 12.21);
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("title", "测试添加1221");
		value.put("market_price", 12.21);
		System.out.println(baseDAO.update(Goods.class, condition, value));
	}
	
	@Test
	public void testUpdate3() {
		Module module = baseDAO.find(Module.class, 303l);
		module.setName("test3");
		module.setPath("test3");
		ModuleGroup group = new ModuleGroup();
		group.setId(131l);
		module.setGroup(group);
		baseDAO.update(module);
	}
	
	@Test
	public void testList() {
		List<Module> modules = baseDAO.list(Module.class);
		System.out.println(modules.size());
	}
	
	@Test
	public void testList1() {
		List<Module> modules = baseDAO.list(Module.class, 1, 5);
		System.out.println(modules.size());
	}
	
	@Test
	public void testList2() {
		PropertyFilter propertyFilter = new PropertyFilter();
		propertyFilter.setCompareClass(Goods.class);
		propertyFilter.setCompareType(PropertyCompareType.LLIKE);
		propertyFilter.setPropertyName("path");
		propertyFilter.setPropertyValue("jdp");
		Sort sort = new Sort();
		sort.setPropertyName("id");
		sort.setSortType(Sort.SORT_TYPE_DESC);
		List<Module> modules = baseDAO.list(Module.class, propertyFilter, sort);
		System.out.println(modules.size());
		System.out.println(modules.get(0).getId());
	}
	
	@Test
	public void testList3() {
		List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
		PropertyFilter propertyFilter = new PropertyFilter();
		propertyFilter.setCompareClass(Goods.class);
		propertyFilter.setCompareType(PropertyCompareType.LLIKE);
		propertyFilter.setPropertyName("path");
		propertyFilter.setPropertyValue("jdp");
		propertyFilters.add(propertyFilter);
		PropertyFilter propertyFilter2 = new PropertyFilter();
		propertyFilter2.setCompareClass(Goods.class);
		propertyFilter2.setCompareType(PropertyCompareType.GT);
		propertyFilter2.setPropertyName("id");
		propertyFilter2.setPropertyValue(291l);
		propertyFilters.add(propertyFilter2);
		PropertyFilter propertyFilter3 = new PropertyFilter();
		propertyFilter3.setCompareClass(Goods.class);
		propertyFilter3.setCompareType(PropertyCompareType.LT);
		propertyFilter3.setPropertyName("id");
		propertyFilter3.setPropertyValue(294l);
		propertyFilters.add(propertyFilter3);
		List<Module> modules = baseDAO.list(Module.class, propertyFilters.toArray(new PropertyFilter[propertyFilters.size()]));
		System.out.println(modules.size());
		System.out.println(modules.get(0).getId());
	}
	
	@Test
	public void testList4() {
		List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
		PropertyFilter propertyFilter = new PropertyFilter();
		propertyFilter.setCompareClass(Goods.class);
		propertyFilter.setCompareType(PropertyCompareType.LLIKE);
		propertyFilter.setPropertyName("path");
		propertyFilter.setPropertyValue("jdp");
		propertyFilters.add(propertyFilter);
		PropertyFilter propertyFilter2 = new PropertyFilter();
		propertyFilter2.setCompareClass(Goods.class);
		propertyFilter2.setCompareType(PropertyCompareType.GT);
		propertyFilter2.setPropertyName("id");
		propertyFilter2.setPropertyValue(291l);
		propertyFilters.add(propertyFilter2);
		PropertyFilter propertyFilter3 = new PropertyFilter();
		propertyFilter3.setCompareClass(Goods.class);
		propertyFilter3.setCompareType(PropertyCompareType.LT);
		propertyFilter3.setPropertyName("id");
		propertyFilter3.setPropertyValue(294l);
		propertyFilters.add(propertyFilter3);
		
		List<Sort> sorts = new ArrayList<Sort>();
		Sort sort = new Sort();
		sort.setPropertyName("id");
		sort.setSortType(Sort.SORT_TYPE_DESC);
		sorts.add(sort);
		List<Module> modules = baseDAO.list(Module.class, propertyFilters, sorts, 0, 1);
		System.out.println(modules.size());
		System.out.println(modules.get(0).getId());
	}
	
	@Test
	public void testCount() {
		List<PropertyFilter> propertyFilters = new ArrayList<PropertyFilter>();
		PropertyFilter propertyFilter = new PropertyFilter();
		propertyFilter.setCompareClass(Goods.class);
		propertyFilter.setCompareType(PropertyCompareType.LLIKE);
		propertyFilter.setPropertyName("path");
		propertyFilter.setPropertyValue("jdp");
		propertyFilters.add(propertyFilter);
		PropertyFilter propertyFilter2 = new PropertyFilter();
		propertyFilter2.setCompareClass(Goods.class);
		propertyFilter2.setCompareType(PropertyCompareType.GT);
		propertyFilter2.setPropertyName("id");
		propertyFilter2.setPropertyValue(291l);
		propertyFilters.add(propertyFilter2);
		PropertyFilter propertyFilter3 = new PropertyFilter();
		propertyFilter3.setCompareClass(Goods.class);
		propertyFilter3.setCompareType(PropertyCompareType.LT);
		propertyFilter3.setPropertyName("id");
		propertyFilter3.setPropertyValue(294l);
		propertyFilters.add(propertyFilter3);
		System.out.println(baseDAO.count(Module.class, propertyFilters));
	}
	
	@Test
	public void testCount1() {
		System.out.println(baseDAO.count(Module.class));
	}
	
	/**
	 * @param dao the dao to set
	 */
	@Resource(name="baseDAO")
	public void setDao(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
}
