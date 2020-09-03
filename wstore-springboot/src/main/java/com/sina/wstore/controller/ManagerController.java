package com.sina.wstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sina.wstore.entity.Product;
import com.sina.wstore.service.ProductService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
    private ProductService productService;
	
	/*@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BillService billService;*/
	
	@GetMapping("")
	public String adminHome() {
		
		return "admin-home-page";
	}
	
	@GetMapping("/product")
	public String showProductPage(Model model) {
		
		List<Product> products = productService.getAllProducts();
		
		model.addAttribute("products", products);
		
		return "product-update-page";
	}
	
	@GetMapping("/showFormforAddProduct")
	public String showFormforAddProduct(Model model) {
		 
		Product product = new Product();
		
		model.addAttribute("product", product);		
		
		return "product-form";
	}
	
	@GetMapping("/showFormForUpdateProducts")
	public String showFormForUpdateProducts(@RequestParam("productId") int id, Model model) {
		
		Product product = productService.getProduct(id);
		
		model.addAttribute("product", product);		
		
		return "product-form";
	}
	
	@RequestMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
		
		 productService.saveProduct(product);		
		
		return "redirect:./product";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("productId") int id) {
		
		productService.deleteProduct(id);		
		
		return "redirect:./product";
	}
}
