/********************************************************************************
File Name: Register.java

Attributes List:
	
	
Methods List:
	
	
********************************************************************************/

import java.util.ArrayList;
import java.io.*;
import java.math.*;
import java.util.Arrays;
import java.util.Scanner;

public class Register {

	
	//Array lists of Employees and Items
	//ArrayList<Employee> employees = new ArrayList<Employee>(); class not created yet
	static ArrayList<Item> items = new ArrayList<Item>();
	
	
	//Party Array lists
	ArrayList<Party> waitingParties = new ArrayList<Party>();
	ArrayList<Party> activeParties = new ArrayList<Party>();
	
	
	//Payroll (not yet)
	
	//finding the Employee and Items files
	String itemFileString = new String("items.txt");
	String employeeFileString = new String("employeeFile.txt");
	

	double currentCash;
	
	Register() {
		//populate employees and items
		popItems(itemFileString);
		popEmployees(employeeFileString);
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
	
	private void popItems(String fileName){
		try{
			//set up file and scanner
			File itemFile = new File(fileName);
			Scanner input = new Scanner(itemFile);
			
			//read file until empty
			while(input.hasNextLine()) {
				
				//preps temp variables for new object creation
				String name = new String();
				double price;
				
				//reads values from txt file
				name = input.next();
				price = input.nextDouble();
				
				//creates an object to add to the item array
				Item newItem = new Item(name, price);
				
				//adds new item to items array
				this.items.add(newItem);
			}
			
			//testing
			System.out.println(this.items.get(1).getName());
			
			
		} catch(IOException e) {
			//fail case
			System.out.println("popItems has horribly failed with file IO");
		
		}
		
	}
	
	private void popEmployees(String fileName){
		try{
			//set up file and scanner
			File itemFile = new File(fileName);
			Scanner input = new Scanner(itemFile);
			
			//read file until empty
			while(input.hasNextLine()) {
				
				//somehow pops Employee objects
			}
			
			
		} catch(IOException e) {
			//fail case
			System.out.println("popEmployees has horribly failed with file IO");
		}
		
	}
		
	
}
