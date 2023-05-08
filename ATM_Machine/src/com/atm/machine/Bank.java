package com.atm.machine;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
 private String name; // name of bank
 
 private ArrayList<UserDetails>users; //user details of bank
 
 
 private ArrayList<AccountDetails>accounts;  // account details of user 
 
 /*
  * create a new bank object with empty lists of users and
  * accounts 
  * 
  * parameters:- 
  * name(name of bank)
  */
 public Bank(String name) {
	this.name=name;
	this.users= new ArrayList<UserDetails>();
	this.accounts=new ArrayList<AccountDetails>();
}

/*
  * Generate a new niversal unique id for
  * user the
  * userid
  */
 public String getNewUserId() {
 
	 //inits 
   String userId;
   Random randomvalue= new Random();
   int length=10;
   boolean nonUnique;
   
   //continue looping until get unique value 
   do {
	   //generate number 
	   userId="";
	   for(int i=0;i<length;i++) {
		   userId+=((Integer)randomvalue.nextInt(10)).toString();
	   }
	   
	   //check to make sure it's unique 
	   nonUnique=false;
	   for(UserDetails u: this.users) {
		   if(userId.compareTo(u.getUUID())==0) {
			   nonUnique=true;
			   break;
		   }
	   }
   }while(nonUnique);
   
  return userId;
	 
 }

 /*
  * generaing a new universal unique ID for an account 
  * the userid
  */
public String getNewAccountUid() {
	
	 //inits 
	   String userId;
	   Random randomvalue= new Random();
	   int length=10;
	   boolean nonUnique;
	   
	   //continue looping until get unique value 
	   do {
		   //generate number 
		   userId="";
		   for(int i=0;i<length;i++) {
			   userId+=((Integer)randomvalue.nextInt(10)).toString();
		   }
		   
		   //check to make sure it's unique 
		   nonUnique=false;
		   for(AccountDetails a: this.accounts) {
			   if(userId.compareTo(a.getUUID())==0) {
				   nonUnique=true;
				   break;
			   }
		   }
	   }while(nonUnique);
	   
	  return userId;
}

/*
 * Add an account for the user 
 * accountDetails the account to add
 */
public void addAccount(AccountDetails accountDetails) {
	this.accounts.add(accountDetails);
}

/*
 * parameters:--------
 * furstname
 * lastname
 * pin
 * 
 * return :-------
 * the new user object
 * 
 */

public UserDetails addUser(String firstname,String lastname, String pin) {
	
	//create a new user object and add it to our list
	UserDetails newUser= new UserDetails(firstname,lastname,pin,this);
	this.users.add(newUser);
	
	//create a savings accounts of user and add to user 
	//and bank into 
	//account list 
	AccountDetails newAccount= new AccountDetails("Savings",newUser,this);
	newUser.addAccount(newAccount);
	this.accounts.add(newAccount);
	
	return newUser;
}

/*
 * get the user object associated with a particular userID and pin
 * if they are valid 
 * 
 * parameters:-------------
 * userid :- the user Id to log in 
 * pin :- the pin of the user 
 * 
 * return :-----------------------
 * the user object , if the login is successfully or not 
 * 
 */

public UserDetails userLogin(String userId,String pin) {
	
	//search througn list of users
	for(UserDetails u:this.users) {
		
		//check user id is correct
		if(u.getUUID().compareTo(userId)==0 && u.validatePin(pin)) {
			return u;
		}
	}
	
	//if we haven't found the user or have an incorrect pin
	return null;
	
}

public Object getName() {
	// TODO Auto-generated method stub
	return this.name;
}
}
