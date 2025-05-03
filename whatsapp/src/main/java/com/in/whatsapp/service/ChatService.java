package com.in.whatsapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.whatsapp.enums.MessageStatus;
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
		message.setStatus(MessageStatus.SENT);
		message.setDeliveredTime(LocalDateTime.now());
		chatRepository.save(message);
		
		if(userService.isConnected(message.getReceiverId())) {
			message.setDeliveredTime(LocalDateTime.now());
			message.setStatus(MessageStatus.DELIVERED);
			chatRepository.save(message);
			notificationService.notifyUser(message.getReceiverId(), message.getContent());
		}
	}
	
	
	public int markMessageAsSeen(Long receiverId) {
		if (!userService.isConnected(receiverId)) {
			return -1;
		}
		
		List<Message> unreadMessages = chatRepository.findByReceiverIdAndStatusNot(receiverId, MessageStatus.SEEN);

		if(unreadMessages.isEmpty()) {
			return 0;
		}
		
		for(Message message : unreadMessages) {
			//System.out.println("Before: ID = " + message.getReceiverId() + ", Status = " + message.getStatus());
			if(message.getStatus() == MessageStatus.SENT) {
				message.setDeliveredTime(LocalDateTime.now());
				message.setStatus(MessageStatus.DELIVERED);
			}
			
			if(message.getStatus() == MessageStatus.DELIVERED) {
				message.setDeliveredTime(LocalDateTime.now());
				message.setStatus(MessageStatus.SEEN);
		    }
	    
			//System.out.println("After: ID = " + message.getReceiverId() + ", Status = " + message.getStatus());
		}
		chatRepository.saveAll(unreadMessages);
		return unreadMessages.size();		
   }
	
	
}
