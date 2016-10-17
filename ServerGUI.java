import java.awt.Dimension;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
public class ServerGUI extends Application {

	public static void main(String[] args) {
		Register r = new Register();
		launch(args);

		
	}
	public void start(Stage primaryStage) throws Exception {
		GUI(primaryStage);
	}
	public static void GUI(Stage primaryStage){
		ChoiceBox itemList = new ChoiceBox(FXCollections.observableArrayList(
			    "Drinks", "Desserts", "Meals")
			);
		ChoiceBox tableList = new ChoiceBox(FXCollections.observableArrayList(
			    "Table1", "Table2", "Table3")
			);
		
		ListView<String> inventory = new ListView<String>();
		ObservableList<String> inventoryItems =FXCollections.observableArrayList (Register.menuItemNames);
		ListView<String> inventoryP = new ListView<String>();
		ObservableList<String> inventoryPrices =FXCollections.observableArrayList (Register.menuItemPrices);
		
		ListView<String> customerCheck = new ListView<String>();
		ObservableList<String> customerCheckItems =FXCollections.observableArrayList ();
		
		ListView<String> prices = new ListView<String>();
		ObservableList<String> itemPrices =FXCollections.observableArrayList ();
		inventory.setItems(inventoryItems);
		inventoryP.setItems(inventoryPrices);
		customerCheck.setItems(customerCheckItems);
		prices.setItems(itemPrices);
		
		POSButton quitButton=new POSButton(40,60,"Close");
		POSButton addItem=new POSButton(100,100,"Add Item");
		POSButton removeItem=new POSButton(60,90,"Remove Item");
		Pane pane = new Pane();
		inventory.relocate(50,150);
		inventory.setPrefWidth(150);
		inventory.setPrefHeight(400);
		inventoryP.relocate(200,150);
		inventoryP.setPrefWidth(150);
		inventoryP.setPrefHeight(400);
		customerCheck.relocate(700, 150);
		customerCheck.setPrefWidth(150);
		customerCheck.setPrefHeight(400);
		prices.relocate(850, 150);
		prices.setPrefWidth(150);
		prices.setPrefHeight(400);
		quitButton.relocate(0,0);
		itemList.relocate(50, 100);
		tableList.relocate(800,100);
		addItem.relocate(450, 300);
		removeItem.relocate(800,550);
		pane.getChildren().addAll(inventory,inventoryP,customerCheck,prices,quitButton,itemList,tableList,addItem,removeItem);

		
		
		// Create and display said the aforementioned pane in a new stage 	
		Scene scene = new Scene(pane, 1000, 600);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Server GUI");
		stage.setResizable(false);
		stage.show();
		
		quitButton.setOnAction(e -> {
			stage.close();
		});
		
		// Handle ListView selection changes.
		inventory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("Selection changed");
		    int index = inventory.getSelectionModel().getSelectedIndex();
		    inventoryP.getSelectionModel().select(index);
		});
		
		inventoryP.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    System.out.println("Selection changed");
		    int index = inventoryP.getSelectionModel().getSelectedIndex();
		    inventory.getSelectionModel().select(index);
		});
		
		addItem.setOnAction(e -> {
		     String selectedItems = inventory.getSelectionModel().getSelectedItem();
		     int index = inventory.getSelectionModel().getSelectedIndex();
		     System.out.println(index);
		         if (selectedItems != null) {
		           inventory.getSelectionModel().clearSelection();
		           customerCheckItems.add(selectedItems);
		           String selectedName = inventoryPrices.get(index);
			         if (selectedName != null) {
				           inventoryP.getSelectionModel().clearSelection();
				           itemPrices.add(selectedName);
				     }
		         }
		         else {
		        	 String selectedPrices = inventoryP.getSelectionModel().getSelectedItem();
				     int indexP = inventoryP.getSelectionModel().getSelectedIndex();
				     System.out.println(indexP);
				     if (selectedPrices != null) {
				           inventoryP.getSelectionModel().clearSelection();
				           customerCheckItems.add(selectedPrices);
				           String selectedName = inventoryPrices.get(index);
					         if (selectedName != null) {
						           inventory.getSelectionModel().clearSelection();
						           itemPrices.add(selectedName);
						     }
		         }
		   
		         }});
		
		
		removeItem.setOnAction(e -> {
		     String selectedItems = customerCheck.getSelectionModel()
		             .getSelectedItem();
		         if (selectedItems != null) {
		           customerCheck.getSelectionModel().clearSelection();
		           customerCheckItems.remove(selectedItems);
		         }
		});
	}
}
