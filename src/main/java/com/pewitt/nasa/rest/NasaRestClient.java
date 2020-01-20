package com.pewitt.nasa.rest;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pewitt.nasa.dto.PhotoListDTO;
import com.pewitt.nasa.dto.RoverListDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NasaRestClient {

	final static String API_KEY = "BRn8gflLVn9RqRnrcU8Atel7JlpIsylSVwQc4sEF";
	final static String API_URL = "https://api.nasa.gov/mars-photos/api/v1";

	public Optional<RoverListDTO> getRovers() {
		RestTemplate restTemplate = new RestTemplate();
		String roverURL = String.format("%s/rovers?api_key=%s", API_URL, API_KEY);
		RoverListDTO roverDTOList = restTemplate.getForObject(roverURL, RoverListDTO.class);
		return Optional.of(roverDTOList);
	}

	public Optional<PhotoListDTO> getPhotos(String name, String date) {
		if (name == null || date == null) {
			return Optional.empty();
		}
		RestTemplate restTemplate = new RestTemplate();
		String photoURL = String.format("%s/rovers/%s/photos?earth_date=%s&api_key=%s", API_URL, name, date, API_KEY);
		PhotoListDTO photoDTOList = restTemplate.getForObject(photoURL, PhotoListDTO.class);
		return Optional.of(photoDTOList);
	}

	public byte[] getPhoto(String url) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, byte[].class);
	}

}
