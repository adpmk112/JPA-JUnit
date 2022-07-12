package com.example.studentJPA.controller;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.studentJPA.dao.CourseService;
import com.example.studentJPA.dao.StudentCourseService;
import com.example.studentJPA.dao.StudentService;
import com.example.studentJPA.model.CourseBean;
import com.example.studentJPA.model.StudentBean;
import com.example.studentJPA.model.StudentCourseBean;

@Controller
public class StudentController {
		Integer studId;
		
		@Autowired
		CourseService courseService;
		
		@Autowired
		StudentService studentService;
		
		@Autowired
		StudentCourseService studentCourseService;
	
		@GetMapping("/studentRegisterView")
		public String studentRegisterView(ModelMap model) {
			List<CourseBean>courseList = courseService.selectAll();
			model.addAttribute("courseList", courseList);
			
			studId = studentService.selectLastRow()+1;
			
			StudentBean studentBean = new StudentBean();
			studentBean.setId(studId);
			model.addAttribute("studId", "STU-"+studId);
			model.addAttribute("studentBean",studentBean);
			return "studentRegister";
		}
		
		@PostMapping("/addStudent")
		public String addStudent(StudentBean studentBean,
													ModelMap model,HttpServletRequest request) {
			
			studId = studentService.selectLastRow()+1;
			
			StudentBean requestStudentDto = new StudentBean();
			requestStudentDto.setId(studId);
			requestStudentDto.setName(studentBean.getName());
			requestStudentDto.setBirth(studentBean.getBirth());
			requestStudentDto.setGender(studentBean.getGender());
			requestStudentDto.setPhone(studentBean.getPhone());
			requestStudentDto.setEducation(studentBean.getEducation());
			studentService.createStudent(requestStudentDto);
			
			CourseBean requestCourseDto = new CourseBean();
			for(int i=0;i<studentBean.getAttend().size();i++) {
				requestCourseDto.setId(Integer.valueOf(studentBean.getAttend().get(i)));
				studentCourseService.createStudent_course(requestStudentDto.getId(), requestCourseDto.getId());
			}
			return "redirect:studentView";	
		}
		
		@PostMapping("/studentUpdate")
		public String updateStudent(@ModelAttribute("studentBean") StudentBean studentBean, 
				ModelMap model) {
			
			StudentBean requestStudentDto = new StudentBean();
			requestStudentDto.setId(Integer.valueOf(studentBean.getId()));
			requestStudentDto.setName(studentBean.getName());
			requestStudentDto.setBirth(studentBean.getBirth());
			requestStudentDto.setGender(studentBean.getGender());
			requestStudentDto.setPhone(studentBean.getPhone());
			requestStudentDto.setEducation(studentBean.getEducation());			
			studentService.updateByStudentId(requestStudentDto,requestStudentDto.getId());
						
			studentCourseService.deleteStudentCourseByStudentId(requestStudentDto.getId());
			CourseBean requestCourseDto = new CourseBean();
			for(int i=0;i<studentBean.getAttend().size();i++) {
				requestCourseDto.setId(Integer.valueOf(studentBean.getAttend().get(i)));
				studentCourseService.createStudent_course(requestStudentDto.getId(), requestCourseDto.getId());
			}
			return "redirect:studentView";	
		}
		
		@GetMapping("/deleteStudent/{deleteId}")
		public String deleteStudent(@PathVariable("deleteId")String deleteId) {
			    StudentBean requestStudentDto = new StudentBean();
				requestStudentDto.setId(Integer.valueOf(deleteId));
				studentService.deleteByStudentId(requestStudentDto.getId());
				return "redirect:/studentView";
		}
		
		@GetMapping("/studentView")
		public ModelAndView viewStudent() {
			List<StudentCourseBean>resStudCourseDtoList = 
					studentCourseService.selectAllStudentwithCourseName();
			return new ModelAndView("studentView","studWithCourse",resStudCourseDtoList);
		}
		
		@GetMapping("/fetchStudentView/{fetchId}")
		public ModelAndView fetchStudent(@PathVariable("fetchId")String fetchId,ModelMap model) {
			StudentCourseBean requestStudentCourseDto = new StudentCourseBean();
			requestStudentCourseDto.setStudentId(Integer.valueOf(fetchId));
			
			StudentBean studentBean = new StudentBean();
			List<StudentCourseBean> responseStudentCourseDtoList =studentCourseService
																			.selectOneById(requestStudentCourseDto);
			
			List<String>attendCourseList = new ArrayList<>(); 
			
			StudentCourseBean responseStudentCourseDto = new StudentCourseBean();
			
			Iterator<StudentCourseBean>it = responseStudentCourseDtoList.iterator();
	    	while(it.hasNext()) {
	    		 responseStudentCourseDto = it.next();
	    		studentBean.setId(responseStudentCourseDto.getStudentId());
	 			studentBean.setName(responseStudentCourseDto.getStudentName());
	 			studentBean.setBirth(responseStudentCourseDto.getBirth());
	 			studentBean.setGender(responseStudentCourseDto.getGender());
	 			studentBean.setPhone(responseStudentCourseDto.getPhone());
	 			studentBean.setEducation(responseStudentCourseDto.getEducation());
	 			attendCourseList.add(responseStudentCourseDto.getCourseName());
	    	}
	    	
	    	studentBean.setAttend(attendCourseList);
	    	System.out.println(studentBean.getAttend().get(0));
	    	
			List<CourseBean> courseList = courseService.selectAll();
			model.addAttribute("courseList",courseList);
			return new ModelAndView("studentUpdate","studentBean",studentBean);
		}
		
		@GetMapping("/searchStudentId")
		public String searchUser(@RequestParam("studentId") String id, ModelMap model) {
			StudentCourseBean requestStudentCourseDto = new StudentCourseBean();
			if(id!="") {
				requestStudentCourseDto.setStudentId(Integer.valueOf(id));
				List<StudentCourseBean>responseStudentCourseDtoList = 
						studentCourseService.selectOneById(requestStudentCourseDto);
				model.addAttribute("searchedStudentDto", responseStudentCourseDtoList);
				}
			
			else {
				model.addAttribute("error", "Fill the blank to search");
			}
			return"studentSearched";
		}
}
