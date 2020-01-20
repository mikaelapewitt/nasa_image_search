package com.pewitt.nasa.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pewitt.nasa.dto.RoverListDTO;
import com.pewitt.nasa.services.RoverService;

@RestController
@RequestMapping("/api")
public class RoverController {

	private RoverService roverService;
	
	public RoverController(RoverService roverService) {
		this.roverService = roverService;
	}
	
	@RequestMapping(value = "rovers", method = RequestMethod.GET)
	public Optional<RoverListDTO> getRovers() {
		return roverService.getRovers();
	}
}
