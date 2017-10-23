package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.UserManagementApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.*;

/**
 * Created by Hai on 10/23/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = UserManagementApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {



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
    public void getAllUsers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/user/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse());
    }

    @Test
    public void userLogin() throws Exception {

    }

}