package com.atm.machine;

import java.util.Date;

public class Transaction_Details {

	private double amount; // Amount of transaction 
	private Date date;    // Time and Date of Transaction 
	private String memo;    // Memo for the transaction 
	
	
	//the account in which transaction was performed
	private AccountDetails toAccount ;  
	

	public Transaction_Details(double amount,AccountDetails inAccount) {
		//this is two arguments constructor 
		this.amount=amount;
		this.toAccount=inAccount;
		this.date=new Date();
		this.memo="";
	}
	
	/*
	 * Create a new transaction amount 
	 * amount := amount transaction 
	 * memo :- the memo for the transaction 
	 * inAccount :- the account the transaction belongs to
	 */
	public Transaction_Details(double amount,String memo,AccountDetails inAccount) {
		
		//calling two arguments constructor first
		this(amount, inAccount);
		
		//set the memo
		this.memo=memo;
		
	}

	/*
	 * get the amount of the transaction
	 * @return the amount
	 */
	public double getAmount() {
	
		return this.amount;
	}

	/*
	 * get a String summarizing the transaction 
	 * @return the summary string 
	 */
	public String getSummaryLine() {
		
		if(this.amount>=0) {
			return String.format("%s : $%.02f : %s", this.date.toString(),
					this.amount,this.memo);
		}else {
			return String.format("%s : $(%.02f) : %s", this.date.toString(),
					-this.amount/*- sign is because after tansaction amount will be negative*/,this.memo);
		}
		
	}
	
}
