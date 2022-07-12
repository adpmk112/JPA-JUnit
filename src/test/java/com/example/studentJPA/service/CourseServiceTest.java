package com.example.studentJPA.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.example.studentJPA.dao.CourseService;
import com.example.studentJPA.model.CourseBean;
import com.example.studentJPA.repository.CourseRepository;

@SpringBootTest
public class CourseServiceTest {

    @Mock
    CourseRepository courseRepo;

    @InjectMocks
    CourseService courseService;

    @Test
    public void createCourseTest() {
        CourseBean courseDto = new CourseBean();
        courseDto.setId(1);
        courseDto.setName("java");
        courseService.createCourse(courseDto);
        verify(courseRepo,times(1)).save(courseDto);
    }

    @Test
    public void selectAllTest() {
        List<CourseBean>list = new ArrayList<>();

        CourseBean courseDto1 = new CourseBean();
        courseDto1.setId(1);
        courseDto1.setName("java");

        list.add(courseDto1);


        CourseBean courseDto2 = new CourseBean();
        courseDto2.setId(2);
        courseDto2.setName("Ruby");

        list.add(courseDto2);

        when(courseRepo.findAll()).thenReturn(list);

        List<CourseBean>courseList = courseService.selectAll();
        assertEquals(2,courseList.size());
        verify(courseRepo,times(1)).findAll();
    }
    
    @Test
    public void selectById() {
    	CourseBean courseDto = new CourseBean();
    	courseDto.setId(1);
    	courseDto.setName("java");
    	
    	Optional<CourseBean>optionalCourseDto = Optional.of(courseDto);
    	when(courseRepo.findById(1)).thenReturn(optionalCourseDto);
    	
    	Optional<CourseBean> matchCourseDto = courseService.selectById(1);
    	assertEquals(1,matchCourseDto.get().getId());
    	assertEquals("java",matchCourseDto.get().getName());
    	
    }

    @Test
    public void selectLastRow() {
        CourseBean courseDto = new CourseBean();

        courseDto.setId(1);
        courseDto.setName("Java");

        when(courseRepo.selectLastRow()).thenReturn(courseDto.getId());
    }
}