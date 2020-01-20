package com.pewitt.nasa.controllers;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RoverPhotoForm {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String date;
	private String rover;
	private String camera;
	
}
