package com.example.studentJPA.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.studentJPA.dao.CourseService;
import com.example.studentJPA.model.CourseBean;
import com.example.studentJPA.repository.CourseRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CourseService courseService;
	
	@MockBean
	CourseRepository courseRepo;
	
	@Test
	public void testCourseView() throws Exception{
		this.mockMvc.perform(get("/course/view"))
		.andExpect(status().isOk())
		.andExpect(view().name("courseRegister"))
		.andExpect(model().attributeExists("courseBean"));
	}
	
	@Test
	public void addCourseTest() throws Exception{
		CourseBean courseDto = new CourseBean();
		courseDto.setId(1);
		courseDto.setName("Java");
		this.mockMvc.perform(post("/course/add").flashAttr("courseBean", courseDto))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("view"));
	}
}
