package com.example.studentJPA.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.studentJPA.dao.UserService;
import com.example.studentJPA.model.UserBean;
import com.example.studentJPA.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@MockBean 
	UserRepository userRepository;
	
	@Test
	public void loginViewTest() throws Exception{
			this.mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(view().name("login"))
			.andExpect(model().attributeExists("userBean"));
	}
	
	@Test
	public void loginTest() throws Exception{
		UserBean userDto = new UserBean();
		userDto.setEmail("user123@gmail.com");
		userDto.setPassword("112233");
		userDto.setConfirmPassword("112233");
		
		when(userService.selectOneByEmail(userDto.getEmail())).thenReturn(userDto);
		//this.mockMvc.perform(post("/login").flashAttr("session", userDto.getEmail()).flashAttr("date", new Date()));
		}
	
	@Test
	public void logOutTest() throws Exception{
		this.mockMvc.perform(get("/logOut"))
		.andExpect(redirectedUrl("/login"));
	}
	
	@Test
	public void menuTest() throws Exception{
		this.mockMvc.perform(get("/menu"))
		.andExpect(status().isOk())
		.andExpect(view().name("menu"));
	}
}
