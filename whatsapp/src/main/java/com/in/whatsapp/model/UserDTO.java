package com.in.whatsapp.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDTO {
	
	@NotNull
	private String name;
	
	@NotNull(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{10}$", message="Phone numbers must be exactly 10 digits")
	private String phone;
	
}
