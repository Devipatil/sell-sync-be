package com.sellSync.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sellSync.entity.Cart;
import com.sellSync.entity.CartData;
import com.sellSync.entity.Product;
import com.sellSync.entity.Users;
import com.sellSync.service.CartService;
import com.sellSync.service.ProductService;
import com.sellSync.service.UsersService;

@CrossOrigin("*")
@RestController
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@Autowired
	UsersService uService;
	
	@Autowired
	CartService cService;
	
	@PostMapping("addProduct")
	public String addProduct(@RequestBody Product product){
		return service.addProduct(product);
	}
	
	@GetMapping("/searchProduct")
	public Product searchProduct(@RequestParam long id){
		return service.searchProduct(id);
	}
	
//	@PostMapping("/searchProduct")
//	public Product searchProduct(@RequestBody String name){
//		return service.searchProduct(name);
//	}
//	
//	@PostMapping("/searchProductByCategory")
//	public Product searchProductByCategory(@RequestBody String category){
//		return service.searchProductByCategory(category);
//	}
//	
	
	@PostMapping("/updateProduct")
	public String updateProduct(@RequestBody Product product){
		return service.updateProduct(product);
	}

	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam long id){
		return service.deleteProduct(id);
	}

	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
	
	@PostMapping("/addToCart")
	public String addToCart(@RequestBody CartData data) {
		Users user = uService.getUser(data.getUsername());
		
		Product prod = service.searchProduct(data.getProductId());
		
		Cart c = null;
		
		if(user.getCart() == null) {
			c = new Cart();
			c.setUser(user);
			List<Product> pList = new ArrayList<Product>();
			pList.add(prod);
			c.setProductList(pList);
		}
		else {
			c = user.getCart();
			c.getProductList().add(prod);
		}
		user.setCart(c);
		//call service to update user entity
		cService.addCart(c);
		return "cart added";
	}
}
