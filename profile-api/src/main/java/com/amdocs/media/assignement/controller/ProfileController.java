package com.amdocs.media.assignement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.media.assignement.dto.OutDTO;
import com.amdocs.media.assignement.dto.ProfileDTO;
import com.amdocs.media.assignement.service.ProfileService;

@RestController
@CrossOrigin("*")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@PostMapping("/profile")
	private ResponseEntity<OutDTO> createProfile(@RequestBody ProfileDTO profile)  {
		OutDTO dto = profileService.createProfile(profile);
		return ResponseEntity.ok(dto);
	}

}
