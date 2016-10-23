import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Check {

	/**
	 * String array list of check Item names
	 */
	ArrayList<String> checkItems = new ArrayList<String>();
	
	/**
	 * String array list of check item prices
	 */
	ArrayList<String> checkPricesString = new ArrayList<String>();
	
	/**
	 * Double array list of check item prices
	 */
	ArrayList<Double> checkPrices = new ArrayList<Double>();
	
	/**
	 * pre-tax total of the check
	 */
	private double subtotal;
	
	/**
	 * tax percentage multiplier
	 */
	private double tax = 0.0675; //percent sales tax for the Inn
	
	/**
	 * total for the check (including tax)
	 */
	private double total;
	
	/**
	 * Check constructor.
	 * Does not take input or run methods
	 */
	public Check() {
		//System.out.println("party made a check");
		
		//testing. adding dummy item to check always
		
		//addItem("something", "0.01");
		
		//receipt();
		//System.out.println(calcTotal());
		//finalize();
	}
	
	
	/**
	 * Adds an item to the check by updating checkItems, checkPriceString, & checkPrices
	 * Updates totals by running calcTotal()
	 * 
	 * @param name  the name of the check item as a string
	 * @param priceS  the price of the check item as a String
	 */
	public void addItem(String name, String priceS) {
		checkItems.add(name);
		checkPricesString.add(priceS);
		
		
		//parse string price to double
		checkPrices.add(Double.parseDouble(priceS));

		//Print when a check item is added.
		// for(Integer i: checkItems)
		// System.out.println(i);
		
		System.out.println(checkItems);
		
		calcTotal();
	}
	
	
	
	/**
	 * Updates total and subtotal by looping through checkPrices
	 * 
	 * @return total  returns the total value of the check if needed
	 */
	private double calcTotal() {
		double subtotal = 0.0;
		double total = 0.0;
		int i; //counter var
		
		//run for length of checkItems arrayList
		for(i = 0; i < checkPrices.size(); i++){
			subtotal += checkPrices.get(i);
		}
		
		//calc tax
		total = subtotal + subtotal*this.tax;
		
		
		//update obj vars
		this.subtotal = subtotal;
		this.total = total;
			
		return total;
	}
	
	/**
	 * Removes an item from the check by updating checkItems, checkPriceString, & checkPrices
	 * based on the first instance of the string name of the item to be removed
	 *
	 * Updates totals by running calcTotal()
	 * 
	 * @param name  the name of the check item as a string
	 */
	public void deleteItem(String name) {
		int removeable = checkItems.indexOf(name);
		checkItems.remove(removeable);
		checkPricesString.remove(removeable);
		checkPrices.remove(removeable);
		
		calcTotal();
	
	}
	
	/**
	 * For use with the finalize feature of the casier/server client.
	 * Updates total values, generates a receipt, and updates the current cash kept in the register
	 */
	public void finalize() {
		calcTotal();
		receipt();
		updateCash(total);
	}
	
	private void getItemsInfo() {
	}
	
	/**
	 * Generates a receipt and prints it to a file with the name of RddMMyyyy_hhmmss.txt to avoid as much write over as possible
	 * 
	 */
	private void receipt(){
		int i; //counter var
		
		//testing
		System.out.println("making receipt");
	
		//time format for receipt
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Date printTime = new Date();
		SimpleDateFormat ddMMyyyyhhmmss = new SimpleDateFormat("ddMMyyyy_hhmmss");
		String formattedDate = new String(ddMMyyyyhhmmss.format(printTime));
		
		//make file in source folder for each receipt
		try {
			File receipt = new File("R" + formattedDate + ".txt");
			receipt.createNewFile();
			
			FileWriter fw = new FileWriter(receipt.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			//start printing receipt
			//header
			bw.write("Dan'l Boone Inn");
			
			//breakline
			bw.newLine();
			bw.write("---------------------");
			bw.newLine();
			
			
			//print check
			for(i = 0; i < checkItems.size(); i++) {
				bw.write("(1) " + checkItems.get(i) + "\t" + "$" + formatter.format(checkPrices.get(i)));
				bw.newLine();
				
			}
			
			//print total
			bw.newLine();
			bw.write("Subtotal = \t$" + formatter.format(this.subtotal));
			bw.newLine();
			bw.write("Tax added = \t$" + formatter.format(this.subtotal*this.tax));
			bw.newLine();
			bw.write("Total = \t$" + formatter.format(this.total));
			bw.newLine();
			bw.newLine();
			bw.write("THANK YOU!");
			
			bw.close();
			
		} catch(IOException e) {
			System.out.println("receipt generation failed");
		}
		
		System.out.println("receipt made");		
	}
	
	/**
	 * Adds an item to the check by updating checkItems, checkPriceString, & checkPrices
	 * Updates totals by running calcTotal()
	 * 
	 * @param difference  double value of the difference to update the register cash value through its updateCash method
	 */
	private void updateCash(double difference) {
		Register.updateCash(difference);
	}
	
	/**
	 * Getter for subtotal
	 * 
	 * @return subtotal 
	 */
	public double getSubtotal() {
		return this.subtotal;
	}
	
	/**
	 * Getter for total
	 * 
	 * @return total
	 */
	public double getTotal() {
		return this.total;
	}

	/**
	 * Getter for tax
	 * 
	 * @return tax
	 */
	public double getTax() {
		return this.tax;
	}

}
