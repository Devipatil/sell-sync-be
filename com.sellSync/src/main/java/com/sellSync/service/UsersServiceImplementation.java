package com.sellSync.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sellSync.repository.UsersRepository;

public class UsersServiceImplementation implements UsersService {
	
	@Autowired
	UsersRepository repo;

}
