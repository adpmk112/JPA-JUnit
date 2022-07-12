package com.example.studentJPA.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentJPA.model.UserBean;
import com.example.studentJPA.repository.UserRepository;


@Service("userService")
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public void createUser(UserBean userBean) {
		userRepository.save(userBean);
	}
	
	public void updateByUserId(UserBean userBean,Integer userId) {
		// TODO Auto-generated method stub
		userRepository.save(userBean);
	}

	public void deleteByUserId(Integer id) {
		userRepository.deleteById(id);
	}

	public Optional<UserBean> selectOneById(Integer id) {
		return userRepository.findById(id);
	}

	public UserBean selectOneByEmail(String email) {
		return userRepository.selectOneByEmail(email);
	}

	public List<UserBean> selectAll() {
		List<UserBean>userList = (List<UserBean>)userRepository.findAll();
		return userList;
	}
}
