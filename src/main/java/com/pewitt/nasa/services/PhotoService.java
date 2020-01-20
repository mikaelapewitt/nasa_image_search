package com.pewitt.nasa.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import com.pewitt.nasa.dto.PhotoListDTO;
import com.pewitt.nasa.rest.NasaRestClient;

@Service
public class PhotoService {

	private NasaRestClient nasaRestClient;
	
	public PhotoService(NasaRestClient nasaRestClient) {
		this.nasaRestClient = nasaRestClient;
	}
	
	public Optional<PhotoListDTO> getPhotoList(String name, String date) {
		return nasaRestClient.getPhotos(name, date);
	}
	
	public File getPhoto(String url) throws IOException {
		final String sha = DigestUtils.sha512Hex(url);
		final String imageFileName = new StringBuilder("/tmp/").append(sha).toString();
		
		File image;
		
		if (Files.exists(Paths.get(imageFileName))) {
			image = Paths.get(imageFileName).toFile();
		} else { 
			byte[] bytePhoto = nasaRestClient.getPhoto(url);
			InputStream inputStream = new ByteArrayInputStream(bytePhoto);
			Path cachedFile = Files.createFile(Paths.get(imageFileName));
			Files.copy(inputStream, cachedFile, StandardCopyOption.REPLACE_EXISTING);
			IOUtils.closeQuietly(inputStream);
			image = cachedFile.toFile();
		}
		
		return image;
	}
}
