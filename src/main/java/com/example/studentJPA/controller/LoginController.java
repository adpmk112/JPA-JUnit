package com.example.studentJPA.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.studentJPA.dao.UserService;
import com.example.studentJPA.model.UserBean;

@Controller
public class LoginController {
	    
	   @Autowired
	   private UserService userService;
	  
	   @GetMapping("/login")
	   public ModelAndView loginView() {
		   UserBean userBean = new UserBean();
		   return new ModelAndView("login","userBean",userBean);
	   }
	   
	   @PostMapping("/login")
	   public String login(UserBean userBean, HttpSession session,ModelMap model) {
		   UserBean responseUserDto = new UserBean();
		   responseUserDto = userService.selectOneByEmail(userBean.getEmail());
		   
			if (userBean.getEmail().equals(responseUserDto.getEmail())
					&& userBean.getPassword().equals(responseUserDto.getPassword())) {
				session.setAttribute("userSession", responseUserDto.getEmail());
				session.setAttribute("date", new Date());
				return "menu";
			} 
			
			else {
				model.addAttribute("error","The account name or password that you have entered is incorrect");
				return "login";
			}
	   }
	   
	   @GetMapping("/logOut")
	   public String logout(ModelMap model,HttpSession session) {
		   session.removeAttribute("userSession");
		   session.invalidate();
		   return "redirect:/login";
	   }
	   
		@RequestMapping(value="/menu",method= RequestMethod.GET)
		public String menu() {
			return "menu";
		}
}
