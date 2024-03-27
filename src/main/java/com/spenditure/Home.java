package com.spenditure;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;

public class Home extends Application {

    private User user;
    private Database db = new Database();
    private Connection conn = db.connect_to_db();
    private Currency currency = new Currency();
    Map<String, Double> exchangeRates = new TreeMap<>(currency.getExchangeRates());

    public void setUser(User user) {this.user = user;}
    public void setConnection(Connection conn) {this.conn = conn;}

    @Override
    public void start(Stage primaryStage) {

        if (this.user == null) {
            System.out.println("User does not exist");
            this.user = new User("WMKSherwani", "password", "Waleed", "Sherwani");
        }

        // ------------------------------------------------------------------------------------------------------------------

        MenuScene menuScene = new MenuScene();
        menuScene.setUser(this.user);
        menuScene.setConnection(this.conn);
        Scene scene = menuScene.menuBar(primaryStage);

        // Create a StackPane to overlay additional content
        StackPane root = new StackPane();
        root.getChildren().addAll(scene.getRoot());

        // ------------------------------------------------------------------------------------------------------------------

        // Add a vertical line to the StackPane
        Line verticalLine = new Line(0.0f, 200.0f, 0.0f, 925.0f);
        verticalLine.setTranslateX(-400);
        verticalLine.setTranslateY(20);
        verticalLine.setStroke(Color.BLACK);
        root.getChildren().add(verticalLine);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(verticalLine.endYProperty(), verticalLine.getStartY())),
            new KeyFrame(Duration.seconds(1), new KeyValue(verticalLine.endYProperty(), verticalLine.getStartY() + 725.0))
        );
        
        timeline.setCycleCount(1);
        timeline.play();

        // ------------------------------------------------------------------------------------------------------------------

        // Add a vertical line to the StackPane
        Line verticalLine2 = new Line(0.0f, 200.0f, 0.0f, 835.0f);
        verticalLine2.setTranslateX(300);
        verticalLine2.setTranslateY(65);
        verticalLine2.setStroke(Color.BLACK);
        root.getChildren().add(verticalLine2);

        Timeline timeline2 = new Timeline();
        timeline2.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(verticalLine2.endYProperty(), verticalLine2.getStartY())),
            new KeyFrame(Duration.seconds(1), new KeyValue(verticalLine2.endYProperty(), verticalLine2.getStartY() + 635.0))
        );

        timeline2.setCycleCount(1);
        timeline2.play();

        // ------------------------------------------------------------------------------------------------------------------

        // Add a vertical line to the StackPane
        Line verticalLine3 = new Line(0.0f, 200.0f, 0.0f, 450.0f);
        verticalLine3.setTranslateX(-50);
        verticalLine3.setTranslateY(-60);
        verticalLine3.setStroke(Color.BLACK);
        root.getChildren().add(verticalLine3);

        Timeline timeline3 = new Timeline();
        timeline3.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(verticalLine3.endYProperty(), verticalLine3.getStartY())),
            new KeyFrame(Duration.seconds(1), new KeyValue(verticalLine3.endYProperty(), verticalLine3.getStartY() + 250.0))
        );

        timeline3.setCycleCount(1);
        timeline3.play();

        // ------------------------------------------------------------------------------------------------------------------
        
        // Add a horizontal line for Overview
        Line horizontalLine = new Line(0.0f, 200.0f, 1100.0f, 200.0f);
        horizontalLine.setTranslateX(175);
        horizontalLine.setTranslateY(-270);
        horizontalLine.setStroke(Color.BLACK);
        root.getChildren().add(horizontalLine);

        Timeline timeline4 = new Timeline();
        timeline4.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(horizontalLine.endXProperty(), horizontalLine.getStartX())),
            new KeyFrame(Duration.seconds(1), new KeyValue(horizontalLine.endXProperty(), horizontalLine.getStartX() + 1100.0))
        );

        timeline4.setCycleCount(1);
        timeline4.play();

        // ------------------------------------------------------------------------------------------------------------------

        // Add a horizontal line for Average Income
        Line horizontalLine2 = new Line(0.0f, 200.0f, 225.0f, 200.0f);
        horizontalLine2.setTranslateX(-225);
        horizontalLine2.setTranslateY(-185);
        horizontalLine2.setStroke(Color.BLACK);
        root.getChildren().add(horizontalLine2);

        Timeline timeline5 = new Timeline();
        timeline5.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(horizontalLine2.endXProperty(), horizontalLine2.getStartX())),
            new KeyFrame(Duration.seconds(1), new KeyValue(horizontalLine2.endXProperty(), horizontalLine2.getStartX() + 225.0))
        );

        timeline5.setCycleCount(1);
        timeline5.play();

        // ------------------------------------------------------------------------------------------------------------------

        // Add a horizontal line for Average Expenditure
        Line horizontalLine3 = new Line(0.0f, 200.0f, 225.0f, 200.0f);
        horizontalLine3.setTranslateX(125);
        horizontalLine3.setTranslateY(-185);
        horizontalLine3.setStroke(Color.BLACK);
        root.getChildren().add(horizontalLine3);

        Timeline timeline6 = new Timeline();
        timeline6.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(horizontalLine3.endXProperty(), horizontalLine3.getStartX())),
            new KeyFrame(Duration.seconds(1), new KeyValue(horizontalLine3.endXProperty(), horizontalLine3.getStartX() + 225.0))
        );

        timeline6.setCycleCount(1);
        timeline6.play();

        // ------------------------------------------------------------------------------------------------------------------

        // Add a horizontal line for Income VS Expenses
        Line horizontalLine4 = new Line(0.0f, 200.0f, 650.0f, 200.0f);
        horizontalLine4.setTranslateX(-50);
        horizontalLine4.setTranslateY(115);
        horizontalLine4.setStroke(Color.BLACK);
        root.getChildren().add(horizontalLine4);

        Timeline timeline7 = new Timeline();
        timeline7.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(horizontalLine4.endXProperty(), horizontalLine4.getStartX())),
            new KeyFrame(Duration.seconds(1), new KeyValue(horizontalLine4.endXProperty(), horizontalLine4.getStartX() + 600.0))
        );

        timeline7.setCycleCount(1);
        timeline7.play();

        // ------------------------------------------------------------------------------------------------------------------

        // Add a horizontal line for Top Expenses VS Previous Average
        Line horizontalLine5 = new Line(0.0f, 200.0f, 400.0f, 200.0f);
        horizontalLine5.setTranslateX(550);
        horizontalLine5.setTranslateY(-185);
        horizontalLine5.setStroke(Color.BLACK);
        root.getChildren().add(horizontalLine5);

        Timeline timeline8 = new Timeline();
        timeline8.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(horizontalLine5.endXProperty(), horizontalLine5.getStartX())),
            new KeyFrame(Duration.seconds(1), new KeyValue(horizontalLine5.endXProperty(), horizontalLine5.getStartX() + 400.0))
        );

        timeline8.setCycleCount(1);
        timeline8.play();

        // ------------------------------------------------------------------------------------------------------------------

        // Income
        List <Transaction> IncomeTransactions = user.getTransactionsByAmount(0, 1);
        double IncomeAccountBalance = IncomeTransactions.isEmpty() ? 0 : IncomeTransactions.stream().mapToDouble(Transaction::getAmount).average().getAsDouble();
        String IncomeAccountBalanceStr = Currency.formatLargeNumber(IncomeAccountBalance);
        Label IncomeCurrencyAmountLabel = new Label(IncomeAccountBalanceStr);
        IncomeCurrencyAmountLabel.setFont(Font.font("Garamond", FontWeight.BOLD, 50));
        IncomeCurrencyAmountLabel.setTranslateX(120);
        IncomeCurrencyAmountLabel.setTranslateY(-100);

        Label IncomeCurrencyLabel = new Label("USD - United States Dollar");
        IncomeCurrencyLabel.setFont(Font.font("Garamond", 15));
        IncomeCurrencyLabel.setTranslateX(120);
        IncomeCurrencyLabel.setTranslateY(-65);

        Label IncomeTimeLabel = new Label("-");
        IncomeTimeLabel.setFont(Font.font("Garamond", 15));
        IncomeTimeLabel.setTranslateX(120);
        IncomeTimeLabel.setTranslateY(-45);

        List <Transaction> ExpenseTransactions = user.getTransactionsByAmount(0, -1);
        double ExpenseAccountBalance = ExpenseTransactions.isEmpty() ? 0 : ExpenseTransactions.stream().mapToDouble(Transaction::getAmount).average().getAsDouble();
        String ExpenseAccountBalanceStr = Currency.formatLargeNumber(ExpenseAccountBalance);
        Label ExpenseCurrencyAmountLabel = new Label(ExpenseAccountBalanceStr);
        ExpenseCurrencyAmountLabel.setFont(Font.font("Garamond", FontWeight.BOLD, 50));
        ExpenseCurrencyAmountLabel.setTranslateX(-230);
        ExpenseCurrencyAmountLabel.setTranslateY(-100);

        Label ExpenseCurrencyLabel = new Label("USD - United States Dollar");
        ExpenseCurrencyLabel.setFont(Font.font("Garamond", 15));
        ExpenseCurrencyLabel.setTranslateX(-230);
        ExpenseCurrencyLabel.setTranslateY(-65);

        Label ExpenseTimeLabel = new Label("-");
        ExpenseTimeLabel.setFont(Font.font("Garamond", 15));
        ExpenseTimeLabel.setTranslateX(-230);
        ExpenseTimeLabel.setTranslateY(-45);

        StringBuilder labelText = new StringBuilder();

        root.getChildren().addAll(IncomeCurrencyLabel, IncomeCurrencyAmountLabel, IncomeTimeLabel);
        root.getChildren().addAll(ExpenseCurrencyLabel, ExpenseCurrencyAmountLabel, ExpenseTimeLabel);

        // ------------------------------------------------------------------------------------------------------------------

        // Add text to the StackPane
        Text Overview = new Text("Overview");
        Overview.setFont(Font.font("Garamond", FontWeight.BOLD, 50));
        Overview.setTranslateX(-250);
        Overview.setTranslateY(-300);
        root.getChildren().add(Overview);

        // Add text to the StackPane
        Text AverageExpenditure = new Text("Average Expenditure");
        AverageExpenditure.setFont(Font.font("Garamond", 25));
        AverageExpenditure.setTranslateX(-225);
        AverageExpenditure.setTranslateY(-200);
        root.getChildren().add(AverageExpenditure);

        // Add text to the StackPane
        Text AverageIncome = new Text("Average Income");
        AverageIncome.setFont(Font.font("Garamond", 25));
        AverageIncome.setTranslateX(125);
        AverageIncome.setTranslateY(-200);
        root.getChildren().add(AverageIncome);

        // Add text to the StackPane
        Text IncomeVSExpenses = new Text("Income VS Expenses");
        IncomeVSExpenses.setFont(Font.font("Garamond", 25));
        IncomeVSExpenses.setTranslateX(-50);
        IncomeVSExpenses.setTranslateY(100);
        root.getChildren().add(IncomeVSExpenses);

        // Add text to the StackPane
        Text ReminderSection = new Text("Reminder Section");
        ReminderSection.setFont(Font.font("Garamond", 25));
        ReminderSection.setTranslateX(550);
        ReminderSection.setTranslateY(-200);
        root.getChildren().add(ReminderSection);

        // ------------------------------------------------------------------------------------------------------------------

        Text CurrencyHead = new Text("Currency");
        CurrencyHead.setFont(Font.font("Garamond", 25));
        CurrencyHead.setTranslateX(-675);
        CurrencyHead.setTranslateY(-150);
        root.getChildren().add(CurrencyHead);

        Text YearHead = new Text("Year");
        YearHead.setFont(Font.font("Garamond", 25));
        YearHead.setTranslateX(-695);
        YearHead.setTranslateY(-50);
        root.getChildren().add(YearHead);

        Text MonthHead = new Text("Month");
        MonthHead.setFont(Font.font("Garamond", 25));
        MonthHead.setTranslateX(-685);
        MonthHead.setTranslateY(50);
        root.getChildren().add(MonthHead);

        Text LocationHead = new Text("Location");
        LocationHead.setFont(Font.font("Garamond", 25));
        LocationHead.setTranslateX(-675);
        LocationHead.setTranslateY(150);
        root.getChildren().add(LocationHead);

        // ------------------------------------------------------------------------------------------------------------------
        
        ComboBox<String> currencyComboBox = new ComboBox<>();
        TextField currencyFilterField = new TextField();
        currencyFilterField.setPromptText("Add Currency...");

        // Assuming exchangeRates is a Map
        ObservableList<String> currencyOptions = FXCollections.observableArrayList(exchangeRates.keySet());

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

        currencyComboBox.setMaxWidth(227.5);
        currencyComboBox.translateXProperty().set(-575);
        currencyComboBox.translateYProperty().set(-115);

        currencyFilterField.setMaxWidth(205);
        currencyFilterField.translateXProperty().set(-587.5);
        currencyFilterField.translateYProperty().set(-115);

        root.getChildren().add(currencyComboBox);
        root.getChildren().add(currencyFilterField);

        // ------------------------------------------------------------------------------------------------------------------
        
        ComboBox<String> yearComboBox = new ComboBox<>();
        TextField yearFilterField = new TextField();
        yearFilterField.setPromptText("Select Year...");

        // Generate the list of years from 1970 to 2024
        List<Integer> yearsList = IntStream.rangeClosed(1724, 2024).boxed().collect(Collectors.toList());
        yearsList.sort(Comparator.reverseOrder()); // Sort the list in reverse order
        List<String> yearStrings = yearsList.stream().map(Object::toString).collect(Collectors.toList()); // Convert to String
        ObservableList<String> yearOptions = FXCollections.observableArrayList(yearStrings);

        yearComboBox.getItems().addAll(yearOptions);

        // Make the ComboBox editable
        yearComboBox.setEditable(true);

        // Filter items based on typed text
        yearFilterField.textProperty().addListener((observable, oldValue, YearNewValue) -> {
            if (!YearNewValue.isEmpty()) {
                List<String> filteredOptions = yearStrings.stream()
                        .filter(year -> year.toString().startsWith(YearNewValue))
                        .collect(Collectors.toList());
                yearComboBox.getItems().setAll(filteredOptions);
                yearComboBox.show();
            } else {
                yearComboBox.getItems().setAll(yearOptions);
                yearComboBox.hide();
            }
        });

        // Synchronize ComboBox selection with TextField
        yearComboBox.valueProperty().addListener((observable, oldValue, YearNewValue) -> {
            if (YearNewValue != null) {
                yearFilterField.setText(YearNewValue.toString());
            }
        });

        // If double clicked, clear value
        yearComboBox.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                yearComboBox.getSelectionModel().clearSelection();
                yearFilterField.clear();
            }
        });

        yearComboBox.setMaxWidth(227.5);
        yearComboBox.translateXProperty().set(-575);
        yearComboBox.translateYProperty().set(-15);

        yearFilterField.setMaxWidth(205);
        yearFilterField.translateXProperty().set(-587.5);
        yearFilterField.translateYProperty().set(-15);

        root.getChildren().add(yearComboBox);
        root.getChildren().add(yearFilterField);

        // ------------------------------------------------------------------------------------------------------------------

        ComboBox<String> monthComboBox = new ComboBox<>();
        TextField monthFilterField = new TextField();
        monthFilterField.setPromptText("Select Month...");

        monthComboBox.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        
        // Make the ComboBox editable
        monthComboBox.setEditable(true);

        // Filter items based on typed text
        monthFilterField.textProperty().addListener((observable, oldValue, MonthNewValue) -> {
            if (!MonthNewValue.isEmpty()) {
                List<String> filteredOptions = monthComboBox.getItems().stream()
                        .filter(month -> month.toLowerCase().startsWith(MonthNewValue.toLowerCase()))
                        .collect(Collectors.toList());
                monthComboBox.getItems().setAll(filteredOptions);
                monthComboBox.show();
            } else {
                monthComboBox.getItems().setAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
                monthComboBox.hide();
            }
        });

        // Synchronize ComboBox selection with TextField
        monthComboBox.valueProperty().addListener((observable, oldValue, MonthNewValue) -> {
            if (MonthNewValue != null) {
                monthFilterField.setText(MonthNewValue);
            }
        });

        // If double clicked, clear value
        monthComboBox.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                monthComboBox.getSelectionModel().clearSelection();
                monthFilterField.clear();
            }
        });

        monthComboBox.setMaxWidth(227.5);
        monthComboBox.translateXProperty().set(-575);
        monthComboBox.translateYProperty().set(85);

        monthFilterField.setMaxWidth(205);
        monthFilterField.translateXProperty().set(-587.5);
        monthFilterField.translateYProperty().set(85);

        root.getChildren().add(monthComboBox);
        root.getChildren().add(monthFilterField);

        // ------------------------------------------------------------------------------------------------------------------

        ComboBox<String> locationComboBox = new ComboBox<>();
        TextField locationFilterField = new TextField();
        locationFilterField.setPromptText("Add Location...");

        if (this.user != null){
            user.getTransactionsAll().stream()
                .map(Transaction::getLocation)
                .filter(location -> !locationComboBox.getItems().contains(location))
                .forEach(locationComboBox.getItems()::add);

            // Make the ComboBox editable
            locationComboBox.setEditable(true);

            // Filter items based on typed text
            locationFilterField.textProperty().addListener((observable, oldValue, LocationNewValue) -> {
                if (!LocationNewValue.isEmpty()) {
                    List<String> filteredOptions = user.getTransactionsAll().stream()
                            .map(Transaction::getLocation)
                            .filter(location -> location.toLowerCase().startsWith(LocationNewValue.toLowerCase()))
                            .collect(Collectors.toList());
                    locationComboBox.getItems().setAll(filteredOptions);
                    locationComboBox.show();
                } else {
                    locationComboBox.getItems().setAll(user.getTransactionsAll().stream().map(Transaction::getLocation).collect(Collectors.toList()));
                    locationComboBox.hide();
                }
            });

            // Synchronize ComboBox selection with TextField
            locationComboBox.valueProperty().addListener((observable, oldValue, LocationNewValue) -> {
                if (LocationNewValue != null) {
                    locationFilterField.setText(LocationNewValue);
                }
            });
        }

        // If double clicked, clear value
        locationComboBox.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                locationComboBox.getSelectionModel().clearSelection();
                locationFilterField.clear();
            }
        });

        locationComboBox.setMaxWidth(227.5);
        locationComboBox.translateXProperty().set(-575);
        locationComboBox.translateYProperty().set(185);

        locationFilterField.setMaxWidth(205);
        locationFilterField.translateXProperty().set(-587.5);
        locationFilterField.translateYProperty().set(185);

        root.getChildren().add(locationComboBox);
        root.getChildren().add(locationFilterField);

        // ------------------------------------------------------------------------------------------------------------------

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Location");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount");

        // Creating the scatter chart
        ScatterChart<String, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

        // Prepare data series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Transactions");

        // Add data to series
        List<Transaction> filteredTransactions = user.getTransactionsAll();

        for (Transaction transaction : filteredTransactions) {
            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(transaction.getLocation(), transaction.getAmount());
            series.getData().add(dataPoint);
        }

        // Add series to chart
        scatterChart.getData().add(series);
        scatterChart.setLegendVisible(false);
        scatterChart.setAnimated(true);
        scatterChart.setMaxSize(700, 290);
        scatterChart.translateXProperty().set(-50);
        scatterChart.translateYProperty().set(260);

        if (user.getTransactionsAll().isEmpty()) {
            scatterChart.setVisible(false);
        }

        ((NumberAxis) scatterChart.getYAxis()).setForceZeroInRange(true);
        ((CategoryAxis) scatterChart.getXAxis()).setAnimated(false);

        root.getChildren().add(scatterChart);

        // ------------------------------------------------------------------------------------------------------------------

        // Interdependent ComboBoxes
        currencyComboBox.setOnAction(event -> {
            String selectedCurrency = currencyComboBox.getSelectionModel().getSelectedItem();
            double IncomeConvertedBalance = currency.convert("USD - United States Dollar", selectedCurrency, IncomeAccountBalance);
            String IncomeFormattedBalance = Currency.formatLargeNumber(IncomeConvertedBalance);
            double ExpenseConvertedBalance = currency.convert("USD - United States Dollar", selectedCurrency, ExpenseAccountBalance);
            String ExpenseFormattedBalance = Currency.formatLargeNumber(ExpenseConvertedBalance);
            if (selectedCurrency != null && !selectedCurrency.isEmpty()) {
                IncomeCurrencyAmountLabel.setText(IncomeFormattedBalance);
                IncomeCurrencyLabel.setText(selectedCurrency);
                ExpenseCurrencyAmountLabel.setText(ExpenseFormattedBalance);
                ExpenseCurrencyLabel.setText(selectedCurrency);
            }
            else {
                IncomeCurrencyAmountLabel.setText(IncomeAccountBalanceStr);
                IncomeCurrencyLabel.setText("USD - United States Dollar");
                ExpenseCurrencyAmountLabel.setText(ExpenseAccountBalanceStr);
                ExpenseCurrencyLabel.setText("USD - United States Dollar");
            }
            
            updateIncomeTimeLabel(currencyComboBox, yearComboBox, monthComboBox, locationComboBox, IncomeTimeLabel, ExpenseTimeLabel, labelText, scatterChart);
        });

        yearComboBox.setOnAction(event -> {
            updateIncomeTimeLabel(currencyComboBox, yearComboBox, monthComboBox, locationComboBox, IncomeTimeLabel, ExpenseTimeLabel, labelText, scatterChart);
        });

        monthComboBox.setOnAction(event -> {
            updateIncomeTimeLabel(currencyComboBox, yearComboBox, monthComboBox, locationComboBox, IncomeTimeLabel, ExpenseTimeLabel, labelText, scatterChart);
        });

        locationComboBox.setOnAction(event -> {
            updateIncomeTimeLabel(currencyComboBox, yearComboBox, monthComboBox, locationComboBox, IncomeTimeLabel, ExpenseTimeLabel, labelText, scatterChart);
        });

        currencyComboBox.setOnShowing(event -> {
            java.net.URL touchURL = getClass().getResource("/Music/Click.mp3");
            Media sound = new Media(touchURL.toExternalForm());
            MediaPlayer touchMusic = new MediaPlayer(sound);
            touchMusic.setVolume(0.10);
            touchMusic.play();
        });

        yearComboBox.setOnShowing(event -> {
            java.net.URL touchURL = getClass().getResource("/Music/Click.mp3");
            Media sound = new Media(touchURL.toExternalForm());
            MediaPlayer touchMusic = new MediaPlayer(sound);
            touchMusic.setVolume(0.10);
            touchMusic.play();
        });

        monthComboBox.setOnShowing(event -> {
            java.net.URL touchURL = getClass().getResource("/Music/Click.mp3");
            Media sound = new Media(touchURL.toExternalForm());
            MediaPlayer touchMusic = new MediaPlayer(sound);
            touchMusic.setVolume(0.10);
            touchMusic.play();
        });

        locationComboBox.setOnShowing(event -> {
            java.net.URL touchURL = getClass().getResource("/Music/Click.mp3");
            Media sound = new Media(touchURL.toExternalForm());
            MediaPlayer touchMusic = new MediaPlayer(sound);
            touchMusic.setVolume(0.10);
            touchMusic.play();
        });

        // ------------------------------------------------------------------------------------------------------------------

        java.net.URL logoOneUrl = getClass().getResource("/Images/LogoHeader.png");
        Image logo = new Image(logoOneUrl.toExternalForm());
        final ImageView logoView = new ImageView(logo);
        logoView.scaleXProperty().set(0.23);
        logoView.scaleYProperty().set(0.23);
        logoView.setTranslateX(-590);
        logoView.setTranslateY(-265);
        root.getChildren().add(logoView);

        // fade in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), logoView);
        fadeIn.setFromValue(0.75);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);

        // fade out
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), logoView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.75);
        fadeOut.setCycleCount(1);

        fadeIn.setOnFinished(event -> fadeOut.play());
        fadeOut.setOnFinished(event -> fadeIn.play());

        fadeIn.play();

        // ------------------------------------------------------------------------------------------------------------------

        TableView<Reminder> reminderTable = new TableView<>();

        if (user.getRemindersAll().stream().filter(reminder -> !reminder.getIsCompleted()).count() == 0) {
            reminderTable.setVisible(false);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        TableColumn<Reminder, LocalDateTime> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date")); // Add this line
        dateColumn.setCellFactory(column -> {
            return new TableCell<Reminder, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime date, boolean empty) {
                    super.updateItem(date, empty);
        
                    if (date == null || empty)
                        {setText(null);}
                    else 
                        {setText(formatter.format(date));}
                }
            };
        });

        // Create a TableColumn for the reminder
        TableColumn<Reminder, String> reminderColumn = new TableColumn<>("Reminder");
        reminderColumn.setCellValueFactory(new PropertyValueFactory<>("reminder"));        
        dateColumn.prefWidthProperty().bind(reminderTable.widthProperty().multiply(0.2));
        reminderColumn.prefWidthProperty().bind(reminderTable.widthProperty().multiply(0.7));

        // Text wrap
        reminderColumn.setCellFactory(tc -> {
            TableCell<Reminder, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Region.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(reminderColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });

        TableColumn<Reminder, Boolean> completeColumn = new TableColumn<>("");
        completeColumn.setCellValueFactory(new PropertyValueFactory<>("isCompleted"));

        completeColumn.prefWidthProperty().bind(reminderTable.widthProperty().multiply(0.09));

        completeColumn.setCellFactory(tc -> {
            CheckBox checkBox = new CheckBox();
            TableCell<Reminder, Boolean> cell = new TableCell<>() {
                @Override
                protected void updateItem(Boolean isCompleted, boolean empty) {
                    super.updateItem(isCompleted, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        checkBox.setSelected(isCompleted);
                        setGraphic(checkBox);
                    }
                }
            };

            checkBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                if (!wasSelected && isSelected && cell.getIndex() < reminderTable.getItems().size()) {
                    Reminder reminder = reminderTable.getItems().get(cell.getIndex());
                    reminder.setIsCompleted(isSelected);
                    db.update_reminder(conn, user, reminder.getId(), reminder.getIsCompleted());

                    FadeTransition ft = new FadeTransition(Duration.millis(750), cell);
                    ft.setFromValue(1.0);
                    ft.setToValue(0.0);
                    ft.setOnFinished(event -> {
                        List<Reminder> modifiableList = new ArrayList<>(reminderTable.getItems());
                        modifiableList.remove(reminder);
                        reminderTable.setItems(FXCollections.observableArrayList(modifiableList));
                    });
                    ft.play();
                    
                    java.net.URL doneURL = getClass().getResource("/Music/Random_levelup.mp3");
                    Media doneSound = new Media(doneURL.toExternalForm());
                    MediaPlayer doneMusic = new MediaPlayer(doneSound);
                    doneMusic.setVolume(0.15);
                    doneMusic.play();

                    if (user.getRemindersAll().stream().filter(r -> !r.getIsCompleted()).count() == 0) {
                        reminderTable.setVisible(false);
                    }
                }
            });
            
            return cell;
        });

        // Add the columns to the table
        reminderTable.getColumns().add(dateColumn);
        reminderTable.getColumns().add(reminderColumn);
        reminderTable.getColumns().add(completeColumn);
        
        // reminderListView.setPrefSize(400, 425);
        reminderTable.setTranslateX(550);
        reminderTable.setTranslateY(100);
        reminderTable.setMaxWidth(400);
        reminderTable.setMaxHeight(535);

        // Add reminders from the user
        List<Reminder> modifiableList = new ArrayList<>(user.getRemindersAll());
        ObservableList<Reminder> reminders = FXCollections.observableArrayList(modifiableList);
        FilteredList<Reminder> filteredremindersList = new FilteredList<>(reminders, p -> !p.getIsCompleted());
        reminderTable.setItems(filteredremindersList);

        root.getChildren().add(reminderTable);

        // ------------------------------------------------------------------------------------------------------------------


        // ------------------------------------------------------------------------------------------------------------------

        // Set the StackPane as the root node of the scene
        Scene overlayScene = new Scene(root, 1540, 790);

        primaryStage.setScene(overlayScene);
        primaryStage.setTitle("Spenditure");

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());

        primaryStage.resizableProperty().setValue(Boolean.FALSE);

        // Show the window
        primaryStage.show();

        // Center the window initially
        primaryStage.centerOnScreen();

        // ------------------------------------------------------------------------------------------------------------------
    }

    // ------------------------------------------------------------------------------------------------------------------

    private void updateIncomeTimeLabel(ComboBox<String> currencyComboBox, ComboBox<String> yearComboBox, ComboBox<String> monthComboBox, ComboBox<String> locationComboBox, Label IncomeTimeLabel, Label ExpenseTimeLabel, StringBuilder labelText, ScatterChart<String, Number> scatterChart) {
        String selectedYear = yearComboBox.getSelectionModel().getSelectedItem();
        String selectedMonth = monthComboBox.getSelectionModel().getSelectedItem();
        String selectedLocation = locationComboBox.getSelectionModel().getSelectedItem();
        labelText.setLength(0);

        if (selectedYear != null && !selectedYear.isEmpty()) {
            labelText.append(selectedYear);
        }

        if (selectedMonth != null && !selectedMonth.isEmpty()) {
            if (labelText.length() > 0 && selectedYear != null && !selectedYear.isEmpty()) {
                labelText.append(" - ");
            }
            labelText.append(selectedMonth);
        }

        if (selectedLocation != null && !selectedLocation.isEmpty()) {
            if (labelText.length() > 0 && (selectedYear != null && !selectedYear.isEmpty() || selectedMonth != null && !selectedMonth.isEmpty())) {
                labelText.append(" - ");
            }
            labelText.append(selectedLocation);
        }

        IncomeTimeLabel.setText(labelText.toString());
        ExpenseTimeLabel.setText(labelText.toString());
        updateScatterPlot(currencyComboBox, yearComboBox, monthComboBox, locationComboBox, scatterChart);
    }

    private void updateScatterPlot(ComboBox<String> currencyComboBox, ComboBox<String> yearComboBox, ComboBox<String> monthComboBox, ComboBox<String> locationComboBox, ScatterChart<String, Number> scatterChart) {       
        String selectedYear = yearComboBox.getSelectionModel().getSelectedItem();
        String selectedMonth = monthComboBox.getSelectionModel().getSelectedItem();
        String selectedLocation = locationComboBox.getSelectionModel().getSelectedItem();

        Platform.runLater(() -> {
            ((CategoryAxis) scatterChart.getXAxis()).getCategories().clear();
            ((NumberAxis) scatterChart.getYAxis()).setAutoRanging(false);

            List<Transaction> filteredData = user.getTransactionsAll().stream()
                    .filter(transaction -> selectedYear == null || selectedYear.isEmpty() || transaction.getTransactionDate().getYear() == Integer.parseInt(selectedYear))
                    .filter(transaction -> selectedMonth == null || selectedMonth.isEmpty() || transaction.getTransactionDate().getMonth().toString().equalsIgnoreCase(selectedMonth))
                    .filter(transaction -> selectedLocation == null || selectedLocation.isEmpty() || transaction.getLocation().equalsIgnoreCase(selectedLocation))
                    .collect(Collectors.toList());

                    List<String> newCategories = filteredData.stream()
                    .map(Transaction::getLocation)
                    .distinct()
                    .collect(Collectors.toList());
    
            CategoryAxis newXAxis = new CategoryAxis();
            newXAxis.getCategories().setAll(newCategories);
    
            scatterChart.getData().forEach(series -> series.getData().clear());

            filteredData.sort(Comparator.comparing(Transaction::getLocation));

            ((CategoryAxis) scatterChart.getXAxis()).getCategories().setAll(
                filteredData.stream()
                    .map(Transaction::getLocation)
                    .distinct()
                    .collect(Collectors.toList())
            );

            filteredData.forEach(data -> {
                if (!scatterChart.getData().isEmpty()) {
                    XYChart.Series<String, Number> series = (XYChart.Series<String, Number>) scatterChart.getData().get(0);
                    
                    if (currencyComboBox != null) {
                        double convertedAmount = currency.convert("USD - United States Dollar", currencyComboBox.getSelectionModel().getSelectedItem(), data.getAmount());
                        series.getData().add(new Data<>(data.getLocation(), convertedAmount));
                    }
                    else {
                        series.getData().add(new Data<>(data.getLocation(), data.getAmount()));
                    }
                }
            });

            // Calculate the maximum absolute value from your data set
            double maxAbsY = filteredData.stream()
                    .mapToDouble(Transaction::getAmount)
                    .map(Math::abs)
                    .max()
                    .orElse(1);

            // Set the lower and upper bounds of the y-axis
            maxAbsY = currency.convert("USD - United States Dollar", currencyComboBox.getSelectionModel().getSelectedItem(), maxAbsY);
            ((NumberAxis) scatterChart.getYAxis()).setLowerBound(-maxAbsY*1.15);
            ((NumberAxis) scatterChart.getYAxis()).setUpperBound(maxAbsY*1.15);
            
            // Determine the tick unit based on the range
            double tickUnit;
            if (maxAbsY*1.15 > 1000000000000L)  {tickUnit = 100000000000L;}
            else if (maxAbsY*1.15 > 1000000000) {tickUnit = 100000000;}
            else if (maxAbsY*1.15 > 1000000)    {tickUnit = 100000;}
            else                                {tickUnit = 1000;}
            ((NumberAxis) scatterChart.getYAxis()).setTickUnit(tickUnit);

            if (user.getTransactionsAll().isEmpty()) {
                scatterChart.setVisible(false);
            }
        }); 
    }
}

