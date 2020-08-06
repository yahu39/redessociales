package com.yahupc.redessociales.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yahupc.redessociales.model.SocialMedia;
import com.yahupc.redessociales.services.SocialMediaService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class SocialMediaController {
	
	@Autowired
	SocialMediaService _socialMediaService;
	
	 //GET
		@RequestMapping(value="/socialMedias", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<List<SocialMedia>> getSocialMedias(@RequestParam(value="name", required=false) String name){
			
			List<SocialMedia> socialMedias = new ArrayList<>();
			
			if (name == null) {
				socialMedias = _socialMediaService.findAllSocialMedias();
				if (socialMedias.isEmpty()) {
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}
				
				return new ResponseEntity<List<SocialMedia>>(socialMedias, HttpStatus.OK);
			} else {
				SocialMedia socialMedia = _socialMediaService.findByName(name);
				if (socialMedia == null) {
					return new ResponseEntity(HttpStatus.NOT_FOUND);
				}
				
				socialMedias.add(socialMedia);
				return new ResponseEntity<List<SocialMedia>>(socialMedias, HttpStatus.OK);
			}
			
			
		}
}
/*
 * 	//GET
	@RequestMapping(value="/socialMedias", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<SocialMedia>> getSocialMedias(@RequestParam(value="name", required=false) String name){
		
		List<SocialMedia> socialMedias = new ArrayList<>();
		
		if (name == null) {
			socialMedias = _socialMediaService.findAllSocialMedias();
			if (socialMedias.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<SocialMedia>>(socialMedias, HttpStatus.OK);
		} else {
			SocialMedia socialMedia = _socialMediaService.findByName(name);
			if (socialMedia == null) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			
			socialMedias.add(socialMedia);
			return new ResponseEntity<List<SocialMedia>>(socialMedias, HttpStatus.OK);
		}
		
		
	}
 * */
