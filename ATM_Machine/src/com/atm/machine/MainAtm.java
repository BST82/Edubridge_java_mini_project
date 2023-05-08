package com.atm.machine;

import java.util.Scanner;

public class MainAtm {

	public static void main(String[] args) {
	
		//init scanner
		Scanner sc= new Scanner(System.in);
		
		// init bank
		Bank theBank = new Bank("Bank of India");
		
		/*add user to bank which also 
		*creates a saving account 
		 * */
		UserDetails auser =theBank.addUser("Bhagyashri","Tamrakar","1234");
		
		//add a checking account for our user
		AccountDetails newAccount = new AccountDetails("Checking",auser,theBank);
		auser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		/***************************/
		UserDetails curUser;
		while(true) {
			//stay in the login prompt until successful login
			curUser= MainAtm.mainMenuPrompt(theBank,sc);
			
			//stay in main menu until user quit
			MainAtm.printUserMenu(curUser,sc);
		}
	}


	/*
	 * parameter theBank
	 * parameter sc
	 */
	private static UserDetails mainMenuPrompt(Bank theBank, Scanner sc) {
		
		//inits
		String userID;
		String pin;
		UserDetails authUser;
		
		//prompt the user for user ID/Pin combo until a correct one is reached
		do {
			System.out.printf("\n\nWelcome to %s \n\n",theBank.getName());
			System.out.print("Enter user ID : ");
			userID=sc.nextLine();
			System.out.print("Enter PIN :");
			pin=sc.nextLine();
			
			//try to get the user object corresponding to the ID and pin combo 
			authUser = theBank.userLogin(userID, pin);
			if(authUser==null) {
				System.out.println("Incorrect user ID/pin combination "+
			"please try again");
			}
			
		}while(authUser==null); // continue looping untill successfully login 
		
		return authUser;
	}
	/************************************************************************/

	private static void printUserMenu(UserDetails theUser, Scanner sc) {

     // print a summery of the users's account
		theUser.printAccountSummery();
		
		//init 
		int choice;
		
		//user menu 
		do {
			System.out.printf("Welcome %s, what would you like to do?",
					theUser.getFirstName());
			System.out.println();
			System.out.println(" 1) show account transaction history");
			System.out.println(" 2) withdrawl");
			System.out.println(" 3) Deposit");
			System.out.println(" 4) Transfer");
			System.out.println(" 5) Quit");
			System.out.println();
			System.out.println("Enter choice:");
			choice=sc.nextInt();
			
			if(choice<1 || choice>5) {
				System.out.println("Invalid choice . Please choose 1-5");
			}
		}while(choice<1 || choice>5);
		
		//process the choice
		switch(choice) {
		case 1:
			MainAtm.showTransactionHistory(theUser,sc);
			break;
		case 2:
			MainAtm.withdrawlFunds(theUser,sc);
			break;
		case 3:
			MainAtm.depositeFunds(theUser,sc);
			break;
		case 4:
			MainAtm.transferFunds(theUser,sc);
			break;
		}
		
		// redisplay the menu unless the user wants to quit
		if(choice!=5) {
			MainAtm.printUserMenu(theUser, sc);
		}
	}





	/*
 * show the transaction history for an account
 * @param theUser:- the logged-in user object
 * @param sc the :- Sacnner object used for user input
 * 
 */
	public static void showTransactionHistory(UserDetails theUser, Scanner sc) {
		
		int theAcct;
		
		//get account whose transaction history to look at 
		do {
			System.out.printf("Enter the number (1-%d) of the acount "+
		"whose transaction you want to see:",theUser.numAccounts());
			theAcct=sc.nextInt()-1;
			if(theAcct<0 || theAcct>=theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again");
			}
			}while(theAcct<0 || theAcct>=theUser.numAccounts());
		
		//print the transaction history 
		theUser.printAccountSummery(theAcct);
		
	}
	
	
	
private static void transferFunds(UserDetails theUser, Scanner sc) {
		
	//inits
	
	int fromAcct;
	int toAcct;
	double amount;
	double acctBal;
	
	//get the account to transfer from
	do {
		System.out.printf("Enter the number (1-%d) of the account\n"+
	 "to transfer from: ",theUser.numAccounts());
		fromAcct= sc.nextInt()-1;
		
		if(fromAcct<0 || fromAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account . Please try again.");
		}
		}while(fromAcct<0 || fromAcct>=theUser.numAccounts());
	
	acctBal= theUser.getAcctBalance(fromAcct);
	
	
	//get the account to transfer to
	do {
		System.out.printf("Enter the number (1-%d) of the account\n"+
	 "to transfer to: ",theUser.numAccounts());
		toAcct= sc.nextInt()-1;
		
		if(toAcct<0 || toAcct>=theUser.numAccounts()) {
			System.out.println("Invalid account . Please try again.");
		}
		}while(toAcct<0 || toAcct>=theUser.numAccounts());
	
	//get the amount to transfer
	do {
		System.out.printf("Enter the amount to transfer (max $%.02f): $",
				acctBal);
		amount=sc.nextDouble();
		if(amount<0) {
			System.out.println("Amount must be greater then 0 ");
		}else if(amount>acctBal){
			System.out.printf("Enter amount is greater than current balance\n"+
		"balance of $%.02f\n",acctBal);
		}
	}while(amount<0 || amount>acctBal);
	
	//finally , do the transfer ammount
	theUser.addAcctTransaction(fromAcct, -1*amount,String.format("Transfer to account %s",theUser.getAcctUUID(toAcct)));
	
	theUser.addAcctTransaction(toAcct, amount,String.format("Transfer from account %s",theUser.getAcctUUID(fromAcct)));
	}

/*process a fund withdraw from an account
 * @parameter theUser= the logged-in user object
 * @parameter sc= the Scanner object user for the user input 
 * 
 */
private static void withdrawlFunds(UserDetails theUser, Scanner sc) {
	
	//inits
	
		int fromAcct;
		double amount;
		double acctBal;
		String memo;
		
		//get the account to transfer from
		do {
			System.out.printf("Enter the number (1-%d) of the account\n"+
		 "to widraw from: ",theUser.numAccounts());
			fromAcct= sc.nextInt()-1;
			
			if(fromAcct<0 || fromAcct>=theUser.numAccounts()) {
				System.out.println("Invalid account . Please try again.");
			}
			}while(fromAcct<0 || fromAcct>=theUser.numAccounts());
		
		acctBal= theUser.getAcctBalance(fromAcct);
		
		//get the amount to transfer
		do {
			System.out.printf("Enter the amount to widraw (max $%.02f): $",
					acctBal);
			amount=sc.nextDouble();
			if(amount<0) {
				System.out.println("Amount must be greater then 0 ");
			}else if(amount>acctBal){
				System.out.printf("Enter amount is greater than current balance\n"+
			"balance of $%.02f\n",acctBal);
			}
		}while(amount<0 || amount>acctBal);
		
		//globle up rest of previous input 
		sc.nextLine();
		
		//get a memo 
		System.out.println("Enter reason for widraw: ");
		memo=sc.nextLine();
		
		//do the withdrawl 
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);
	
}



/*
 * Process a fund deposit to an account 
 * @parameter theUser = the logged in User Object
 * @parameter sc= the Scanner object used for user input 
 * 
 */
private static void depositeFunds(UserDetails theUser, Scanner sc) {

	//inits
	
			int toAcct;
			double amount;
			double acctBal;
			String memo;
			
			//get the account to transfer from
			do {
				System.out.printf("Enter the number (1-%d) of the account\n"+
			 "to transform from: ",theUser.numAccounts());
				toAcct= sc.nextInt()-1;
				
				if(toAcct<0 || toAcct>=theUser.numAccounts()) {
					System.out.println("Invalid account . Please try again.");
				}
				}while(toAcct<0 || toAcct>=theUser.numAccounts());
			
			acctBal= theUser.getAcctBalance(toAcct);
			
			//get the amount to transfer
			do {
				System.out.printf("Enter the amount to transfer (max $%.02f): $",
						acctBal);
				amount=sc.nextDouble();
				if(amount<0) {
					System.out.println("Amount must be greater then 0 ");
				}
			}while(amount<0);
			
			//globle up rest of previous input 
			sc.nextLine();
			
			//get a memo 
			System.out.println("Enter your reason to deposite ammount : ");
			memo=sc.nextLine();
			
			//do the withdrawl 
			theUser.addAcctTransaction(toAcct, amount, memo);
}
}
