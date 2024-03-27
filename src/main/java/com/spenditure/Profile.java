package com.spenditure;

import javafx.event.EventHandler;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class Profile extends Application {

    private User user;
    private Connection conn;
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

        // Create your existing scene content
        MenuScene menuScene = new MenuScene();
        menuScene.setUser(this.user);
        menuScene.setConnection(this.conn);
        Scene scene = menuScene.menuBar(primaryStage);

        // Create a StackPane to overlay additional content
        StackPane root = new StackPane();
        root.getChildren().addAll(scene.getRoot()); // Add existing scene content

        // ------------------------------------------------------------------------------------------------------------------

        java.net.URL logoOneUrl = getClass().getResource("/Images/GoldCover.png");
        Image coverImage = new Image(logoOneUrl.toExternalForm());
        ImageView coverImageView = new ImageView(coverImage);
        coverImageView.setFitWidth(1500);
        coverImageView.setFitHeight(200);
        coverImageView.setSmooth(true);

        java.net.URL logoTwoUrl = getClass().getResource("/Images/Logo.png");
        Image profileImage = new Image(logoTwoUrl.toExternalForm());
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitWidth(150);
        profileImageView.setFitHeight(150);
        profileImageView.setPreserveRatio(true);
        profileImageView.setSmooth(true);
        profileImageView.translateXProperty().set(100);
        profileImageView.translateYProperty().set(-75);

        // Profile information
        Text name = new Text(user.getUsername());
        name.setFont(Font.font("Garamond", FontWeight.BOLD, 50));
        name.translateXProperty().set(125);
        name.translateYProperty().set(0);

        Text location = new Text();
        List<String> possiblelocations = new ArrayList<String>();
        for (Transaction t : user.getTransactionsAll()) {
            if (!possiblelocations.contains(t.getLocation())){
                possiblelocations.add(t.getLocation());
            }
        }

        if (possiblelocations.size() == 0) {
            possiblelocations = List.of("Lahore", "Karachi", "Islamabad", "Peshawar", "Quetta");
        }

        simulateTyping(location, possiblelocations);
        location.setFont(Font.font("Garamond", 25));
        location.translateXProperty().set(120);
        location.translateYProperty().set(-75);

        Text about = new Text("About me: I love Money $$$");
        about.setFont(Font.font("Garamond", 15));
        // about.setWrappingWidth(300);
        about.translateXProperty().set(120);
        about.translateYProperty().set(-75);

        HBox hbox = new HBox(profileImageView, name);

        // Create profile layout
        VBox profileBox = new VBox(10);
        profileBox.getChildren().addAll(coverImageView, hbox, location, about);
        profileBox.setPadding(new Insets(20, 20, 20, 20));
        // profileBox.setStyle("-fx-background-color: #f0f0f0;");
        profileBox.translateYProperty().set(20);

        root.getChildren().add(profileBox);

        // ------------------------------------------------------------------------------------------------------------------
        // Calculate analytics
        int totalTransactions = user.getTransactionsAll().size();
        double totalAmount = Math.round(user.getTransactionsAll().stream().mapToDouble(Transaction::getAmount).sum() * 100.0) / 100.0;
        double averageAmount = totalTransactions > 0 ? Math.round((totalAmount / totalTransactions) * 100.0) / 100.0 : 0;
        double maxAmount = Math.round(user.getTransactionsAll().stream().mapToDouble(Transaction::getAmount).max().orElse(0) * 100.0) / 100.0;
        double minAmount = Math.round(user.getTransactionsAll().stream().mapToDouble(Transaction::getAmount).min().orElse(0) * 100.0) / 100.0;
        double totalIncome = Math.round(user.getTransactionsAll().stream().filter(t -> t.getAmount() > 0).mapToDouble(Transaction::getAmount).sum() * 100.0) / 100.0;
        double totalExpenses = Math.round(user.getTransactionsAll().stream().filter(t -> t.getAmount() < 0).mapToDouble(Transaction::getAmount).sum() * 100.0) / 100.0;
        long uniqueLocationsCount = user.getTransactionsAll().stream().map(Transaction::getLocation).distinct().count();
        Map<String, Long> locationFrequencyMap = user.getTransactionsAll().stream().collect(Collectors.groupingBy(Transaction::getLocation, Collectors.counting()));
        String mostFrequentLocation = locationFrequencyMap.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("N/A");

        // Create analytics labels
        // Total Transactions
        TextFlow totalTransactionsFlow = new TextFlow();
        Text totalTransactionsBold = new Text("Total Transactions: ");
        totalTransactionsBold.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Text totalTransactionsItalic = new Text("\t\t\t\t" + totalTransactions);
        totalTransactionsItalic.setFont(Font.font("Garamond", FontPosture.ITALIC, 20));
        totalTransactionsFlow.getChildren().addAll(totalTransactionsBold, totalTransactionsItalic);
        totalTransactionsFlow.setTranslateX(120);
        totalTransactionsFlow.setTranslateY(-55);

        // Total Amount
        TextFlow totalAmountFlow = new TextFlow();
        Text totalAmountBold = new Text("Total Amount: ");
        totalAmountBold.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Text totalAmountValue = new Text(totalAmount < 0 ? "\t\t\t\t\t-$" + totalAmount : "\t\t\t\t\t$" + totalAmount);
        totalAmountValue.setFont(Font.font("Garamond", FontPosture.ITALIC, 20));
        totalAmountFlow.getChildren().addAll(totalAmountBold, totalAmountValue);
        totalAmountFlow.setTranslateX(120);
        totalAmountFlow.setTranslateY(-55);

        // Average Amount per Transaction
        TextFlow averageAmountFlow = new TextFlow();
        Text averageAmountBold = new Text("Average Amount per Transaction: ");
        averageAmountBold.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Text averageAmountValue = new Text(averageAmount < 0 ? "\t-$" + averageAmount : "\t$" + averageAmount);
        averageAmountValue.setFont(Font.font("Garamond", FontPosture.ITALIC, 20));
        averageAmountFlow.getChildren().addAll(averageAmountBold, averageAmountValue);
        averageAmountFlow.setTranslateX(120);
        averageAmountFlow.setTranslateY(-55);

        // Maximum Transaction Amount
        TextFlow maxAmountFlow = new TextFlow();
        Text maxAmountBold = new Text("Maximum Transaction Amount: ");
        maxAmountBold.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Text maxAmountValue = new Text(maxAmount < 0 ? "\t\t-$" + maxAmount : "\t\t$" + maxAmount);
        maxAmountValue.setFont(Font.font("Garamond", FontPosture.ITALIC, 20));
        maxAmountFlow.getChildren().addAll(maxAmountBold, maxAmountValue);
        maxAmountFlow.setTranslateX(120);
        maxAmountFlow.setTranslateY(-55);

        // Minimum Transaction Amount
        TextFlow minAmountFlow = new TextFlow();
        Text minAmountBold = new Text("Minimum Transaction Amount: ");
        minAmountBold.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Text minAmountValue = new Text(minAmount < 0 ? "\t\t-$" + minAmount : "\t\t$" + minAmount);
        minAmountValue.setFont(Font.font("Garamond", FontPosture.ITALIC, 20));
        minAmountFlow.getChildren().addAll(minAmountBold, minAmountValue);
        minAmountFlow.setTranslateX(120);
        minAmountFlow.setTranslateY(-55);

        // Total Income
        TextFlow totalIncomeFlow = new TextFlow();
        Text totalIncomeBold = new Text("Total Income: ");
        totalIncomeBold.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Text totalIncomeValue = new Text(totalIncome < 0 ? "\t\t\t\t\t-$" + totalIncome : "\t\t\t\t\t$" + totalIncome);
        totalIncomeValue.setFont(Font.font("Garamond", FontPosture.ITALIC, 20));
        totalIncomeFlow.getChildren().addAll(totalIncomeBold, totalIncomeValue);
        totalIncomeFlow.setTranslateX(120);
        totalIncomeFlow.setTranslateY(-55);

        // Total Expenses
        TextFlow totalExpensesFlow = new TextFlow();
        Text totalExpensesBold = new Text("Total Expenses: ");
        totalExpensesBold.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Text totalExpensesValue = new Text("\t\t\t\t\t$" + Math.abs(totalExpenses));
        totalExpensesValue.setFont(Font.font("Garamond", FontPosture.ITALIC, 20));
        totalExpensesFlow.getChildren().addAll(totalExpensesBold, totalExpensesValue);
        totalExpensesFlow.setTranslateX(120);
        totalExpensesFlow.setTranslateY(-55);

        // Unique Locations
        TextFlow uniqueLocationsFlow = new TextFlow();
        Text uniqueLocationsBold = new Text("Unique Locations: ");
        uniqueLocationsBold.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Text uniqueLocationsValue = new Text("\t\t\t\t" + uniqueLocationsCount);
        uniqueLocationsValue.setFont(Font.font("Garamond", FontPosture.ITALIC, 20));
        uniqueLocationsFlow.getChildren().addAll(uniqueLocationsBold, uniqueLocationsValue);
        uniqueLocationsFlow.setTranslateX(120);
        uniqueLocationsFlow.setTranslateY(-55);

        // Most Frequent Location
        TextFlow mostFrequentLocationFlow = new TextFlow();
        Text mostFrequentLocationBold = new Text("Most Frequent Location: ");
        mostFrequentLocationBold.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
        Text mostFrequentLocationValue = new Text("\t\t\t" + mostFrequentLocation);
        mostFrequentLocationValue.setFont(Font.font("Garamond", FontPosture.ITALIC, 20));
        mostFrequentLocationFlow.getChildren().addAll(mostFrequentLocationBold, mostFrequentLocationValue);
        mostFrequentLocationFlow.setTranslateX(120);
        mostFrequentLocationFlow.setTranslateY(-55);

        // Add all labels to the profileBox
        profileBox.getChildren().addAll(totalTransactionsFlow, totalAmountFlow, averageAmountFlow,
                maxAmountFlow, minAmountFlow, totalIncomeFlow, totalExpensesFlow,
                uniqueLocationsFlow, mostFrequentLocationFlow);
        // ------------------------------------------------------------------------------------------------------------------

        // Set the StackPane as the root node of the scene
        Scene overlayScene = new Scene(root, 1540, 790);

        primaryStage.setScene(overlayScene);
        primaryStage.setTitle("Spenditure");

        // Set a listener to ensure the window is centered upon resizing
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());

        primaryStage.resizableProperty().setValue(Boolean.FALSE);

        // Show the window
        primaryStage.show();

        // Center the window initially
        primaryStage.centerOnScreen();
    
    }
    
    private void simulateTyping(Text location, List<String> hardpossibleLocations) {
        StringBuilder currentLocation = new StringBuilder();
        Timeline timeline = new Timeline();
        PauseTransition pause = new PauseTransition(Duration.seconds(1)); // Define a pause of 0.25 seconds
        
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(location.textProperty(), "\t\t\t\t\t")),
            new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
                int index = 0;
                boolean backspacing = false;
    
                @Override
                public void handle(ActionEvent event) {
                    if (!backspacing) {
                        if (currentLocation.length() < hardpossibleLocations.get(index).length()) {
                            currentLocation.append(hardpossibleLocations.get(index).charAt(currentLocation.length()));
                            location.setText(currentLocation.toString());
                        } else {
                            backspacing = true;
                            pause.play(); // Start the pause after typing a character
                        }
                    } else {
                        if (currentLocation.length() > 0) {
                            currentLocation.deleteCharAt(currentLocation.length() - 1);
                            location.setText(currentLocation.toString());
                        } else {
                            backspacing = false;
                            index = (index + 1) % hardpossibleLocations.size(); // Change index to next location
                        }
                    }
                }
            }),
            new KeyFrame(Duration.millis(100))
        );
    
        pause.setOnFinished(e -> timeline.playFromStart()); // Restart typing after the pause finishes
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
}