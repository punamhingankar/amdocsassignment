package com.amdocs.media.assignement.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdocs.media.assignement.entity.Users;

public interface UsersRepository  extends JpaRepository<Users, Long>{

	Optional<Users> findByUserNameAndPassword(String UserName , String password);
	
	Optional<Users> findByToken(String tokean);
}
