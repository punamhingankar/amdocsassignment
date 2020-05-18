package com.amdocs.media.assignement.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdocs.media.assignement.entity.Profiles;

public interface ProfileRepository extends JpaRepository<Profiles, Long> {

	Optional<Profiles> findByIdAndUserId(Long profileId , Long userId);
}
