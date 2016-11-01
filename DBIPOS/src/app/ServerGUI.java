package app;
import java.awt.Dimension;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Server GUI displays an interface for the servers. Capabilities include
 * viewing/editing items on a parties check, calculating totals, and printing a
 * receipt.
 *
 *
 */

public class ServerGUI extends Application {

	public static void main(String[] args) {
		// Register r = new Register();
		launch(args);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage) throws Exception {

	}

	static ObservableList<Integer> seatedTablesList = FXCollections.observableArrayList();
	static ComboBox tableList;

	static Label subtotalLabel = new Label();
	static Label taxLabel = new Label();
	static Label totalLabel = new Label();

	/**
	 * @param primaryStage:
	 *            Stage upon which the GUI will be created.
	 * @param r:
	 *            Register that is created in HostGUI.
	 */
	/**
	 * @param primaryStage
	 * @param r
	 */
	@SuppressWarnings("unchecked")
	public static void GUI(Stage primaryStage, Register r) {

		// create drop down menu for item categories
		ComboBox itemList = new ComboBox(
				FXCollections.observableArrayList("Menu Items", "Drink Items", "Dessert Items", "Gift Shop Items"));
		itemList.setStyle("-fx-font-size: 16px");

		// create drop down menu for available tables
		seatedTablesList.addAll(Register.getSeatedTables());
		tableList = new ComboBox(FXCollections.observableArrayList(seatedTablesList));
		tableList.setStyle("-fx-font-size: 16px");

		// create listView for customer check and prices (right side)
		ListView<String> customerCheck = new ListView<String>();
		ObservableList<String> customerCheckItems = FXCollections.observableArrayList();
		customerCheck.setItems(customerCheckItems);
		ListView<String> prices = new ListView<String>();
		ObservableList<String> itemPrices = FXCollections.observableArrayList();
		prices.setItems(itemPrices);

		// When table selection is changed, listView updates with appropriate
		// items
		tableList.getSelectionModel().selectFirst();

		tableList.setOnAction(e -> {
			int partyIndex2 = -99;
			customerCheckItems.clear();
			itemPrices.clear();
			// seatedTablesList.clear();
			// seatedTablesList.addAll(Register.getSeatedTables());
			// tableList.setItems(seatedTablesList);
			// add to check
			for (int i = 0; i < Register.activeParties.size(); i++) {

				if ((Register.activeParties.get(i)
						.getTableNumber() == (int) tableList.getSelectionModel().getSelectedItem())
						&& (partyIndex2 == -99)) {
					partyIndex2 = i;
				}
			}

			customerCheckItems.addAll(Register.activeParties.get(partyIndex2).getCheck().checkItems);
			customerCheck.setItems(customerCheckItems);
			itemPrices.addAll(Register.activeParties.get(partyIndex2).getCheck().checkPricesString);
			prices.setItems(itemPrices);

			updateTotalLabels(partyIndex2);

		});

		// create listView for inventory items and prices (left side)
		ListView<String> inventory = new ListView<String>();
		ListView<String> inventoryP = new ListView<String>();
		ObservableList<String> inventoryItems = FXCollections.observableArrayList(Register.menuItemNames);
		ObservableList<String> inventoryPrices = FXCollections.observableArrayList(Register.menuItemPrices);
		inventory.setItems(inventoryItems);
		inventoryP.setItems(inventoryPrices);

		// initiate category drop down to menu items
		itemList.getSelectionModel().selectFirst();

		// When category selection is changed, listView updates with appropriate
		// items
		itemList.setOnAction(e -> {
			inventoryItems.clear();
			inventory.setItems(inventoryItems);
			inventoryPrices.clear();
			inventoryP.setItems(inventoryPrices);

			// changed to "Menu Items"
			if (itemList.getSelectionModel().getSelectedItem().toString() == "Menu Items") {
				inventoryItems.addAll(Register.menuItemNames);
				inventory.setItems(inventoryItems);

				inventoryPrices.addAll(Register.menuItemPrices);
				inventoryP.setItems(inventoryPrices);
			}

			// changed to "Drink Items"
			else if (itemList.getSelectionModel().getSelectedItem().toString() == "Drink Items") {
				inventoryItems.addAll(Register.drinkItemNames);
				inventory.setItems(inventoryItems);

				inventoryPrices.addAll(Register.drinkItemPrices);
				inventoryP.setItems(inventoryPrices);
			}

			// changed to "Dessert Items"
			else if (itemList.getSelectionModel().getSelectedItem().toString() == "Dessert Items") {
				inventoryItems.addAll(Register.dessertItemNames);
				inventory.setItems(inventoryItems);

				inventoryPrices.addAll(Register.dessertItemPrices);
				inventoryP.setItems(inventoryPrices);
			}

			// chagned to "Gift Shop Items"
			else if (itemList.getSelectionModel().getSelectedItem().toString() == "Gift Shop Items") {
				inventoryItems.addAll(Register.giftItemNames);
				inventory.setItems(inventoryItems);

				inventoryPrices.addAll(Register.giftItemPrices);
				inventoryP.setItems(inventoryPrices);
			}
		});

		// add buttons
		POSButton quitButton = new POSButton(40, 100, "Close");
		POSButton addItem = new POSButton(100, 100, "Add Item");
		POSButton removeItem = new POSButton(40, 120, "Remove Item");
		Pane pane = new Pane();

		// add labels
		POSButton finalizeButton = new POSButton(40, 120, "Print Receipt");
		Label inventoryItemLabel = new Label("Item");
		inventoryItemLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		inventoryItemLabel.relocate(50, 125);
		Label inventoryPriceLabel = new Label("Price");
		inventoryPriceLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		inventoryPriceLabel.relocate(250, 125);
		Label checkItemLabel = new Label("Item");
		checkItemLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		checkItemLabel.relocate(700, 125);
		Label checkPriceLabel = new Label("Price");
		checkPriceLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		checkPriceLabel.relocate(900, 125);
		Label tableLabel = new Label("Table ");
		Label categoryLabel = new Label("Category ");

		// set locations and sizes for items within the GUI
		subtotalLabel.relocate(850, 450);
		subtotalLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		taxLabel.relocate(850, 475);
		taxLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		totalLabel.relocate(850, 500);
		totalLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		inventory.relocate(50, 150);
		inventory.setPrefWidth(200);
		inventory.setPrefHeight(400);
		inventory.setStyle("-fx-font-size: 16px");
		inventoryP.setPrefWidth(100);
		inventoryP.setPrefHeight(400);
		inventoryP.relocate(250, 150);
		inventoryP.setStyle("-fx-font-size: 16px");
		customerCheck.relocate(700, 150);
		customerCheck.setPrefWidth(200);
		customerCheck.setPrefHeight(300);
		customerCheck.setStyle("-fx-font-size: 16px");
		prices.relocate(900, 150);
		prices.setPrefWidth(100);
		prices.setPrefHeight(300);
		prices.setStyle("-fx-font-size: 16px");
		quitButton.relocate(0, 0);
		quitButton.setStyle("-fx-font-size: 16px");
		itemList.relocate(127, 75);
		tableList.relocate(755, 75);
		addItem.relocate(475, 300);
		addItem.setStyle("-fx-font-size: 16px");
		removeItem.relocate(740, 550);
		removeItem.setStyle("-fx-font-size: 16px");
		finalizeButton.relocate(860, 550);
		finalizeButton.setStyle("-fx-font-size: 16px");
		tableLabel.relocate(700, 80);
		tableLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");
		categoryLabel.relocate(50, 80);
		categoryLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold");

		// add everything to the GUI
		pane.getChildren().addAll(inventory, inventoryP, customerCheck, prices, quitButton, itemList, tableList,
				addItem, removeItem, inventoryItemLabel, inventoryPriceLabel, checkItemLabel, checkPriceLabel,
				subtotalLabel, taxLabel, totalLabel, finalizeButton, tableLabel, categoryLabel);
		Scene scene = new Scene(pane, 1000, 600);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Server GUI");
		stage.setResizable(false);
		stage.show();

		// Listener for inventory. When item in inventory is selected, the
		// corresponding price is also selected
		inventory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			int index = inventory.getSelectionModel().getSelectedIndex();
			inventoryP.getSelectionModel().select(index);
		});

		// Listener for inventoryP. When item in inventory price is selected,
		// the corresponding price is also selected
		inventoryP.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			int index = inventoryP.getSelectionModel().getSelectedIndex();
			inventory.getSelectionModel().select(index);
		});

		// Listener for customerCheck. When item in customerCheck is selected,
		// the corresponding price is also selected
		customerCheck.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			int index = customerCheck.getSelectionModel().getSelectedIndex();
			prices.getSelectionModel().select(index);
		});

		// Listener for prices. When price is selected, the corresponding item
		// is also selected
		prices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			int index = prices.getSelectionModel().getSelectedIndex();
			customerCheck.getSelectionModel().select(index);
		});

		// when "Quit" button is pressed, close window
		quitButton.setOnAction(e -> {
			stage.close();
		});

		// when "Add Item" button is pressed, add item & price from inventory to
		// customers check
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

				// add changes to check
				int partyIndexForCheck = -99; // set to impossible
				for (int i = 1; i < Register.activeParties.size(); i++) {
					if ((Register.activeParties.get(i)
							.getTableNumber() == (int) tableList.getSelectionModel().getSelectedItem())
							&& (partyIndexForCheck == -99)) {
						partyIndexForCheck = i;
					}
				}

				Register.activeParties.get(partyIndexForCheck).getCheck().addItem(inventoryItems.get(index),
						inventoryPrices.get(index));
				updateTotalLabels(partyIndexForCheck);

			} else {
				String selectedPrices = inventoryP.getSelectionModel().getSelectedItem();
				int indexP = inventoryP.getSelectionModel().getSelectedIndex();
				if (selectedPrices != null) {
					inventoryP.getSelectionModel().clearSelection();
					customerCheckItems.add(selectedPrices);
					String selectedName = inventoryPrices.get(indexP);
					if (selectedName != null) {
						inventory.getSelectionModel().clearSelection();
						itemPrices.add(selectedName);
					}

					// add changes to check
					int partyIndexForCheck = -99;
					for (int i = 1; i < Register.activeParties.size(); i++) {
						if ((Register.activeParties.get(i)
								.getTableNumber() == (int) tableList.getSelectionModel().getSelectedItem())
								&& (partyIndexForCheck == -99)) {
							partyIndexForCheck = i;
						}
					}

					Register.activeParties.get(partyIndexForCheck).getCheck().addItem(inventoryItems.get(indexP),
							inventoryPrices.get(indexP));
					updateTotalLabels(partyIndexForCheck);
				}

			}

		});

		// when "Remove Item" button is pressed, removes item and price from
		// customers check
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
				// add changes to check
				int partyIndexForCheck = -99;
				for (int i = 1; i < Register.activeParties.size(); i++) {
					if ((Register.activeParties.get(i)
							.getTableNumber() == (int) tableList.getSelectionModel().getSelectedItem())
							&& (partyIndexForCheck == -99)) {
						partyIndexForCheck = i;
					}
				}

				Register.activeParties.get(partyIndexForCheck).getCheck().deleteItem(inventoryItems.get(index));
				updateTotalLabels(partyIndexForCheck);

			} else {
				String selectedPrices = prices.getSelectionModel().getSelectedItem();
				int indexP = prices.getSelectionModel().getSelectedIndex();
				if (selectedPrices != null) {
					prices.getSelectionModel().clearSelection();
					itemPrices.remove(selectedPrices);
					String selectedName = itemPrices.get(indexP);
					if (selectedName != null) {
						customerCheck.getSelectionModel().clearSelection();
						customerCheckItems.remove(selectedName);
					}
					// add changes to check
					int partyIndexForCheck = -99;
					for (int i = 1; i < Register.activeParties.size(); i++) {
						if ((Register.activeParties.get(i)
								.getTableNumber() == (int) tableList.getSelectionModel().getSelectedItem())
								&& (partyIndexForCheck == -99)) {
							partyIndexForCheck = i;
						}
					}

					Register.activeParties.get(partyIndexForCheck).getCheck().deleteItem(inventoryItems.get(indexP));
					updateTotalLabels(partyIndexForCheck);
				}

			}
		});

		// when user clicks finalize button receipt is printed.
		finalizeButton.setOnAction(e -> {
			int partyIndexForCheck = -99;
			for (int i = 1; i < Register.activeParties.size(); i++) {
				if ((Register.activeParties.get(i)
						.getTableNumber() == (int) tableList.getSelectionModel().getSelectedItem())
						&& (partyIndexForCheck == -99)) {
					partyIndexForCheck = i;
				}
			}

			Register.activeParties.get(partyIndexForCheck).getCheck().finalize();

			// music
			try {
				AudioClip registerSound = new AudioClip("http://sep800.mine.nu/files/sounds/cashregister.wav");
				registerSound.play();
			} catch (Exception ex) {
				// nothing
			}
		});
	}

	/**
	 * updates the subtotal, tax, and total labels for a given party
	 * 
	 * @param partyIndex
	 *            index of the party
	 */
	private static void updateTotalLabels(int partyIndex) {

		DecimalFormat formatter = new DecimalFormat("#0.00");

		subtotalLabel.setText(
				"Subtotal:     $" + formatter.format(Register.activeParties.get(partyIndex).getCheck().getSubtotal()));
		taxLabel.setText("Tax Added:   $" + formatter.format(Register.activeParties.get(partyIndex).getCheck().getTax()
				* Register.activeParties.get(partyIndex).getCheck().getSubtotal()));
		totalLabel.setText(
				"Total:          $" + formatter.format(Register.activeParties.get(partyIndex).getCheck().getTotal()));
	}
}
