package com.service;

import java.util.List;

import com.model.User;

public interface UserService {
	
	public User createUser(User user);
	
	public User loginUser(String email,String password);
	
	public User findByUsername(String username);
	
	public List<User> getAllUsers();
	
	public User getUserById(Long id);
	
	public User updateUser(Long id, User userDetails);
	
	public void deleteUser(Long id);
	
	public User getOneUserByEmail(String email) ;
	
	
	
	
}
	
