package com.amdocs.media.assignement.kafka.service;

import java.awt.color.ProfileDataException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.amdocs.media.assignement.dto.ProfileDTO;
import com.amdocs.media.assignement.service.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class kafkaConsumerService<T> {

	@Autowired
	private ProfileService profileService;

	@KafkaListener(topics = "Update", groupId = "Profile")
	public void consumeUpdateTopicForProfile(String message)
			throws JsonMappingException, JsonProcessingException, ProfileDataException {
		
		System.out.println("-----------------Update-----------------");
		ProfileDTO profile = getProfileObject(message);
		profileService.updateProfile(profile);
	}
	
	@KafkaListener(topics = "Delete", groupId = "Profile")
	public void consumeInsertTopicForProfile(String message) throws JsonMappingException, JsonProcessingException {
		System.out.println("-----------------Delete-----------------");
		ObjectMapper mapper = new ObjectMapper();
		Long profileId =  mapper.readValue(message, Long.class);
		profileService.deleteProfile(profileId);
	}

	public ProfileDTO getProfileObject(String message) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ProfileDTO profileDTO = mapper.readValue(message, ProfileDTO.class);
		return profileDTO;

	}
}
