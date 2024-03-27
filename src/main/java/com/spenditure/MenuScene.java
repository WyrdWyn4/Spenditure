package com.spenditure;

import java.sql.Connection;
import java.time.LocalDateTime;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuScene {

    private User user;
    private Database db = new Database();
    private Connection conn = db.connect_to_db();

    public void setUser(User user) {
        this.user = user;
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public Scene menuBar(Stage primaryStage) {
        if (this.user == null) {
            System.out.println("User does not exist");
            this.user = new User("WMKSherwani", "password", "Waleed", "Sherwani");

            // Add transactions
            Transaction t1 = new Transaction(1000, "Lahore", LocalDateTime.now());
            Transaction t2 = new Transaction(500, "Karachi", LocalDateTime.now());
            Transaction t3 = new Transaction(2000, "Islamabad", LocalDateTime.now());
            Transaction t4 = new Transaction(1000, "Peshawar", LocalDateTime.now());
            Transaction t5 = new Transaction(500, "Quetta", LocalDateTime.now());
            this.user.addTransaction(t1);
            this.user.addTransaction(t2);
            this.user.addTransaction(t3);
            this.user.addTransaction(t4);
            this.user.addTransaction(t5);
        }
        
        BorderPane root = new BorderPane();

        java.net.URL logoOneUrl = getClass().getResource("/Music/Chest_open.mp3");
        Media sound = new Media(logoOneUrl.toExternalForm());
        MediaPlayer openBarMusic = new MediaPlayer(sound);
        openBarMusic.setVolume(0.15);

        java.net.URL logoTwoUrl = getClass().getResource("/Music/Chest_close2.mp3");
        Media sound2 = new Media(logoTwoUrl.toExternalForm());
        MediaPlayer closeBarMusic = new MediaPlayer(sound2);
        closeBarMusic.setVolume(0.15);
        
        // Create MenuBar
        MenuBar menuBar = new MenuBar();

        // Home Button
        Menu HomeMenu = new Menu();
        Button Homebutton = new Button("Home");
        Homebutton.setFocusTraversable(false);
        Homebutton.setStyle("-fx-background-color: transparent;");
        HomeMenu.setGraphic(Homebutton);
        Homebutton.setOnAction(event -> {
            Home home = new Home();
            home.setUser(this.user);
            home.setConnection(this.conn);
            home.start(primaryStage);
            closeBarMusic.play();
        });

        // Profile Button
        Menu ProfileMenu = new Menu();
        Button ProfileButton = new Button("Profile");
        ProfileButton.setFocusTraversable(false);
        ProfileButton.setStyle("-fx-background-color: transparent;");
        ProfileMenu.setGraphic(ProfileButton);
        ProfileButton.setOnAction(event -> {
            Profile Profile = new Profile();
            Profile.setUser(this.user);
            Profile.setConnection(this.conn);
            Profile.start(primaryStage);
            openBarMusic.play();
        });

        // Purchase Button
        Menu PurchaseMenu = new Menu();
        Button PurchaseButton = new Button("Transactions");
        PurchaseButton.setFocusTraversable(false);
        PurchaseButton.setStyle("-fx-background-color: transparent;");
        PurchaseMenu.setGraphic(PurchaseButton);
        PurchaseButton.setOnAction(event -> {
            Purchase Purchase = new Purchase();
            Purchase.setUser(this.user);
            Purchase.setConnection(this.conn);
            Purchase.start(primaryStage);
            openBarMusic.play();
        });

        // Upcoming Button
        Menu upcomingMenu = new Menu();
        Button upcomingButton = new Button("Upcoming");
        upcomingButton.setFocusTraversable(false);
        upcomingButton.setStyle("-fx-background-color: transparent;");
        upcomingMenu.setGraphic(upcomingButton);
        upcomingButton.setOnAction(event -> {
            Upcoming upcoming = new Upcoming();
            upcoming.setUser(this.user);
            upcoming.setConnection(this.conn);
            upcoming.start(primaryStage);
            openBarMusic.play();
        });

        Menu Space = new Menu("                                                                                                                                                                                                                                                                                                                                               ");
        Space.setDisable(true);

        // Log Out Button
        Menu LogOut = new Menu();
        Button LogOutButton = new Button("Log Out");
        LogOutButton.setFocusTraversable(false);
        LogOutButton.setStyle("-fx-background-color: transparent;");
        LogOut.setGraphic(LogOutButton);
        LogOutButton.setOnAction(event -> {
            Start start = new Start();
            start.setConnection(this.conn);
            start.start(primaryStage);
            openBarMusic.play();
        });
        
        menuBar.getMenus().addAll(HomeMenu, ProfileMenu, PurchaseMenu, upcomingMenu, Space, LogOut);
        root.setTop(menuBar);
        
        Scene scene = new Scene(root, 1540, 790);
        return scene;
    }
}
