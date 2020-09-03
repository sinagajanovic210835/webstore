package com.sina.wstore.service;

import java.util.List;

import com.sina.wstore.entity.Bill;
import com.sina.wstore.entity.Customer;

public interface BillService {
	
	public List<Bill> getAllBills();
	
	public Bill saveBill(Bill theBill);
	
	public Bill getById(int id);
	
	public void deleteBill(int id);
	
	public Bill findLast(Customer customer);

}
