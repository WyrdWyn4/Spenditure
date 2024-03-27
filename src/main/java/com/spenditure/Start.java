package com.spenditure;

import java.sql.Connection;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Start extends Application {

    public User user;
    private Database db = new Database();
    private Connection conn = db.connect_to_db();

    public void setUser(User user) {
        this.user = user;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void start(Stage primaryStage) {

        // Create the user table if it's not already created
        db.createUserTable(conn);
        db.createTransactionsTable(conn);
        db.createRemindersTable(conn);

        java.net.URL logoOneUrl = getClass().getResource("/Images/Logo.png");
        Image logoOne = new Image(logoOneUrl.toExternalForm());
        primaryStage.getIcons().add(logoOne);

        // Add the above as a resource

        primaryStage.setTitle("Spenditure");
        primaryStage.setScene(signInScene(primaryStage));

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), primaryStage.getScene().getRoot());
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

        // Set a listener to ensure the window is centered upon resizing
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());
        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());

        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public static Scene signUpScene(Stage primaryStage) {
        java.net.URL logoOneUrl = Start.class.getResource("/Images/Logo.png");
        Image logo = new Image(logoOneUrl.toExternalForm());
        
        final ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(100);
        logoView.setFitWidth(100);

        Database db = new Database();
        Connection conn = db.connect_to_db();

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);

        Label userLabel = new Label("Username");
        final TextField userTextField = new TextField();

        Label emailLabel = new Label("Email");
        final TextField emailTextField = new TextField();

        Label passwordLabel = new Label("Password");
        final PasswordField passwordField = new PasswordField();

        Label phoneLabel = new Label("Phone Number");
        final TextField phoneTextField = new TextField();

        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {
            // Handle sign up
            String username = userTextField.getText();
            String email = emailTextField.getText();
            String password = passwordField.getText();
            String phoneNumber = phoneTextField.getText();
            System.out.println("New User: " + username);
            System.out.println("Email: " + email);
            System.out.println("New Password: " + password);
            System.out.println("Phone Number: " + phoneNumber);
            boolean UserPresent = db.user_exists(conn, username);

            if (username.isEmpty() || username == "user" || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || UserPresent) {
                System.out.println("Invalid username or password");
                if (UserPresent) {
                    errorLabel.setText("Username already exists");
                } else {
                    errorLabel.setText("Invalid username or password");
                }
                errorLabel.setVisible(true);

                // Create TranslateTransition for shaking
                TranslateTransition shakeTransition1 = new TranslateTransition(Duration.seconds(0.05), logoView);
                shakeTransition1.setFromX(0);
                shakeTransition1.setToX(10);
                shakeTransition1.setCycleCount(1);

                TranslateTransition shakeTransition2 = new TranslateTransition(Duration.seconds(0.05), logoView);
                shakeTransition2.setFromX(0);
                shakeTransition2.setToX(-10);
                shakeTransition2.setCycleCount(1);

                // Chain the transitions to create a shaking effect
                shakeTransition1.setOnFinished(event -> shakeTransition2.play());
                shakeTransition2.setOnFinished(event -> shakeTransition1.play());

                // Play the first shaking transition
                shakeTransition1.play();

                // Gradually reduce the duration of the shaking transitions until they stop
                Timeline slowDown = new Timeline(
                        new KeyFrame(Duration.seconds(0.5), event -> {
                            // Reduce duration by 20% each time
                            shakeTransition1.setFromX(shakeTransition1.getFromX() * 0.8);
                            shakeTransition1.setToX(shakeTransition1.getToX() * 0.8);
                            shakeTransition2.setFromX(shakeTransition2.getFromX() * 0.8);
                            shakeTransition2.setToX(shakeTransition2.getToX() * 0.8);
                        }));
                slowDown.setCycleCount(2); // Adjust the number of times to slow down to desired effect
                slowDown.setOnFinished(event -> {
                    shakeTransition1.stop();
                    shakeTransition2.stop();
                });
                slowDown.play();

                java.net.URL creeper = Start.class.getResource("/Music/Fuse.mp3");
                Media creepersound = new Media(creeper.toExternalForm());
                MediaPlayer shock = new MediaPlayer(creepersound);
                shock.setVolume(0.15);
                shock.play();

            } else {
                User user = new User(username, email, phoneNumber, password);
                db.insert_user(conn, user);
                // db.createTable(conn, user);

                Home home = new Home();
                home.setUser(user);
                home.start(primaryStage);
            }
        });

        Button signInButton = new Button("Sign In");
        signInButton.setOnAction(e -> {
            primaryStage.setScene(signInScene(primaryStage));
            primaryStage.setTitle("Spenditure");

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000),
                    primaryStage.getScene().getRoot()); // Adjust duration as needed
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);
            fadeTransition.play();

            // Set a listener to ensure the window is centered upon resizing
            primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());
            primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());

            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.show();
            primaryStage.centerOnScreen();
        });

        HBox buttonBox = new HBox(signInButton, signUpButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(160);

        VBox vbox = new VBox(logoView, userLabel, userTextField, emailLabel, emailTextField, passwordLabel,
                passwordField, phoneLabel, phoneTextField, buttonBox, errorLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        return new Scene(vbox, 300, 450);
    }

    public static Scene signInScene(Stage primaryStage) {
        java.net.URL logoOneUrl = Start.class.getResource("/Images/Logo.png");
        Image logo = new Image(logoOneUrl.toExternalForm());
        
        final ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(100);
        logoView.setFitWidth(100);

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);

        Label userLabel = new Label("Username");
        final TextField userTextField = new TextField();

        Label passwordLabel = new Label("Password");
        final PasswordField passwordField = new PasswordField();

        Button signInButton = new Button("Sign In");
        signInButton.setOnAction(e -> {
            // Handle sign in
            String username = userTextField.getText();
            String password = passwordField.getText();
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            Database db = new Database();
            Connection conn = db.connect_to_db();
            boolean UserPresent = db.check_user(conn, username, password);

            if (!UserPresent) {
                System.out.println("Invalid username or password");
                errorLabel.setText("Invalid username or password");
                errorLabel.setVisible(true);

                // Create TranslateTransition for shaking
                TranslateTransition shakeTransition1 = new TranslateTransition(Duration.seconds(0.05), logoView);
                shakeTransition1.setFromX(0);
                shakeTransition1.setToX(10);
                shakeTransition1.setCycleCount(1);

                TranslateTransition shakeTransition2 = new TranslateTransition(Duration.seconds(0.05), logoView);
                shakeTransition2.setFromX(0);
                shakeTransition2.setToX(-10);
                shakeTransition2.setCycleCount(1);

                // Chain the transitions to create a shaking effect
                shakeTransition1.setOnFinished(event -> shakeTransition2.play());
                shakeTransition2.setOnFinished(event -> shakeTransition1.play());

                // Play the first shaking transition
                shakeTransition1.play();

                // Gradually reduce the duration of the shaking transitions until they stop
                Timeline slowDown = new Timeline(
                        new KeyFrame(Duration.seconds(1), event -> {
                            // Reduce duration by 20% each time
                            shakeTransition1.setFromX(shakeTransition1.getFromX() * 0.8);
                            shakeTransition1.setToX(shakeTransition1.getToX() * 0.8);
                            shakeTransition2.setFromX(shakeTransition2.getFromX() * 0.8);
                            shakeTransition2.setToX(shakeTransition2.getToX() * 0.8);
                        }));
                slowDown.setCycleCount(2); // Adjust the number of times to slow down to desired effect
                slowDown.setOnFinished(event -> {
                    shakeTransition1.stop();
                    shakeTransition2.stop();
                });
                slowDown.play();

                java.net.URL creeper = Start.class.getResource("/Music/Fuse.mp3");
                Media creepersound = new Media(creeper.toExternalForm());
                MediaPlayer shock = new MediaPlayer(creepersound);
                shock.setVolume(0.15);
                shock.play();

            } else {

                java.net.URL enterHome = Start.class.getResource("/Music/Chest_open.mp3");
                Media sound = new Media(enterHome.toExternalForm());
                MediaPlayer enterHomeMusic = new MediaPlayer(sound);
                enterHomeMusic.setVolume(0.15);

                User user = db.retrieve_user(conn, username, password);
                Home home = new Home();
                home.setUser(user);
                home.start(primaryStage);
            }
        });

        Button signUpButton = new Button("Sign Up");
        signUpButton.setOnAction(e -> {

            primaryStage.setScene(signUpScene(primaryStage));
            primaryStage.setTitle("Spenditure");

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000),
                    primaryStage.getScene().getRoot()); // Adjust duration as needed
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);
            fadeTransition.play();

            // Set a listener to ensure the window is centered upon resizing
            primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());
            primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> primaryStage.centerOnScreen());

            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.show();
            primaryStage.centerOnScreen();
        });

        HBox buttonBox = new HBox(signInButton, signUpButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(160);

        VBox vbox = new VBox(logoView, userLabel, userTextField, passwordLabel, passwordField, buttonBox, errorLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        return new Scene(vbox, 300, 350);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
