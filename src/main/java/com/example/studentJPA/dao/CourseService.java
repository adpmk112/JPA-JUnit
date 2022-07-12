package com.example.studentJPA.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studentJPA.model.CourseBean;
import com.example.studentJPA.repository.CourseRepository;

@Service()
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	public void createCourse(CourseBean courseBean) {
		courseRepository.save(courseBean);
	}
	
	public List<CourseBean> selectAll() {
		List<CourseBean>courseList = (List<CourseBean>)
										courseRepository.findAll();
		return courseList;
	}
	
	public Optional<CourseBean> selectById(Integer id) {
		return courseRepository.findById(id);
	}
	
	public Integer selectLastRow() {
		return courseRepository.selectLastRow();
	}
}
