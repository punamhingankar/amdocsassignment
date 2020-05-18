package com.amdocs.media.assignement.exceptions;


public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String messsage) {
		
		super(messsage);
	}

}
