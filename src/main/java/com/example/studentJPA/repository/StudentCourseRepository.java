package com.example.studentJPA.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.studentJPA.model.StudentCourseBean;

@Repository

public interface StudentCourseRepository extends CrudRepository<StudentCourseBean,Integer> {
	@Modifying
	@Transactional
	@Query(value= "insert into student_course (student_id, course_id) values(?,?);",
			nativeQuery= true)
	public void createStudent_course(Integer student_id,Integer course_id);
	
	@Modifying
	@Transactional
	@Query(value = "Delete from `student_course` where `course_id` = ? ;", nativeQuery= true)
	public void deleteStudentCourseByCourseId(Integer course_id);
	
	@Modifying
	@Transactional
	@Query(value = "Delete from `student_course` where `student_id` = ? ;", nativeQuery= true)
	public void deleteStudentCourseByStudentId(Integer student_id);
	
	@Modifying
	@Transactional
	@Query(value = " select s.`id` AS `student_id`, s.`name` AS `student_name`, s.`birth`, s.`gender`,"
			+ "s.`phone`, s.`education`, c.`id` AS `course_id`, c.`name` AS `course_name` from `student`"
			+ "s join `student_course` sc join `course` c on s.`id`=sc.`student_id` and c.id=sc.`course_id` "
			+ "where s.`id`=?;", nativeQuery= true)
	public List<StudentCourseBean> selectByStudentId(Integer student_id);
	
	String sql = "select s.`id` AS `student_id` ,s.`name` AS `student_name`,"
			+ "s.`birth`, s.`gender`, s.`phone`, s.`education`,"
			+ "c.`id` AS `course_id`,  c.`name` AS `course_name` from "
			+ "`student` s join `student_course` sc join `course` c on"
			+ " s.`id`=sc.`student_id` and c.`id`=sc.`course_id`;";
	
	@Modifying
	@Transactional
	@Query(value = sql, nativeQuery= true)
	public List<StudentCourseBean> selectAllStudentwithCourseName();
}
