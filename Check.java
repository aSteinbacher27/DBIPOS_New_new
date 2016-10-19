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

	ArrayList<Integer> checkItems = new ArrayList<Integer>();
	
	
	double subtotal;
	double tax = 0.0675; //NC standard (??)
	double total;
	
	//Check constructor
	public Check() {
		//System.out.println("party made a check");
		
		//testing. adding dummy item to check always
		
		//addItem(1);
		
		//receipt();
		//System.out.println(calcTotal());
		//finalize();
	}
	
	
	//adding an item to the index array list
	public void addItem(int index) {
		checkItems.add(index);

		//Print when a check item is added.
		// for(Integer i: checkItems)
		// System.out.println(i);
	}
	
	
	private double calcTotal() {
		double subtotal = 0.0;
		double total = 0.0;
		int i; //counter var
		
		//run for length of checkItems arrayList
		for(i = 0; i < checkItems.size(); i++){
			subtotal += Register.getItemPrice(checkItems.get(i));
		}
		
		//calc tax
		total = subtotal + subtotal*this.tax;
		
		
		//update obj vars
		this.subtotal = subtotal;
		this.total = total;
			
		return total;
	}
	
	private void deleteItem(int index) {
		this.checkItems.remove(index);
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
				bw.write("(1) " + Register.getItemName(checkItems.get(i)) + "\t" + "$" + formatter.format(Register.getItemPrice(checkItems.get(i))));
				bw.newLine();
				
			}
			
			//print total
			bw.newLine();
			bw.write("Subtotal = \t$" + this.subtotal);
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
	

}
