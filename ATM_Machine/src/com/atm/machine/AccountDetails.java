package com.atm.machine;

import java.util.ArrayList;

public class AccountDetails {

	private String name; // name of account 
//	private double balance;  // current balance of user 
	private String accountNumber;   // Account number of user //uuid
	
	//user object who is owner of this acoount 
	private UserDetails accountHolder;  
	
	//Transaction Details will store here (List of transaction details)
	private ArrayList<Transaction_Details>transactions;
	
	/*
	 * this constructor is for creating new account 
	 * name :- name of account
	 * holder :- user (object) those hold this account
	 * bank :- Bank name where this account is opened 
	 */
	public AccountDetails(String name,UserDetails holder,Bank bank ) {
		
		//set the account name and holder 
		this.name=name;
		this.accountHolder=holder;
		
		//get new account userId
		this.accountNumber=bank.getNewAccountUid();
		
		//store of transaction details 
		this.transactions=new ArrayList<Transaction_Details>();
		
		//adding to holder and bank list
		
	}

	public String getUUID() {
	
		return this.accountNumber;
	}

	/*
	 * get summary line for the account
	 * @return the string summary 
	 */
	public String getSummary() {
		
		//get the account's balance 
		double balance = this.getBalance();
		
		//format the summary line
		//depending on the whether the balance 
		// is negative 
		
		if(balance>=0) {
			return String.format("%s : $%.02f : %s", this.accountNumber,balance,this.name);
		}else {
			return String.format("%s : $(%.02f) : %s", this.accountNumber,balance,this.name);
		}
		
	}
/*
 * get the balance of this account by adding the amount of the transactions 
 * @return the balance values
 * 
 */
	public double getBalance() {
		double balance =0;
		for(Transaction_Details t: this.transactions) {
			balance += t.getAmount();
		}
		return balance;
	}

	/*
	 * print the transaction history of the 
	 * account 
	 */
	public void printTransactionHistory() {
		System.out.printf("\nTransaction history for account %s\n",
				this.accountNumber);
		
		for(int i=this.transactions.size()-1;i>=0;i--) {
			System.out.println(this.transactions.get(i).getSummaryLine());
		}
		System.out.println();
	}

	/*
	 * Add a new transaction in this account
	 * @parameter amount :- the amount transaction
	 * @parameter memo the transaction memo
	 */
	public void addTransaction(double amount, String memo) {
		// create a new transaction object and add it to
		
		Transaction_Details newTrans= new Transaction_Details(amount,memo,this);
	    this.transactions.add(newTrans);
	}

}
