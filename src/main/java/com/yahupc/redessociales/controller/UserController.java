package com.yahupc.redessociales.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.yahupc.redessociales.model.User;
import com.yahupc.redessociales.services.SocialMediaServiceImpl;
import com.yahupc.redessociales.services.UserService;

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

}
