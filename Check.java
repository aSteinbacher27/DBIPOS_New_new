import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.math.*;

public class Check {

	ArrayList<Integer> checkItems = new ArrayList<Integer>();
	
	BigDecimal subtotal = new BigDecimal(0.0);
	BigDecimal tax = new BigDecimal(0.0);
	BigDecimal total = new BigDecimal(0.0);
	
	//Check constructor
	public Check() {
	}
	
	//adding an item to the index array list
	private void addItem(int index) {
		checkItems.add(index);
	}
	
	private BigDecimal calcTax() {
		return null;
	}
	
	private BigDecimal calcTotal() {
		return null;
	}
	
	private void deleteItem(int index) {
	this.checkItems.remove(index);
	}
	
	public void finalize() {
	}
	
	private void getItemsInfo() {
	}
	
	private void receipt(){
	}
	
	private void updateCash(BigDecimal difference) {
	}
	

}

}

