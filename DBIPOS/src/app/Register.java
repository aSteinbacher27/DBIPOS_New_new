package app;
import java.util.ArrayList;
import java.io.*;
import java.math.*;
import java.util.Arrays;
import java.util.Scanner;

public class Register {

	
	/**
	 * names of ALL items 
	 */
	static ArrayList<String> stringItems = new ArrayList<String>();
	
	/**
	 * names of items with menu classification
	 */
	static ArrayList<String> menuItemNames = new ArrayList<String>();
	
	/**
	 * names of items with gift classification
	 */
	static ArrayList<String> giftItemNames = new ArrayList<String>();
	
	/**
	 * names of items with drink classification
	 */
	static ArrayList<String> drinkItemNames = new ArrayList<String>();
	
	/**
	 * names of items with desert classification
	 */
	static ArrayList<String> dessertItemNames = new ArrayList<String>();
	
	/**
	 * prices of items with menu classification as strings
	 */
	static ArrayList<String> menuItemPrices = new ArrayList<String>();
	
	/**
	 * prices of items with menu gift as strings
	 */
	static ArrayList<String> giftItemPrices = new ArrayList<String>();
	
	/**
	 * prices of items with drink classification as strings
	 */
	static ArrayList<String> drinkItemPrices = new ArrayList<String>();
	
	/**
	 * prices of items with desert classification as strings
	 */
	static ArrayList<String> dessertItemPrices = new ArrayList<String>();
	
	
	/**
	 * array with indicies corresponding to table status
	 */
	static int[] table = new int[32];
	
	/**
	 * arraylist of employee objects
	 * (NONFUNCTIONAL FOR SPRINT 1)
	 */
	static ArrayList<Employee> employees = new ArrayList<Employee>();
	
	/**
	 * arraylist of employee objects
	 * (NONFUNCTIONAL FOR SPRINT 1)
	 */
	static ArrayList<String> employeeNames = new ArrayList<String>();
	
	/**
	 * arraylist of item objects
	 */
	static ArrayList<Item> items = new ArrayList<Item>();
	
	
	/**
	 * array list of party objects that have not yet been seated
	 */
	static ArrayList<Party> waitingParties = new ArrayList<Party>();
	
	/**
	 * array list of party objects that have been seated 
	 */
	static ArrayList<Party> activeParties = new ArrayList<Party>();
	
	
	//Payroll (not yet)
	
	/**
	 * name of the items file to populate the items array list
	 */
	String itemFileString = new String("items.txt");
	
	/**
	 * name of the employees file to populate the employee array list
	 */
	String employeeFileString = new String("employees.txt");
	
	/**
	 * double value representing the cash currently in the register. updated with check finalize method.
	 */
	static double currentCash;
	
	/**
	 * constructor for register.
	 * populates item array list and employee array list and initializes table array
	 */
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
		//table[10] = 2;
		//table[11] = 2;
		System.out.println(Arrays.toString(table));

			
	//Test Employees
	//employees.add(new Employee("Brad", 123456, 10.60, "212-323-5555", "123 E Road", "brad@email.com",  "839-49-149", 0));
	//employeeNames.add("Brad");
	//employees.add(new Employee("Gilbert", 987654, 8.80, "212-323-5555", "456 N. Street", "gilbert@email.com", "839-49-349", 2));
	//employeeNames.add("Gilbert");


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
	
	/**
	 * gets name of desired item with index 
	 *
	 * @param id  index of desired item in items array list
	 * @return name  name of the item
	 */
	static public String getItemName(int id){
		String name = items.get(id).getName();
		return name;
	} 
	
	/**
	 * This method populates the items array using fileIO and populates the name and price arrays for the different categories of item
	 *
	 * @param fileName  name of the CSV text file with the items
	 */
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
				
				stringItems.add(name);
				
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
	
	/**
	 * This method populates the employees array using fileIO
	 * (NON/MINIMALLY FUNCTIONAL FOR SPRINT 1)
	 *
	 * @param fileName  name of the CSV text file with the employees
	 */
	private void popEmployees(String fileName){
		//set up file and scanner
		File itemFile = new File(fileName);

		String[] toSplit = new String[8];

		BufferedReader br = null;
		String line = new String("");
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				
				// use comma as separator and populate toString
				toSplit = line.split(cvsSplitBy);
				
				//preps temp variables for object creation
				String IDS;
				String wageS;
				String clearanceS;
				
				//preps usable object variables
				String name;
				int ID;
				double wage;
				String phone;
				String address;
				String email;
				String SSN;
				int clearance;
				
				//splits string into appropriate temp variables
				name = toSplit[0];
				IDS = toSplit[1];
				wageS = toSplit[2];
				phone = toSplit[3];
				address = toSplit[4];
				email = toSplit[5];
				SSN = toSplit[6];
				clearanceS = toSplit[7];
				
				//converts attributes as needed
				ID = Integer.parseInt(IDS);
				wage = Double.parseDouble(wageS);
				clearance = Integer.parseInt(clearanceS);
				
				//creates new employee object
				Employee newEmployee = new Employee(name, ID, wage, phone, address, email, SSN, clearance);
				
				this.employees.add(newEmployee);
				employeeNames.add(name);
				
			}
			
			
			}catch(IOException e) {
			//fail case
			System.out.println("popEmployees has horribly failed with file IO");
		}
		
	}
	/**
	 * updates the current cash in the register. Called with a check object.
	 *
	 * @param difference
	 */
	public static void updateCash(double difference) {
		currentCash += difference;
	}
	
	/**
	 * This method searches the table array for 0s and keeps the number (index + 1) of those tables in an array list
	 *
	 * @return availableTables  array list of the indexes of 0s in the table array
	 */
	public static ArrayList<Integer> getAvailableTables(){
		ArrayList<Integer> availableTables = new ArrayList<Integer>();
		for(int i = 0; i < table.length; i++){
			if(table[i] == 0){
				availableTables.add(i+1);
			}
		}
		return availableTables;
	}
	
	/**
	 * This method searches the table array for 2s and keeps the number (index + 1) of those tables in an array list
	 *
	 * @return seatedTables  array list of the indexes of 2s in the table array
	 */
	public static ArrayList<Integer> getSeatedTables(){
		ArrayList<Integer> seatedTables = new ArrayList<Integer>();
		for(int i = 0; i < table.length; i++){
			if(table[i] == 2){
				seatedTables.add(i+1);
			}
		}
		return seatedTables;
	}
	
	/**
	 * sets the index of tableNumber - 1 to 2 to mark it as "seated"
	 *
	 * @param tableNumber
	 */
	public static void seatParty(int tableNumber) {
		table[tableNumber - 1] = 2;
	}

	/**
	 * This method creates and returns an arraylist of employeeNames
	 *
	 * @return employeeNames
	 */
	public static ArrayList<String> getEmployeeNames(){
		ArrayList<String> employeeNames = new ArrayList<String>();

		for(Employee e: employees)
		employeeNames.add(e.getName());

		return employeeNames;
	}
	
	public static void updateItems(){
		//kills everything and remakes
	}
	
	public static void addToItems(Item newItem){
		//adds to end of file and seperate arrays
		
		//adds item object to items array
		items.add(newItem);
		generateReferenceArrays();
	
	}
	
	public static void generateReferenceArrays() {
		int i;
		//wipe reference arrays
		giftItemNames.clear();
		giftItemPrices.clear();
		menuItemNames.clear();
		menuItemPrices.clear();
		drinkItemNames.clear();
		drinkItemPrices.clear();
		dessertItemNames.clear();
		dessertItemPrices.clear();
		
		for(i = 0; i < items.size(); i++) {
			String type = new String(items.get(i).getType());
			String name = new String(items.get(i).getName());
			String priceString = new String(Double.toString(items.get(i).getPrice()));
			
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
			
			stringItems.add(name);
			
		}
	}

	
}
