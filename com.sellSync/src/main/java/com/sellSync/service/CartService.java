package com.sellSync.service;

import java.util.List;

import com.sellSync.entity.Cart;
import com.sellSync.entity.CartItem;

public interface CartService {
	void addCart(Cart cart);
	
	void clearCart(String username);

	List<CartItem> getItems(String username);
	
	List<CartItem> cloneItems(String username);
}
