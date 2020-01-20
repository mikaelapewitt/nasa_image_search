package com.pewitt.nasa.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pewitt.nasa.services.DateService;

@RestController
@RequestMapping("/api")
public class DateController {
	
	private DateService dateService;
	
	public DateController(DateService dateService) {
		this.dateService = dateService;
	}
	
	@RequestMapping(value = "dates", method = RequestMethod.GET)
	public List<String> getDates() {
		return dateService.getDates();
	}


}
