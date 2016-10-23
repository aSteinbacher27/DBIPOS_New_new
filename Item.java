
/**Item class creates an item with a name, price, and type
 * 
 * @author 
 * @version 1.0
 *
 */
public class Item {
	
	/**
	 * name of item
	 */
	String name;
	
	/**
	 * price of object as a double
	 */
	double price;
	
	/**
	 * type classification of item (menu, gift, drink, dessert) as a string
	 */
	String type;
	

	/**
	 * Item constructor
	 * 
	 * @param name
	 * @param type
	 * @param price
	 */
	Item(String name, String type, double price) {
		this.name = name;
		this.type = type;
		this.price = price;
	}
	
	
	/**
	 * Getter for price
	 * 
	 * @return price
	 */
	public double getPrice(){
		return this.price;
	}
	
	/**
	 * Getter for name
	 * 
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	

}
