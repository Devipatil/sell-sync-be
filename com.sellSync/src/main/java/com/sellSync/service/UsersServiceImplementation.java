package com.sellSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellSync.entity.Users;
import com.sellSync.repository.UsersRepository;

@Service
public class UsersServiceImplementation implements UsersService {
	
	@Autowired
	UsersRepository repo;

	public void signUp(Users user) {
		repo.save(user);
		
	}

	public Users getUser(String username) {
		return repo.findByUsername(username);
	}

}
