package com.sellSync.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sellSync.entity.Product;
import com.sellSync.service.ProductService;

@CrossOrigin("*")
@RestController
public class ProductController {
	
	@Autowired
	ProductService service;
	
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
}
