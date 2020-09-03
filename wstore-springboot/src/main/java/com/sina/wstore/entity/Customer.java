package com.sina.wstore.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="first_name")
	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	private String firstName;
	
	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	@Column(name="last_name")
	private String lastName;
	
	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	@Column(name="e_mail")
	private String email;
	
	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	@Column(name="home_adress")
	private String homeAdress;
	
	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	@Column(name="home_place")
	private String homePlace;
	
	@NotNull(message = "is required")
	@Size(min = 1, message="is required")
	@Column(name="phone_number")
	private String phoneNumber;
	
	@NotNull(message = "is required")
	@Size(min = 8, message="is required, minimum 8 chars")
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Bill> userBills;
	
	@Column(name="korpa")
	private String korpa;

	public Customer() {
		
	}
	
	

	public Customer(String firstName, String lastName, String eMail, String homeAdress, String homePlace,
			String phoneNumber, String userPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = eMail;
		this.homeAdress = homeAdress;
		this.homePlace = homePlace;
		this.phoneNumber = phoneNumber;
		this.password = userPassword;
	}



	public String getKorpa() {
		return korpa;
	}



	public void setKorpa(String korpa) {
		this.korpa = korpa;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String userPassword) {
		this.password = userPassword;
	}



	public List<Bill> getUserBills() {
		return userBills;
	}



	public void setUserBills(List<Bill> userBills) {
		this.userBills = userBills;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getHomeAdress() {
		return homeAdress;
	}

	public void setHomeAdress(String homeAdress) {
		this.homeAdress = homeAdress;
	}

	

	public String getHomePlace() {
		return homePlace;
	}



	public void setHomePlace(String homePlace) {
		this.homePlace = homePlace;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void addBill(Bill theBill){
		
		if(userBills == null) userBills = new ArrayList<>();
		
		userBills.add(theBill);
		
		theBill.setCustomer(this);
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", eMail=" + email
				+ ", homeAdress=" + homeAdress + ", placeOfLiving=" + homePlace + ", phoneNumber=" + phoneNumber
				+ "]";
	}
	
}
