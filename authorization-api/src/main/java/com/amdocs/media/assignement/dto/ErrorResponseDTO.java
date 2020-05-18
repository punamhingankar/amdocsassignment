package com.amdocs.media.assignement.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {

	private String error;
	private Integer statusCode;
}
