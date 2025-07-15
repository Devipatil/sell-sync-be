package com.sellSync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellSync.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	Users findByUsername(String username);
}
