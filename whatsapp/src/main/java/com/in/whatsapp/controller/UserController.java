package com.in.whatsapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public User registerUser(@RequestBody @Validated UserDTO userDTO) {
		return userService.registerUser(userDTO.getName(), userDTO.getPhone());
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable Long userId){
		Optional<User> userOptional = userService.getUser(userId);
		if(userOptional.isPresent()) {
			return userOptional.get();
		}
		throw new RuntimeException("User Not Found!!!");
	}
	
	
	@PostMapping("/login")
	public void login(@RequestParam Long userId) {
		userService.login(userId);
	}
	
	@PostMapping("/logout")
	public void logout(@RequestParam Long userId) {
		userService.logout(userId);
	}
}
