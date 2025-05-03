package com.in.whatsapp.controller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.whatsapp.model.User;
import com.in.whatsapp.model.UserDTO;
import com.in.whatsapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
//	public User registerUser(@RequestParam String name, @RequestParam String phone) {
	public ResponseEntity<?> registerUser(@RequestBody @Validated UserDTO userDTO) {
		if(userDTO.getName()==null || userDTO.getPhone()==null) {
			return ResponseEntity.badRequest().body("Name and Phone number must not be null.");
		}
		
		User registeredUser = userService.registerUser(userDTO.getName(), userDTO.getPhone());
		return ResponseEntity.ok(registeredUser);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable Long userId){
		Optional<User> userOptional = userService.getUser(userId);
		if(userOptional.isPresent()) {
			return ResponseEntity.ok(userOptional.get());
		}
		return ResponseEntity.status(404).body("User with userId: "+userId+ " NOT FOUND.");
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam Long userId) {
		if(!userService.existsById(userId)) {
			return ResponseEntity.status(404).body("User with userId: "+userId+ " NOT FOUND.");
		}
		
		userService.login(userId);
		return 	ResponseEntity.ok("User logged in successfully.");
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam Long userId) {
		if(!userService.existsById(userId)) {
			return ResponseEntity.status(404).body("User with userId: "+userId+ " NOT FOUND.");
		}
		
		userService.logout(userId);
		return 	ResponseEntity.ok("User logged in successfully.");
	}
}
