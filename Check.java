import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.math.*;

public class Check {

	ArrayList<Integer> checkItems = new ArrayList<Integer>();
	
	/*BigDecimal subtotal = new BigDecimal(0.0);
	BigDecimal tax = new BigDecimal(0.0);
	BigDecimal total = new BigDecimal(0.0);*/
	
	double subtotal;
	double tax;
	double total;
	
	//Check constructor
	public Check() {
		//System.out.println("party made a check");
		
		//testing. adding dummy item to check always
		addItem(0);
		
		//receipt();
	}
	
	
	//adding an item to the index array list
	private void addItem(int index) {
		checkItems.add(index);
	}
	
	private double calcTax() {
		return 0.0;
	}
	
	private double calcTotal() {
		return 0.0;
	}
	
	private void deleteItem(int index) {
	this.checkItems.remove(index);
	}
	
	public void finalize() {
	}
	
	private void getItemsInfo() {
	}
	
	private void receipt(){
		
		//this works!!!!!!
		System.out.println(Register.getItemPrice(checkItems.get(0)));
	}
	
	private void updateCash(double difference) {
	}
	

}