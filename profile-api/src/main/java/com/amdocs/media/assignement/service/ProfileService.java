package com.amdocs.media.assignement.service;

import java.awt.color.ProfileDataException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.amdocs.media.assignement.dao.ProfileRepository;
import com.amdocs.media.assignement.dto.OutDTO;
import com.amdocs.media.assignement.dto.ProfileDTO;
import com.amdocs.media.assignement.entity.Profiles;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	public void updateProfile(ProfileDTO profileDTO) throws ProfileDataException {
		Profiles profile = ProfileDTO.toEntity(profileDTO);
		Optional<Profiles> userProfile = profileRepository.findByIdAndUserId(profileDTO.getId(),
				profileDTO.getUserId());
		if (userProfile.isPresent()) {
			profileRepository.save(profile);
		} else {
			throw new ProfileDataException("Profile not found");
		}

	}

	public void deleteProfile(Long profileId) throws ProfileDataException {
		Optional<Profiles> userProfile = profileRepository.findById(profileId);
		if (userProfile.isPresent()) {
			profileRepository.delete(userProfile.get());
		} else {
			throw new ProfileDataException("Profile not found");
		}

	}

	public OutDTO createProfile(ProfileDTO profile) {
		Profiles userProfile = ProfileDTO.toEntity(profile);
		userProfile = profileRepository.save(userProfile);
		OutDTO out = new OutDTO();
		out.setUrl("profile/" + userProfile.getId());
		out.setHttpStatus(HttpStatus.OK.value());
		return out;

	}

}
