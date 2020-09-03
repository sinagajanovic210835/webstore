package com.sina.wstore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sina.wstore.dao.ProductRepository;
import com.sina.wstore.entity.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
		
	}

	@Override
	public Product getProduct(int id) {
		
		return productRepository.getOne(id);
	}

	@Override
	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(int id) {
		
		productRepository.deleteById(id);
		
	}
}
