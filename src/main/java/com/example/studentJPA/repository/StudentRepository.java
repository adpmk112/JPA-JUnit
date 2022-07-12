package com.example.studentJPA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.studentJPA.model.StudentBean;
@Repository
public interface StudentRepository extends CrudRepository<StudentBean,Integer> {
	
	@Query(value = "SELECT `id` FROM `student` ORDER BY `id` DESC LIMIT 1",
			nativeQuery= true)
	public Integer selectLastRow();
	
	@Query(value = "select * from student where name=?", nativeQuery= true)
	public List<StudentBean> selectOneByName(String name);
}
