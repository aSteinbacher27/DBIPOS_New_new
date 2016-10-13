public class Party {
	
	Check check = new Check();
	String name;
	int size;
	
	
	public Party(String name, int size, Check check) {
	//party class constructer
	check = this.check;
	name = this.name;
	size = this.size;
	}
	
	public sendToKitchen() {
	//eventually will send order (check) information to kitchen server
	}
		
}
