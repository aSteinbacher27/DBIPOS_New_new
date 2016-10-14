/********************************************************************************
File Name: Register.java

Attributes List:
	
	
Methods List:
	
	
********************************************************************************/

import java.util.ArrayList;
import java.io.*;
import java.math.*;
import java.util.Arrays;

public class Register {

	
	//Array lists of Employees and Items
	//ArrayList<Employee> employees = new ArrayList<Employee>(); class not created yet
	//ArrayList<Item> items = new ArrayList<Item>(); class not created yet
	
	
	//Party Array lists
<<<<<<< HEAD
	private ArrayList<Party> waitingParties = new ArrayList<Party>();
	private ArrayList<Party> activeParties = new ArrayList<Party>();
=======
	ArrayList<Party> waitingParties = new ArrayList<Party>();
	ArrayList<Party> activeParties = new ArrayList<Party>();
>>>>>>> f96eda0e7fc86ed327b9be55c50dffe97bc8200e
	
	
	//Payroll (not yet)
	
<<<<<<< HEAD
	//BigDecimal curentCash = new BigDecimal(0.0);
	double currentCash;
	
	public Register() {
		//populate employees and items
		
		//dummy party
		Party testParty = new Party("test", 3);
		activeParties.add(testParty);
		
		
	}
	
	/*
	 * 
	 */
	public void addWaitingParty(Party p){
		
		waitingParties.add(p);
	}
=======
	BigDecimal curentCash = new BigDecimal(0.0);
	
	Register() {
		//populate employees and items
		
		
		
	}
>>>>>>> f96eda0e7fc86ed327b9be55c50dffe97bc8200e
		
	
}
