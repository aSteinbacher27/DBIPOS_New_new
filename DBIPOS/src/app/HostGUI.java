package app;
import java.awt.Font;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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

import java.util.ArrayList;

/**
 * @author Brian Jaury
 * @author Spencer Mowrey
 * @author Alex Steinbacher
 * @author Anissa Zacharias
 * @author Kayla Wilson
 * 
 * @version 1.0
 * 
 *          The HostGUI class creates tables and a party list along with several
 *          other buttons and finalizes stuff
 *          
 *          //Alex Steinbacher was here 4:28 10/29/2016
 *          //I'm going to murder someone
 *          
 */
public class HostGUI extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	Register register = new Register();
	ArrayList<String> waitingParties = new ArrayList<String>();
	// components necessary to declare outside of method
	String keyPadLabelString = "";

	@Override
	public void start(Stage primaryStage) throws Exception {

		/*
		 * Throw your panes down here home slizzle
		 */
		//Adding a comment
		BorderPane rootPane = new BorderPane();
		Pane diningRoomOverviewPane = new Pane();
		Pane porchMapPane = new Pane();
		Pane bdrMapPane = new Pane();
		Pane ldrMapPane = new Pane();
		HBox clockPaneMainMenu = new HBox();
		Pane queuePane = new Pane();
		rootPane.setTop(clockPaneMainMenu);
		rootPane.setCenter(diningRoomOverviewPane);
		rootPane.setRight(queuePane);

		/*
		 * stuff for the queue pane
		 */

		// initialize the digital clock to put on the top pane of the main menu
		DigitalClock mainMenuClock = new DigitalClock();
		mainMenuClock.relocate(500, 610);
		mainMenuClock.setStyle("-fx-font-size: 16px; -fx-border-color: black");

		// list stuff
		ListView<String> queue = new ListView<String>();
		queue.setStyle("-fx-font-size: 16px");
		ObservableList<String> parties = FXCollections.observableArrayList();
		queue.setItems(parties);

		// buttons
		Label waitingLabel = new Label("Size:\t\t\tName:");
		waitingLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		Label totalWaitingLabel = new Label("Total Parties Waiting: 0");
		totalWaitingLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");

		POSButton createNewParty = new POSButton(50, 150, "Create New Party");
		createNewParty.setStyle("-fx-font-size: 14px");

		POSButton seatParty = new POSButton(50, 150, "Seat Party");
		seatParty.setStyle("-fx-font-size: 16px");

		POSButton deleteParty = new POSButton(50, 150, "Delete Party");
		deleteParty.setStyle("-fx-font-size: 16px");

		waitingLabel.relocate(150, 0);
		totalWaitingLabel.relocate(260, 510);
		queue.relocate(150, 25);
		queue.setPrefHeight(475);
		queue.setPrefWidth(300);
		createNewParty.relocate(75, 550);
		seatParty.relocate(225, 550);
		deleteParty.relocate(375, 550);
		queuePane.setPrefWidth(600);

		queuePane.getChildren().addAll(queue, createNewParty, seatParty, deleteParty, waitingLabel, totalWaitingLabel,
				mainMenuClock);

		/*
		 * _______________________________________________________________________________
		 * Create New Party Pop-up
		 * _______________________________________________________________________________
		 */

		Stage newPartyStage = new Stage();
		Pane newPartyPane = new Pane();
		Scene newPartyScene = new Scene(newPartyPane, 500, 370);
		newPartyStage.setScene(newPartyScene);

		// create list of items
		ListView<String> addPartiesOrderListView = new ListView<String>();
		ObservableList<String> orderListItems = FXCollections.observableArrayList();
		addPartiesOrderListView.setStyle("-fx-font-size: 16px");
		addPartiesOrderListView.setItems(orderListItems);
		addPartiesOrderListView.setLayoutX(300);
		addPartiesOrderListView.setLayoutY(20);
		addPartiesOrderListView.setPrefWidth(180);
		addPartiesOrderListView.setPrefHeight(300);

		Label newPartyLabel = new Label("Create a New Party: ");
		newPartyLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		newPartyLabel.setLayoutX(5);
		newPartyLabel.setLayoutY(5);
		Label newPartyNameLabel = new Label("Name: ");
		newPartyNameLabel.setStyle("-fx-font-size: 16px");
		newPartyNameLabel.setLayoutX(20);
		newPartyNameLabel.setLayoutY(30);
		TextField newPartyNameField = new TextField();
		newPartyNameField.setStyle("-fx-font-size: 16px");
		newPartyNameField.setLayoutX(20);
		newPartyNameField.setLayoutY(50);
		Label newPartySizeLabel = new Label("Size: ");
		newPartySizeLabel.setStyle("-fx-font-size: 16px");
		newPartySizeLabel.setLayoutX(20);
		newPartySizeLabel.setLayoutY(90);
		TextField newPartySizeField = new TextField();
		newPartySizeField.setStyle("-fx-font-size: 16px");
		newPartySizeField.setMaxWidth(50);
		newPartySizeField.setLayoutX(20);
		newPartySizeField.setLayoutY(110);

		// breakfast and lunch radio buttons
		RadioButton isBreakfastRadioButton = new RadioButton("Breakfast");
		isBreakfastRadioButton.setStyle("-fx-font-size: 16px");
		isBreakfastRadioButton.setLayoutX(20);
		isBreakfastRadioButton.setLayoutY(155);
		RadioButton isLunchDinnerRadioButton = new RadioButton("Lunch/Dinner");
		isLunchDinnerRadioButton.setStyle("-fx-font-size: 16px");
		isLunchDinnerRadioButton.setLayoutX(150);
		isLunchDinnerRadioButton.setLayoutY(155);

		ToggleGroup breakfastLunch = new ToggleGroup();
		isBreakfastRadioButton.setToggleGroup(breakfastLunch);
		isLunchDinnerRadioButton.setToggleGroup(breakfastLunch);

		// meal buttons
		POSButton ageAdultButton = new POSButton(40, 100, "Adult");
		ageAdultButton.setStyle("-fx-font-size: 16px");
		ageAdultButton.setLayoutX(20);
		ageAdultButton.setLayoutY(190);
		ageAdultButton.setOnAction(e -> {

			System.out.println("Add Age Adult to List");
			orderListItems.add("Adult");

		});
		POSButton ageFreeButton = new POSButton(40, 100, "Age 0-3");
		ageFreeButton.setStyle("-fx-font-size: 16px");
		ageFreeButton.setLayoutX(140);
		ageFreeButton.setLayoutY(190);
		ageFreeButton.setOnAction(e -> {

			System.out.println("Add Age Free to List");
			orderListItems.add("Age 0-3");

		});
		POSButton ageFourButton = new POSButton(40, 100, "Age 4-5");
		ageFourButton.setStyle("-fx-font-size: 16px");
		ageFourButton.setLayoutX(20);
		ageFourButton.setLayoutY(250);
		ageFourButton.setOnAction(e -> {

			System.out.println("Add Age Four to List");
			orderListItems.add("Age 4-5");

		});
		POSButton ageSixButton = new POSButton(40, 100, "Age 6-8");
		ageSixButton.setStyle("-fx-font-size: 16px");
		ageSixButton.setLayoutX(140);
		ageSixButton.setLayoutY(250);
		ageSixButton.setOnAction(e -> {

			System.out.println("Add Age Six to List");
			orderListItems.add("Age 6-8");

		});
		POSButton ageNineButton = new POSButton(40, 100, "Age 9-11");
		ageNineButton.setStyle("-fx-font-size: 16px");
		ageNineButton.setLayoutX(20);
		ageNineButton.setLayoutY(310);
		ageNineButton.setOnAction(e -> {

			System.out.println("Add Age Nine to List");
			orderListItems.add("Age 9-11");
		});

		// delete and create button
		POSButton deleteButton = new POSButton(40, 100, "Delete");
		deleteButton.setStyle("-fx-font-size: 16px");
		deleteButton.setLayoutX(285);
		deleteButton.setLayoutY(330);

		// deleteButton onClickAction Listener
		// Removes selected party from items ObservableList and updates view.
		deleteButton.setOnAction(e -> {

			System.out.println("Delete Item!");
			System.out.println(
					"Index of selected item: " + addPartiesOrderListView.getSelectionModel().getSelectedIndex());

			if (addPartiesOrderListView.getSelectionModel().getSelectedIndex() != -1) {

				orderListItems.remove(addPartiesOrderListView.getSelectionModel().getSelectedIndex());
				// Display items again.
				addPartiesOrderListView.setItems(orderListItems);
			}

		});

		POSButton createButton = new POSButton(40, 100, "Create");
		createButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		createButton.setLayoutX(390);
		createButton.setLayoutY(330);
		createButton.setOnAction(e -> {

			String partyName = newPartyNameField.getText();
			int partySize = Integer.parseInt(newPartySizeField.getText());
			boolean isBreakfast = isBreakfastRadioButton.isSelected();

			System.out.println("Is it breakfast: " + isBreakfast);

			if (partyName.contentEquals("") || partyName.contentEquals(" ")) {

				System.err.println("Empty Name");

			} else {

				System.out.println("Create Party!");
				System.out.println("Party Name: " + partyName);
				System.out.println("Party Size: " + partySize);

				// Create a new Party object to waitingParties array in Register
				// Class.
				Register.waitingParties.add(new Party(partyName, partySize, isBreakfast));

				for (String s : orderListItems) {
					System.out.print(s);
					switch (s) {
					case "Adult":
						if (isBreakfast) {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"10.95");
						} else {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"17.95");
						}
						break;

					case "Age 0-3":
						if (isBreakfast) {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"0.00");
						} else {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"0.00");
						}
						break;
					case "Age 4-5":
						if (isBreakfast) {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"5.95");
						} else {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"6.95");
						}
						break;
					case "Age 6-8":
						if (isBreakfast) {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"6.95");
						} else {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"8.95");
						}
						break;
					case "Age 9-11":
						if (isBreakfast) {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"7.95");
						} else {
							Register.waitingParties.get(Register.waitingParties.size() - 1).getCheck().addItem(s,
									"10.95");
						}
						break;
					}

				}

				// TESTING
				// Register.waitingParties.get(0).getCheck().finalize();

				// add new party to waiting list
				waitingParties.clear();
				for (int i = 0; i < Register.waitingParties.size(); i++) {
					waitingParties.add(Register.waitingParties.get(i).getPartySize() + "\t\t\t"
							+ Register.waitingParties.get(i).getPartyName());
					System.out.println(waitingParties);
				}
				parties.clear();
				parties.addAll(waitingParties);
				queue.setItems(parties);

				// update number of parties waiting
				totalWaitingLabel.setText("Total Parties Waiting: " + Register.waitingParties.size());

				// close window
				newPartyStage.close();

				// reset all fields
				isBreakfastRadioButton.setSelected(false);
				isLunchDinnerRadioButton.setSelected(false);
				newPartyNameField.clear();
				newPartySizeField.clear();
				orderListItems.clear();
				addPartiesOrderListView.setItems(orderListItems);

				// //Print Statements strictly for debugging
				// for(Party p: Register.waitingParties){
				// System.out.println("The name of the new party added is: " +
				// p.getPartyName());
				// System.out.println("The size of the new party added is: " +
				// p.getPartySize());
				// System.out.println("Is the party getting breakfast? " +
				// p.isBreakfast());

				// }

			}

		});

		// add everything to pane
		newPartyPane.getChildren().addAll(addPartiesOrderListView, newPartyLabel, newPartyNameLabel, newPartySizeLabel,
				newPartyNameField, newPartySizeField, isBreakfastRadioButton, isLunchDinnerRadioButton, ageAdultButton,
				ageFreeButton, ageFourButton, ageSixButton, ageNineButton, deleteButton, createButton);

		/*
		 * _______________________________________________________________________________
		 * Seat Party Pop-up
		 * _______________________________________________________________________________
		 */

		Stage seatPartyStage = new Stage();
		Pane seatPartyPane = new Pane();
		Scene seatPartyScene = new Scene(seatPartyPane, 200, 220);
		seatPartyStage.setScene(seatPartyScene);

		Label seatPartyLabel = new Label("Seat Party ");
		seatPartyLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		seatPartyLabel.setLayoutX(5);
		seatPartyLabel.setLayoutY(5);
		Label seatPartyServerLabel = new Label("Name: ");
		seatPartyServerLabel.setStyle("-fx-font-size: 16px");
		seatPartyServerLabel.setLayoutX(20);
		seatPartyServerLabel.setLayoutY(30);
		ComboBox serverChoice = new ComboBox();
		ObservableList<String> serverChoiceItems = FXCollections.observableArrayList(Register.getEmployeeNames());
		serverChoice.setItems(serverChoiceItems);
		serverChoice.setStyle("-fx-font-size: 16px");
		serverChoice.setLayoutX(20);
		serverChoice.setLayoutY(50);
		Label seatPartyTableLabel = new Label("Table: ");
		seatPartyTableLabel.setStyle("-fx-font-size: 16px");
		seatPartyTableLabel.setLayoutX(20);
		seatPartyTableLabel.setLayoutY(90);
		ComboBox tableChoice = new ComboBox();
		ObservableList<Integer> tableChoiceItems = FXCollections.observableArrayList(Register.getAvailableTables());
		tableChoice.setItems(tableChoiceItems);
		tableChoice.setStyle("-fx-font-size: 16px");
		tableChoice.setLayoutX(20);
		tableChoice.setLayoutY(110);
		POSButton seatButton = new POSButton(40, 100, "Seat Party");
		seatButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		seatButton.setLayoutX(20);
		seatButton.setLayoutY(160);

		// Seating party inside pop-up: Transfers party from waiting to seated.
		seatButton.setOnAction(e -> {

			System.out.println("Seating party");

			// Adding Party from waiting party to active party
			Register.activeParties.add(Register.waitingParties.get(queue.getSelectionModel().getSelectedIndex()));

			totalWaitingLabel.setText("Total Parties Waiting: " + Register.waitingParties.size());

			int tableNumber = (int) (tableChoice.getSelectionModel().getSelectedItem());

			int selectedPartyIndex = queue.getSelectionModel().getSelectedIndex();
			int selectedServerIndex = serverChoice.getSelectionModel().getSelectedIndex();
			System.out.println("selectedPartyIndex = " + selectedPartyIndex);
			System.out.println("taleNumber = " + tableNumber);
			Register.waitingParties.get(selectedPartyIndex).setTable(tableNumber);

			System.out.println("Value: " + tableChoice.getSelectionModel().getSelectedItem());

			// System.out.println("Table Number: "+ tableNumber);

			seatPartyStage.close();

			// Removing Party from waiting party
			Register.waitingParties.remove(queue.getSelectionModel().getSelectedIndex());
			System.out.println("Removed party from waiting parties");

			Register.seatParty(tableNumber);

			// Getting id for Employee
			Register.activeParties.get(Register.activeParties.size() - 1)
					.setServerID(Register.employees.get(selectedServerIndex).getID());

			// Refreshing List:
			// add new party to waiting list
			waitingParties.clear();
			for (int i = 0; i < Register.waitingParties.size(); i++) {
				waitingParties.add(Register.waitingParties.get(i).getPartySize() + "\t\t\t"
						+ Register.waitingParties.get(i).getPartyName());
				System.out.println(waitingParties);
			}

			parties.clear();
			parties.addAll(waitingParties);
			queue.setItems(parties);

			// for(Party p: Register.activeParties){
			// System.out.println("Server ID:" + p.getServerID());
			// }

			System.out.println(
					"Server ID is: " + Register.activeParties.get(Register.activeParties.size() - 1).getServerID());

			// End of Refreshing

		});

		// add everything to pane
		seatPartyPane.getChildren().addAll(seatButton, seatPartyLabel, seatPartyServerLabel, seatPartyTableLabel,
				serverChoice, tableChoice);

		/*
		 * When create new party, or seat party button is pushed, show pop-up
		 * window
		 */
		createNewParty.setOnAction(e -> {
			newPartyStage.show();
		});

		seatParty.setOnAction(e -> {
			// Button for seat party on HostGUI
			System.out.println("Populating list of avaliable tables on seatParty screen.");
			tableChoiceItems.clear();
			tableChoiceItems.addAll(Register.getAvailableTables());
			tableChoice.setItems(tableChoiceItems);
			seatPartyStage.show();
		});

		/*
		 * Delete Party from Pop-up Waiting List
		 */
		Stage deletePartyStage = new Stage();
		Pane deletePartyPane = new Pane();
		Scene deletePartyScene = new Scene(deletePartyPane, 375, 130);
		deletePartyStage.setScene(deletePartyScene);

		Label deletePartyLabel = new Label("Delete Party");
		deletePartyLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
		deletePartyLabel.setLayoutX(5);
		deletePartyLabel.setLayoutY(5);

		Label deletePartyMsgLabel = new Label();
		deletePartyMsgLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold");
		deletePartyMsgLabel.setLayoutX(10);
		deletePartyMsgLabel.setLayoutY(35);
		deletePartyMsgLabel.setWrapText(true);

		POSButton cancelDeletePartyButton = new POSButton(40, 100, "Cancel");
		cancelDeletePartyButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		cancelDeletePartyButton.setLayoutX(40);
		cancelDeletePartyButton.setLayoutY(70);

		POSButton confirmDeletePartyButton = new POSButton(40, 100, "Delete");
		confirmDeletePartyButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		confirmDeletePartyButton.setLayoutX(230);
		confirmDeletePartyButton.setLayoutY(70);

		deleteParty.setOnAction(e -> {
			deletePartyStage.show();
			String deletePartyName = Register.waitingParties.get(queue.getSelectionModel().getSelectedIndex())
					.getPartyName();
			deletePartyMsgLabel.setText("Are you sure you would like to delete the " + deletePartyName + " party?");

		});

		cancelDeletePartyButton.setOnAction(e -> {
			deletePartyStage.close();
		});

		confirmDeletePartyButton.setOnAction(e -> {

			Register.waitingParties.remove(queue.getSelectionModel().getSelectedIndex());

			// Refreshing List:
			// add new party to waiting list
			waitingParties.clear();
			for (int i = 0; i < Register.waitingParties.size(); i++) {
				waitingParties.add(Register.waitingParties.get(i).getPartySize() + "\t\t\t"
						+ Register.waitingParties.get(i).getPartyName());
				System.out.println(waitingParties);
			}

			// update number of parties waiting
			totalWaitingLabel.setText("Total Parties Waiting: " + Register.waitingParties.size());

			parties.clear();
			parties.addAll(waitingParties);
			queue.setItems(parties);
			deletePartyStage.close();
		});

		deletePartyPane.getChildren().addAll(deletePartyLabel, deletePartyMsgLabel, cancelDeletePartyButton,
				confirmDeletePartyButton);

		/*
		 * Keypad stuff
		 */

		Stage keyPadStage = new Stage();
		Pane keyPadGridPane = new Pane();
		Scene keyPadScene = new Scene(keyPadGridPane, 240, 525);
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
		POSButton backspaceButton = new POSButton(20, 40, "<-");
		backspaceButton.setStyle("-fx-font-size: 16px");
		backspaceButton.setLayoutX(150);
		backspaceButton.setLayoutY(55);

		POSButton k1 = new POSButton(80, 80, "1");
		k1.setStyle("-fx-font-size: 16px");
		k1.setLayoutX(0);
		k1.setLayoutY(130);
		POSButton k2 = new POSButton(80, 80, "2");
		k2.setStyle("-fx-font-size: 16px");
		k2.setLayoutX(80);
		k2.setLayoutY(130);
		POSButton k3 = new POSButton(80, 80, "3");
		k3.setStyle("-fx-font-size: 16px");
		k3.setLayoutX(160);
		k3.setLayoutY(130);
		POSButton k4 = new POSButton(80, 80, "4");
		k4.setStyle("-fx-font-size: 16px");
		k4.setLayoutX(0);
		k4.setLayoutY(210);
		POSButton k5 = new POSButton(80, 80, "5");
		k5.setStyle("-fx-font-size: 16px");
		k5.setLayoutX(80);
		k5.setLayoutY(210);
		POSButton k6 = new POSButton(80, 80, "6");
		k6.setStyle("-fx-font-size: 16px");
		k6.setLayoutX(160);
		k6.setLayoutY(210);
		POSButton k7 = new POSButton(80, 80, "7");
		k7.setStyle("-fx-font-size: 16px");
		k7.setLayoutX(0);
		k7.setLayoutY(290);
		POSButton k8 = new POSButton(80, 80, "8");
		k8.setStyle("-fx-font-size: 16px");
		k8.setLayoutX(80);
		k8.setLayoutY(290);
		POSButton k9 = new POSButton(80, 80, "9");
		k9.setStyle("-fx-font-size: 16px");
		k9.setLayoutX(160);
		k9.setLayoutY(290);
		POSButton k0 = new POSButton(80, 80, "0");
		k0.setStyle("-fx-font-size: 16px");
		k0.setLayoutX(80);
		k0.setLayoutY(370);
		POSButton submitButton = new POSButton(40, 100, "Submit");
		submitButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		submitButton.setLayoutX(70);
		submitButton.setLayoutY(460);

		keyPadGridPane.getChildren().addAll(submitButton, EnterIDLabel, backspaceButton, k1, k2, k3, k4, k5, k6, k7, k8,
				k9, k0, keyPadLabel);

		// action listeners for all buttons
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
			keyPadLabelString = keyPadLabelString.substring(0, keyPadLabelString.length() - 1);
			keyPadLabel.setText(keyPadLabelString);
		});
		submitButton.setOnAction(e -> {
			// do something
			keyPadStage.close();
		});

		/*
		 * Everything for the login/logout/quit screen goes here
		 */
		// pane stage and scene for the quit screen
		VBox quitPane = new VBox();
		VBox optionsPane = new VBox();
		Stage quitStage = new Stage();
		Stage optionsStage = new Stage();
		Scene quitScene = new Scene(quitPane);
		Scene optionsScene = new Scene(optionsPane);

		// This button is the log in button
		POSButton logInButton = new POSButton(50, 150, "Log In");
		logInButton.setStyle("-fx-font-size: 16px");
		logInButton.setOnAction(e -> {
			keyPadStage.show();
		});
		// This button is the log out button
		POSButton logOutButton = new POSButton(50, 150, "Log Out");
		logOutButton.setStyle("-fx-font-size: 16px");
		POSButton logOutGoBack = new POSButton(100, 100, "cool");
		logOutGoBack.setStyle("-fx-font-size: 16px");
		Text logOutConfirm = new Text("You WIN!!!!");
		Stage logOutStage = new Stage();
		VBox logOutPane = new VBox();
		Scene logOutScene = new Scene(logOutPane);
		logOutPane.getChildren().addAll(logOutConfirm, logOutGoBack);
		logOutButton.setOnAction(e -> {
			logOutStage.setScene(logOutScene);
			logOutStage.show();
		});
		logOutGoBack.setOnAction(e -> {
			logOutStage.close();
		});

		// This button brings up the screen for the login/logout/quit functions
		POSButton optionButton = new POSButton(50, 150, "OPTIONS");
		optionButton.setStyle("-fx-font-size: 16px");
		optionButton.setOnAction(e -> {
			optionsStage.setScene(optionsScene);
			optionsStage.show();
		});

		// This button is the quit button on the options pane
		POSButton optionsQuit = new POSButton(50, 150, "Quit Program");
		optionsQuit.setStyle("-fx-font-size: 16px");
		optionsQuit.setOnAction(e -> {
			quitStage.setScene(quitScene);
			optionsStage.close();
			quitStage.show();
		});

		// This button closes all stages of the program
		POSButton quitYes = new POSButton(50, 150, "YES");
		quitYes.setStyle("-fx-font-size: 16px");
		quitYes.setOnAction(e -> {
			optionsStage.close();
			quitStage.close();
			primaryStage.close();
		});

		// This button closes only the quit stage
		POSButton quitNo = new POSButton(50, 150, "NO");
		quitNo.setStyle("-fx-font-size: 16px");
		quitNo.setOnAction(e -> {
			quitStage.close();
		});

		// Text for explaining the window
		Text quitText = new Text("Do you really want to quit?");
		quitText.setStyle("-fx-font-size: 16px");

		// puts children on the correct panes
		POSButton serverGUI = new POSButton(50, 150, "Server GUI");
		serverGUI.setOnAction(e -> {
			ServerGUI serverObject = new ServerGUI();
			serverObject.GUI(primaryStage, register);

			serverObject.seatedTablesList.clear();
			serverObject.seatedTablesList.addAll(Register.getSeatedTables());
			serverObject.tableList.setItems(serverObject.seatedTablesList);

		});
		POSButton clockIn = new POSButton(50, 150, "Clock In");
		clockIn.setStyle("-fx-font-size: 16px");
		POSButton clockOut = new POSButton(50, 150, "Clock Out");
		clockOut.setStyle("-fx-font-size: 16px");

		// //Disables the clock out function while you haven't clocked in yet,
		// and saves clock in/clock out times for the file
		// clockOut.setDisable(true);
		// clockIn.setOnAction(e -> {
		// SimpleDateFormat ddMMyyyyhhmmss = new
		// SimpleDateFormat("dd/MM/yyyy_hh:mm:ss");
		// String clockInDate;
		// Date createdTime = new Date();
		// clockInDate = new String(ddMMyyyyhhmmss.format(createdTime));
		// System.out.println(clockInDate);
		// try {
		// BufferedWriter inverseBucket = new BufferedWriter(new
		// FileWriter("clockinclockoutdata.txt",true));
		// inverseBucket.write(String.format("%n%s ",clockInDate));
		// inverseBucket.close();
		// clockIn.setDisable(true);
		// clockOut.setDisable(false);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }
		// });
		// clockOut.setOnAction(e -> {
		// SimpleDateFormat ddMMyyyyhhmmss = new
		// SimpleDateFormat("dd/MM/yyyy_hh:mm:ss");
		// String clockOutDate;
		// Date createdTime = new Date();
		// clockOutDate = new String(ddMMyyyyhhmmss.format(createdTime));
		// System.out.println(clockOutDate);
		// try {
		// BufferedWriter inverseBucket = new BufferedWriter(new
		// FileWriter("clockinclockoutdata.txt",true));
		// inverseBucket.write(String.format("%s",clockOutDate));
		// inverseBucket.close();
		// clockIn.setDisable(false);
		// clockOut.setDisable(true);
		// clockIn.setOnAction(e -> {
		// SimpleDateFormat ddMMyyyyhhmmss = new
		// SimpleDateFormat("dd/MM/yyyy_hh:mm:ss");
		// String clockInDate;
		// Date createdTime = new Date();
		// clockInDate = new String(ddMMyyyyhhmmss.format(createdTime));
		// System.out.println(clockInDate);
		// try {
		// BufferedWriter inverseBucket = new BufferedWriter(new
		// FileWriter("clockinclockoutdata.txt",true));
		// inverseBucket.write(String.format("%n%s ",clockInDate));
		// inverseBucket.close();
		// clockIn.setDisable(true);
		// clockOut.setDisable(false);
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }
		// });
		// }
		clockOut.setOnAction(e -> {
			SimpleDateFormat ddMMyyyyhhmmss = new SimpleDateFormat("dd/MM/yyyy_hh:mm:ss");
			String clockOutDate;
			Date createdTime = new Date();
			clockOutDate = new String(ddMMyyyyhhmmss.format(createdTime));
			System.out.println(clockOutDate);
			try {
				BufferedWriter inverseBucket = new BufferedWriter(new FileWriter("clockinclockoutdata.txt", true));
				inverseBucket.write(String.format("%s", clockOutDate));
				inverseBucket.close();
				clockIn.setDisable(false);
				clockOut.setDisable(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		quitPane.getChildren().addAll(quitText, quitYes, quitNo);
		optionsPane.getChildren().addAll(clockIn, clockOut, optionsQuit);

		/*
		 * This part is going to have the overview screen of all 3 dining rooms.
		 * This will be the "screen saver" that will appear before a user logs
		 * in and is brought to their screen.
		 */

		Rectangle t2, t3, t5, t6, t7, t8, t9, t11, t12, t13, t14, t15, t16, t17, t19, t20, t21, t22, t23, t24, t25, t26,
				t27, t28, t29, t30, t31, t32;
		Label l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23,
				l24, l25, l26, l27, l28, l29, l30, l31, l32;

		Circle t1 = new Circle(100, 125, 30); // round sec 1
		l1 = new Label("1");
		StackPane t1stack = new StackPane();
		t1stack.getChildren().addAll(t1, l1);
		l1.relocate(100, 125);
		l1.setTextFill(Color.WHITE);
		t1.setFill(Color.GREEN);

		t2 = new Rectangle(140, 70, 40, 40); // sec 1 window
		t2.setFill(Color.GREEN);
		l2 = new Label("2");
		StackPane t2stack = new StackPane();
		t2stack.getChildren().addAll(t2, l2);
		l2.relocate(150, 80);
		l2.setTextFill(Color.WHITE);

		t3 = new Rectangle(140, 130, 40, 40);// sec 1 wall
		t3.setFill(Color.GREEN);
		l3 = new Label("3");
		StackPane t3stack = new StackPane();
		t3stack.getChildren().addAll(t3, l3);
		l3.relocate(150, 140);
		l3.setTextFill(Color.WHITE);

		Circle t4 = new Circle(250, 70, 30); // round sec 2
		t4.setFill(Color.GREEN);
		l4 = new Label("4");
		StackPane t4stack = new StackPane();
		t4stack.getChildren().addAll(t4, l4);
		l4.relocate(250, 70);
		l4.setTextFill(Color.WHITE);

		t5 = new Rectangle(205, 135, 30, 30); // 2top sec 2
		t5.setFill(Color.GREEN);
		l5 = new Label("5");
		StackPane t5stack = new StackPane();
		t5stack.getChildren().addAll(t5, l5);
		l5.relocate(215, 145);
		l5.setTextFill(Color.WHITE);

		t6 = new Rectangle(300, 70, 40, 40); // sec 2 window
		t6.setFill(Color.GREEN);
		l6 = new Label("6");
		StackPane t6stack = new StackPane();
		t6stack.getChildren().addAll(t6, l6);
		l6.relocate(310, 80);
		l6.setTextFill(Color.WHITE);

		t7 = new Rectangle(260, 130, 40, 40);// sec 2 wall
		t7.setFill(Color.GREEN);
		l7 = new Label("7");
		StackPane t7stack = new StackPane();
		t7stack.getChildren().addAll(t7, l7);
		l7.relocate(270, 140);
		l7.setTextFill(Color.WHITE);

		t8 = new Rectangle(325, 135, 30, 30);// sec 3 2top
		t8.setFill(Color.GREEN);
		l8 = new Label("8");
		StackPane t8stack = new StackPane();
		t8stack.getChildren().addAll(t8, l8);
		l8.relocate(335, 145);
		l8.setTextFill(Color.WHITE);

		t9 = new Rectangle(400, 70, 40, 40);// sec 3 window
		t9.setFill(Color.GREEN);
		l9 = new Label("9");
		StackPane t9stack = new StackPane();
		t9stack.getChildren().addAll(t9, l9);
		l9.relocate(410, 80);
		l9.setTextFill(Color.WHITE);

		Circle t10 = new Circle(500, 70, 30); // sec 3 round
		t10.setFill(Color.GREEN);
		l10 = new Label("10");
		StackPane t10stack = new StackPane();
		t10stack.getChildren().addAll(t10, l10);
		l10.relocate(500, 70);
		l10.setTextFill(Color.WHITE);

		t11 = new Rectangle(470, 130, 40, 40); // sec 3 wall
		t11.setFill(Color.GREEN);
		l11 = new Label("11");
		StackPane t11stack = new StackPane();
		t11stack.getChildren().addAll(t11, l11);
		l11.relocate(480, 140);
		l11.setTextFill(Color.WHITE);

		t12 = new Rectangle(560, 70, 40, 40);// sec 4 near window
		t12.setFill(Color.GREEN);
		l12 = new Label("12");
		StackPane t12stack = new StackPane();
		t12stack.getChildren().addAll(t12, l12);
		l12.relocate(570, 80);
		l12.setTextFill(Color.WHITE);

		t13 = new Rectangle(565, 135, 30, 30); // sec 4 2top
		t13.setFill(Color.GREEN);
		l13 = new Label("13");
		StackPane t13stack = new StackPane();
		t13stack.getChildren().addAll(t13, l13);
		l13.relocate(575, 145);
		l13.setTextFill(Color.WHITE);

		t14 = new Rectangle(620, 70, 40, 40);// sec4 far window
		t14.setFill(Color.GREEN);
		l14 = new Label("14");
		StackPane t14stack = new StackPane();
		t14stack.getChildren().addAll(t14, l14);
		l14.relocate(630, 80);
		l14.setTextFill(Color.WHITE);

		t15 = new Rectangle(620, 130, 40, 40);// sec 4 wall
		t15.setFill(Color.GREEN);
		l15 = new Label("15");
		StackPane t15stack = new StackPane();
		t15stack.getChildren().addAll(t15, l15);
		l15.relocate(630, 140);
		l15.setTextFill(Color.WHITE);

		Line line1 = new Line(0, 200, 730, 200);
		Line line2 = new Line(730, 0, 730, 200);

		t16 = new Rectangle(100, 250, 40, 40);// sec 5 wall
		t16.setFill(Color.GREEN);
		l16 = new Label("16");
		StackPane t16stack = new StackPane();
		t16stack.getChildren().addAll(t16, l16);
		l16.relocate(110, 260);
		l16.setTextFill(Color.WHITE);

		t17 = new Rectangle(200, 250, 40, 40);// sec 5 center
		t17.setFill(Color.GREEN);
		l17 = new Label("17");
		StackPane t17stack = new StackPane();
		t17stack.getChildren().addAll(t17, l17);
		l17.relocate(210, 260);
		l17.setTextFill(Color.WHITE);

		Circle t18 = new Circle(325, 275, 30);// sec 5 round
		t18.setFill(Color.GREEN);
		l18 = new Label("18");
		StackPane t18stack = new StackPane();
		t18stack.getChildren().addAll(t18, l18);
		l18.relocate(325, 275);
		l18.setTextFill(Color.WHITE);

		t19 = new Rectangle(325, 335, 30, 30);// sec 6 2top
		t19.setFill(Color.GREEN);
		l19 = new Label("19");
		StackPane t19stack = new StackPane();
		t19stack.getChildren().addAll(t19, l19);
		l19.relocate(335, 345);
		l19.setTextFill(Color.WHITE);

		t20 = new Rectangle(200, 365, 40, 40);// sec 6 center
		t20.setFill(Color.GREEN);
		l20 = new Label("20");
		StackPane t20stack = new StackPane();
		t20stack.getChildren().addAll(t20, l20);
		l20.relocate(210, 375);
		l20.setTextFill(Color.WHITE);

		t21 = new Rectangle(325, 400, 40, 40);// sec 6 window
		t21.setFill(Color.GREEN);
		l21 = new Label("21");
		StackPane t21stack = new StackPane();
		t21stack.getChildren().addAll(t21, l21);
		l21.relocate(335, 410);
		l21.setTextFill(Color.WHITE);

		t22 = new Rectangle(325, 480, 40, 40);// sec 6 wall
		t22.setFill(Color.GREEN);
		l22 = new Label("22");
		StackPane t22stack = new StackPane();
		t22stack.getChildren().addAll(t22, l22);
		l22.relocate(335, 490);
		l22.setTextFill(Color.WHITE);

		t23 = new Rectangle(200, 480, 40, 40);// sec 7 back wall
		t23.setFill(Color.GREEN);
		l23 = new Label("23");
		StackPane t23stack = new StackPane();
		t23stack.getChildren().addAll(t23, l23);
		l23.relocate(210, 490);
		l23.setTextFill(Color.WHITE);

		t24 = new Rectangle(100, 480, 40, 40);// sec 7 corner
		t24.setFill(Color.GREEN);
		l24 = new Label("24");
		StackPane t24stack = new StackPane();
		t24stack.getChildren().addAll(t24, l24);
		l24.relocate(110, 490);
		l24.setTextFill(Color.WHITE);

		t25 = new Rectangle(100, 365, 40, 40);// sec 7 side wall
		t25.setFill(Color.GREEN);
		l25 = new Label("25");
		StackPane t25stack = new StackPane();
		t25stack.getChildren().addAll(t25, l25);
		l25.relocate(110, 375);
		l25.setTextFill(Color.WHITE);

		Line line3 = new Line(400, 200, 400, 700);

		t26 = new Rectangle(435, 480, 40, 40);// sec 8 near
		t26.setFill(Color.GREEN);
		l26 = new Label("26");
		StackPane t26stack = new StackPane();
		t26stack.getChildren().addAll(t26, l26);
		l26.relocate(445, 490);
		l26.setTextFill(Color.WHITE);

		t27 = new Rectangle(435, 400, 40, 40);// sec 8 center
		t27.setFill(Color.GREEN);
		l27 = new Label("27");
		StackPane t27stack = new StackPane();
		t27stack.getChildren().addAll(t27, l27);
		l27.relocate(445, 410);
		l27.setTextFill(Color.WHITE);

		t28 = new Rectangle(435, 320, 40, 40);// sec 8 window
		t28.setFill(Color.GREEN);
		l28 = new Label("28");
		StackPane t28stack = new StackPane();
		t28stack.getChildren().addAll(t28, l28);
		l28.relocate(445, 330);
		l28.setTextFill(Color.WHITE);

		t29 = new Rectangle(515, 340, 50, 160);// middle table
		t29.setFill(Color.GREEN);
		l29 = new Label("29");
		StackPane t29stack = new StackPane();
		t29stack.getChildren().addAll(t29, l29);
		l29.relocate(525, 400);
		l29.setTextFill(Color.WHITE);

		t30 = new Rectangle(610, 480, 40, 40);// sec 9 near
		t30.setFill(Color.GREEN);
		l30 = new Label("30");
		StackPane t30stack = new StackPane();
		t30stack.getChildren().addAll(t30, l30);
		l30.relocate(620, 490);
		l30.setTextFill(Color.WHITE);

		t31 = new Rectangle(610, 400, 40, 40);// sec 9 center
		t31.setFill(Color.GREEN);
		l31 = new Label("31");
		StackPane t31stack = new StackPane();
		t31stack.getChildren().addAll(t31, l31);
		l31.relocate(620, 410);
		l31.setTextFill(Color.WHITE);

		t32 = new Rectangle(610, 320, 40, 40);// sec 9 window
		t32.setFill(Color.GREEN);
		l32 = new Label("32");
		StackPane t32stack = new StackPane();
		t32stack.getChildren().addAll(t32, l32);
		l32.relocate(620, 330);
		l32.setTextFill(Color.WHITE);

		Line line4 = new Line(730, 200, 730, 800);

		diningRoomOverviewPane.getChildren().addAll(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15,
				t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, line1, line2,
				line3, line4, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20,
				l21, l22, l23, l24, l25, l26, l27, l28, l29, l30, l31, l32);
		/*
		 * This part has the create new party screen on it
		 */

		// words words words

		/*
		 * This part will contain all of the declarations and placement for the
		 * table buttons PORCH
		 */
		POSButton p1 = new POSButton(100, 100, "1");
		p1.relocate(50, 300);
		p1.setStyle(
				// making a circle button
				"-fx-background-radius: 5em; " + "-fx-min-width: 125px; " + "-fx-min-height: 125px; "
						+ "-fx-max-width: 125px; " + "-fx-max-height: 125px;");
		POSButton p2 = new POSButton(100, 100, "2");
		p2.relocate(180, 150);
		POSButton p3 = new POSButton(100, 100, "3");
		p3.relocate(180, 450);
		POSButton p4 = new POSButton(100, 100, "4");
		p4.relocate(330, 150);
		p4.setStyle("-fx-background-radius: 5em; " + "-fx-min-width: 125px; " + "-fx-min-height: 125px; "
				+ "-fx-max-width: 125px; " + "-fx-max-height: 125px;");
		POSButton p5 = new POSButton(100, 100, "5");
		p5.relocate(330, 450);
		POSButton p6 = new POSButton(100, 100, "6");
		p6.relocate(480, 150);
		POSButton p7 = new POSButton(100, 100, "7");
		p7.relocate(480, 450);
		POSButton p8 = new POSButton(100, 100, "8");
		p8.relocate(630, 150);
		POSButton p9 = new POSButton(100, 100, "9");
		p9.relocate(630, 450);
		POSButton p10 = new POSButton(100, 100, "10");
		p10.relocate(780, 150);
		p10.setStyle("-fx-background-radius: 5em; " + "-fx-min-width: 125px; " + "-fx-min-height: 125px; "
				+ "-fx-max-width: 125px; " + "-fx-max-height: 125px;");
		POSButton p11 = new POSButton(100, 100, "11");
		p11.relocate(890, 450);
		POSButton p12 = new POSButton(100, 100, "12");
		p12.relocate(980, 150);
		POSButton p13 = new POSButton(100, 100, "13");
		p13.relocate(1040, 450);
		POSButton p14 = new POSButton(100, 100, "14");
		p14.relocate(1130, 150);
		POSButton p15 = new POSButton(100, 100, "15");
		p15.relocate(1190, 450);

		// add all children to the pane
		porchMapPane.getChildren().addAll(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15);

		/*
		 * This part has all of the bdr table button declarations and
		 * configurations
		 */
		POSButton b1 = new POSButton(50, 150, "1");
		b1.relocate(100, 100);
		POSButton b2 = new POSButton(50, 150, "2");
		b2.relocate(350, 100);
		POSButton b3 = new POSButton(50, 150, "3");
		b3.setStyle("-fx-background-radius: 5em; " + "-fx-min-width: 125px; " + "-fx-min-height: 125px; "
				+ "-fx-max-width: 125px; " + "-fx-max-height: 125px;");
		POSButton b4 = new POSButton(50, 150, "4");
		POSButton b5 = new POSButton(50, 150, "5");
		POSButton b6 = new POSButton(50, 150, "6");
		POSButton b7 = new POSButton(50, 150, "7");
		POSButton b8 = new POSButton(50, 150, "8");
		POSButton b9 = new POSButton(50, 150, "9");
		POSButton b10 = new POSButton(50, 150, "10");

		bdrMapPane.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);

		clockPaneMainMenu.getChildren().addAll(optionButton, logInButton, logOutButton, serverGUI);
		clockPaneMainMenu.setSpacing(20);
		Scene scene = new Scene(rootPane, 1400, 700);
		primaryStage.setTitle("DBIPOS");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		/*
		 * ----------------------------------------------------------------------
		 * Edit item pop-up
		 */

		Stage editItemStage = new Stage();
		editItemStage.setTitle("Edit Items");
		Pane editItemPane = new Pane();
		Scene editItemScene = new Scene(editItemPane, 400, 420);
		editItemStage.setScene(editItemScene);

		ListView<String> itemsListView = new ListView<String>();
		ObservableList<String> ListItems = FXCollections.observableArrayList(
				"Display Items", "Register.items");
		itemsListView.setStyle("-fx-font-size: 16px");
		itemsListView.setItems(ListItems);
		itemsListView.setLayoutX(100);
		itemsListView.setLayoutY(35);
		itemsListView.setPrefWidth(200);
		itemsListView.setPrefHeight(250);

		Label editItemLabel = new Label("Select an item:");
		editItemLabel.setStyle("-fx-font-size: 16px");
		editItemLabel.relocate(10, 5);

		POSButton addItemButton = new POSButton(40, 140, "Add New Item");
		addItemButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		addItemButton.relocate(130, 360);

		POSButton editItemButton = new POSButton(40, 110, "Edit Item");
		editItemButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		editItemButton.relocate(80, 300);

		POSButton deleteItemButton = new POSButton(40, 110, "Delete Item");
		deleteItemButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		deleteItemButton.relocate(210, 300);

		editItemPane.getChildren().addAll(editItemLabel, itemsListView,
				addItemButton, editItemButton, deleteItemButton);
		//editItemStage.show();

		/*
		 * ------------------------------------------------------------ Add item
		 * / edit item pop-up For add item: text fields would be blank For edit
		 * item: text fields would populate with the selected item's info
		 */

		Stage addItemStage = new Stage();
		addItemStage.setTitle("Add/Edit Item");
		Pane addItemPane = new Pane();
		Scene addItemScene = new Scene(addItemPane, 400, 250);
		addItemStage.setScene(addItemScene);

		Label ItemNameLabel = new Label("Name:");
		ItemNameLabel.setStyle("-fx-font-size: 16px");
		ItemNameLabel.relocate(10, 15);

		TextField ItemName = new TextField();
		ItemName.setStyle("-fx-font-size: 16px");
		ItemName.relocate(100, 15);

		Label ItemPriceLabel = new Label("Price:");
		ItemPriceLabel.setStyle("-fx-font-size: 16px");
		ItemPriceLabel.relocate(10, 65);

		TextField ItemPrice = new TextField();
		ItemPrice.setPromptText("xx.xx");
		ItemPrice.setStyle("-fx-font-size: 16px");
		ItemPrice.relocate(100, 65);

		Label ItemCategoryLabel = new Label("Category:");
		ItemCategoryLabel.setStyle("-fx-font-size: 16px");
		ItemCategoryLabel.relocate(10, 115);

		ComboBox ItemCategory = new ComboBox();
		ObservableList<String> ItemCategoryItems = FXCollections
				.observableArrayList("Menu", "Drinks", "Dessert", "Gift Shop");
		ItemCategory.setItems(ItemCategoryItems);
		ItemCategory.setStyle("-fx-font-size: 16px");
		ItemCategory.relocate(100, 115);

		POSButton ItemAddButton = new POSButton(40, 150, "Add Item");
		ItemAddButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		ItemAddButton.relocate(125, 185);

		addItemPane.getChildren().addAll(ItemNameLabel, ItemName,
				ItemPriceLabel, ItemPrice, ItemCategoryLabel, ItemCategory,
				ItemAddButton);
		//addItemStage.show();

		/*
		 * -----------------------------------------------------------------
		 * Edit employee pop-up
		 */

		Stage editEmployeeStage = new Stage();
		editEmployeeStage.setTitle("Edit Employees");
		Pane editEmployeePane = new Pane();
		Scene editEmployeeScene = new Scene(editEmployeePane, 400, 420);
		editEmployeeStage.setScene(editEmployeeScene);

		ListView<String> employeesListView = new ListView<String>();
		ObservableList<String> ListEmployees = FXCollections
				.observableArrayList("Display Employees", "Register.employees");
		employeesListView.setStyle("-fx-font-size: 16px");
		employeesListView.setItems(ListEmployees);
		employeesListView.setLayoutX(100);
		employeesListView.setLayoutY(35);
		employeesListView.setPrefWidth(200);
		employeesListView.setPrefHeight(250);

		Label editEmployeeLabel = new Label("Select an employee:");
		editEmployeeLabel.setStyle("-fx-font-size: 16px");
		editEmployeeLabel.relocate(10, 5);

		POSButton addEmployeeButton = new POSButton(40, 180, "Add New Employee");
		addEmployeeButton
				.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		addEmployeeButton.relocate(110, 360);

		POSButton editEmployeeButton = new POSButton(40, 160, "Edit Employee");
		editEmployeeButton
				.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		editEmployeeButton.relocate(40, 300);

		POSButton deleteEmployeeButton = new POSButton(40, 160,
				"Delete Employee");
		deleteEmployeeButton
				.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		deleteEmployeeButton.relocate(210, 300);

		editEmployeePane.getChildren().addAll(editEmployeeLabel,
				employeesListView, addEmployeeButton, editEmployeeButton,
				deleteEmployeeButton);
		// editEmployeeStage.show();

		/*
		 * ---------------------------------------------- Add employee / edit
		 * employee pop-up For add employee: text fields would be blank For edit
		 * employee: text fields would populate with the selected employees info
		 */

		Stage addEmployeeStage = new Stage();
		addEmployeeStage.setTitle("Add/Edit Employee");
		Pane addEmployeePane = new Pane();
		Scene addEmployeeScene = new Scene(addEmployeePane, 400, 500);
		addEmployeeStage.setScene(addEmployeeScene);

		Label EmpNameLabel = new Label("Name:");
		EmpNameLabel.setStyle("-fx-font-size: 16px");
		EmpNameLabel.relocate(10, 15);

		TextField EmpName = new TextField();
		EmpName.setPromptText("First MI Last");
		EmpName.setStyle("-fx-font-size: 16px");
		EmpName.relocate(100, 15);

		Label EmpSSNLabel = new Label("SSN:");
		EmpSSNLabel.setStyle("-fx-font-size: 16px");
		EmpSSNLabel.relocate(10, 65);

		TextField EmpSSN = new TextField();
		EmpSSN.setPromptText("xxx-xx-xxxx");
		EmpSSN.setStyle("-fx-font-size: 16px");
		EmpSSN.relocate(100, 65);

		Label EmpPhoneLabel = new Label("Phone:");
		EmpPhoneLabel.setStyle("-fx-font-size: 16px");
		EmpPhoneLabel.relocate(10, 115);

		TextField EmpPhone = new TextField();
		EmpPhone.setPromptText("xxx-xxx-xxxx");
		EmpPhone.setStyle("-fx-font-size: 16px");
		EmpPhone.relocate(100, 115);

		Label EmpEmailLabel = new Label("E-mail:");
		EmpEmailLabel.setStyle("-fx-font-size: 16px");
		EmpEmailLabel.relocate(10, 165);

		TextField EmpEmail = new TextField();
		EmpEmail.setPromptText("address@email.com");
		EmpEmail.setStyle("-fx-font-size: 16px");
		EmpEmail.relocate(100, 165);

		Label EmpAddressLabel = new Label("Address:");
		EmpAddressLabel.setStyle("-fx-font-size: 16px");
		EmpAddressLabel.relocate(10, 215);

		TextField EmpAddress = new TextField();
		EmpAddress.setStyle("-fx-font-size: 16px");
		EmpAddress.relocate(100, 215);

		Label EmpRoleLabel = new Label("Role:");
		EmpRoleLabel.setStyle("-fx-font-size: 16px");
		EmpRoleLabel.relocate(10, 265);

		ComboBox EmpRole = new ComboBox();
		ObservableList<String> EmpRoleItems = FXCollections
				.observableArrayList("Host", "Server", "Manager", "Cashier",
						"Crew");
		EmpRole.setItems(EmpRoleItems);
		EmpRole.setStyle("-fx-font-size: 16px");
		EmpRole.relocate(100, 265);

		Label EmpWageLabel = new Label("Wage:");
		EmpWageLabel.setStyle("-fx-font-size: 16px");
		EmpWageLabel.relocate(10, 315);

		TextField EmpWage = new TextField();
		EmpWage.setPromptText("xx.xx");
		EmpWage.setStyle("-fx-font-size: 16px");
		EmpWage.relocate(100, 315);

		Label EmpClearanceLabel = new Label("Clearance:");
		EmpClearanceLabel.setStyle("-fx-font-size: 16px");
		EmpClearanceLabel.relocate(10, 365);

		ComboBox EmpClearance = new ComboBox();
		ObservableList<String> EmpClearanceItems = FXCollections
				.observableArrayList("0 - No POS", "1 - Host",
						"2 - Host & Server", "3 - Manager", "4 - Kitchen");
		EmpClearance.setItems(EmpClearanceItems);
		EmpClearance.setStyle("-fx-font-size: 16px");
		EmpClearance.relocate(100, 365);

		POSButton EmpAddButton = new POSButton(40, 150, "Add Employee");
		EmpAddButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		EmpAddButton.relocate(125, 455);

		addEmployeePane.getChildren().addAll(EmpNameLabel, EmpName,
				EmpSSNLabel, EmpSSN, EmpPhoneLabel, EmpPhone, EmpEmailLabel,
				EmpEmail, EmpAddressLabel, EmpAddress, EmpRoleLabel, EmpRole,
				EmpWageLabel, EmpWage, EmpAddButton, EmpClearanceLabel,
				EmpClearance);
		// addEmployeeStage.show();

	}

}
