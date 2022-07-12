package com.example.studentJPA.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.studentJPA.dao.UserService;
import com.example.studentJPA.model.UserBean;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	String confirmPassword;
	
	@GetMapping("/view")
	public ModelAndView userView(ModelMap model) {
		List<UserBean> resUserDtoList = new ArrayList<>();
		resUserDtoList = userService.selectAll();
		model.addAttribute("resUserDtoList", resUserDtoList);
		return new ModelAndView("userView","userBean",new UserBean());
	}
	
	@GetMapping("/register")
	public ModelAndView registerView() {
		return new ModelAndView("userRegister","userBean",new UserBean());
	}
	
	@PostMapping("/add")
	public String addUser(@ModelAttribute("userBean") UserBean userBean, ModelMap model) {
		UserBean requestUserDto = new UserBean();
		requestUserDto.setEmail(userBean.getEmail());
		requestUserDto.setPassword(userBean.getPassword());
		confirmPassword = userBean.getConfirmPassword();
		
		if(requestUserDto.getPassword().equals(confirmPassword)) {
			userService.createUser(requestUserDto);
			return "redirect:/user/view";
		}
		else {
			model.addAttribute("error","Something is wrong in email and password.");
			return "redirect:/user/register";
		}
	}
	
	@GetMapping("/updateView/{updateId}")
	public ModelAndView fetchUserToUpdate(@PathVariable("updateId") String updateId, Model model) {
		UserBean requestUserDto = new UserBean();	
		
		requestUserDto.setId(Integer.valueOf(updateId));
		Optional<UserBean>responseUserDto  = userService.selectOneById(requestUserDto.getId());
		model.addAttribute("fetchedUserData",responseUserDto.get());
		UserBean userBean =new UserBean();
		userBean.setId(responseUserDto.get().getId());
		userBean.setEmail(responseUserDto.get().getEmail());
		userBean.setPassword(responseUserDto.get().getPassword());
		return new ModelAndView("userUpdate","userBean",userBean);
	}
	
	@PostMapping("/update")
	public String updateUser(@ModelAttribute("userBean") UserBean userBean, ModelMap model){
		UserBean requestUserDto = new UserBean();	
		requestUserDto.setId(Integer.valueOf(userBean.getId()));
		requestUserDto.setEmail(userBean.getEmail());
		requestUserDto.setPassword(userBean.getPassword());
		userService.updateByUserId(requestUserDto,requestUserDto.getId());
		return "redirect:/user/view";
	}
	
	@GetMapping("/delete/{deleteId}")
	public String deleteUser(@PathVariable("deleteId")String deleteId) {
		UserBean requestUserDto = new UserBean();	
		requestUserDto.setId(Integer.valueOf(deleteId));
		userService.deleteByUserId(requestUserDto.getId());
		return "redirect:/user/view";
	}
	
	@GetMapping("/search")
	public ModelAndView searchUser(@ModelAttribute("userBean") UserBean userBean, ModelMap model) {
		UserBean requestUserDto = new UserBean();
		UserBean responseUserDto = new UserBean();	
		
		if(Optional.ofNullable(userBean.getId()) != null) {
			requestUserDto.setId(userBean.getId());
			Optional<UserBean>responseUserDto1 = userService.selectOneById(requestUserDto.getId());
			model.addAttribute("searchedUserDto", responseUserDto1.get());
			}
		
		else if(userBean.getEmail()!=""){
			requestUserDto.setEmail(userBean.getEmail());
			responseUserDto = userService.selectOneByEmail(requestUserDto.getEmail());
			model.addAttribute("searchedUserDto", responseUserDto);
			}
		
		else {
			model.addAttribute("error", "Fill the blank to search");
		}
		return new ModelAndView("userSearched","userBean",userBean);
	}
}