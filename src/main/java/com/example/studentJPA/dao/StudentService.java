package com.example.studentJPA.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentJPA.model.StudentBean;
import com.example.studentJPA.repository.StudentRepository;

@Service()
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	public void createStudent(StudentBean studentBean) {
		studentRepository.save(studentBean);
	}
	
	public void updateByStudentId(StudentBean studentBean, Integer id) {
		studentRepository.save(studentBean);
	}
	
	public void deleteByStudentId(Integer id) {
		studentRepository.deleteById(id);
	}
	
	public Optional<StudentBean> selectOneById(Integer id) {
	    return studentRepository.findById(id);
	}
	
	public List<StudentBean> selectOneByName(String name) {
	    return studentRepository.selectOneByName(name);
	}
	
	public List<StudentBean> selectAll() {
	    List<StudentBean> studentList = (List<StudentBean>) studentRepository.findAll();
		return studentList;
	}
	
	public Integer selectLastRow() {
		return studentRepository.selectLastRow();
	}
}
