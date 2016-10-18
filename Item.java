
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
