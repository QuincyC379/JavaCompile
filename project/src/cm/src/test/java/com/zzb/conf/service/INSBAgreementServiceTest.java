package com.zzb.conf.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
		"classpath:config/spring-security-config.xml",
		"classpath:config/spring-mvc-config.xml",
		"classpath:config/spring-config-db.xml", })
public class INSBAgreementServiceTest {

	@Resource
	private INSBAgreementService s;

	
	@Test
	public void initEditePageDataTest() {

//		Map<String, Object> result = s.initEditePageData(null);
//		
//		
//		System.out.println(result);
	}

}