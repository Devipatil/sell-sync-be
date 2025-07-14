package com.sellSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sellSync.service.UsersService;

@RestController
public class UsersController {
	
	@Autowired
	UsersService service;
}
