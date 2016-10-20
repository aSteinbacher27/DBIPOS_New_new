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

	ArrayList<String> checkItems = new ArrayList<String>();
	ArrayList<String> checkPricesString = new ArrayList<String>();
	ArrayList<Double> checkPrices = new ArrayList<Double>();
	
	
	private double subtotal;
	private double tax = 0.0675; //NC standard (??)
	private double total;
	
	//Check constructor
	public Check() {
		//System.out.println("party made a check");
		
		//testing. adding dummy item to check always
		
		//addItem("something", "0.01");
		
		//receipt();
		//System.out.println(calcTotal());
		//finalize();
	}
	
	
	//adding an item to the index array list
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
	
	public void deleteItem(String name) {
		int removeable = checkItems.indexOf(name);
		checkItems.remove(removeable);
		checkPricesString.remove(removeable);
		checkPrices.remove(removeable);
		
		calcTotal();
	
	}
	
	public void finalize() {
		calcTotal();
		receipt();
		updateCash(total);
	}
	
	private void getItemsInfo() {
	}
	
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
	
	private void updateCash(double difference) {
		Register.updateCash(difference);
	}
	
	
	public double getSubtotal() {
		return this.subtotal;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public double getTax() {
		return this.tax;
	}

}
