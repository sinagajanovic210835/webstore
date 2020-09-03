package com.sina.wstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sina.wstore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {	
	
	 
	}
