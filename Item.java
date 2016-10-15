
public class Item {
	
	String name;
	double price;
	
	//constructor
	Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public String getName(){
		return this.name;
	}
	

}
