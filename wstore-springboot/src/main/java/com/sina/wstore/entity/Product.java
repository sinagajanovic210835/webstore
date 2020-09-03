package com.sina.wstore.entity;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	@Column(name="name")
	private String productName;
	
	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	@Column(name="category")
	private String category;
	
	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	@Column(name="pictureurl")
	private String pictureUrl;
	
	@Column(name="price")
	private double price;
	
	@Column(name="stack")
	private int stack;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="bill_product",	
	joinColumns = @JoinColumn(name = "prod_id"),
	inverseJoinColumns = @JoinColumn(name="bill_id"))
    private List<Bill> listBill;

	public Product() {
		
	}

	public Product(String productName, String category, String pictureUrl, double price, int stack) {
		this.productName = productName;
		this.category = category;
		this.pictureUrl = pictureUrl;
		this.price = price;
		this.stack = stack;
	}

	public List<Bill> getListBill() {
		return listBill;
	}

	public void setListBill(List<Bill> listBill) {
		this.listBill = listBill;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStack() {
		return stack;
	}

	public void setStack(int stack) {
		this.stack = stack;
	}
	
	 public void addBill(Bill theBill) {
	    	
	    	if (listBill == null) listBill = new ArrayList<>();
	    	
	    	listBill.add(theBill);
	    	
	    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", category=" + category + ", pictureUrl="
				+ pictureUrl + ", price=" + price + ", stack=" + stack + "]";
	}
	
	
	
}

