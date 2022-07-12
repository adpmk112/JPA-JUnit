package com.example.studentJPA.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name="student")
public class StudentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name, birth, gender, phone, education;
	@Transient
	private List<String>attend; 
	//private String attend[];
	
	@OneToMany(mappedBy = "studentBean")
	private Set<StudentCourseBean> studentCourseBean;
	/*
	 * @OneToOne(mappedBy = "studentBean") private StudentCourseBean
	 * studentCourseBean;
	 */
	
}
