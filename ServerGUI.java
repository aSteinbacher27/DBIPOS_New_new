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
		
		ListView<String> newItemList = new ListView<String>();
		ObservableList<String> newitems =FXCollections.observableArrayList (
		    "Item1", "Item2", "Item3", "Item4");
		
		ListView<String> existingItemList = new ListView<String>();
		ObservableList<String> existingitems =FXCollections.observableArrayList (
				"Item1", "Item2", "Item3", "Item4");
		
		ListView<String> prices = new ListView<String>();
		ObservableList<String> itemPrices =FXCollections.observableArrayList (
				"Price1", "Price2", "Price3", "Price4");
		newItemList.setItems(newitems);
		existingItemList.setItems(existingitems);
		prices.setItems(itemPrices);
		
		POSButton quitButton=new POSButton(40,60,"Close");
		POSButton addItem=new POSButton(100,100,"Add Item");
		POSButton removeItem=new POSButton(60,90,"Remove Item");
		Pane pane = new Pane();
		newItemList.relocate(50,150);
		existingItemList.relocate(700, 150);
		existingItemList.setPrefWidth(150);
		existingItemList.setPrefHeight(400);
		prices.relocate(850, 150);
		prices.setPrefWidth(150);
		prices.setPrefHeight(400);
		quitButton.relocate(0,0);
		itemList.relocate(50, 100);
		tableList.relocate(800,100);
		addItem.relocate(450, 300);
		removeItem.relocate(800,550);
		pane.getChildren().addAll(newItemList,existingItemList,prices,quitButton,itemList,tableList,addItem,removeItem);

		
		
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
		addItem.setOnAction(e -> {
		     String selectedItems = newItemList.getSelectionModel()
		             .getSelectedItem();
		         if (selectedItems != null) {
		           newItemList.getSelectionModel().clearSelection();
		           existingitems.add(selectedItems);
		         }
		});
		removeItem.setOnAction(e -> {
		     String selectedItems = existingItemList.getSelectionModel()
		             .getSelectedItem();
		         if (selectedItems != null) {
		           existingItemList.getSelectionModel().clearSelection();
		           existingitems.remove(selectedItems);
		         }
		});
	}
}
