import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class HostGUI extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane rootPane = new BorderPane();
		HBox tableMapButtonsPane = new HBox();
		Pane tableMapPane = new Pane();
		Pane clockPaneMainMenu = new Pane();
		Pane testPane = new Pane();
		rootPane.setTop(clockPaneMainMenu);
		rootPane.setCenter(tableMapPane);
		
		Text testText = new Text("hey this shit works fam");
		Button testbtn = new Button("click this shit nigga");
		
		//initialize the digital clock to put on the top pane of the main menu
		DigitalClock mainMenuClock = new DigitalClock();
		mainMenuClock.setLayoutX(1200);
		clockPaneMainMenu.getChildren().add(mainMenuClock);
		
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
		
		//This button is the log out button
		POSButton logOutButton = new POSButton(50, 150, "Log Out");
		
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
		
		quitPane.getChildren().addAll(quitText,quitYes,quitNo);
		optionsPane.getChildren().addAll(logInButton,logOutButton,optionsQuit);
		
/*
 * This part will contain all of the declarations and placement for the table buttons
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
		tableMapPane.getChildren().addAll(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,optionButton);
		
		Scene scene = new Scene(rootPane, 1400, 700);
		primaryStage.setTitle("DBIPOS");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}