package com.amdocs.media.assignement.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.amdocs.media.assignement.dao.UsersRepository;
import com.amdocs.media.assignement.dto.OutDTO;
import com.amdocs.media.assignement.dto.ProfileInDTO;
import com.amdocs.media.assignement.dto.UserInDTO;
import com.amdocs.media.assignement.dto.UserOutDto;
import com.amdocs.media.assignement.entity.Users;
import com.amdocs.media.assignement.exceptions.UnauthorizedUserException;
import com.amdocs.media.assignement.exceptions.UserNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.utility.RandomString;

@Service
public class AuthorizationsService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private RestTemplate restTemplate;

	public UserOutDto login(UserInDTO userInDto) throws UserNotFoundException {

		Optional<Users> user = usersRepository.findByUserNameAndPassword(userInDto.getUserName(),
				userInDto.getPassword());
		if (user.isPresent()) {
			UserOutDto userOutDto = new UserOutDto();
			String token = RandomString.make(10);
			user.get().setStatus(Boolean.TRUE);
			user.get().setToken(token);
			usersRepository.save(user.get());
			userOutDto.setId(user.get().getId());
			userOutDto.setToken(token);
			return userOutDto;
		}
		throw new UserNotFoundException("user not exsits in DB");
	}

	public Boolean logout(Long userId) {
		Optional<Users> user = usersRepository.findById(userId);
		if (user.isPresent()) {
			user.get().setStatus(Boolean.FALSE);
			user.get().setToken(null);
			usersRepository.save(user.get());
		}
		return Boolean.FALSE;
	}

	public OutDTO updateProfile(ProfileInDTO profileInDTO, String token)
			throws UserNotFoundException, JsonProcessingException {

		if (authenticateUser(profileInDTO, token)) {
			String json = getJsonString(profileInDTO);
			kafkaProducer.sendAsynMessage(json, "Update");
			return OutDTO.toOutDto("update/profile/" + profileInDTO.getUserId(), HttpStatus.OK.value());
		}
		throw new UnauthorizedUserException("user is not authorized to perform this opration.");
	}
	
	
	public OutDTO deleteProfile(Long profileId, String token)
			throws UserNotFoundException, JsonProcessingException {
		Optional<Users> user = usersRepository.findByToken(token);
		if (user.isPresent()) {
			String id = String.valueOf(profileId);
			kafkaProducer.sendAsynMessage(id, "Delete");
			return OutDTO.toOutDto("Delete/profile/" + profileId, HttpStatus.OK.value());
		}
		throw new UnauthorizedUserException("user is not authorized to perform this opration.");
	}

	public OutDTO InsertProfile(ProfileInDTO profileInDTO, String token)
			throws UserNotFoundException, JsonProcessingException {
		Optional<Users> user = usersRepository.findByToken(token);
		if (user.isPresent()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<ProfileInDTO> entity = new HttpEntity<ProfileInDTO>(profileInDTO, headers);
			return restTemplate.exchange("http://localhost:8092/profile", HttpMethod.POST, entity, OutDTO.class)
					.getBody();

		}
		throw new UnauthorizedUserException("user is not authorized to perform this opration.");
	}

	private Boolean authenticateUser(ProfileInDTO userInDto, String token) throws UserNotFoundException {
		Optional<Users> user = usersRepository.findByToken(token);
		if (user.isPresent() && user.get().getStatus()) {
			userInDto.setUserId(user.get().getId());
			return Boolean.TRUE;
		}
		throw new UserNotFoundException("user not exsits in DB");
	}

	private String getJsonString(ProfileInDTO profileInDTO) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(profileInDTO);
	}
}
