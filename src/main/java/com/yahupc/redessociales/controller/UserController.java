package com.yahupc.redessociales.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.yahupc.redessociales.model.User;
import com.yahupc.redessociales.services.SocialMediaServiceImpl;
import com.yahupc.redessociales.services.UserService;

@Controller
@RequestMapping(value="/v1")
public class UserController {

	@Autowired
	private UserService _userService;
	
	//GET 
	@RequestMapping(value = "/users", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<User>> getUsers(){
		List<User> users = new ArrayList<User>();
		
			users = _userService.findAllUsers();
	        if (users.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
		   
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		

    }
}
