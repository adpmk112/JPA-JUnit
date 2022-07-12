package com.example.studentJPA.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.studentJPA.dao.CourseService;
import com.example.studentJPA.model.CourseBean;

@Controller
@RequestMapping("/course")
public class CourseController {
	@Autowired
	CourseService courseService;
	
	@GetMapping("/view")
	public ModelAndView courseView(Model model) {
		CourseBean courseBean = new CourseBean();
		courseBean.setId(courseService.selectLastRow()+1);
		return new ModelAndView("courseRegister","courseBean",courseBean);
	}
	
	@PostMapping("/add")
	public String addCourse(CourseBean courseBean) {
		courseService.createCourse(courseBean);
		return "redirect:view";
	}
}
