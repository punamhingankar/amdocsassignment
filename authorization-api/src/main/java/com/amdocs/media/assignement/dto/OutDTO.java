package com.amdocs.media.assignement.dto;

import lombok.Data;

@Data
public class OutDTO {

	private String url;
	private Integer  httpStatus;;

	public static OutDTO toOutDto(String url , Integer httpStatus) {
		OutDTO outDTO = new OutDTO();
		outDTO.setUrl(url);
		outDTO.setHttpStatus(httpStatus);
		return outDTO;
	}
}
