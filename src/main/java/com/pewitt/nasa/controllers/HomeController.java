package com.pewitt.nasa.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.pewitt.nasa.dto.PhotoDTO;
import com.pewitt.nasa.dto.PhotoListDTO;
import com.pewitt.nasa.services.PhotoService;

@Controller
@RequestMapping(value="/")
public class HomeController {
	
	@Autowired
	public PhotoService photoService;

	@GetMapping(value = "/")
	public String getHome(Model model, @RequestParam(name= "photoUrl", required = false) String photoUrl) {
		model.addAttribute(new RoverPhotoForm());
		
		model.addAttribute("photoUrl", photoUrl);
		
		return "home";
	}
	
	@PostMapping(value = "/")
	public String sendForm(Model model, @ModelAttribute("roverPhotoForm") RoverPhotoForm roverPhotoForm) {
		String rover = roverPhotoForm.getRover();
		String date = roverPhotoForm.getDate();
		String camera = roverPhotoForm.getCamera();
		Optional<PhotoListDTO> photoList = photoService.getPhotoList(rover, date);

		String photoUrl = "";
		for(PhotoDTO photo: photoList.get().getPhotos()) {
			if(photo.getCamera().getName().equals(camera)) {
				photoUrl = photo.getImgSrc();
			}
		}
	    return "redirect:/?photoUrl=" + photoUrl;

	}
	
}
