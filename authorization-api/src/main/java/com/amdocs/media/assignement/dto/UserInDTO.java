package com.amdocs.media.assignement.dto;



import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class UserInDTO {
	
	private Long id;
	
	@NotNull
	private String userName;
	
	@NotNull
	private String password;
	
	
}
