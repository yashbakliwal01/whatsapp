package com.in.whatsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.whatsapp.enums.MessageStatus;
import com.in.whatsapp.model.Message;

@Repository
public interface ChatRepository extends JpaRepository<Message, Long>{

	//retrieves all messages received by a specific user that do NOT have the given status(unseen or unread messages)
	List<Message> findByReceiverIdAndStatusNot(Long receiverId, MessageStatus status);
	
	List<Message> findByReceiverIdAndStatus(Long receiverId, MessageStatus status);
	
}
