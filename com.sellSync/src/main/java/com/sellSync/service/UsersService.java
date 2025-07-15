package com.sellSync.service;

import com.sellSync.entity.Users;

public interface UsersService {
	
	void signUp(Users user);
	Users getUser(String username);
}
