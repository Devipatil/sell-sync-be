package com.sellSync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellSync.entity.Cart;
import com.sellSync.repository.CartRepository;

@Service
public class CartServiceImplementation implements CartService {
	
	@Autowired
	CartRepository repo;
	
	@Override
	public void addCart(Cart cart) {
		repo.save(cart);	
	}

}
