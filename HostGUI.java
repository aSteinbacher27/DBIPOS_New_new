//Disables the clock out function while you haven't clocked in yet, and saves clock in/clock out times for the file
		clockOut.setDisable(true);
		clockIn.setOnAction(e -> {
			SimpleDateFormat ddMMyyyyhhmmss = new SimpleDateFormat("dd/MM/yyyy_hh:mm:ss");
			String clockInDate;
			Date createdTime = new Date();
			clockInDate = new String(ddMMyyyyhhmmss.format(createdTime));
			System.out.println(clockInDate);
			 try {
				BufferedWriter inverseBucket = new BufferedWriter(new FileWriter("clockinclockoutdata.txt",true));
				inverseBucket.write(String.format("%n%s				",clockInDate));
				inverseBucket.close();
				clockIn.setDisable(true);
				clockOut.setDisable(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		clockOut.setOnAction(e -> {
			SimpleDateFormat ddMMyyyyhhmmss = new SimpleDateFormat("dd/MM/yyyy_hh:mm:ss");
			String clockOutDate;
			Date createdTime = new Date();
			clockOutDate = new String(ddMMyyyyhhmmss.format(createdTime));
			System.out.println(clockOutDate);
			 try {
				BufferedWriter inverseBucket = new BufferedWriter(new FileWriter("clockinclockoutdata.txt",true));
				inverseBucket.write(String.format("%s",clockOutDate));
				inverseBucket.close();
				clockIn.setDisable(false);
				clockOut.setDisable(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
