package com.model2;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Account {

	@Id
	private int id;
	private String name;
	private String address;
	private long mobno;
	private String AccType;
	public String getAccType() {
		return AccType;
	}
	public void setAccType(String accType) {
		AccType = accType;
	}
	private double balance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobno() {
		return mobno;
	}
	public void setMobno(long mobno) {
		this.mobno = mobno;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
