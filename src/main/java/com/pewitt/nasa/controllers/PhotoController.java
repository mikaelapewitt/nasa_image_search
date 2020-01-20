package com.pewitt.nasa.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pewitt.nasa.dto.PhotoListDTO;
import com.pewitt.nasa.services.PhotoService;

@RestController
@RequestMapping("/api")
public class PhotoController {

	private PhotoService photoService;
	
	public PhotoController(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	@GetMapping("rovers/{name}/photos")
	public ResponseEntity<PhotoListDTO> getRoverPhotos(@PathVariable String name, @RequestParam("earth_date") String date) {
		Optional<PhotoListDTO> photoList = photoService.getPhotoList(name, date);
		if(!photoList.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		}
		
		return ResponseEntity.ok().body(photoList.get());
	}
	
	@GetMapping("rovers/{name}/photos/{id}")
	public ResponseEntity<byte[]> getImgUrl(@PathVariable String id, @RequestParam("img_src") String imgSrc) throws IOException {
		File file = photoService.getPhoto(imgSrc);
		byte[] imageBytes = Files.readAllBytes(file.toPath());
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imageBytes);
	}
}
