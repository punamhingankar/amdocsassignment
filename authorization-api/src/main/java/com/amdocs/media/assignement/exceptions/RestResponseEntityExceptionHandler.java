package com.amdocs.media.assignement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.amdocs.media.assignement.dto.ErrorResponseDTO;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = UserNotFoundException.class)
	protected ResponseEntity<ErrorResponseDTO> AccessDeniedException(RuntimeException ex, WebRequest request) {
		ErrorResponseDTO error = new ErrorResponseDTO();
		error.setError(ex.getMessage());
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorResponseDTO>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UnauthorizedUserException.class)
	protected ResponseEntity<ErrorResponseDTO> unauthorizedUserException(RuntimeException ex, WebRequest request) {
		ErrorResponseDTO error = new ErrorResponseDTO();
		error.setError(ex.getMessage());
		error.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<ErrorResponseDTO>(error,HttpStatus.UNAUTHORIZED);
	}
}
