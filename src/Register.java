import java.util.ArrayList;
import java.io.*;
import java.math.*;
import java.util.Arrays;
import java.util.Scanner;

public class Register {

	
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
	employees.add(new Employee("Brian", 212323, 100.60, 555555, "Address", "email", 8394949, 222));
	employees.add(new Employee("Gilbert", 435643, 100.60, 55555, "Address", "email", 8394949, 222));


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
		try{
			//set up file and scanner
			File itemFile = new File(fileName);
			Scanner input = new Scanner(itemFile);
			
			//read file until empty
			while(input.hasNextLine()) {
				
				//pop employees
			}
			
			
		} catch(IOException e) {
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

	
}
