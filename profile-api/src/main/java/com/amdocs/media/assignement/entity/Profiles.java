package com.amdocs.media.assignement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Profiles {

	@Column(name = "Id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "User_Id")
	private Long userId;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Phone_NO")
	private String phoneNo;
	
	
}
