package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.UserManagementApplication;

import junit.framework.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = UserManagementApplication.class
)
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {
	
	public String userAuthorization;
	
	@Before
	public void setup() throws Exception, Exception {
		
		userAuthorization = "Basic " +
	            Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
		
		String response = mockMvc.perform(get("/user/addNewUser")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("password", "qualitypasssword")
                .param("password2", "qualitypasssword")
                .param("userName", "Testy McTesterson")
                .param("first", "Bill")
                .param("last", "Bobaggins")
                .param("height", "47")
                .param("weight", "147")
                .param("age", "37")
                .param("gender", "1"))
                .andReturn().getResponse().getContentAsString();
		
	}

    private static final Logger log = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addNewUser() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void getUser() throws Exception {

    }

    @Test
    public void getAllUsers() throws Exception{
    	
    	String response = mockMvc.perform(get("/user/all")
                .header("Authorization", userAuthorization)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        
        log.info("getAllUsers Response: " + response);
    }

    @Test
    public void userLoginSuccess() throws Exception {
    	String response = mockMvc.perform(get("/user/login")
                .header("Authorization", userAuthorization)
                .param("email", "jUnitTester@tester.com")
                .param("password", "qualitypasssword")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        
        log.info("userLoginSuccess Response: " + response);
    }

}