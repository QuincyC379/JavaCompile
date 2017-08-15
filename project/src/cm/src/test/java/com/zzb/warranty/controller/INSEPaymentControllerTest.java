package com.zzb.warranty.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Administrator on 2017/1/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = { "classpath:config/spring-config.xml",
        "classpath:config/spring-security-config.xml",
        "classpath:config/spring-mvc-config.xml",
        "classpath:config/spring-config-db.xml", })
public class INSEPaymentControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testPay() throws Exception {
        MvcResult result = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/warranty/payment/pay")
                                .header("token", "123")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "\t\"orderNo\": \"10001352-20170117103525\",\n" +
                                        "\t\"channelId\": \"baofu\",\n" +
                                        "\t\"payType\": \"wqrcode\",\n" +
                                        "\t\"paySource\": \"zzb.system.core\",\n" +
                                        "\t\"redirectUrl\": \"https://morg.52zzb.com/index.html?v=1482289604467#/insuredInfo/2011440101-1867224\"\n" +
                                        "}")
                )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        int status = response.getStatus();
        String body = response.getContentAsString();
        Assert.assertEquals(200, status);
        Assert.assertNotNull(body);
    }
}
