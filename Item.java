
/**Item class creates an item with a name, price, and type
 * 
 * @author 
 * @version 1.0
 *
 */
public class Item {
	
	String name;
	double price;
	String type;
	
	//constructor
	Item(String name, String type, double price) {
		this.name = name;
		this.type = type;
		this.price = price;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public String getName(){
		return this.name;
	}
	

}
