package com.sina.wstore.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sina.wstore.entity.Bill;
import com.sina.wstore.entity.Customer;
import com.sina.wstore.entity.Product;
import com.sina.wstore.service.BillService;
import com.sina.wstore.service.CustomerService;
import com.sina.wstore.service.ProductService;

@Controller
@RequestMapping("/store")
public class ProductController{
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private CustomerService customerService;	
	
	@Autowired
	private BillService billService;	
	
	List<String> categories;	
	
	private List<Product> listProduct;
	
	@PostConstruct
	private void fillList() {
		
		categories = new ArrayList<>();
		
		categories.add("Svi proizvodi");
		
		listProduct = productService.getAllProducts();
		
		for(Product theProduct : listProduct) {
				
				if(!categories.contains(theProduct.getCategory())) {
					
					categories.add(theProduct.getCategory());
					
				}
			}
		
	}
	
	@GetMapping("/products")
	public String showStore(@RequestParam("category") String category,
							@RequestParam("korpa") String korpa,
							@RequestParam("total") String total,							
							Model model) {
		
		String basketimg = "kart.png";		
		
		if(!total.equals("0")&&!korpa.isEmpty()) basketimg = "kartfull.png";
		
		else {total="0"; korpa="";}
		
		if(category.equals("Svi proizvodi")) {
			
			model.addAttribute("products", listProduct);
			
		}else {			
			
			List<Product> listCateg = new ArrayList<>();
			
			for(Product theProduct : listProduct) {
				
				if(theProduct.getCategory().equals(category)) {
					
					listCateg.add(theProduct);
					
				}				
				
			}
			
			model.addAttribute("products", listCateg);
			
		}				
		
		model.addAttribute("categories", categories);
		
		model.addAttribute("total", total);
		
		model.addAttribute("korpa", korpa);
		
		model.addAttribute("basketimg", basketimg);
		
		if(category.equals("kuleroprema")) category = "kuleri i oprema";
		
		model.addAttribute("category", category.replaceAll("_", " ").replaceFirst(""+category.charAt(0), (""+category.charAt(0)).toUpperCase()));
		
		return "store";
	}
	
	@GetMapping("/showProduct")
	public String showProduct(@RequestParam("productId") int id,
							  @RequestParam("korpa") String korpa,
							  @RequestParam("total") String total,						  
							  @RequestParam("category") String category,
							  Model model) {
		
		if(korpa.isEmpty())	model.addAttribute("basketimg", "kart.png");
		
					else 	model.addAttribute("basketimg", "kartfull.png");
		
		Product product = productService.getProduct(id);
		
		model.addAttribute("korpa", korpa);
		
		model.addAttribute("total", total);
		
		model.addAttribute("product", product);	
		
		if(category.equals("Svi proizvodi"))model.addAttribute("category", "Svi proizvodi");
		
		else
		model.addAttribute("category", category.replaceAll(" ", "_").replaceFirst(""+category.charAt(0), (""+category.charAt(0)).toLowerCase()));
		
		if(category.equals("Kuleri i oprema"))model.addAttribute("category", "kuleroprema");
	return "show-product";	
	}
	
	@GetMapping("/addProductIntoBill")
	public String addProductIntoBill(@RequestParam("productId") int id,
									 @RequestParam("korpa") String korpa,
									 @RequestParam("total") String total,
									 @RequestParam("category") String category) {
		
		if(!category.equals("Svi proizvodi"))category = category.toLowerCase();
		
		if(category.equals("hard disk"))category = "hard_disk";
		
		if(category.equals("kuleri i oprema")) category = "kuleroprema";
									 
		Product product = productService.getProduct(id);
		
		korpa = korpa + product.getId() +  ",";
		
		double totl = 0;
		
		try {
			
			totl = Double.parseDouble(total);
			
		}catch(Exception exc) {
			
			exc.printStackTrace();
			
		}
		
		totl += product.getPrice();		
		
		return "redirect:products?category="+category+"&korpa="+korpa+"&total="+totl;
	}
	
	
	@GetMapping("/showCart")
	public String showCart(	@RequestParam("korpa") String korpa,
							@RequestParam("total") String total,
							@RequestParam("category") String category,
							Model model) {
		
		if(!category.equals("Svi proizvodi"))category = category.toLowerCase();
		
		if(category.equals("hard disk"))category = "hard_disk";
		
		if(category.equals("kuleri i oprema")) category = "kuleroprema";
	
	if(korpa.isEmpty()) return "redirect:products?category=Svi+proizvodi&korpa="+korpa+"&total="+total;
	
	korpa = korpa.substring(0, korpa.length()-1);
	
	String[] prodIds = korpa.split(",");
	
	List<Product> cart = new ArrayList<>();
	
	for(String id : prodIds) {
		
		cart.add(productService.getProduct(Integer.parseInt(id)));
		
	}
	
	korpa = korpa+",";
	
	model.addAttribute("category", category);
	
	model.addAttribute("korpa", korpa);
	
	model.addAttribute("total", total);
	
	model.addAttribute("cartList", cart);
	
	model.addAttribute("basketimg", "kartfull.png");
	
	return "show-cart";
	
	}
	
	
	@GetMapping("/removeProduct")
	public String removeProduct(@RequestParam("productId") int id,
								@RequestParam("korpa") String korpa,
								@RequestParam("total") String total,
								@RequestParam("category") String category,
								Model model) {
		

		if(!category.equals("Svi proizvodi"))category = category.toLowerCase();
		
		if(category.equals("hard disk"))category = "hard_disk";
		
		if(category.equals("kuleri i oprema")) category = "kuleroprema";
		
		String ids = id+",";
		
		korpa = korpa.replaceFirst(ids,"");
		
		if(korpa.isEmpty()) return "redirect:products?category=Svi+proizvodi&korpa=&total=0";
		
		Product p = productService.getProduct(id);
		
		double totl = Double.parseDouble(total);
		
		totl -= p.getPrice();
		
		return "redirect:showCart?korpa="+korpa+"&total="+totl+"&category="+category;
}
	
	@GetMapping("/buy")
	public String buyAll(
						@RequestParam("korpa") String korpa,
						@RequestParam("total") String total,							
						Model model) {	
		Customer theCustomer = new Customer();
		
		theCustomer.setKorpa(korpa);
		
		model.addAttribute("theCustomer", theCustomer);	
		
		model.addAttribute("message", "");
		
		model.addAttribute("korpa", korpa);
		
		model.addAttribute("total", total);
		
		return "login-user-form";
	}
	
	
	
	@RequestMapping("/showBill")
	public String showBill(@Valid@ModelAttribute("theCustomer")
							Customer theCustomer,
							BindingResult br,													
							Model model) {	
		
		if(br.hasErrors()) return "login-user-form";
		
		Bill bill = new Bill();
		
		bill.setDatum(LocalDateTime.now());
		
		double total = 0;
		
		String korpa = theCustomer.getKorpa().substring(0, theCustomer.getKorpa().length() - 1);
		
		String[] ids = korpa.split(",");
		
		for(String idstr : ids) {
			
			Product p = productService.getProduct(Integer.parseInt(idstr));
			
			bill.addProduct(p);
			
			total += p.getPrice();
			
			p.setStack(p.getStack() - 1);
			
			productService.saveProduct(p);
			
		}			
		
		bill.setTotal(total);
		
		theCustomer.addBill(bill);
		
		customerService.saveCustomer(theCustomer);	
		
		Bill bill1 = billService.findLast(theCustomer);
		
		model.addAttribute("bill", bill1);
		
		model.addAttribute("datum", bill1.getDatum().toString().replace("T", ", "));
		
		return "bill-print";
	}
	
	
	@RequestMapping("/checkUser")
	public String showBill(	@ModelAttribute("theCustomer")Customer theCustomer,							
							Model model) {	
		
		Customer existingCustomer = customerService.getCustomer(theCustomer.getEmail(), theCustomer.getPassword());
		
		if(existingCustomer == null) {
			
			model.addAttribute("message", "Invalid username/password");
			
			model.addAttribute("theCustomer", theCustomer);
			
		
		}else {				
		
		existingCustomer.setKorpa(theCustomer.getKorpa());		
		
		model.addAttribute("message", "");
				
		model.addAttribute("theCustomer", existingCustomer);	
		
		}		
	
	return "login-user-form";
	
	}
	
	
}