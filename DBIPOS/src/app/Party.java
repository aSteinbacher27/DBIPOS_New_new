package app;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Party {
	
	/**
	 * Party's check
	 */
	private Check check;
	
	/**
	 * name of party. used for reference.
	 */
	private String name;
	
	/**
	 * integer size of party. independent from number of meals
	 */
	private int size;
	
	/**
	 * boolean for if the party has breakfast meals
	 */
	private Boolean isBreakfast;
	
	/**
	 * integer ID of server assigned to party
	 */
	private int serverID;
	
	/**
	 * integer identifier of table the party is assigned to
	 */
	private int table;

	
	/**
	 * dates saved as strings for specific time markers for the party
	 * in the form [created, sat, check given, finalized]
	 */
	private String[] progressTimes = new String[4];
	
	/**
	 * Party constructor. initializes a new check. sets values for name, size, and isBreakfast
	 *
	 * @param name
	 * @param size
	 * @param isBreakfast
	 */
	public Party(String name, int size, boolean isBreakfast) {
		this.name = name;
		this.size = size;
		this.isBreakfast = isBreakfast;

		check = new Check();
	
		//System.out.println(this.name);
	}
	
	/**
	 * sends a party check information to the kitchen client
	 * (NON-FUNCTIONAL FOR SPRINT 1)
	 */
	public void sendToKitchen() {
	//eventually will send order (check) information to kitchen server
	}
	
	/**
	 * updates the progressParty array with the time of a new event, given the index of the event to be updated
	 *
	 * @param index  indended index for the progressParty array
	 */
	public void updateProgress(int index) {
		SimpleDateFormat ddMMyyyyhhmmss = new SimpleDateFormat("ddMMyyyy_hhmmss");
		String formattedDate;
		switch (index) {
			case 0:	Date createdTime = new Date();
				formattedDate = new String(ddMMyyyyhhmmss.format(createdTime));
				this.progressTimes[index] = formattedDate;
				break;
				
			case 1: Date satTime = new Date();
				formattedDate = new String(ddMMyyyyhhmmss.format(satTime));
				this.progressTimes[index] = formattedDate;
				break;
			
			case 2: Date checkTime = new Date();
				formattedDate = new String(ddMMyyyyhhmmss.format(checkTime));
				this.progressTimes[index] = formattedDate;
				break;
			
			case 3: Date finalizeTime = new Date();
				formattedDate = new String(ddMMyyyyhhmmss.format(finalizeTime));
				this.progressTimes[index] = formattedDate;
				break;
		}
	}

	/**
	 * Getter for partyName
	 *
	 * @return name  String name of party
	 */
	public String getPartyName(){
		return name;
	}

	/**
	 * Getter for party size
	 *
	 * @return size  size of party
	 */
	public int getPartySize(){

		return size;
	}

	/**
	 * Getter for isBreakfast boolean
	 *
	 * @return isBreakfast
	 */
	public boolean isBreakfast(){

		return isBreakfast;
	}

	/**
	 * Getter for party check object
	 *
	 * @return check  Check object of the party
	 */
	public Check getCheck(){
		return check;
	}
	
	/**
	 * Setter for table number (int table)
	 *
	 * @param tableNumber
	 */
	public void setTable(int tableNumber) {
		this.table = tableNumber;
	}

	/**
	 * Setter for serveID
	 *
	 * @param serverID
	 */
	public void setServerID(int serverID){
		
		this.serverID = serverID;
	}

	/**
	 * Getter for serverID
	 *
	 * @return serverID serverID of party
	 */
	public int getServerID(){

		return serverID;
	}
	
	/**
	 * Getter for table number of party
	 *
	 * @return table  table number
	 */
	public int getTableNumber() {
		return this.table;
	}
	
		
}
