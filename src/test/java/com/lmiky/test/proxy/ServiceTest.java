package com.lmiky.test.proxy;

import org.junit.Test;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;

import com.lmiky.jdp.service.BaseService;
import com.lmiky.jdp.service.exception.ServiceException;
import com.lmiky.test.BaseTest;
import com.lmiky.tiger.goods.pojo.Goods;

/**
 * @author lmiky
 * @date 2013-4-16
 */
public class ServiceTest extends BaseTest {
	private BaseService baseService;
	
	@Test
	public void testFind() throws ServiceException {
		System.out.println(baseService);
		BaseService testService = ProxyUtils.generateProxy(baseService);
		Goods goods = testService.find(Goods.class, 1l);
		System.out.println(goods.getTitle());
	}
	
	@Test
	public void testFind2() throws ServiceException {
		System.out.println(baseService);
		BaseService testService = CglibProxyUtils.generateProxy(baseService);
		Goods goods = testService.find(Goods.class, 1l);
		System.out.println(goods.getTitle());
	}
	
	@Test
	public void testFind3() throws ServiceException {
		System.out.println(baseService);
		BaseService testService = SpringCglibProxyUtils.generateProxy(baseService);
		Goods goods = testService.find(Goods.class, 1l);
		System.out.println(goods.getTitle());
	}
	
	@Test
	public void testFind4() throws ServiceException {
		System.out.println(baseService);
		Goods goods = baseService.find(Goods.class, 1l);
		System.out.println(goods.getTitle());
	}
	
	@Test
	public void test() throws ServiceException {
		BeanNameAutoProxyCreator beanNameAutoProxyCreator=new BeanNameAutoProxyCreator();  
        //设置要创建代理的那些Bean的名字  
        beanNameAutoProxyCreator.setBeanNames("userSer*");  
        //设置拦截链名字(这些拦截器是有先后顺序的)  
        beanNameAutoProxyCreator.setInterceptorNames("myMethodInterceptor");  
	}
	
	/**
	 * @return the baseService
	 */
	public BaseService getBaseService() {
		return baseService;
	}

	/**
	 * @param baseService the baseService to set
	 */
	@Autowired
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
}
