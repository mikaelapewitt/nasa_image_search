package com.pewitt.nasa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CameraDTO {

	private String id;
	private String name;
	@JsonProperty("full_name")
	private String fullName;
}
