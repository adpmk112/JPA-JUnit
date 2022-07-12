package com.example.studentJPA.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.studentJPA.dao.StudentService;
import com.example.studentJPA.model.CourseBean;
import com.example.studentJPA.model.StudentBean;
import com.example.studentJPA.model.UserBean;
import com.example.studentJPA.repository.StudentRepository;

@SpringBootTest
public class StudentServiceTest {
		
	@Mock
	StudentRepository studRepo;
	
	@InjectMocks
	StudentService studService;
	
	@Test
	public void createStudent() {
		StudentBean studentDto = new StudentBean();
		studentDto.setId(1);
		studentDto.setName("LP");
		
		studService.createStudent(studentDto);
		verify(studRepo,times(1)).save(studentDto);
	}
	
	@Test
	public void updateStudent() {
		StudentBean studentDto = new StudentBean();
		studentDto.setId(1);
		studentDto.setName("Kimi");
		studentDto.setPhone("1122");
		studService.updateByStudentId(studentDto, 1);
		verify(studRepo,times(1)).save(studentDto);
	}
	
	@Test
	public void deleteStudentById() {
		studService.deleteByStudentId(1);
		verify(studRepo,times(1)).deleteById(1);
	}
	
	@Test
	public void selectOneById() {
		
		StudentBean studentDto = new StudentBean();
		studentDto.setId(1);
		studentDto.setName("Gojo");
		studentDto.setPhone("1122");
		
		Optional<StudentBean> optionalStudentDto = Optional.of(studentDto);
		when(studRepo.findById(1)).thenReturn(optionalStudentDto);
		
		Optional<StudentBean> studentMatchDto = studService.selectOneById(1);
		assertEquals(1,studentMatchDto.get().getId());
		assertEquals("Gojo", studentMatchDto.get().getName());
		assertEquals("1122",studentMatchDto.get().getPhone());
		
	}
	
	@Test
	public void selectOneByName() {
		
		StudentBean studentDto = new StudentBean();
		studentDto.setId(1);
		studentDto.setName("Gojo");
		studentDto.setPhone("1122");
		
		List<StudentBean> studentDtoList = new ArrayList<>();
		studentDtoList.add(studentDto);
		when(studRepo.selectOneByName("Gojo")).thenReturn(studentDtoList);
		
		List<StudentBean> studentMatchDto = studService.selectOneByName("Gojo");
		assertEquals(1,studentMatchDto.get(0).getId());
		assertEquals("Gojo", studentMatchDto.get(0).getName());
		assertEquals("1122",studentMatchDto.get(0).getPhone());
		
	}
	
	@Test
	public void selectAll() {
		List<StudentBean>list = new ArrayList<>();
		
		StudentBean studentDto = new StudentBean();
		studentDto.setId(1);
		studentDto.setName("Gojo");
		studentDto.setPhone("1122");
		
		StudentBean studentDto1 = new StudentBean();
		studentDto.setId(2);
		studentDto.setName("Satouru");
		studentDto.setPhone("1111");
		
		list.add(studentDto);
		list.add(studentDto1);
		
		when(studRepo.findAll()).thenReturn(list);
		
		List<StudentBean> studentList = studService.selectAll();
		assertEquals(2,studentList.size());
	}
	
    @Test
    public void selectLastRow() {
        StudentBean studentDto = new StudentBean();

        studentDto.setId(1);
		studentDto.setName("Gojo");
		studentDto.setPhone("1122");

        when(studRepo.selectLastRow()).thenReturn(studentDto.getId());
    }
}
