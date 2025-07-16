package com.sellSync.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellSync.entity.Product;
import com.sellSync.repository.ProductRepository;

@Service
public class ProductServiceImplementation implements ProductService {
	@Autowired
	ProductRepository repo;

	public String addProduct(Product product) {
		repo.save(product);
		return "Product added successfully!";
	}

	public Product searchProduct(long id) {
		return repo.findById(id).get();
		
	}

	public Product searchProduct(String name) {
		return repo.findByName(name);
	}
	
	public Product searchProductByCategory(String category) {	
		return repo.findByCategory(category);
	}

	public String  updateProduct(Product product) {
		repo.save(product);
		return "product updated successfully!";
	}

	public String deleteProduct(long id) {
		repo.deleteById(id);
		return "product deleted successfully!";
		
	}

	public List<Product> getAllProducts() {
		return repo.findAll();
	}

}
