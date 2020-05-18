package com.amdocs.media.assignement.dto;

import org.modelmapper.ModelMapper;

import com.amdocs.media.assignement.entity.Profiles;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class ProfileDTO {
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	private Long id;
	
	@NotNull
	private String address;
	
	@NotNull
	private String phoneNo;
	
	private Long userId;
	
	public static Profiles toEntity(ProfileDTO profileDTO) {
		Profiles profile =  modelMapper.map(profileDTO, Profiles.class);
		return profile;
	}

}
