package com.pewitt.nasa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pewitt.nasa.dto.PhotoListDTO;
import com.pewitt.nasa.dto.RoverListDTO;
import com.pewitt.nasa.rest.NasaRestClient;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class NasaApplicationTests {

	@Autowired
	private NasaRestClient nasaRestClient;
	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getRovers() {
		Optional<RoverListDTO> roverListOptional = nasaRestClient.getRovers();
		assertThat(roverListOptional.isPresent());
	}
	
	@Test
	public void getPhotos() {
		Optional<PhotoListDTO> photoListOptional = nasaRestClient.getPhotos("Curiosity", "02015-01-01");
		assertThat(photoListOptional.isPresent());
	}
	
	@Test
	public void getPhoto() {
		byte[] photo = nasaRestClient.getPhoto("http://mars.jpl.nasa.gov/msl-raw-images/ods/surface/sol/00855/opgs/edr/fcam/FRB_473388069EDR_F0442414FHAZ00323M_.JPG");
		assertThat(photo != null);
	}
    
}
