public class Employee{
	String name;
	double price;
	int ID;
	double wage;
	int phone;
	String address;
	String email;
	int SSN;
	int clearance;
	double shiftHours;
	
	//constructor
	Employee(String name, int ID, double wage, int phone, String address, String email, int SSN, int clearance) {
		this.name = name;
		this.ID = ID;
		this.wage = wage;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.SSN = SSN;
		this.clearance = clearance;
	}

	
	public String getName(){
		return this.name;
	}
	

}
