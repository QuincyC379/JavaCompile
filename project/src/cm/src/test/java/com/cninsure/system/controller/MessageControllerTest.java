package com.cninsure.system.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
		"classpath:config/spring-security-config.xml",
		"classpath:config/spring-mvc-config.xml",})

public class MessageControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void testGetMenuTree() throws Exception{
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/message/getNotReadCount").
						contentType(MediaType.APPLICATION_XML).sessionAttr("userId", "1234567890")
						.accept(MediaType.TEXT_PLAIN).characterEncoding("utf-8")
						.param("receiver", "zhangsan")			
						)
						.andDo(MockMvcResultHandlers.print()).andReturn();
		
		Assert.assertNotNull(result.toString());
	}
	@Test
	public void testAdd() throws Exception{
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/message/add").
						contentType(MediaType.APPLICATION_XML).sessionAttr("userId", "1234567890")
						.accept(MediaType.TEXT_PLAIN).characterEncoding("utf-8")
						.param("receiver", "zhangsan")			
						)
						.andDo(MockMvcResultHandlers.print()).andReturn();
		
		Assert.assertNotNull(result.toString());
	}
}
