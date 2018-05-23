package com.api.dto;

import org.springframework.hateoas.ResourceSupport;

public class GenericResponseDTO extends ResourceSupport {

	private String message;
	private Object data;

	public GenericResponseDTO() {
	}

	public GenericResponseDTO(String message) {
		this.message = message;
	}

	public GenericResponseDTO(String message, Object data) {
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
