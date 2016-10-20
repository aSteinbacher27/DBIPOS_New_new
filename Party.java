import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Party {
	
	private Check check;
	
	private String name;
	private int size;
	private Boolean isBreakfast;
	private int serverID;
	private int table;
	
	
	//progress times saved as formatted date strings in format:
	// [created, sat, check given, finalized]
	private String[] progressTimes = new String[4];
	
	//party class constructer
	public Party(String name, int size, boolean isBreakfast) {
		this.name = name;
		this.size = size;
		this.isBreakfast = isBreakfast;

		check = new Check();
	
		//System.out.println(this.name);
	}
	
	public void sendToKitchen() {
	//eventually will send order (check) information to kitchen server
	}
	
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

	public String getPartyName(){

		return name;
	}

	public int getPartySize(){

		return size;
	}

	public boolean isBreakfast(){

		return isBreakfast;
	}

	public Check getCheck(){
		return check;
	}
	
	public void setTable(int tableNumber) {
		this.table = tableNumber;
	}
	
	
	public int getTableNumber() {
		return this.table;
	}
	
		
}
