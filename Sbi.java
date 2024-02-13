package com.serviceImplements;

import java.util.Iterator;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.config.HibernateUtil;
import com.model2.Account;
import com.service.Rbi;

public class Sbi implements Rbi {

	static Scanner s= new Scanner (System.in);
	Account a=new Account();
	
	@Override
	public void addAccount () {
		
		Session ss= HibernateUtil.getSessionFactory().openSession();
		System.out.println("How many accounts you want to open");
		int nos= s.nextInt();
	for (int i=1; i<=nos;i++) {
		Transaction tr= ss.beginTransaction();
		int count=0;
		System.out.println("Enter your 8 digit Account Id-");
		int id=s.nextInt();
		int newid=id;
		while (id>0) {
			id=id/10;
			count++;
		}
		if(count==8) {
			a.setId(newid);
		}else {
			System.out.println("Enter Valid Id ");
			addAccount();
		}
		
		System.out.println("Enter your Name-");
		String name=s.next();
		String c[]= {"!","@","#","$","%","^","&","*","(",")","-","_","=","+","[","]","{","}",";","<",">","?","/"};
		for(int i1=0;i1<c.length;i1++)
		{
			if (!name.contains(c[i1])) {
				a.setName(name);
			}
			else {
				System.out.println("Enter Valid Name");
				addAccount();
			}
		}
		
		System.out.println("Enter Address-");
		String add=s.next();
		a.setAddress(add);
		
		System.out.println("Enter Mobile Number-");
		long mobno=s.nextLong();
		long newmobno=mobno;
		count=0;
		while (newmobno>0) {
			newmobno=newmobno/10;
			count++;
		}
		if(count==10) {
			a.setMobno(mobno);
		}else {
			System.out.println("Invalid Mobile Number");
			addAccount();
		}
		
		System.out.println("Enter Account Type- ");
		System.out.println("1.Savings Account\n2.Current Account ");
		int numb=s.nextInt();
		if (numb==1) {
			String aType="Savings Account";
			a.setAccType(aType);
		}
		else if(numb==2) {
			String aType="Current Account";
			a.setAccType(aType);
		}
		else {
			System.out.println("Invalid Choice");
			addAccount();
		}
		
		System.out.println("Enter Account Balance-");
		double bal=s.nextDouble();
		if(bal>=2000) {
			a.setBalance(bal);
			System.out.println("Balance set");
		}else {
			System.out.println("Minimun accout balance should be 2000");
			addAccount();
		}
		ss.save(a);
		tr.commit();
		ss.clear();
		System.out.println("Account Added");
		}
	    System.out.println("ALL Data inserted");
	}


	@Override
	public void viewAccount() {
		
		Session ss= HibernateUtil.getSessionFactory().openSession();
		System.out.println("Enter Account No to view your account");
		int aid= s.nextInt();
		Account a= ss.get(Account.class, aid);
		System.out.println("Id-"+a.getId());
		System.out.println("Name- "+a.getName());
		System.out.println("Address- "+a.getAddress());
		System.out.println("MobNo- "+a.getMobno());
		System.out.println("AccType- "+a.getAccType());
		System.out.println("AccBalance- "+a.getBalance());
		
	}

	@Override
	public void depositMoney() {
		Session ss= HibernateUtil.getSessionFactory().openSession();
		Transaction tr = ss.beginTransaction();
		System.out.println("Enter Account no in which you want to deposit money");
		int aid= s.nextInt();
		System.out.println("Enter money to deposit");
		Double dMoney=s.nextDouble();
		if(dMoney<=100000 && dMoney>=500) {
			Account a= ss.load(Account.class, aid);
			Double Total=a.getBalance()+dMoney;
			a.setBalance(Total);
			ss.update(a);
			System.out.println("Money deposited");
		}else {
			System.out.println("Enter Amount within 500 to 100000");
			depositMoney();
		}
		tr.commit();
	}

	@Override
	public void withdrawMoney() {
		Session ss= HibernateUtil.getSessionFactory().openSession();
		Transaction tr = ss.beginTransaction();
		System.out.println("Enter id from which you want to withdraw money");
		int aid= s.nextInt();
		Account a= ss.load(Account.class, aid);
		System.out.println("Enter money to withdraw");
		Double wMoney=s.nextDouble();
		if(wMoney<=a.getBalance()) {
			if(wMoney<=20000 && wMoney>=500) {
				Double Total=a.getBalance()-wMoney;
				a.setBalance(Total);
				ss.update(a);
				System.out.println("Money Withdrawn");
			}else {
			System.out.println("Enter Amount within 500 to 20000");}
			
			}
		else {
			System.out.println("You do not have sufficient account balance");
			
		}
		tr.commit();
	}

	@Override
	public void balanceCheck() {
		Session ss= HibernateUtil.getSessionFactory().openSession();
		System.out.println("Enter id to check balance");
		int aid= s.nextInt();
		Account a = ss.load(Account.class, aid);
		System.out.println("Account Balance= "+a.getBalance() );
		
	}
	
	@Override
	public void deleteAccount() {
		Session ss= HibernateUtil.getSessionFactory().openSession();
		Transaction tr= ss.beginTransaction();
		System.out.println("Enter Account id which you want to delete");
		int aid= s.nextInt();
		Account a = ss.get(Account.class, aid);
		ss.delete(a);
		tr.commit();
		ss.clear();
		System.out.println("Account Deleted Successfully");
	}
}
