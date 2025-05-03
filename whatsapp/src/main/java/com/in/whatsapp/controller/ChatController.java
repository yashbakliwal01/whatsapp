package com.in.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.whatsapp.model.MessageDTO;
import com.in.whatsapp.service.ChatService;
import com.in.whatsapp.service.UserService;

@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	@PostMapping
	public ResponseEntity<String> sendMessage(@RequestBody @Validated MessageDTO messageDTO) {
		if (messageDTO.getSenderId() == null || messageDTO.getReceiverId() == null) {
			return ResponseEntity.badRequest().body("Sender and Receiver must be valid.");
	    }
		
		if (!userService.existsById(messageDTO.getSenderId())) {
	        return ResponseEntity.badRequest().body("Sender ID does not exist.");
	    }

	    if (!userService.existsById(messageDTO.getReceiverId())) {
	        return ResponseEntity.badRequest().body("Receiver ID does not exist.");
	    }

		
		chatService.sendMessage(messageDTO);
		
		return ResponseEntity.ok("Message send successfully!");
	}
	
	
	@PostMapping("/messages/seen/{receiverId}")
	public ResponseEntity<String> markSeen(@PathVariable Long receiverId){
		
		int seenCount = chatService.markMessageAsSeen(receiverId);
		
		if(seenCount==0) {
			return ResponseEntity.ok("No new messages to mark as seen for receiver ID: " + receiverId);
		}else if(seenCount == -1) {
			return ResponseEntity.ok("Cannot mark messages as seen. User is offline."); 
		}else {
			return ResponseEntity.ok(seenCount + " message(s) marked as seen for receiver ID: " + receiverId); 
		}
	}
	
}
