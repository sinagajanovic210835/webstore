package com.sina.wstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sina.wstore.dao.BillRepository;
import com.sina.wstore.entity.Bill;
import com.sina.wstore.entity.Customer;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillRepository billRepository;

	@Override
	public Bill saveBill(Bill theBill) {
		
		return billRepository.save(theBill);
		
	}

	@Override
	public List<Bill> getAllBills() {
		
		return billRepository.findAll();
	}

	@Override
	public Bill getById(int id) {
		
		return billRepository.getOne(id);
	}

	@Override
	public void deleteBill(int id) {
		
		billRepository.deleteById(id);
		
	}

	@Override
	public Bill findLast(Customer customer) {
		
		List<Bill> l = billRepository.findByCustomer(customer);
		
		return  l.get(l.size() - 1);
	}

	
	
	

}
