package com.sina.wstore.service;

import java.util.List;

import com.sina.wstore.entity.Product;

public interface ProductService {
	
public List<Product> getAllProducts();

public Product getProduct(int id);

public Product saveProduct(Product product);

public void deleteProduct(int id);

}
