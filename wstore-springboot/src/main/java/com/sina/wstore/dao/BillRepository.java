package com.sina.wstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sina.wstore.entity.Bill;
import com.sina.wstore.entity.Customer;

public interface BillRepository extends JpaRepository<Bill, Integer>{
	
	public List<Bill> findByCustomer(Customer customer);
}

