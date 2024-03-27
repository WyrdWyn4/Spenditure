package com.spenditure;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ComboBox;



public class Purchase extends Application {

    private User user;
    private Database db = new Database();
    private Connection conn = db.connect_to_db();
    private Currency currency = new Currency();

    public void setUser(User user) {
        this.user = user;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void start(Stage primaryStage) {

        if (this.user == null) {
            System.out.println("User does not exist");
            this.user = new User("WMKSherwani", "password", "Waleed", "Sherwani");
        }

        // ------------------------------------------------------------------------------------------------------------------

        // Create your existing scene content
        MenuScene menuScene = new MenuScene();
        menuScene.setUser(this.user);
        menuScene.setConnection(this.conn);
        Scene scene = menuScene.menuBar(primaryStage);

        // Create a StackPane to overlay additional content
        StackPane root = new StackPane();
        root.getChildren().addAll(scene.getRoot()); // Add existing scene content

        // ------------------------------------------------------------------------------------------------------------------

        TableView<Transaction> transactionTable = new TableView<>();

        ObservableList<Transaction> transactionsView = FXCollections
                .observableArrayList(this.user.getTransactionsAll());
        transactionTable.setItems(transactionsView);

        TableColumn<Transaction, UUID> idColumn = new TableColumn<>("Transaction ID");
        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTransactionId()));

        TableColumn<Transaction, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(Double.valueOf(cellData.getValue().getAmount())));

        TableColumn<Transaction, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLocation()));

        // Update the Date column to display only the date part
        TableColumn<Transaction, String> dateColumn = new TableColumn<>("Date");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateColumn.setCellValueFactory(cellData -> {
            LocalDateTime date = cellData.getValue().getTransactionDate();
            String formattedDate = date.format(dateFormatter);
            return new SimpleObjectProperty<>(formattedDate);
        });

        transactionTable.getColumns().addAll(List.of(idColumn, amountColumn, locationColumn, dateColumn));

        transactionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Allow the table to expand horizontally
        transactionTable.prefWidthProperty().bind(root.widthProperty());
        VBox.setVgrow(transactionTable, Priority.ALWAYS);

        // Add a region that will fill the remaining space horizontally
        HBox hbox = new HBox();
        hbox.getChildren().addAll(transactionTable);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(30);
        vbox.getChildren().addAll(transactionTable);
        
        // ----------------------------------------------------------------------------------------------------------------

        Label amountLabel = new Label("Amount:");
        Label locationLabel = new Label("Location:");

        // Text fields for user input
        TextField amountField = new TextField();
        amountField.setPromptText("Add Amount ...");

        TextField locationField = new TextField();
        locationField.setPromptText("Add Location ...");


        Label dateLabel = new Label("Date:");
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select Date ...");
        
        ComboBox<String> currencyComboBox = new ComboBox<>();
        TextField currencyFilterField = new TextField();
        currencyFilterField.setPromptText("Add Currency...");
        currencyFilterField.setPrefWidth(260);
        currencyFilterField.translateXProperty().setValue(-283);

        // Assuming exchangeRates is a Map
        ObservableList<String> currencyOptions = FXCollections.observableArrayList(currency.getExchangeRates().keySet());

        // Sort the currencyOptions list in ascending order
        Collections.sort(currencyOptions);

        // Add items to the ComboBox
        currencyComboBox.getItems().addAll(currencyOptions);

        // Make the ComboBox editable
        currencyComboBox.setEditable(true);

        // Filter items based on typed text
        currencyFilterField.textProperty().addListener((observable, oldValue, CurrencyNewValue) -> {
            if (!CurrencyNewValue.isEmpty()) {
                List<String> filteredOptions = currencyOptions.stream()
                        .filter(option -> option.toLowerCase().startsWith(CurrencyNewValue.toLowerCase()))
                        .collect(Collectors.toList());
                currencyComboBox.getItems().setAll(filteredOptions);
                currencyComboBox.show();
            } else {
                currencyComboBox.getItems().setAll(currencyOptions);
                currencyComboBox.hide();
            }
        });

        // Synchronize ComboBox selection with TextField
        currencyComboBox.valueProperty().addListener((observable, oldValue, CurrencyNewValue) -> {
            if (CurrencyNewValue != null) {
                currencyFilterField.setText(CurrencyNewValue);
            }
        });

        // If double clicked, clear value
        currencyComboBox.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                currencyComboBox.getSelectionModel().clearSelection();
                currencyFilterField.clear();
            }
        });

        // Add buttons for adding and deleting transactions
        Button addButton = new Button("Add Transaction");
        Button deleteButton = new Button("Delete Transaction");
        
        // Add event handlers for buttons
        addButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String location = locationField.getText();
                LocalDate date = datePicker.getValue();
                String selectedCurrency = currencyComboBox.getValue();
                
                if (date == null) {
                    System.err.println("No date selected");
                    return; // Exit the handler
                }
                
                LocalDateTime dateTime = date.atStartOfDay();
                Currency currency = new Currency();
                
                double convertedAmount = currency.convert(selectedCurrency, "USD - United States Dollar", amount);
                convertedAmount = Double.parseDouble(String.format("%.3f", convertedAmount));

                // Create a new transaction and add it to the user's list of transactions
                Transaction newTransaction = new Transaction(convertedAmount, location, dateTime);
                user.addTransaction(newTransaction);
                db.insert_transaction(conn, user, newTransaction);

                // Update the table view
                transactionsView.add(newTransaction);
                
                // Clear input fields
                amountField.clear();
                locationField.clear();
                datePicker.setValue(null);
                currencyComboBox.setValue(null);

                java.net.URL deleteURL = getClass().getResource("/Music/Random_levelup.mp3");
                Media deleteSound = new Media(deleteURL.toExternalForm());
                MediaPlayer addMusic = new MediaPlayer(deleteSound);
                addMusic.setVolume(0.15);
                addMusic.play();
                
            } catch (NumberFormatException | DateTimeParseException ex) {
                amountField.clear();
                locationField.clear();
                System.err.println("Invalid input format");
            }
        });

        deleteButton.setOnAction(e -> {
            Transaction selectedTransaction = transactionTable.getSelectionModel().getSelectedItem();
            if (selectedTransaction != null) {
                // Remove the selected transaction from the user's list of transactions
                user.removeTransaction(selectedTransaction.getTransactionId());
                db.delete_row_by_transactionId(conn, user, selectedTransaction.getTransactionId());
                // Remove from the table view
                transactionsView.remove(selectedTransaction);

                java.net.URL deleteURL = getClass().getResource("/Music/Random_break.mp3");
                Media deleteSound = new Media(deleteURL.toExternalForm());
                MediaPlayer deleteMusic = new MediaPlayer(deleteSound);
                deleteMusic.setVolume(0.15);
                deleteMusic.play();
            } else {
                System.err.println("No transaction selected");
            }
        });

        HBox CurrencyBox = new HBox(currencyComboBox, currencyFilterField);

        locationLabel.translateXProperty().setValue(-255);
        locationField.translateXProperty().setValue(-255);
        dateLabel.translateXProperty().setValue(-255);
        datePicker.translateXProperty().setValue(-255);

        Button GenerateReport = new Button("Generate Report");
        GenerateReport.setOnAction(e -> {
            ReportGenerator reportGenerator = new ReportGenerator();
            reportGenerator.generateReport(user);
        });

        GenerateReport.translateXProperty().setValue(0);

        Button ScanReceipt = new Button("Scan Receipt");
        ScanReceipt.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Receipt File");

            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                try {
                    ReceiptReader reader = new ReceiptReader();
                    ReceiptParser parser = new ReceiptParser();
                    ProductCategorizer categorizer = new ProductCategorizer();

                    String text = reader.readReceipt(file.toString());
                    Receipt receipt = parser.parseReceipt(text);

                    for (Item item : receipt.getItems()) {
                        String category = categorizer.categorize(item.getName());
                        item.setCategory(category);
                    }

                    // Print the receipt
                    System.out.println("Receipt:");
                    for (Item item : receipt.getItems()) {
                        System.out.println("Item: " + item.getName());
                        System.out.println("Price: " + item.getPrice());
                        System.out.println("Currency: " + item.getCurrency());
                        System.out.println("Category: " + item.getCategory());
                        System.out.println();
                    }
                    System.out.println("Total: " + receipt.getTotal());

                    double amount = receipt.getTotal();
                    Transaction newTransaction = new Transaction(amount, "Dundas", LocalDateTime.now());
                    user.addTransaction(newTransaction);
                    db.insert_transaction(conn, user, newTransaction);
                    
                } catch (IOException ex) {
                    System.err.println("Error reading file: " + ex.getMessage());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        ScanReceipt.translateXProperty().setValue(0);

        HBox inputFields = new HBox(10);
        inputFields.getChildren().addAll(amountLabel, amountField, CurrencyBox, locationLabel, locationField, dateLabel, datePicker, GenerateReport, ScanReceipt);
        inputFields.translateXProperty().setValue(100);
        inputFields.translateYProperty().setValue(-20);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, deleteButton);
        buttonBox.translateXProperty().setValue(100);
        buttonBox.translateYProperty().setValue(-40);

        vbox.getChildren().addAll(inputFields, buttonBox);
        vbox.translateYProperty().set(33);
        root.getChildren().add(vbox);

        // ------------------------------------------------------------------------------------------------------------------

        Scene overlayScene = new Scene(root, 1540, 790); 

        primaryStage.setScene(overlayScene);
        primaryStage.setTitle("Spenditure");

        // Set a listener to ensure the window is centered upon resizing
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());

        primaryStage.resizableProperty().setValue(Boolean.FALSE);

        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
