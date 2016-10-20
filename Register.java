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
	
	static int[] table = new int[32];
	
	//Array lists of Employees and Items
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	(String name, int ID, double wage, int phone, String address, String email, int SSN, int clearance)

	//Test Employees
	employees.add(new Employee("Brian", 212323, 100.60, 555555, "Address", "email", 8394949, 222), new Employee("Gilbert", 435643, 100.60, 55555, "Address", "email", 8394949, 222));

	static ArrayList<Item> items = new ArrayList<Item>();
	
	
	//Party Array lists
	static ArrayList<Party> waitingParties = new ArrayList<Party>();
	static ArrayList<Party> activeParties = new ArrayList<Party>();
	
	
	//Payroll (not yet)
	
	//finding the Employee and Items files
	String itemFileString = new String("items.txt");
	String employeeFileString = new String("employees.txt");
	

	static double currentCash;
	
	Register(){
		//populate employees and items
		popItems(itemFileString);
		popEmployees(employeeFileString);
		
		//dummy items
		//Item testItem = new Item("toy", 10.03);
		//items.add(testItem);
		
		//dummy party
		Party testParty = new Party("test", 3, false);
		activeParties.add(testParty);
		
		this.currentCash = 200.0; //generic starting cash amount. figure out in sprint 2
		
		for(int i : table) {
			i = 0;
		}
		
		//TESTING
		table[10] = 2;
		table[11] = 2;
		System.out.println(Arrays.toString(table));
	}
	
	/**
	 * This method gets the price attribute associated with an item object stored in Register in the items arraylist
	 * 
	 * @param id index of the indicated item as an integer
	 * @return the price of the indicated Item
	 */
	static public double getItemPrice(int id){
		double price = items.get(id).getPrice();
		return price;
		
	}
	
	static public String getItemName(int id){
		String name = items.get(id).getName();
		return name;
	}
	
	
	//method to populate the Items array. 
	private void popItems(String fileName) {

		// set up file and scanner
		File itemFile = new File(fileName);

		String[] toSplit = new String[3];

		BufferedReader br = null;
		String line = new String("");
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(fileName));

			while ((line = br.readLine()) != null) {
				// preps temp variables for new object creation
				String name = new String();
				String type = new String();
				String priceString = new String();
				double price;

				// use comma as separator
				toSplit = line.split(cvsSplitBy);

				name = toSplit[0];
				type = toSplit[1];
				priceString = toSplit[2];

				// parse double for price from priceString
				price = Double.parseDouble(priceString);

				// sorts the items into appropriate arrays
				switch (type) {
				case "gift":
					giftItemNames.add(name);
					giftItemPrices.add(priceString);
					break;

				case "meal":
					menuItemNames.add(name);
					menuItemPrices.add(priceString);
					break;

				case "drink":
					drinkItemNames.add(name);
					drinkItemPrices.add(priceString);
					break;

				case "dessert":
					dessertItemNames.add(name);
					dessertItemPrices.add(priceString);
					break;
				}
				
				//System.out.println(giftItemNames);
				//System.out.println(name);
				//System.out.println(type);
				//System.out.println(price);
				
				// creates an object to add to the item array
				Item newItem = new Item(name, type, price);

				// adds new item to items array
				this.items.add(newItem);
			}

			// testing
			// System.out.println(this.items.get(1).getName());

		} catch (IOException e) {
			// fail case
			System.out.println("popItems has horribly failed with file IO");
			e.printStackTrace();

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
	
	public static void updateCash(double difference) {
		currentCash += difference;
	}
	
	public static ArrayList<Integer> getAvailableTables(){
		ArrayList<Integer> availableTables = new ArrayList<Integer>();
		for(int i = 0; i < table.length; i++){
			if(table[i] == 0){
				availableTables.add(i+1);
			}
		}
		return availableTables;
	}
	
	public static ArrayList<Integer> getSeatedTables(){
		ArrayList<Integer> seatedTables = new ArrayList<Integer>();
		for(int i = 0; i < table.length; i++){
			if(table[i] == 2){
				seatedTables.add(i+1);
			}
		}
		return seatedTables;
	}
	
	public static void seatParty(int tableNumber) {
		table[tableNumber - 1] = 2;
	}

	
}
