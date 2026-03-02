package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.UserRepository;
import com.exception.ResourceNotFoundException;
import com.model.User;


@Service
public  class UserServiceImpl  implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}	

	@Override
	public User loginUser(String email, String password) {

	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Invalid Email"));

	    if (!user.getPassword().equals(password)) {
	        throw new RuntimeException("Invalid Password");
	    }

	    return user;
	}
	@Override
	public User findByUsername(String username) {

	    return userRepository.findByUsername(username)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("User not found with username: " + username));
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		
		return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found with id" + id));
    }
	
	 @Override
	    public User updateUser(Long userId, User updatedUser) {

	        User user = getUserById(userId);
	        if (updatedUser.getUsername() != null) {
	            user.setUsername(updatedUser.getUsername());
	        }
	        if (updatedUser.getEmail() != null) {
	            user.setEmail(updatedUser.getEmail());
	        }
	        if (updatedUser.getPassword() != null) {
	            user.setPassword(updatedUser.getPassword());
	        }      
	        return userRepository.save(user);
	    }

	@Override
	public void deleteUser(Long id) {
		
		User user = getUserById(id);
		userRepository.delete(user);
				
	}
	
	@Override
    public User getOneUserByEmail(String email) {
       
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found with email: " + email));
    }

	
}
