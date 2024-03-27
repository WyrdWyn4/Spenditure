package com.spenditure;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Calendar;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Upcoming extends Application {

    private User user;
    private Database db = new Database();
    private Connection conn = db.connect_to_db();
    private YearMonth currentYearMonth;
    private GridPane calendarGrid;

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
            this.user = new User("WMKSherwani", "password", "phone", "123");

            // Add transactions
            Transaction t1 = new Transaction(1000, "Lahore", LocalDateTime.now());
            Transaction t2 = new Transaction(500, "Karachi", LocalDateTime.now());
            ;
            Transaction t3 = new Transaction(2000, "Islamabad", LocalDateTime.now());
            ;
            Transaction t4 = new Transaction(1000, "Peshawar", LocalDateTime.now());
            ;
            Transaction t5 = new Transaction(500, "Quetta", LocalDateTime.now());
            this.user.addTransaction(t1);
            this.user.addTransaction(t2);
            this.user.addTransaction(t3);
            this.user.addTransaction(t4);
            this.user.addTransaction(t5);
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

        // Add a horizontal line for Schedule
        Line horizontalLine = new Line(0, 0, 1500, 0);
        horizontalLine.setTranslateX(0);
        horizontalLine.setTranslateY(-290);
        horizontalLine.setStroke(Color.BLACK);

        root.getChildren().add(horizontalLine);
        

        Timeline timeline1 = new Timeline();
        timeline1.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(horizontalLine.endXProperty(), horizontalLine.getStartX())),
            new KeyFrame(Duration.seconds(1), new KeyValue(horizontalLine.endXProperty(), horizontalLine.getStartX() + 1500.0))
        );

        timeline1.setCycleCount(1);
        timeline1.play();

        // ------------------------------------------------------------------------------------------------------------------

        // Add text to the StackPane
        Text Schedule = new Text(YearMonth.now().getMonth().toString() + " " + YearMonth.now().getYear());
        Schedule.setFont(Font.font("Garamond", FontWeight.BOLD, 50));
        Schedule.setTranslateX(0);
        Schedule.setTranslateY(-320);
        root.getChildren().add(Schedule);


        // ------------------------------------------------------------------------------------------------------------------

        // Implement a Calendar to display upcoming reminders
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, YearMonth.now().getYear());

        currentYearMonth = YearMonth.now();
        calendarGrid = new GridPane();
        calendarGrid.setAlignment(Pos.CENTER);
        calendarGrid.setTranslateY(30);

        String PrevMonthYear = YearMonth.now().minusMonths(1).getMonth().toString() + " " + YearMonth.now().minusMonths(1).getYear();
        PrevMonthYear = PrevMonthYear.substring(0, 1).toUpperCase() + PrevMonthYear.substring(1).toLowerCase();

        String NextMonthYear = YearMonth.now().plusMonths(1).getMonth().toString() + " " + YearMonth.now().plusMonths(1).getYear();
        NextMonthYear = NextMonthYear.substring(0, 1).toUpperCase() + NextMonthYear.substring(1).toLowerCase();

        int PrevSpacesToAdd = 15 - PrevMonthYear.length();
        int NextSpacesToAdd = 15 - NextMonthYear.length();

        PrevMonthYear = "⏪" + " ".repeat(PrevSpacesToAdd) + PrevMonthYear;
        NextMonthYear = NextMonthYear + " ".repeat(NextSpacesToAdd) + "⏩";

        Button previousMonth = new Button(PrevMonthYear);
        Button nextMonth = new Button(NextMonthYear);
        
        // Set sizes and styles for the buttons
        previousMonth.setPrefSize(200, 50);
        nextMonth.setPrefSize(200, 50);
        previousMonth.setStyle("-fx-font-size: 18px;");
        nextMonth.setStyle("-fx-font-size: 18px;");
        
        previousMonth.setOnAction(e -> prevMonth(previousMonth, nextMonth, Schedule));
        nextMonth.setOnAction(e -> nextMonth(previousMonth, nextMonth, Schedule));

        updateCalendar(Schedule);
        
        GridPane navigationBar = new GridPane();
        navigationBar.add(previousMonth, 0, 0);
        navigationBar.add(nextMonth, 2, 0);
        navigationBar.setTranslateY(700);
        navigationBar.setTranslateX(550);

        // Write code for add reminders button
        // There need to be entry fields for the text and the date, and a button to add the reminder
        // The reminder should be added to the user's list of reminders

        TextField reminderText = new TextField();
        DatePicker reminderDate = new DatePicker();
        Button addReminderButton = new Button("Add Reminder");

        // Add event handler for addReminderButton
        addReminderButton.setOnAction(e -> {
            if (reminderText.getText().isEmpty() || reminderDate.getValue() == null) {
                reminderText.clear();
                reminderDate.setValue(null);
            }
            else {
                String text = reminderText.getText();
                LocalDate date = reminderDate.getValue();
                Reminder reminder = new Reminder(text, LocalDateTime.of(date, LocalTime.MIDNIGHT));
                user.addReminder(reminder);
                db.insert_reminder(conn, user, reminder);
                updateCalendar(Schedule);
            }
        });

        reminderDate.setOnMouseClicked(e ->{
            if (e.getClickCount() == 2)
            {
                reminderText.clear();
                reminderDate.setValue(null);
            }
        });

        reminderText.setOnMouseClicked(e ->{
            if (e.getClickCount() == 2)
            {
                reminderText.clear();
                reminderDate.setValue(null);
            }
        });

        HBox reminderBox = new HBox(reminderText, reminderDate, addReminderButton);
        reminderBox.setTranslateY(715);
        reminderBox.setTranslateX(1055);

        root.getChildren().addAll(calendarGrid, navigationBar, reminderBox);

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

    private void prevMonth(Button previousMonth, Button nextMonth, Text Schedule) {
        currentYearMonth = currentYearMonth.minusMonths(1);

        String PrevMonthYear = currentYearMonth.minusMonths(1).getMonth().toString() + " " + currentYearMonth.minusMonths(1).getYear();
        PrevMonthYear = PrevMonthYear.substring(0, 1).toUpperCase() + PrevMonthYear.substring(1).toLowerCase();

        String NextMonthYear = currentYearMonth.plusMonths(1).getMonth().toString() + " " + currentYearMonth.plusMonths(1).getYear();
        NextMonthYear = NextMonthYear.substring(0, 1).toUpperCase() + NextMonthYear.substring(1).toLowerCase();

        int PrevSpacesToAdd = 15 - PrevMonthYear.length();
        int NextSpacesToAdd = 15 - NextMonthYear.length();

        PrevMonthYear = "⏪" + " ".repeat(PrevSpacesToAdd) + PrevMonthYear;
        NextMonthYear = NextMonthYear + " ".repeat(NextSpacesToAdd) + "⏩";

        // Change buttons to previous and next month
        previousMonth.textProperty().set(PrevMonthYear);
        nextMonth.textProperty().set(NextMonthYear);

        updateCalendar(Schedule);
    }

    private void nextMonth(Button previousMonth, Button nextMonth, Text Schedule) {
        currentYearMonth = currentYearMonth.plusMonths(1);

        String PrevMonthYear = currentYearMonth.minusMonths(1).getMonth().toString() + " " + currentYearMonth.minusMonths(1).getYear();
        PrevMonthYear = PrevMonthYear.substring(0, 1).toUpperCase() + PrevMonthYear.substring(1).toLowerCase();

        String NextMonthYear = currentYearMonth.plusMonths(1).getMonth().toString() + " " + currentYearMonth.plusMonths(1).getYear();
        NextMonthYear = NextMonthYear.substring(0, 1).toUpperCase() + NextMonthYear.substring(1).toLowerCase();

        int PrevSpacesToAdd = 15 - PrevMonthYear.length();
        int NextSpacesToAdd = 15 - NextMonthYear.length();

        PrevMonthYear = "⏪" + " ".repeat(PrevSpacesToAdd) + PrevMonthYear;
        NextMonthYear = NextMonthYear + " ".repeat(NextSpacesToAdd) + "⏩";

        // Change buttons to previous and next month
        previousMonth.textProperty().set(PrevMonthYear);
        nextMonth.textProperty().set(NextMonthYear);

        updateCalendar(Schedule);
    }

    private void updateCalendar(Text Schedule) {
        calendarGrid.getChildren().clear();
        LocalDate calendarDate = LocalDate.of(currentYearMonth.getYear(), currentYearMonth.getMonth(), 1);
        int dayOfWeek = calendarDate.getDayOfWeek().getValue() % 7; // get the day of the week as a value from 0 (Sunday) to 6 (Saturday)
        calendarDate = calendarDate.minusDays(dayOfWeek); // go back to the start of the week
    
        // Add weekdays to the calendarGrid
        String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < 7; i++) {
            Text weekdayText = new Text(weekdays[i]);
            weekdayText.setFont(Font.font("Garamond", FontWeight.BOLD, 20));
            weekdayText.translateYProperty().set(-30);
            calendarGrid.add(weekdayText, i, 0); // column=i, row=0
            GridPane.setHalignment(weekdayText, HPos.CENTER); // Center align the weekday text
        }
    
        for (int i = 0; i < 42; i++) {
            Text dayText = new Text(String.valueOf(" " + calendarDate.getDayOfMonth()));
            dayText.setFont(Font.font("Garamond", 15));
            // Create a box for each day
            VBox dayBox = new VBox(dayText);
            dayBox.setPrefSize(200, 90);

            for (Reminder reminder : user.getRemindersAll()) {

                if (dayBox.getChildren().size() == 4) {
                    Text reminderText = new Text(" • " + "...");
                    reminderText.setFont(Font.font("Garamond", 15));
                    dayBox.getChildren().add(reminderText);
                    break;
                }

                if (reminder.getDate().toLocalDate().equals(calendarDate)) {
                    Text reminderText = new Text(" • " + reminder.getReminder());
                    if (reminder.getReminder().length() > 14)
                        reminderText.setText(" • " + reminder.getReminder().substring(0, 14) + "...");
                    reminderText.setFont(Font.font("Garamond", 15));

                    if (calendarDate.equals(LocalDate.now())) {
                        reminderText.setFill(Color.BLACK);
                    } else if (calendarDate.isBefore(LocalDate.now())) {
                        reminderText.setFill(Color.RED);
                    } else if (calendarDate.isAfter(LocalDate.now())) {
                        reminderText.setFill(Color.BLACK);
                    }

                    if (reminder.getIsCompleted()) {
                        reminderText.strikethroughProperty().set(true);
                        reminderText.setFill(Color.BROWN);
                    }

                    dayBox.getChildren().add(reminderText);
                }
            }

            if (calendarDate.equals(LocalDate.now()))
                {dayBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #FFFFFF;");}
            else if (!calendarDate.getMonth().equals(currentYearMonth.getMonth()))
                {dayBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #FFF6AA;");}
            else
                {dayBox.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #FFD770;");}

            dayBox.translateYProperty().set(-30);
    
            calendarGrid.add(dayBox, i % 7, i / 7 + 1); // add 1 to row index to leave space for weekdays
            calendarDate = calendarDate.plusDays(1);
        }

        Schedule.setText(currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear());
    }
}