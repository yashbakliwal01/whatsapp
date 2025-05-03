package com.in.whatsapp.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.validation.annotation.Validated;

import com.in.whatsapp.enums.ContentType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDTO {
	
	@NotNull
	private Long senderId;
	
	@NotNull
	private Long receiverId;
	
	@NotNull
	private String content;
	
	@NotNull
	private ContentType contentType;
	
	@CreatedDate
	private LocalDateTime createdOn;
}
