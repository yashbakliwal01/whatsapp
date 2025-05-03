package com.in.whatsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.whatsapp.model.MessageDTO;
import com.in.whatsapp.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@PostMapping
	public void sendMessage(@RequestBody @Validated MessageDTO messageDTO) {
		chatService.sendMessage(messageDTO);
		
	}
	
}
