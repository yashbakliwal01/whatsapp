package com.in.whatsapp.service;


import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.whatsapp.model.User;
import com.in.whatsapp.repository.UserRepository;

@Service
public class UserService {

	private Set<Long> connectedUsers = ConcurrentHashMap.newKeySet();
	
    @Autowired
    private UserRepository userRepository;

    public User registerUser(String name, String phone){
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        return userRepository.save(user);
    }
    
    public Optional<User> getUser(Long userId){
    	return userRepository.findById(userId);
    }
    
    
    public void login(Long userId) {
    	connectedUsers.add(userId);
    }
    
    
    public void logout(Long userId) {
    	connectedUsers.remove(userId);
    }
    
    public boolean isConnected(Long userId) {
		return connectedUsers.contains(userId);
	}
    
    public boolean existsById(Long userId) {
    	return userRepository.existsById(userId);
    }
    
}
