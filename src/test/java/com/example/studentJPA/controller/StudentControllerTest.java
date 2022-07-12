package com.example.studentJPA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.studentJPA.dao.StudentService;
import com.example.studentJPA.repository.StudentRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	StudentService studentService;
	
	@MockBean
	StudentRepository studRepo;
}
