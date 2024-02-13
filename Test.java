package com.client2;

import java.util.Scanner;

import com.serviceImplements.Sbi;


public class Test {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		Sbi sbi=new Sbi();
		
		while(true) {
		System.out.println("1.Add Account\n2.View Account\n3.Deposit Money\n4.Withdraw money\n5.Check Balance\n6.Delete Account\n7.Exit ");
		System.out.println("Enter your choice");
		int no=s.nextInt();
		
		switch(no) {
		case 1:
			sbi.addAccount();
			break;
		case 2:
			sbi.viewAccount();
			break;
		case 3:
			sbi.depositMoney();
			break;
		case 4:
			sbi.withdrawMoney();
			break;
		case 5:
			sbi.balanceCheck();
			break;
		case 6:
			sbi.deleteAccount();
			break;
		case 7:
			System.exit(0);
		default:
			System.out.println("Invalid Input");
			
		}
		}

		
	}

	
}
