package com.in.whatsapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.whatsapp.model.Message;
import com.in.whatsapp.model.MessageDTO;
import com.in.whatsapp.repository.ChatRepository;

@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notificationService;
	
	public void sendMessage(MessageDTO messageDTO) {
		Message message = new Message();
		message.setSenderId(messageDTO.getSenderId());
		message.setReceiverId(messageDTO.getReceiverId());
		message.setContent(messageDTO.getContent());
		message.setContentType(messageDTO.getContentType());
		chatRepository.save(message);
		
		if(userService.isConnected(message.getReceiverId())) {
			notificationService.notifyUser(message.getReceiverId(), message.getContent());
		}
	}
}
