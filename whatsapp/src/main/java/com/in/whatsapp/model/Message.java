package com.in.whatsapp.model;

import java.time.LocalDateTime;

import com.in.whatsapp.enums.ContentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="messages")
@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long messageId;
	private Long senderId;
	private Long receiverId;
	private String content;
	private ContentType contentType;
	private LocalDateTime createdOn;
	

}
