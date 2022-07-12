package com.example.studentJPA.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.studentJPA.dao.StudentCourseService;
import com.example.studentJPA.model.CourseBean;
import com.example.studentJPA.model.StudentBean;
import com.example.studentJPA.repository.StudentCourseRepository;

@SpringBootTest
public class StudentCourseServiceTest {

	@Mock
	StudentCourseRepository studentCourseRepo;
	
	@InjectMocks
	StudentCourseService studentCourseService;
	
	@Test
	public void createStudentCourse() {
		StudentBean studentDto = new StudentBean();
		studentDto.setId(1);
		studentDto.setName("Kimi");
		studentDto.setPhone("1122");
		
		 CourseBean courseDto = new CourseBean();
	     courseDto.setId(1);
	     courseDto.setName("java");
	      
	      studentCourseService.createStudent_course(studentDto.getId(), courseDto.getId());
	      verify(studentCourseRepo,times(1)).createStudent_course(studentDto.getId(), courseDto.getId());
	}
	
	@Test
	public void deleteStudentCourseByCourseId() {
		studentCourseService.deleteStudentCourseByCourseId(1);
		verify(studentCourseRepo,times(1)).deleteStudentCourseByCourseId(1);
	}
	
	@Test
	public void deleteStudentCourseByStudentId() {
		studentCourseService.deleteStudentCourseByStudentId(1);
		verify(studentCourseRepo,times(1)).deleteStudentCourseByStudentId(1);
	}
	
}
