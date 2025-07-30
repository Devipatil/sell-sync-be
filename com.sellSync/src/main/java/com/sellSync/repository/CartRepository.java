package com.sellSync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellSync.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
