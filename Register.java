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
	//ArrayList<Employee> employees = new ArrayList<Employee>();
	//ArrayList<Item> items = new ArrayList<Item>(); classes not created yet
	
	
	//Party Array lists
	ArrayList<Party> waitingParties = new ArrayList<Party>();
	ArrayList<Party> activeParties = new ArrayList<Party>();

	
	
	//Payroll (not yet)
	

	//BigDecimal curentCash = new BigDecimal(0.0);
	double currentCash;
	
	
	Register() {
		//populate employees and items
		
		//dummy party
		Party testParty = new Party("test", 3);
		this.activeParties.add(testParty);
		
	}
	
	/*
	 * 
	 */
	public void addWaitingParty(Party p){
		
		this.waitingParties.add(p);
	}

	
}
