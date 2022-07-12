package com.example.studentJPA.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.studentJPA.dao.UserService;
import com.example.studentJPA.model.UserBean;
import com.example.studentJPA.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
	@Mock
	UserRepository userRepo;
	
	@InjectMocks
	UserService userService;
	
	@Test
	public void createUser() {
		UserBean userDto = new UserBean();
		userDto.setEmail("user123@gmail.com");
		userDto.setPassword("112233");
		userService.createUser(userDto);
		verify(userRepo,times(1)).save(userDto);
	}
	
	@Test
	public void updateByUserId() {
		UserBean userDto = new UserBean();
		userDto.setEmail("keeper@gmail.com");
		userDto.setPassword("12345");
		userService.updateByUserId(userDto, 1);
		verify(userRepo,times(1)).save(userDto);
	}
	
	@Test
	public void deleteByUserId() {
		userService.deleteByUserId(1);
		verify(userRepo,times(1)).deleteById(1);
	}
	
	@Test
	public void selectOneById() {
		
		UserBean userDto = new UserBean();
		userDto.setId(1);
		userDto.setEmail("user123@gmail.com");
		userDto.setPassword("112233");
		
		Optional<UserBean> optionalUserDto = Optional.of(userDto);
		when(userRepo.findById(1)).thenReturn(optionalUserDto);
		
		Optional<UserBean> userMatchDto = userService.selectOneById(1);
		assertEquals(1,userMatchDto.get().getId());
		assertEquals("user123@gmail.com", userMatchDto.get().getEmail());
		assertEquals("112233",userMatchDto.get().getPassword());

	}
	
	@Test
	public void selectOneByEmail() {
		
		String email = "user123@gmail.com";
		
		UserBean userDto = new UserBean();
		userDto.setId(1);
		userDto.setEmail(email);
		userDto.setPassword("112233");
		
		when(userRepo.selectOneByEmail(email)).thenReturn(userDto);
		
		UserBean userMatchDto = userService.selectOneByEmail(email);
		assertEquals(1,userMatchDto.getId());
		assertEquals("user123@gmail.com", userMatchDto.getEmail());
		assertEquals("112233",userMatchDto.getPassword());
	
	}
	
	@Test
	public void selectAll() {
		List<UserBean>list = new ArrayList<>();
		
		UserBean userDto = new UserBean();
		userDto.setId(1);
		userDto.setEmail("user123@gmail.com");
		userDto.setPassword("112233");
		
		UserBean userDto1 = new UserBean();
		userDto1.setId(2);
		userDto1.setEmail("keeper@gmail.com");
		userDto1.setPassword("12345");
		
		list.add(userDto);
		list.add(userDto1);
		
		when(userRepo.findAll()).thenReturn(list);
		
		List<UserBean> userList = userService.selectAll();
		assertEquals(2,userList.size());
	}

}
