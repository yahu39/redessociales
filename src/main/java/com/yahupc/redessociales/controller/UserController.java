package com.yahupc.redessociales.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.yahupc.redessociales.model.User;
import com.yahupc.redessociales.services.SocialMediaServiceImpl;
import com.yahupc.redessociales.services.UserService;
import com.yahupc.redessociales.util.CustomErrorType;

@Controller
@RequestMapping(value = "/v1")
public class UserController {

	@Autowired
	private UserService _userService;

	// POST CREATE
	@RequestMapping(value = "/users", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> createSocialMedia(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
		if (user.getName().equals(null) || user.getName().isEmpty()) {
			return new ResponseEntity("User name is required", HttpStatus.CONFLICT);
		}

		if (_userService.findByName(user.getName()) != null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		_userService.saveUser(user);
		User user2 = _userService.findByName(user.getName());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/v1/users/{id}").buildAndExpand(user2.getIdUser()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// GET READ
	@RequestMapping(value = "/users", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = new ArrayList<User>();

		users = _userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// FIND BY ID
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
		User user = _userService.findById(id);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// UPDATE
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH, headers = "Accept=application/json")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long idUser, @RequestBody User user) {
		if (idUser == null || idUser <= 0) {
			return new ResponseEntity("idUser is required", HttpStatus.CONFLICT);
		}

		User currentUser = _userService.findById(idUser);
		if (currentUser == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		currentUser.setName(user.getName());
		currentUser.setAvatar(user.getAvatar());

		_userService.updateUser(currentUser);
		;
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// DELETE
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long idUser) {
		if (idUser == null || idUser <= 0) {
			return new ResponseEntity("idUser is required", HttpStatus.CONFLICT);
		}

		User user = _userService.findById(idUser);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		_userService.deleteUserById(idUser);
		return new ResponseEntity<User>(HttpStatus.OK);

	}
	
	
	public static final String USER_UPLOADED_FOLDER ="images/users/";
	//CREATE USER IMAGE
	@RequestMapping(value="/users/images", method = RequestMethod.POST, headers=("content-type=multipart/form-data"))
	public ResponseEntity<byte[]> uploadUserImage(@RequestParam("id_User") Long idUser, 
			@RequestParam("file") MultipartFile multipartFile, 
			UriComponentsBuilder componentsBuilder){
		if (idUser == null) {
			return new ResponseEntity(new CustomErrorType("Please set id_User"), HttpStatus.NO_CONTENT);
		}
		
		if (multipartFile.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("Please select a file to upload"), HttpStatus.NO_CONTENT);
		}
		
		User user = _userService.findById(idUser);
		if (user == null) {
			return new ResponseEntity(new CustomErrorType("User with id_USER: " + idUser + " not dfound"), HttpStatus.NOT_FOUND);
		}
		
		if (!user.getAvatar().isEmpty() || user.getAvatar() != null) {
			String fileName = user.getAvatar();
			Path path = Paths.get(fileName);
			File f = path.toFile();
			if (f.exists()) {
				f.delete();
			}
		}
		
		try {
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateName = dateFormat.format(date);
			
			String fileName = String.valueOf(idUser) + "-pictureUser-" + dateName + "." + multipartFile.getContentType().split("/")[1];
			user.setAvatar(USER_UPLOADED_FOLDER + fileName);
			
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(USER_UPLOADED_FOLDER + fileName);
			Files.write(path, bytes);
			
			_userService.updateUser(user);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity(new CustomErrorType("Error during upload: " + multipartFile.getOriginalFilename()),HttpStatus.CONFLICT);
		}
	}
}
	

