package com.sina.wstore.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="datum")
	LocalDateTime datum;
	
	@Column(name="total")
	private double total;
	
	@Column(name="payed")
	private boolean paid;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="userid")
	private Customer customer;
	
	 @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
		@JoinTable(name="bill_product",	
		joinColumns = @JoinColumn(name = "bill_id"),
		inverseJoinColumns = @JoinColumn(name="prod_id"))
	private List<Product> listProducts;
	 
	 

	public Bill() {
		
	}



	public Bill(LocalDateTime datum, double total, int userId, List<Product> listProducts) {
		this.datum = datum;
		this.total = total;
		this.listProducts = listProducts;
	}



	public boolean isPaid() {
		return paid;
	}



	public void setPaid(boolean paid) {
		this.paid = paid;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public LocalDateTime getDatum() {
		return datum;
	}



	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}




	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public List<Product> getListProducts() {
		return listProducts;
	}



	public void setListProducts(List<Product> listProducts) {
		this.listProducts = listProducts;
	}

    public void addProduct(Product theProduct) {
    	
    	if (listProducts == null) listProducts = new ArrayList<>();
    	
    	listProducts.add(theProduct);
    	
    }



	@Override
	public String toString() {
		return "Bill [id=" + id + ", datum=" + datum + ", total=" + total + ", user=" + customer + ", listProducts="
				+ listProducts + "]";
	}

	
}
