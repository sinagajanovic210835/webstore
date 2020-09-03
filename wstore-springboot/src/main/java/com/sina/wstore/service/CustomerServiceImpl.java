package com.sina.wstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sina.wstore.dao.CustomerRepository;
import com.sina.wstore.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository userRepository;

	@Override
	public Customer getCustomer(int id) {
		 
		return userRepository.getOne(id);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		
		userRepository.save(customer);
		
		return customer;
	}

	@Override
	public Customer getCustomer(String eMail, String password) {
		
		List<Customer> users = userRepository.findByEmailAndPassword(eMail, password);
		
		if(users.isEmpty()) return null;
		
		return users.get(0);
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return userRepository.findAll();
	}

	@Override
	public void deleteCustomer(int id) {

		userRepository.deleteById(id);
		
	}

	
    

}
