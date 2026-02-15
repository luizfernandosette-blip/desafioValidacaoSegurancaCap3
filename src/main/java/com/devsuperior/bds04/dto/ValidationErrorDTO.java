package com.devsuperior.bds04.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO  {
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String path;
	
	private List<FieldErrorDTO> errors = new ArrayList<>();
	
	public ValidationErrorDTO() {
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<FieldErrorDTO> getErrors() {
		return errors;
	}
	
	public void addError(String fieldName, String message) {
		errors.add(new FieldErrorDTO(fieldName, message));
	}
}
