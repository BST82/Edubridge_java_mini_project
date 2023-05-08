package com.atm.machine;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UserDetails {
	
	private String firstName;   // user first name
	private String lastName;    // user last name 
	private String userId;     // user Id 
	private byte pinNumber[];    //user atm pin number 
	

	// number of accounts and details of user  
	private ArrayList<AccountDetails>account;  
	
	/*
	 * Here I am using Constructor for 
	 * firstName
	 * lastName
	 * pin number
	 * Bank name
	 */
	public UserDetails(String fn,String ln,String pin,Bank bank) {
		
		//Setting user first name
		this.firstName=fn;
		//Setting user last name
		this.lastName=ln;
		
		//Setting user pin in MD5 hash for security purpose 
		try {
			
			//here is exception is accruing 
			MessageDigest md=MessageDigest.getInstance("MD5");
			
			
			/*********************************************
			 */
			this.pinNumber=md.digest(pin.getBytes());
			/*********************************************
			 */
			
		} catch (NoSuchAlgorithmException e) {
			System.out.println("unhandle exception NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);   // Exit jvm when Exception accrue
		}
		
		
		//get a new and unique Id for the user;
		this.userId=bank.getNewUserId();
		
		// creating empty list of accounts 
		this.account = new ArrayList<AccountDetails>();
		
		//print message
		System.out.printf("New user %s,%s with Id %s created .\n",
				firstName,lastName,this.userId);
		
	}

	/*
	 * 
	 * Add an account for the user 
	 * accountDetails the account to add
	 */
	public void addAccount(AccountDetails accountDetails) {
		this.account.add(accountDetails);
	}

	/*
	 * It will return user id
	 * 
	 */
	public String getUUID() {
		
		return this.userId;
	}

	/*
	 * check whether a given pin matches the true user pin
	 * parameter :-  userpin (the pin to check)
	 * 
	 * return statement;- 
	 * whether the pin is valid or not 
	 */
	public boolean validatePin(String userpin) {
		 
		try {
			MessageDigest md= MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(userpin.getBytes()),
					this.pinNumber);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("unhandle exception NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);   // Exit jvm when Exception accrue
		}
		return false;
	}

	public String getFirstName() {
		
		return this.firstName;
	}

	public void printAccountSummery() {
		System.out.printf("\n\n%s's account summery \n",this.firstName);
		for(int i=0;i<this.account.size();i++) {
			System.out.printf("%d) %s\n ",i+1,this.account.get(i).getSummary());
		}
		System.out.println();
	}
	
	public int numAccounts() {
		return this.account.size();
	}

	/*
	 * print transaction history for a perticular
	 * account 
	 * @parameter acctInd the index of the account to use
	 */
	public void printAccountSummery(int acctInd) {
		
		this.account.get(acctInd).printTransactionHistory();
		
	}

	/*
	 * get the balance of  a particular account
	 * @parameter accInd:- the index of the account to use 
	 * @return the balance of the account 
	 */
	public double getAcctBalance(int acctInd) {
		
		return this.account.get(acctInd).getBalance();
	}

	/*
	 * get the uuid of a particular account
	 * @param acctInd :- the index of the account to use 
	 * @return :- the uuid of the account 
	 */
	public String getAcctUUID(int acctInd) {
		
		return this.account.get(acctInd).getUUID();
	}

	/*
	 * Add a transaction to a particular account
	 * @params acctIdx :- the index of the account 
	 * @param amount:- the amount of the transaction
	 * @param memo:- the memo of the transaction 
	 */
	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.account.get(acctIdx).addTransaction(amount,memo);
	}

}

/* 
 * Some extra information 
 * System.exit(0) or EXIT_SUCCESS;  ---> Success
  System.exit(1) or EXIT_FAILURE;  ---> Exception
  System.exit(-1) or EXIT_ERROR;   ---> Error
  
  
 */
