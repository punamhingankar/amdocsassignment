package com.amdocs.media.assignement.dto;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class ProfileInDTO {
	
	
	private Long id;
	
	@NotNull
	private String address;
	
	@NotNull
	private String phoneNo;
	
	private Long userId;

}
