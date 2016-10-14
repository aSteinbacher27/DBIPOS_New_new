import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HostGUI extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
/*
 * Throw your panes down here home slizzle
 */
		BorderPane rootPane = new BorderPane();
		Pane diningRoomOverviewPane = new Pane();
		ListView queue = new ListView();
		Pane porchMapPane = new Pane();
		Pane bdrMapPane = new Pane();
		Pane ldrMapPane = new Pane();
		HBox clockPaneMainMenu = new HBox();
		rootPane.setTop(clockPaneMainMenu);
		rootPane.setCenter(diningRoomOverviewPane);
				
		//initialize the digital clock to put on the top pane of the main menu
		DigitalClock mainMenuClock = new DigitalClock();
		mainMenuClock.setLayoutX(1200);
		
		
			
/*
 * Stuff for switching dining rooms		
 */
		//declare the buttons
		POSButton porchSwitch,BDRSwitch,LDRSwitch;
		porchSwitch = new POSButton(50,150,"Porch");
		BDRSwitch = new POSButton(50,150,"BDR");
		LDRSwitch = new POSButton(50,150,"LDR");
		
		//set their actions. The pressing of the buttons merely switches the center setting
		//of the root pane (a border pane) to the new selection. The top and bottom panes stay
		//as they are.
		porchSwitch.setOnAction(e->{
			rootPane.setCenter(porchMapPane);
		});
		BDRSwitch.setOnAction(e->{
			rootPane.setCenter(bdrMapPane);
		});
		LDRSwitch.setOnAction(e->{
			rootPane.setCenter(ldrMapPane);
		});
		
		
/*
 * _______________________________________________________________________________
 * Keypad stuff
 * _______________________________________________________________________________
 */
		
		Stage keyPadStage = new Stage();
		FlowPane keyPadGridPane = new FlowPane();
		Scene keyPadScene = new Scene(keyPadGridPane,240,320);
		keyPadStage.setScene(keyPadScene);
		POSButton k1 = new POSButton(80,80,"1");
		POSButton k2 = new POSButton(80,80,"2");
		POSButton k3 = new POSButton(80,80,"3");
		POSButton k4 = new POSButton(80,80,"4");
		POSButton k5 = new POSButton(80,80,"5");
		POSButton k6 = new POSButton(80,80,"6");
		POSButton k7 = new POSButton(80,80,"7");
		POSButton k8 = new POSButton(80,80,"8");
		POSButton k9 = new POSButton(80,80,"9");
		POSButton k0 = new POSButton(80,80,"0");
		
		Label keyPadLabel = new Label("");
		keyPadGridPane.getChildren().addAll(k1,k2,k3,k4,k5,k6,k7,k8,k9,k0);
		
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
		logInButton.setOnAction(e->{
			keyPadStage.show();
		});
		//This button is the log out button
		POSButton logOutButton = new POSButton(50, 150, "Log Out");
		POSButton logOutGoBack = new POSButton(100,100,"cool");
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
		optionButton.setOnAction(e -> {
			optionsStage.setScene(optionsScene);
			optionsStage.show();
		});
		
		//This button is the quit button on the options pane
		POSButton optionsQuit = new POSButton(50, 150, "Quit Program");
		optionsQuit.setOnAction(e->{
			quitStage.setScene(quitScene);
			optionsStage.close();
			quitStage.show();
		});
		
		//This button closes all stages of the program
		POSButton quitYes = new POSButton(50, 150, "YES");
		quitYes.setOnAction(e->{
			optionsStage.close();
			quitStage.close();
			primaryStage.close();
		});
		
		//This button closes only the quit stage
		POSButton quitNo = new POSButton(50, 150, "NO");
		quitNo.setOnAction(e->{
			quitStage.close();
		});
		
		//Text for explaining the window
		Text quitText = new Text("Do you really want to quit?");
		
		//puts children on the correct panes
		
		POSButton clockIn = new POSButton(50,150,"Clock In");
		POSButton clockOut = new POSButton(50,150,"Clock Out");
		
		
		quitPane.getChildren().addAll(quitText,quitYes,quitNo);
		optionsPane.getChildren().addAll(clockIn,clockOut,optionsQuit);
		
/*
 * This part is going to have the overview screen of all 3 dining rooms. 
 * This will be the "screen saver" that will appear before a user logs in 
 * and is brought to their screen.
 */
		
		Rectangle t2,t3,t5,t6,t7,t8,t9,t11,t12,t13,t14,t15,t16,t17,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,t31,t32;
		Circle t1 = new Circle(100,125,30); //round sec 1
		t2 = new Rectangle(140,70,40,40); //sec 1 window
		t3 = new Rectangle(140,130,40,40);//sec 1 wall
		Circle t4 = new Circle(250,70,30); //round sec 2
		t5 = new Rectangle(205,135,30,30); //2top sec 2
		t6 = new Rectangle(300,70,40,40); //sec 2 window
		t7 = new Rectangle(260,130,40,40);//sec 2 wall
		t8 = new Rectangle(325,135,30,30);//sec 3 2top
		t9 = new Rectangle(400,70,40,40);//sec 3 window
		Circle t10 = new Circle(500,70,30); //sec 3 round
		t11 = new Rectangle(470,130,40,40); //sec 3 wall
		t12 = new Rectangle(560,70,40,40);//sec 4 near window
		t13 = new Rectangle(565,135,30,30); //sec 4 2top
		t14 = new Rectangle(620,70,40,40);//sec4 far window
		t15 = new Rectangle(620,130,40,40);//sec 4 wall
		Line line1 = new Line(0,200,730,200);
		Line line2 = new Line(730,0,730,200);
		t16 = new Rectangle(100,250,40,40);//sec 5 wall
		t17 = new Rectangle(200,250,40,40);//sec 5 center
		Circle t18 = new Circle(325,275,30);//sec 5 round
		t19 = new Rectangle(325,335,30,30);//sec 6 2top
		t20 = new Rectangle(200,365,40,40);//sec 6 center
		t21 = new Rectangle(325,400,40,40);//sec 6 window
		t22 = new Rectangle(325,480,40,40);//sec 6 wall
		t23 = new Rectangle(200,480,40,40);//sec 7 back wall
		t24 = new Rectangle(100,480,40,40);//sec 7 corner
		t25 = new Rectangle(100,365,40,40);//sec 7 side wall
		Line line3 = new Line(400,200,400,700);
		t26 = new Rectangle(30,30,0,0);//sec 8 near
		t27 = new Rectangle(30,30,0,0);//sec 8 center 
		t28 = new Rectangle(30,30,0,0);//sec 8 window
		t29 = new Rectangle(30,30,0,0);//middle table
		t30 = new Rectangle(30,30,0,0);//sec 9 near
		t31 = new Rectangle(30,30,0,0);//sec 9 center
		t32 = new Rectangle(30,30,0,0);//sec 9 window
		
		
		diningRoomOverviewPane.getChildren().addAll(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27,t28,t29,t30,t31,t32,line1,line2,line3);
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
		
		clockPaneMainMenu.getChildren().addAll(mainMenuClock,optionButton,logInButton,logOutButton);
		clockPaneMainMenu.setSpacing(20);
		Scene scene = new Scene(rootPane, 1400, 700);
		primaryStage.setTitle("DBIPOS");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}