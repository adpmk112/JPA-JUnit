package com.example.studentJPA.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

import lombok.Data;

@Data
@Entity
@Table(name="course")
public class CourseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String name;
	
	@OneToMany(mappedBy = "courseBean")
	private Set<StudentCourseBean> studentCourseBean;
	
	/*
	 * @OneToOne(mappedBy = "courseBean") 
	 * private StudentCourseBean studentCourseBean;
	 */
	
}
