package com.pewitt.nasa.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pewitt.nasa.dto.RoverListDTO;
import com.pewitt.nasa.rest.NasaRestClient;

@Service
public class RoverService {
	
	private NasaRestClient nasaRestClient;
	
	public RoverService(NasaRestClient nasaRestClient) {
		this.nasaRestClient = nasaRestClient;
	}
	
	public Optional<RoverListDTO> getRovers() {
		return nasaRestClient.getRovers();
	}

}
