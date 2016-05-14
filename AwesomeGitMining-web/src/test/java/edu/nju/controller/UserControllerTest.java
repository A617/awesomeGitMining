package edu.nju.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Dora on 2016/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:spring-context.xml")
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
//
//    @Test
//    public void listUsers() throws Exception {
//
//    }
//
//    @Test
//    public void showUser() throws Exception {
//        System.out.println(mockMvc.perform(get("/gregwinn")).toString());
//    }
//
//    @Test
//    public void searchUser() throws Exception {
//
//    }

}