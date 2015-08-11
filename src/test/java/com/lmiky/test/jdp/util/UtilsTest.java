package com.lmiky.test.jdp.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lmiky.jdp.util.PropertiesUtils;
import com.lmiky.jdp.util.StringUtils;
import com.lmiky.jdp.util.UUIDGenerator;
import com.lmiky.test.BaseTest;

/**
 * UUID生成器
 * @author lmiky
 * @date 2013-10-16
 */
public class UtilsTest extends BaseTest {
    
    @Autowired
    private VelocityEngine velocityEngine;
	
	@Test
	public void generate() {
		for(int i=0; i<50; i++) {
			System.out.println(UUIDGenerator.generateString());
		}
	}
	
	@Test
    public void testVelocityEngine() {
           System.out.println(velocityEngine);
           //String template = PropertiesUtils.getStringContextValue("system.test");
           String template = "我的<a href=\"http://localhost?userId=${userId}\">收藏</a>";
           System.out.println(template);
           StringWriter writer = null;  
           Map<String, String> dataMap = new HashMap<>();
           dataMap.put("userId", 1 + "");
           try {  
               writer = new StringWriter();  
               VelocityContext context = new VelocityContext(dataMap);  
               velocityEngine.evaluate(context, writer, "", template);  
               System.out.println(writer.toString());
           } finally {  
               if (writer != null) {  
                   try {  
                       writer.close();  
                   } catch (IOException e) {  
                       e.printStackTrace();
                   }  
               }  
           }  
    }
	
	@Test
	public void getFirstLetterTest() {
		System.out.println(StringUtils.chinese2Spell("中国 中文 empty 靑淼"));
		System.out.println(StringUtils.chinese2Spell("people of china"));
		System.out.println(StringUtils.getChineseFirstLetterl("中国 中文 empty 靑淼"));
		System.out.println(StringUtils.getChineseFirstLetterl("people of china"));
	}
}
