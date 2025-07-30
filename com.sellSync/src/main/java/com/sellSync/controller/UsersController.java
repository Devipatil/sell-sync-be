package com.sellSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sellSync.entity.UserLoginData;
import com.sellSync.entity.Users;
import com.sellSync.service.UsersService;

@CrossOrigin("*")
@RestController
public class UsersController {
	
	@Autowired
	UsersService uService;
	
	@PostMapping("/signUp")
	public String signUp(@RequestBody Users user) {
		
		String msg = "";
		String username = user.getUsername();
		Users u = uService.getUser(username);
		
		if (u == null) {
			uService.signUp(user);
			msg = "User created successfully!";
		}
		else {
			msg = "User already exist!";
		}
		return msg;		
	}
	
	@PostMapping("/signIn")
	public String signIn(@RequestBody UserLoginData user) {
		String msg = "";
		String username = user.getUsername();
		String password = user.getPassword();
		Users u = uService.getUser(username);
		if (u == null) {
			msg = "Username does not exist!";
		} else {
			boolean status = uService.validate(username, password);
			if (status == true) {
				if (u.getRole().equals("admin")) {
				     msg = "admin";
			     }
				else {
					msg = "customer";
				}
			}
			 else {
			       msg = "Wrong password";
			}
		}
		return msg;
	}
}
