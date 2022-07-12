package com.example.studentJPA.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.studentJPA.model.StudentCourseBean;
import com.example.studentJPA.repository.StudentCourseRepository;

@Service
@Transactional
public class StudentCourseService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unused")
	private StudentCourseBean mapRowToStudentCourse(ResultSet row, int rowNum) throws SQLException{
		StudentCourseBean studentCourseDto = new StudentCourseBean();
		studentCourseDto.setStudentId(row.getInt("student_id"));
		studentCourseDto.setCourseId(row.getInt("course_id"));
		studentCourseDto.setStudentName(row.getString("student_name"));
		studentCourseDto.setBirth(row.getString("birth"));
		studentCourseDto.setGender(row.getString("gender"));
		studentCourseDto.setPhone(row.getString("phone"));
		studentCourseDto.setEducation(row.getString("education"));
		studentCourseDto.setCourseName(row.getString("course_name"));
		return studentCourseDto;
	}	
	
	public List<StudentCourseBean> selectOneById(StudentCourseBean studentCourseDto) {
		String sql = "select s.`id` AS `student_id`, s.`name` AS `student_name`, s.`birth`, s.`gender`, "
				+ "s.`phone`, s.`education`, c.`id` AS `course_id`, c.`name` AS `course_name` from `student` "
				+ "s join `student_course` sc join `course` c on s.`id`=sc.`student_id` and c.id=sc.`course_id` "
				+ "where s.`id`=?";
		
		List<StudentCourseBean> results = this.jdbcTemplate.query(sql, 
				this::mapRowToStudentCourse,
				studentCourseDto.getStudentId());
		
		List<StudentCourseBean> studentCourseDtoList = new ArrayList<>();
		
		if(results.size()>0) {
			studentCourseDtoList = results;
		}
		return studentCourseDtoList;
	}

	public List<StudentCourseBean> selectAllStudentwithCourseName() {

		String sql = "select s.`id` AS `student_id` ,s.`name` AS `student_name`,"
				+ "s.`birth`, s.`gender`, s.`phone`, s.`education`,"
				+ "c.`id` AS `course_id`,  c.`name` AS `course_name` from "
				+ "`student` s join `student_course` sc join `course` c on"
				+ " s.`id`=sc.`student_id` and c.`id`=sc.`course_id`;";
		
		return this.jdbcTemplate.query(sql, 
				this::mapRowToStudentCourse);
	}
	
	@Autowired
	StudentCourseRepository  studentCourseRepository;
	
	public void createStudent_course(Integer studentId,Integer courseId) {
		studentCourseRepository.createStudent_course(studentId, courseId);
	}
	
	public void deleteStudentCourseByCourseId(Integer courseId) {
		studentCourseRepository.deleteStudentCourseByCourseId(courseId);
	}
	
	public void deleteStudentCourseByStudentId(Integer studentId) {
		studentCourseRepository.deleteStudentCourseByStudentId(studentId);
	}
}
