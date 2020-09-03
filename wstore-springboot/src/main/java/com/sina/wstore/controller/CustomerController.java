package com.sina.wstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sina.wstore.entity.Bill;
import com.sina.wstore.entity.Customer;
import com.sina.wstore.service.BillService;
import com.sina.wstore.service.CustomerService;

@Controller
@RequestMapping("/manager/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BillService billService;
	
	@GetMapping("")
	public String customerList(Model model) {
		
		List<Customer> customers = customerService.getAllCustomers();
		
		model.addAttribute("customers", customers);
		
		return "customer";
	}
	
	@GetMapping("/showCustomerBills")
		public String showCustomerBills(@RequestParam("customerId") int customerId, Model model) {
		
		List<Bill> bills = billService.getAllBills();	
		
		List<Bill> filter = new ArrayList<>();		
		
				switch (customerId) {
		
						case 0:
							
							model.addAttribute("bills", bills);
										
							break;
							
						case -1:
							
							for(Bill theBill : bills) {
								
								if(!theBill.isPaid())
													
												filter.add(theBill);
								
							    }
							
							model.addAttribute("bills", filter);
							
							break;
				
						default:
							
							for(Bill theBill : bills) {
								
								if(theBill.getCustomer().getId() == customerId)
								
													filter.add(theBill);
								
							}
			
			model.addAttribute("bills", filter);
			
			break;
		}	
	
		return "show-bills";
	
	}
	
	@GetMapping("payBill")
	public String payBill(@RequestParam("billId") int billId) {
		
		Bill bill = billService.getById(billId);
		
		bill.setPaid(true);
		
		billService.saveBill(bill);
		
		return "redirect:/manager/customer";
	}
	
	@GetMapping("/updateCustomer")
	public String updateCustomer(@RequestParam("customerId") int customerId, Model model) {
		
		Customer customer = customerService.getCustomer(customerId);
		
		model.addAttribute("customer", customer);		
		
		return "customer-update-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		customerService.saveCustomer(customer);	
		
		return "redirect:/manager/customer";		
		
	}
	

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId) {
		
		customerService.deleteCustomer(customerId);		
		
		return "redirect:/manager/customer";		
	}
	
}
