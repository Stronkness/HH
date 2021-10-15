//*******************************************************
// Account.java
//
//@author André Frisk
//@version 1.0
//@since 2019-04-09
//
// A bank account class with methods to deposit to, withdraw from,
// change the name on, charge a fee to, and print a summary of the account.
//*******************************************************
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
   public class Account implements Comparable<Object> 
   {
      private double balance;
      private String accountNumber;
   
   //----------------------------------------------
   //Constructor -- initializes balance, owner, and account number
   //----------------------------------------------
      public Account(String number, double initBal)
      {
         balance = initBal;
         accountNumber = number;
      }
   
   //----------------------------------------------
   // Checks to see if balance is sufficient for withdrawal.
   // If so, decrements balance by amount; if not, prints message.
   //----------------------------------------------
      public String withdraw(double amount)
      {
         String info="Insufficient funds";
         if (balance >= amount){
            balance=balance- amount;
            info= "Succeeded, the new balance is : "+ balance ;
         }
      
         return info;
      }
   
   //----------------------------------------------
   // Adds deposit amount to balance.
   //----------------------------------------------
      public String deposit(double amount)
      {
         String info=""  ;
         if( amount<0)
            info = " Wrong amount";
         else{
            balance=balance+ amount;
            info=" Succeeded, the new balance is: " + balance;
         }
      
         return info;
      }
   
   //----------------------------------------------
   // Returns balance.
   //----------------------------------------------
      public double getBalance()
      {
         return balance;
      }
   
   
   //----------------------------------------------
   // Returns a string containing the name, account number, and balance.
   //----------------------------------------------
      public String toString()
      {
         return  " Nummer: "+ accountNumber+ " Balance: " + balance;
      }
   
   //----------------------------------------------
   // Returns account number.
   //----------------------------------------------
   
      public String getAcctNum()
      {
         return accountNumber;
      }
   	
      public boolean equals(Object other){
    	  if(accountNumber.equals(other)) {
    		  return false;
    	  }
         return true;
      }
   	
   	
   		
   
      public static void main ( String [] arg)
      {
         MyArrayList <Account> mylist = new MyArrayList<Account>();
        
         Account acc_1 = new Account ( "1111111111", 700);
         mylist.add(acc_1);
         mylist.add( new Account ( "0987654321", 7000));
         mylist.add( new Account ( "1234567890", 50));
         mylist.add( new Account ( "3651780543", 100000));
         
         mylist.get(2).deposit(1200);
         Iterator<Account> itr = mylist.iterator();
         Account acc = mylist.get(1);
         System.out.println(acc.deposit(500));
         
         
         double over = 5000;
         double lower = 1000;
       
    	   
        do{
        	double depositValue = ((over-lower)*Math.random()) + lower;
        	System.out.println(itr.next().deposit(depositValue));
         }while(itr.hasNext());
        
       // Collections.sort(mylist);
        // The method sort(List<T>) in the type Collections is not applicable for the arguments (MyArrayList<Account>)
        // MyArrayList does not have the sort method so it cant sort the list.
       }
      

	@Override
	/*
	 * param o; the input which will be compared, ex. balance or account name
	 */
	public int compareTo(Object o) {
		if(((Account)o).getBalance() < balance) {
			return 1;
		}
		if(((Account)o).getBalance() > balance) {
			return -1;
		}
		return 0;
	}
   }