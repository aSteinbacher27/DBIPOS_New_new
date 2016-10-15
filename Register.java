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
	static ArrayList<Item> items = new ArrayList<Item>();
	
	
	//Party Array lists
	ArrayList<Party> waitingParties = new ArrayList<Party>();
	ArrayList<Party> activeParties = new ArrayList<Party>();
	
	
	//Payroll (not yet)
	
	//BigDecimal curentCash = new BigDecimal(0.0);
	double currentCash;
	
	Register() {
		//populate employees and items
		//dummy items
		Item testItem = new Item("toy", 10.03);
		items.add(testItem);
		
		//dummy party
		Party testParty = new Party("test", 3);
		activeParties.add(testParty);
		
	}
	
	static public double getItemPrice(int id){
		double price = items.get(id).getPrice();
		return price;
		
	}
	
	static public String getItemName(int id){
		String name = items.get(id).getName();
		return name;
	}
		
	
}
