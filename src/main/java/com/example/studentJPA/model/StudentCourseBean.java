package com.example.studentJPA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Data
@Entity
@Table(name="student_course")
public class StudentCourseBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private int id;
	
	@Transient
	private int studentId; 
	@Transient
	private int courseId;
	@Transient
	private String studentName, birth, gender, phone, education, courseName;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="student_id")
	private StudentBean studentBean;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="course_id")
	private CourseBean courseBean;

	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "student_id", referencedColumnName = "id") private
	 * StudentBean studentBean;
	 * 
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "course_id", referencedColumnName = "id") private
	 * CourseBean courseBean;
	 */
}
