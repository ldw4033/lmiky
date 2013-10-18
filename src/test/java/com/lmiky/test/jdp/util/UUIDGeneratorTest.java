package com.lmiky.test.jdp.util;

import org.junit.Test;

import com.lmiky.jdp.util.UUIDGenerator;
import com.lmiky.test.BaseTest;

/**
 * UUID生成器
 * @author lmiky
 * @date 2013-10-16
 */
public class UUIDGeneratorTest extends BaseTest {
	
	@Test
	public void generate() {
		for(int i=0; i<50; i++) {
			System.out.println(UUIDGenerator.generateString());
		}
	}
}
