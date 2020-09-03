package com.sina.wstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sina.wstore.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {	
	
	public List<Customer> findByEmailAndPassword(String email, String customerPassword);
	 
}

