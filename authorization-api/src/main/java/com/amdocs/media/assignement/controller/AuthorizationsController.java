package com.amdocs.media.assignement.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amdocs.media.assignement.dto.OutDTO;
import com.amdocs.media.assignement.dto.ProfileInDTO;
import com.amdocs.media.assignement.dto.UserInDTO;
import com.amdocs.media.assignement.dto.UserOutDto;
import com.amdocs.media.assignement.exceptions.UserNotFoundException;
import com.amdocs.media.assignement.service.AuthorizationsService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@CrossOrigin("*")
public class AuthorizationsController {

	@Autowired
	private AuthorizationsService authService;
	
	private static String X_Authorization = "x-Authorization";

	@PostMapping("/login")
	private ResponseEntity<UserOutDto> login(@RequestBody UserInDTO userInDto) throws UserNotFoundException {
		UserOutDto user = authService.login(userInDto);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/update/profile")
	private ResponseEntity<OutDTO> updateProfile(@RequestBody ProfileInDTO profileInDTO, HttpServletRequest request)
			throws UserNotFoundException, JsonProcessingException {
		String token = request.getHeader(X_Authorization);
		OutDTO outDTO = authService.updateProfile(profileInDTO, token);
		return ResponseEntity.ok(outDTO);
	}
	
	@DeleteMapping("/delete/{profileId}")
	private ResponseEntity<OutDTO> updateProfile(@PathVariable Long  profileId, HttpServletRequest request)
			throws UserNotFoundException, JsonProcessingException {
		String token = request.getHeader(X_Authorization);
		OutDTO outDTO = authService.deleteProfile(profileId, token);
		return ResponseEntity.ok(outDTO);
	}
	
	@PostMapping("/profile")
	private ResponseEntity<OutDTO> insertProfile(@RequestBody ProfileInDTO profileInDTO, HttpServletRequest request)
			throws UserNotFoundException, JsonProcessingException {
		String token = request.getHeader(X_Authorization);
		OutDTO outDTO = authService.InsertProfile(profileInDTO, token);
		return ResponseEntity.ok(outDTO);
	}

}
