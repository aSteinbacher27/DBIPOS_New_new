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
import javafx.scene.control.ComboBox;
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
		ComboBox itemList = new ComboBox(FXCollections.observableArrayList(
			    "Menu Items", "Drink Items", "Dessert Items","Gift Shop Items")
			);
		ComboBox tableList = new ComboBox(FXCollections.observableArrayList(
			    "Table1", "Table2", "Table3")
			);
		
		ListView<String> inventory = new ListView<String>();
		ListView<String> inventoryP = new ListView<String>();
		ObservableList<String> inventoryItems=FXCollections.observableArrayList();
		ObservableList<String> inventoryPrices=FXCollections.observableArrayList();
		
		
		// When category (comboBox) selection is changed, listView updates with appropriate items
		itemList.setOnAction(e -> {
			if (itemList.getSelectionModel().getSelectedItem().toString() == "Menu Items") {
				
				inventoryItems.addAll(Register.menuItemNames);
				inventory.setItems(inventoryItems);
				
				inventoryPrices.addAll(Register.menuItemPrices);
				inventoryP.setItems(inventoryPrices);
			}
			else if (itemList.getSelectionModel().getSelectedItem().toString() == "Drink Items") {
				inventoryItems.addAll(Register.drinkItemNames);
				inventory.setItems(inventoryItems);
				
				inventoryPrices.addAll(Register.drinkItemPrices);
				inventoryP.setItems(inventoryPrices);
			}
			else if (itemList.getSelectionModel().getSelectedItem().toString() == "Dessert Items") {
				inventoryItems.addAll(Register.dessertItemNames);
				inventory.setItems(inventoryItems);
				
				inventoryPrices.addAll(Register.dessertItemPrices);
				inventoryP.setItems(inventoryPrices);
			}
			else if (itemList.getSelectionModel().getSelectedItem().toString() == "Gift Shop Items") {
				inventoryItems.addAll(Register.giftItemNames);
				inventory.setItems(inventoryItems);
				
				inventoryPrices.addAll(Register.giftItemPrices);
				inventoryP.setItems(inventoryPrices);
			}
		});
		
		
//		DO NOT DELETE.. NEEDED FOR TESTING & DEBUGGING
//		ListView<String> inventory = new ListView<String>();
//		ObservableList<String> inventoryItems =FXCollections.observableArrayList (Register.menuItemNames);
//		ObservableList<String> inventory.setItems(inventoryItems);
//		
//		ListView<String> inventoryP = new ListView<String>();
//		ObservableList<String> inventoryPrices =FXCollections.observableArrayList (Register.menuItemPrices);
//		ObservableList<String> inventoryP.setItems(inventoryPrices);
		
		
		ListView<String> customerCheck = new ListView<String>();
		ObservableList<String> customerCheckItems =FXCollections.observableArrayList ();
		customerCheck.setItems(customerCheckItems);
		
		ListView<String> prices = new ListView<String>();
		ObservableList<String> itemPrices =FXCollections.observableArrayList ();
		prices.setItems(itemPrices);
		
		
		POSButton quitButton=new POSButton(40,60,"Close");
		POSButton addItem=new POSButton(100,100,"Add Item");
		POSButton removeItem=new POSButton(60,90,"Remove Item");
		Pane pane = new Pane();
		inventory.relocate(50,150);
		inventory.setPrefWidth(150);
		inventory.setPrefHeight(400);
		inventoryP.setPrefWidth(150);
		inventoryP.setPrefHeight(400);
		inventoryP.relocate(200,150);
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
		
		// Listener for inventory. When item in inventory is selected, the corresponding price is also selected
		inventory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    int index = inventory.getSelectionModel().getSelectedIndex();
		    inventoryP.getSelectionModel().select(index);
		});
		
		// Listener for inventoryP. When item in inventory price is selected, the corresponding price is also selected
		inventoryP.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    int index = inventoryP.getSelectionModel().getSelectedIndex();
		    inventory.getSelectionModel().select(index);
		});
		
		// Listener for inventory. When item in inventory is selected, the corresponding price is also selected
		customerCheck.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			int index = customerCheck.getSelectionModel().getSelectedIndex();
			prices.getSelectionModel().select(index);
		});
				
		// Listener for inventoryP. When item in inventory price is selected, the corresponding price is also selected
		prices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			int index = prices.getSelectionModel().getSelectedIndex();
			customerCheck.getSelectionModel().select(index);
		});
		
		// add item to customers check
		addItem.setOnAction(e -> {
		     String selectedItems = inventory.getSelectionModel().getSelectedItem();
		     int index = inventory.getSelectionModel().getSelectedIndex();
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
		
		
		// remove item from customers check
		removeItem.setOnAction(e -> {
			 String selectedItems = customerCheck.getSelectionModel().getSelectedItem();
		     int index = customerCheck.getSelectionModel().getSelectedIndex();
		         if (selectedItems != null) {
		           customerCheck.getSelectionModel().clearSelection();
		           customerCheckItems.remove(selectedItems);
		           String selectedName = itemPrices.get(index);
			         if (selectedName != null) {
				           prices.getSelectionModel().clearSelection();
				           itemPrices.remove(selectedName);
				     }
		         }
		         else {
		        	 String selectedPrices = prices.getSelectionModel().getSelectedItem();
				     int indexP = prices.getSelectionModel().getSelectedIndex();
				     if (selectedPrices != null) {
				    	 prices.getSelectionModel().clearSelection();
				           itemPrices.remove(selectedPrices);
				           String selectedName = itemPrices.get(index);
					         if (selectedName != null) {
						           customerCheck.getSelectionModel().clearSelection();
						           customerCheckItems.remove(selectedName);
						     }
		         }
		   
		         }});
	}
}
