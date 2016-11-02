package app;
/**
 * @author
 * 
 * @version 1.0
 * 
 *This class generates an employee with all attributes needed 
 */
public class Employee{
	String name;
	int ID;
	double wage;
	String phone;
	String address;
	String email;
	String SSN;
	int clearance;
	double shiftHours;
	
	//constructor
	Employee(String name, int ID, double wage, String phone, String address, String email, String SSN, int clearance) {
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
	
	public String getSSN(){
		return this.SSN;
	}
	
	public double getWage(){
		return this.wage;
	}
	
	public String getPhone(){
		return this.phone;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public int getClearance(){
		return this.clearance;
	}






	public int getID(){
	
		return ID;
	}
	

}
