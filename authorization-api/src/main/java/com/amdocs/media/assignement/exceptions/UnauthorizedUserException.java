package com.amdocs.media.assignement.exceptions;

public class UnauthorizedUserException extends RuntimeException{

	private static final long serialVersionUID = -4249397047804787722L;
	
	public UnauthorizedUserException(String message) {
		super(message);
	}

}
