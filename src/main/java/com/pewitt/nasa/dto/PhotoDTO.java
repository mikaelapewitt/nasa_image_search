package com.pewitt.nasa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PhotoDTO {

	private String id;
	@JsonProperty("earth_date")
	private String earthDate;
	@JsonProperty("img_src")
	private String imgSrc;
	private String sol;
	private CameraDTO camera;
	private RoverDTO rover;
}
