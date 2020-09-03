package com.sina.wstore.service;

import java.util.List;

import com.sina.wstore.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomer(int id);
	
	public Customer saveCustomer(Customer customer);
	
	public void deleteCustomer(int id);
	
	public Customer getCustomer(String email, String password);

	
}
