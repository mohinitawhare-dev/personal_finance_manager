package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exception.ResourceNotFoundException;
import com.model.User;
import com.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	
	private UserService userService;
	
	 public UserController(UserService userService) {
	        this.userService = userService;
	    }
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}
	
		
	 @PostMapping("/login")
	    public ResponseEntity<User> loginUser(@RequestBody User user) {
	        return ResponseEntity.ok(
	                userService.loginUser(user.getEmail(),user.getPassword()));
	        }
	 
	 @GetMapping("find/{username}")
	 public User getUserByUsername(@PathVariable String username) {
		 return userService.findByUsername(username);
	 }
	 
	 @GetMapping("get/{id}")
	   public ResponseEntity<?> getUserById(@PathVariable Long id) {
		 
		 try {
			 return ResponseEntity.ok(userService.getUserById(id));
		 } catch (ResourceNotFoundException ex) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		 }
	 }
	 
	 @PutMapping("update/{id}")
	 public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User users) {
		 
		 try {
			 return ResponseEntity.ok(userService.updateUser(id, users));
		 } catch (ResourceNotFoundException ex) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(users);
		 }
	 }
	 
	 @DeleteMapping("delete/{id}")
	 public ResponseEntity<String> deleteUser(@PathVariable Long id) {
	     try {
	         userService.deleteUser(id);
	         return ResponseEntity.ok("User deleted successfully");
	     } catch (ResourceNotFoundException ex) {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	     }
	 }
	 @GetMapping("/getOneEmail/{email}")
	    public ResponseEntity<User> getOneUserByEmail(@PathVariable String email) {
	        try {
	            User s = userService.getOneUserByEmail(email);  // Use userService
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .header("get", "get one record")
	                    .body(s);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	   }
}