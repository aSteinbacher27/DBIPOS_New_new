import java.awt.Font;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HostGUI extends Application{

	public static void main(String[] args) {
		Application.launch(args);
		
		//Register register = new Register();
	}
	
	
	//components necessary to declare outside of method
	String keyPadLabelString = "";



	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
/*
 * Throw your panes down here home slizzle
 */
		BorderPane rootPane = new BorderPane();
		Pane diningRoomOverviewPane = new Pane();
		Pane porchMapPane = new Pane();
		Pane bdrMapPane = new Pane();
		Pane ldrMapPane = new Pane();
		HBox clockPaneMainMenu = new HBox();
		VBox queuePane = new VBox();
		rootPane.setTop(clockPaneMainMenu);
		rootPane.setCenter(diningRoomOverviewPane);
		rootPane.setRight(queuePane);
				
		//initialize the digital clock to put on the top pane of the main menu
		DigitalClock mainMenuClock = new DigitalClock();
		mainMenuClock.setLayoutX(1200);
		
		
			

		
/*
 * _______________________________________________________________________________
 * Create New Party Pop-up
 * _______________________________________________________________________________
 */		


		Stage newPartyStage = new Stage();
		Pane newPartyPane = new Pane();
		Scene newPartyScene = new Scene(newPartyPane,500,370);
		newPartyStage.setScene(newPartyScene);

		Label newPartyLabel = new Label("Create a New Party: ");
		newPartyLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		newPartyLabel.setLayoutX(5);
		newPartyLabel.setLayoutY(5);
		Label newPartyNameLabel = new Label("Name: ");
		newPartyNameLabel.setStyle("-fx-font-size: 16px");
		newPartyNameLabel.setLayoutX(20);
		newPartyNameLabel.setLayoutY(30);
		TextField newPartyNameField = new TextField ();
		newPartyNameField.setStyle("-fx-font-size: 16px");
		newPartyNameField.setLayoutX(20);
		newPartyNameField.setLayoutY(50);
		Label newPartySizeLabel = new Label("Size: ");
		newPartySizeLabel.setStyle("-fx-font-size: 16px");
		newPartySizeLabel.setLayoutX(20);
		newPartySizeLabel.setLayoutY(90);
		TextField newPartySizeField = new TextField ();
		newPartySizeField.setStyle("-fx-font-size: 16px");
		newPartySizeField.setMaxWidth(50);
		newPartySizeField.setLayoutX(20);
		newPartySizeField.setLayoutY(110);
		
		// breakfast and lunch radio buttons
		RadioButton rb1 = new RadioButton("Breakfast");
		rb1.setStyle("-fx-font-size: 16px");
		rb1.setLayoutX(20);
		rb1.setLayoutY(155);
		RadioButton rb2 = new RadioButton("Lunch/Dinner");
		rb2.setStyle("-fx-font-size: 16px");
		rb2.setLayoutX(150);
		rb2.setLayoutY(155);
		
		ToggleGroup breakfastLunch = new ToggleGroup();
		rb1.setToggleGroup(breakfastLunch);
		rb2.setToggleGroup(breakfastLunch);
		
		// meal buttons
		POSButton ageAdultButton = new POSButton(40,100,"Adult");
		ageAdultButton.setStyle("-fx-font-size: 16px");
		ageAdultButton.setLayoutX(20);
		ageAdultButton.setLayoutY(190);
		POSButton ageFreeButton = new POSButton(40,100,"Age 0-3");
		ageFreeButton.setStyle("-fx-font-size: 16px");
		ageFreeButton.setLayoutX(140);
		ageFreeButton.setLayoutY(190);
		POSButton ageFourButton = new POSButton(40,100,"Age 4-5");
		ageFourButton.setStyle("-fx-font-size: 16px");
		ageFourButton.setLayoutX(20);
		ageFourButton.setLayoutY(250);
		POSButton ageSixButton = new POSButton(40,100,"Age 6-8");
		ageSixButton.setStyle("-fx-font-size: 16px");
		ageSixButton.setLayoutX(140);
		ageSixButton.setLayoutY(250);
		POSButton ageNineButton = new POSButton(40,100,"Age 9-11");
		ageNineButton.setStyle("-fx-font-size: 16px");
		ageNineButton.setLayoutX(20);
		ageNineButton.setLayoutY(310);
		
		// create list of items
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList (
		    "Single", "Double", "Suite", "Family App");
		list.setStyle("-fx-font-size: 16px");
		list.setItems(items);
		list.setLayoutX(300);
		list.setLayoutY(20);
		list.setPrefWidth(180);
		list.setPrefHeight(300);
		
		//delete and create button
		POSButton deleteButton = new POSButton(40,100,"Delete");
		deleteButton.setStyle("-fx-font-size: 16px");
		deleteButton.setLayoutX(285);
		deleteButton.setLayoutY(330);
		POSButton createButton = new POSButton(40,100,"Create");
		createButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		createButton.setLayoutX(390);
		createButton.setLayoutY(330);
		
		// add everything to pane
		newPartyPane.getChildren().addAll(list, newPartyLabel, newPartyNameLabel, newPartySizeLabel, newPartyNameField, newPartySizeField, rb1, rb2,ageAdultButton, ageFreeButton, ageFourButton, ageSixButton, ageNineButton, deleteButton, createButton);

		//newPartyStage.show();
		
/*
 * _______________________________________________________________________________
 * Seat Party Pop-up
 * _______________________________________________________________________________
 */		


		Stage seatPartyStage = new Stage();
		Pane seatPartyPane = new Pane();
		Scene seatPartyScene = new Scene(seatPartyPane,200,220);
		seatPartyStage.setScene(seatPartyScene);

		Label seatPartyLabel = new Label("Seat Party ");
		seatPartyLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		seatPartyLabel.setLayoutX(5);
		seatPartyLabel.setLayoutY(5);
		Label seatPartyServerLabel = new Label("Name: ");
		seatPartyServerLabel.setStyle("-fx-font-size: 16px");
		seatPartyServerLabel.setLayoutX(20);
		seatPartyServerLabel.setLayoutY(30);
		ChoiceBox serverChoice = new ChoiceBox();
		serverChoice.setItems(FXCollections.observableArrayList("Kayla", "Anissa", "Spencer"));
		serverChoice.setStyle("-fx-font-size: 16px");
		serverChoice.setLayoutX(20);
		serverChoice.setLayoutY(50);
		Label seatPartyTableLabel = new Label("Table: ");
		seatPartyTableLabel.setStyle("-fx-font-size: 16px");
		seatPartyTableLabel.setLayoutX(20);
		seatPartyTableLabel.setLayoutY(90);
		ChoiceBox tableChoice = new ChoiceBox();
		tableChoice.setItems(FXCollections.observableArrayList("1", "3","4","5","11","21"));
		tableChoice.setStyle("-fx-font-size: 16px");
		tableChoice.setLayoutX(20);
		tableChoice.setLayoutY(110);
		POSButton seatButton = new POSButton(40,100,"Seat Party");
		seatButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		seatButton.setLayoutX(20);
		seatButton.setLayoutY(160);
		
		// add everything to pane
		seatPartyPane.getChildren().addAll(seatButton, seatPartyLabel, seatPartyServerLabel, seatPartyTableLabel, serverChoice, tableChoice);

		//seatPartyStage.show();

/*
 * Keypad stuff
 */
		
		Stage keyPadStage = new Stage();
		Pane keyPadGridPane = new Pane();
		Scene keyPadScene = new Scene(keyPadGridPane,240,525);
		keyPadStage.setScene(keyPadScene);
		
		Label EnterIDLabel = new Label("Enter ID:");
		EnterIDLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		EnterIDLabel.setLayoutX(20);
		EnterIDLabel.setLayoutY(20);
		Label keyPadLabel = new Label(keyPadLabelString);
		keyPadLabel.setStyle("-fx-font-size: 16px; -fx-border-color:black;");
		keyPadLabel.setLayoutX(50);
		keyPadLabel.setLayoutY(60);
		keyPadLabel.setMinWidth(100);
		POSButton backspaceButton = new POSButton(20,40,"<-");
		backspaceButton.setStyle("-fx-font-size: 16px");
		backspaceButton.setLayoutX(150);
		backspaceButton.setLayoutY(55);
		
		
		POSButton k1 = new POSButton(80,80,"1");
		k1.setStyle("-fx-font-size: 16px");
		k1.setLayoutX(0);
		k1.setLayoutY(130);
		POSButton k2 = new POSButton(80,80,"2");
		k2.setStyle("-fx-font-size: 16px");
		k2.setLayoutX(80);
		k2.setLayoutY(130);
		POSButton k3 = new POSButton(80,80,"3");
		k3.setStyle("-fx-font-size: 16px");
		k3.setLayoutX(160);
		k3.setLayoutY(130);
		POSButton k4 = new POSButton(80,80,"4");
		k4.setStyle("-fx-font-size: 16px");
		k4.setLayoutX(0);
		k4.setLayoutY(210);
		POSButton k5 = new POSButton(80,80,"5");
		k5.setStyle("-fx-font-size: 16px");
		k5.setLayoutX(80);
		k5.setLayoutY(210);
		POSButton k6 = new POSButton(80,80,"6");
		k6.setStyle("-fx-font-size: 16px");
		k6.setLayoutX(160);
		k6.setLayoutY(210);
		POSButton k7 = new POSButton(80,80,"7");
		k7.setStyle("-fx-font-size: 16px");
		k7.setLayoutX(0);
		k7.setLayoutY(290);
		POSButton k8 = new POSButton(80,80,"8");
		k8.setStyle("-fx-font-size: 16px");
		k8.setLayoutX(80);
		k8.setLayoutY(290);
		POSButton k9 = new POSButton(80,80,"9");
		k9.setStyle("-fx-font-size: 16px");
		k9.setLayoutX(160);
		k9.setLayoutY(290);
		POSButton k0 = new POSButton(80,80,"0");
		k0.setStyle("-fx-font-size: 16px");
		k0.setLayoutX(80);
		k0.setLayoutY(370);
		POSButton submitButton = new POSButton(40,100,"Submit");
		submitButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		submitButton.setLayoutX(70);
		submitButton.setLayoutY(460);
		
		
		
		keyPadGridPane.getChildren().addAll(submitButton,EnterIDLabel, backspaceButton,k1,k2,k3,k4,k5,k6,k7,k8,k9,k0,keyPadLabel);
		
		//action listeners for all buttons		
		k1.setOnAction(e -> {
			keyPadLabelString += "1";
			keyPadLabel.setText(keyPadLabelString);
		});
		k2.setOnAction(e -> {
			keyPadLabelString += "2";
			keyPadLabel.setText(keyPadLabelString);
		});
		k3.setOnAction(e -> {
			keyPadLabelString += "3";
			keyPadLabel.setText(keyPadLabelString);
		});
		k4.setOnAction(e -> {
			keyPadLabelString += "4";
			keyPadLabel.setText(keyPadLabelString);
		});
		k5.setOnAction(e -> {
			keyPadLabelString += "5";
			keyPadLabel.setText(keyPadLabelString);
		});
		k6.setOnAction(e -> {
			keyPadLabelString += "6";
			keyPadLabel.setText(keyPadLabelString);
		});
		k7.setOnAction(e -> {
			keyPadLabelString += "7";
			keyPadLabel.setText(keyPadLabelString);
		});
		k8.setOnAction(e -> {
			keyPadLabelString += "8";
			keyPadLabel.setText(keyPadLabelString);
		});
		k9.setOnAction(e -> {
			keyPadLabelString += "9";
			keyPadLabel.setText(keyPadLabelString);
		});
		k0.setOnAction(e -> {
			keyPadLabelString += "0";
			keyPadLabel.setText(keyPadLabelString);
		});
		backspaceButton.setOnAction(e -> {
			keyPadLabelString = keyPadLabelString.substring(0,keyPadLabelString.length()-1);
			keyPadLabel.setText(keyPadLabelString);
		});
		submitButton.setOnAction(e -> {
			//do something
			keyPadStage.close();
		});
		
		
		
/*
* stuff for the queue pane		
*/
				
		//list stuff
		ListView<String> queue = new ListView<String>();
		queue.setStyle("-fx-font-size: 16px");
		ObservableList<String> parties = FXCollections.observableArrayList(
					"Yeah","ok","sure","maybe");		
			queue.setItems(parties);
						
			//buttons
			POSButton createNewParty = new POSButton(50,150,"Create New Party");
			createNewParty.setStyle("-fx-font-size: 16px");
			POSButton seatParty = new POSButton(50,150,"Seat Party");
			seatParty.setStyle("-fx-font-size: 16px");

						
			createNewParty.setOnAction(e->{
				newPartyStage.show();
			});
			seatParty.setOnAction(e->{
				seatPartyStage.show();
			});
						
						
			queuePane.getChildren().addAll(queue,createNewParty,seatParty);
				
						
		
		
/*
 * Everything for the login/logout/quit screen goes here
 */
		//pane stage and scene for the quit screen
		VBox quitPane = new VBox();
		VBox optionsPane = new VBox();
		Stage quitStage = new Stage();
		Stage optionsStage = new Stage();
		Scene quitScene = new Scene(quitPane);
		Scene optionsScene = new Scene(optionsPane);
		
		//This button is the log in button
		POSButton logInButton = new POSButton(50, 150, "Log In");
		logInButton.setStyle("-fx-font-size: 16px");
		logInButton.setOnAction(e->{
			keyPadStage.show();
		});
		//This button is the log out button
		POSButton logOutButton = new POSButton(50, 150, "Log Out");
		logOutButton.setStyle("-fx-font-size: 16px");
		POSButton logOutGoBack = new POSButton(100,100,"cool");
		logOutGoBack.setStyle("-fx-font-size: 16px");
		Text logOutConfirm = new Text("You WIN!!!!");
		Stage logOutStage = new Stage();
		VBox logOutPane = new VBox();
		Scene logOutScene = new Scene(logOutPane);
		logOutPane.getChildren().addAll(logOutConfirm,logOutGoBack);
		logOutButton.setOnAction(e->{
			logOutStage.setScene(logOutScene);
			logOutStage.show();
		});
		logOutGoBack.setOnAction(e->{
			logOutStage.close();
		});
		
		//This button brings up the screen for the login/logout/quit functions
		POSButton optionButton = new POSButton(50, 150, "OPTIONS");
		optionButton.setStyle("-fx-font-size: 16px");
		optionButton.setOnAction(e -> {
			optionsStage.setScene(optionsScene);
			optionsStage.show();
		});
		
		//This button is the quit button on the options pane
		POSButton optionsQuit = new POSButton(50, 150, "Quit Program");
		optionsQuit.setStyle("-fx-font-size: 16px");
		optionsQuit.setOnAction(e->{
			quitStage.setScene(quitScene);
			optionsStage.close();
			quitStage.show();
		});
		
		//This button closes all stages of the program
		POSButton quitYes = new POSButton(50, 150, "YES");
		quitYes.setStyle("-fx-font-size: 16px");
		quitYes.setOnAction(e->{
			optionsStage.close();
			quitStage.close();
			primaryStage.close();
		});
		
		//This button closes only the quit stage
		POSButton quitNo = new POSButton(50, 150, "NO");
		quitNo.setStyle("-fx-font-size: 16px");
		quitNo.setOnAction(e->{
			quitStage.close();
		});
		
		//Text for explaining the window
		Text quitText = new Text("Do you really want to quit?");
		quitText.setStyle("-fx-font-size: 16px");
		
		//puts children on the correct panes
		POSButton serverGUI=new POSButton(50,150,"Server GUI");
		serverGUI.setOnAction(e->{
			ServerGUI serverObject=new ServerGUI();
			serverObject.GUI(primaryStage);
		});
		POSButton clockIn = new POSButton(50,150,"Clock In");
		clockIn.setStyle("-fx-font-size: 16px");
		POSButton clockOut = new POSButton(50,150,"Clock Out");
		clockOut.setStyle("-fx-font-size: 16px");
		
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
		
		
		quitPane.getChildren().addAll(quitText,quitYes,quitNo);
		optionsPane.getChildren().addAll(clockIn,clockOut,optionsQuit);
		
/*
 * This part is going to have the overview screen of all 3 dining rooms. 
 * This will be the "screen saver" that will appear before a user logs in 
 * and is brought to their screen.
 */
		
		Rectangle t2,t3,t5,t6,t7,t8,t9,t11,t12,t13,t14,t15,t16,t17,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,t31,t32;
		Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,l23,l24,l25,l26,l27,l28,l29,l30,l31,l32;
		
		Circle t1 = new Circle(100,125,30); //round sec 1
		l1 = new Label("1");
		StackPane t1stack = new StackPane();
		t1stack.getChildren().addAll(t1,l1);
		l1.relocate(100, 125);
		l1.setTextFill(Color.WHITE);
		//l1.toFront();
		
		
		t1.setFill(Color.BLUE);
		t2 = new Rectangle(140,70,40,40); //sec 1 window
		
		t2.setFill(Color.BLUE);
		t3 = new Rectangle(140,130,40,40);//sec 1 wall
		t3.setFill(Color.BLUE);
		Circle t4 = new Circle(250,70,30); //round sec 2
		t4.setFill(Color.BLUE);
		t5 = new Rectangle(205,135,30,30); //2top sec 2
		t5.setFill(Color.BLUE);
		t6 = new Rectangle(300,70,40,40); //sec 2 window
		t6.setFill(Color.BLUE);
		t7 = new Rectangle(260,130,40,40);//sec 2 wall
		t7.setFill(Color.BLUE);
		t8 = new Rectangle(325,135,30,30);//sec 3 2top
		t8.setFill(Color.BLUE);
		t9 = new Rectangle(400,70,40,40);//sec 3 window
		t9.setFill(Color.BLUE);
		Circle t10 = new Circle(500,70,30); //sec 3 round
		t10.setFill(Color.BLUE);
		t11 = new Rectangle(470,130,40,40); //sec 3 wall
		t11.setFill(Color.BLUE);
		t12 = new Rectangle(560,70,40,40);//sec 4 near window
		t12.setFill(Color.BLUE);
		t13 = new Rectangle(565,135,30,30); //sec 4 2top
		t13.setFill(Color.BLUE);
		t14 = new Rectangle(620,70,40,40);//sec4 far window
		t14.setFill(Color.BLUE);
		t15 = new Rectangle(620,130,40,40);//sec 4 wall
		t15.setFill(Color.BLUE);
		Line line1 = new Line(0,200,730,200);
		Line line2 = new Line(730,0,730,200);
		t16 = new Rectangle(100,250,40,40);//sec 5 wall
		t16.setFill(Color.BLUE);
		t17 = new Rectangle(200,250,40,40);//sec 5 center
		t17.setFill(Color.BLUE);
		Circle t18 = new Circle(325,275,30);//sec 5 round
		t18.setFill(Color.BLUE);
		t19 = new Rectangle(325,335,30,30);//sec 6 2top
		t19.setFill(Color.BLUE);
		t20 = new Rectangle(200,365,40,40);//sec 6 center
		t20.setFill(Color.BLUE);
		t21 = new Rectangle(325,400,40,40);//sec 6 window
		t21.setFill(Color.BLUE);
		t22 = new Rectangle(325,480,40,40);//sec 6 wall
		t22.setFill(Color.BLUE);
		t23 = new Rectangle(200,480,40,40);//sec 7 back wall
		t23.setFill(Color.BLUE);
		t24 = new Rectangle(100,480,40,40);//sec 7 corner
		t24.setFill(Color.BLUE);
		t25 = new Rectangle(100,365,40,40);//sec 7 side wall
		t25.setFill(Color.BLUE);
		Line line3 = new Line(400,200,400,700);
		t26 = new Rectangle(435,480,40,40);//sec 8 near
		t26.setFill(Color.BLUE);
		t27 = new Rectangle(435,400,40,40);//sec 8 center 
		t27.setFill(Color.BLUE);
		t28 = new Rectangle(435,320,40,40);//sec 8 window
		t28.setFill(Color.BLUE);
		t29 = new Rectangle(515,340,50,160);//middle table
		t29.setFill(Color.BLUE);
		t30 = new Rectangle(610,480,40,40);//sec 9 near
		t30.setFill(Color.BLUE);
		t31 = new Rectangle(610,400,40,40);//sec 9 center
		t31.setFill(Color.BLUE);
		t32 = new Rectangle(610,320,40,40);//sec 9 window
		t32.setFill(Color.BLUE);
		Line line4 = new Line(730,200,730,800);
		
		
		diningRoomOverviewPane.getChildren().addAll(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,t31,t32,line1,line2,line3,line4,
				l1);
/*
 * This part has the create new party screen on it
 */
		
	//words words words	
		
/*
 * This part will contain all of the declarations and placement for the table buttons PORCH
 */
		POSButton p1 = new POSButton(100,100,"1");
		p1.relocate(50, 300);
		p1.setStyle(
				//making a circle button
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 125px; " +
                "-fx-min-height: 125px; " +
                "-fx-max-width: 125px; " +
                "-fx-max-height: 125px;"
        );
		POSButton p2 = new POSButton(100,100,"2");
		p2.relocate(180, 150);
		POSButton p3 = new POSButton(100,100,"3");
		p3.relocate(180, 450);
		POSButton p4 = new POSButton(100,100,"4");
		p4.relocate(330, 150);
		p4.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 125px; " +
                "-fx-min-height: 125px; " +
                "-fx-max-width: 125px; " +
                "-fx-max-height: 125px;"
        );
		POSButton p5 = new POSButton(100,100,"5");
		p5.relocate(330, 450);
		POSButton p6 = new POSButton(100,100,"6");
		p6.relocate(480, 150);
		POSButton p7 = new POSButton(100,100,"7");
		p7.relocate(480, 450);
		POSButton p8 = new POSButton(100,100,"8");
		p8.relocate(630, 150);
		POSButton p9 = new POSButton(100,100,"9");
		p9.relocate(630, 450);
		POSButton p10 = new POSButton(100,100,"10");
		p10.relocate(780, 150);
		p10.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 125px; " +
                "-fx-min-height: 125px; " +
                "-fx-max-width: 125px; " +
                "-fx-max-height: 125px;"
        );
		POSButton p11 = new POSButton(100,100,"11");
		p11.relocate(890, 450);
		POSButton p12 = new POSButton(100,100,"12");
		p12.relocate(980, 150);
		POSButton p13 = new POSButton(100,100,"13");
		p13.relocate(1040, 450);
		POSButton p14 = new POSButton(100,100,"14");
		p14.relocate(1130, 150);
		POSButton p15 = new POSButton(100,100,"15");
		p15.relocate(1190, 450);
		
		//add all children to the pane
		porchMapPane.getChildren().addAll(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15);
		
/*
 * This part has all of the bdr table button declarations and configurations
 */
		POSButton b1 = new POSButton(50,150,"1");
		b1.relocate(100, 100);
		POSButton b2 = new POSButton(50,150,"2");
		b2.relocate(350, 100);
		POSButton b3 = new POSButton(50,150,"3");
		b3.setStyle("-fx-background-radius: 5em; " +
                "-fx-min-width: 125px; " +
                "-fx-min-height: 125px; " +
                "-fx-max-width: 125px; " +
                "-fx-max-height: 125px;");
		POSButton b4 = new POSButton(50,150,"4");
		POSButton b5 = new POSButton(50,150,"5");
		POSButton b6 = new POSButton(50,150,"6");
		POSButton b7 = new POSButton(50,150,"7");
		POSButton b8 = new POSButton(50,150,"8");
		POSButton b9 = new POSButton(50,150,"9");
		POSButton b10 = new POSButton(50,150,"10");
		
		bdrMapPane.getChildren().addAll(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10);
		
		clockPaneMainMenu.getChildren().addAll(mainMenuClock,optionButton,logInButton,logOutButton,serverGUI);
		clockPaneMainMenu.setSpacing(20);
		Scene scene = new Scene(rootPane, 1400, 700);
		primaryStage.setTitle("DBIPOS");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
