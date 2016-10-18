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

	static ArrayList<String> menuItemNames = new ArrayList<String>();
	static ArrayList<String> giftItemNames = new ArrayList<String>();
	static ArrayList<String> drinkItemNames = new ArrayList<String>();
	static ArrayList<String> dessertItemNames = new ArrayList<String>();
	static ArrayList<String> menuItemPrices = new ArrayList<String>();
	static ArrayList<String> giftItemPrices = new ArrayList<String>();
	static ArrayList<String> drinkItemPrices = new ArrayList<String>();
	static ArrayList<String> dessertItemPrices = new ArrayList<String>();
	
	//Array lists of Employees and Items
	//ArrayList<Employee> employees = new ArrayList<Employee>(); class not created yet
	static ArrayList<Item> items = new ArrayList<Item>();
	
	
	//Party Array lists
	ArrayList<Party> waitingParties = new ArrayList<Party>();
	ArrayList<Party> activeParties = new ArrayList<Party>();
	
	
	//Payroll (not yet)
	
	//finding the Employee and Items files
	String itemFileString = new String("items.txt");
	String employeeFileString = new String("employees.txt");
	

	double currentCash;
	
	Register() {
		//populate employees and items
		popItems(itemFileString);
		popEmployees(employeeFileString);
		
		//dummy items
		//Item testItem = new Item("toy", 10.03);
		//items.add(testItem);
		
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
	
	
	//method to populate the Items array. 
	private void popItems(String fileName){
		try{
			
			//set up file and scanner
			File itemFile = new File(fileName);
			Scanner input = new Scanner(itemFile);
			
			input.useDelimiter(",");
			
			//read file until empty
			while(input.hasNext()) {
				
				//preps temp variables for new object creation
				String name = new String();
				String type = new String();
				Double price;
				
				//reads values from txt file
				name = input.next();
				type = input.next();
				price = input.nextDouble();
				
				//sorts the items into appropriate arrays
				switch(type) {
				case "gift":	giftItemNames.add(name);
								giftItemPrices.add(price.toString());
								break;
								
				case "meal":	menuItemNames.add(name);
								menuItemPrices.add(price.toString());
								break;
								
				case "drink":	drinkItemNames.add(name);
								drinkItemPrices.add(price.toString());
								break;
								
				case "dessert":	dessertItemNames.add(name);
								dessertItemPrices.add(price.toString());
								break;
				}
				
				//creates an object to add to the item array
				Item newItem = new Item(name, type, price);
				
				//adds new item to items array
				this.items.add(newItem);
			}
			input.close();
			
			//testing
			//System.out.println(this.items.get(1).getName());
			
			
		} catch(IOException e) {
			//fail case
			System.out.println("popItems has horribly failed with file IO");
			e.printStackTrace();
		
		}
		
	}
	
	//populating the employee array. non-functional but compile-able for sprint 1
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
