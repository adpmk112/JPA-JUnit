package com.example.studentJPA.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

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
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@MockBean
	UserRepository userRepository;
	
	@Test
	public void testUserView() throws Exception{
		this.mockMvc.perform(get("/user/view"))
		.andExpect(status().isOk())
		.andExpect(view().name("userView"))
		.andExpect(model().attributeExists("resUserDtoList"))
		.andExpect(model().attributeExists("userBean"));
	}
	
	@Test
	public void testUserRegisterView() throws Exception{
		this.mockMvc.perform(get("/user/register"))
		.andExpect(status().isOk())
		.andExpect(view().name("userRegister"))
		.andExpect(model().attributeExists("userBean"));
	}
	
	@Test
	public void fetchUserTest() throws Exception{
		UserBean userDto = new UserBean();
		userDto.setId(1);
		
		Optional<UserBean>optionalUserDto = Optional.of(new UserBean());
		when(userService.selectOneById(userDto.getId())).thenReturn(optionalUserDto);
	}
	
	@Test
	public void updateUserTest() throws Exception{
		UserBean userDto = new UserBean();
		userDto.setId(1);
		userDto.setEmail("user111@gmail.com");
		userDto.setPassword("112233");
		this.mockMvc.perform(post("/user/update"))
		.andExpect(redirectedUrl("/user/view"));
	}
}
