import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Party {
	
	Check check = new Check();
	
	String name;
	int size;
	Boolean isBreakfast;
	int serverID;
	int table;
	
	
	//progress times saved as formatted date strings in format:
	// [created, sat, check given, finalized]
	String[] progressTimes = new String[4];
	
	
	public Party(String name, int size) {
	//party class constructer
	this.name = name;
	this.size = size;
	
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
	
	
		
}
