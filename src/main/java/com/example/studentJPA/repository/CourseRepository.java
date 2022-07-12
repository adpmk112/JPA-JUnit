package com.example.studentJPA.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.studentJPA.model.CourseBean;

@Repository
public interface CourseRepository extends CrudRepository<CourseBean,Integer> {

	@Query(value = "SELECT `id` FROM `course` ORDER BY `id` DESC LIMIT 1",
			nativeQuery= true)
	public Integer selectLastRow();
}
