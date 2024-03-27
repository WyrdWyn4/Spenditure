package com.spenditure;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String dbname = "SpenditureDB";
    private String username = "postgres";
    private String pass = "password";

    private String transactionsTableName = "Transactions";
    private String reminderTableName = "Reminders";
    

    private Connection conn = null;

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

    public Connection connect_to_db() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:1024/" + this.dbname, this.username.toLowerCase(), pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    // -----------------------------------------------------------------------------------------------------------------------

    public void createTransactionsTable(Connection conn) {
        String table_name = transactionsTableName;
        Statement statement;
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + table_name
                    + "(UserId UUID,"
                    + "transactionId UUID,"
                    + "amount DECIMAL(10,2),"
                    + "location VARCHAR(200),"
                    + "transactionDate TIMESTAMP,"
                    + "PRIMARY KEY(transactionId));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Transaction Table Created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insert_transaction(Connection conn, User user, Transaction transaction) {
        String table_name = transactionsTableName;
        Statement statement;
        try {
            String query = String.format("INSERT INTO %s(UserId, transactionId, amount, location, transactionDate) VALUES('%s', '%s', %f, '%s', '%s');", 
                table_name, user.getUserId(), transaction.getTransactionId(), transaction.getAmount(), transaction.getLocation(), transaction.getTransactionDate());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Transaction> retrieve_transactions(Connection conn, User user) {
        String table_name = transactionsTableName;
        Statement statement;
        ResultSet rs = null;
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            String query = String.format("SELECT * FROM %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("UserId").equals(user.getUserId().toString())) {
                    Transaction transaction = new Transaction(0.0, "location", LocalDateTime.now());
                    transaction.setTransactionId(UUID.fromString(rs.getString("transactionId")));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setLocation(rs.getString("location"));
                    transaction.setTransactionDate(rs.getTimestamp("transactionDate").toLocalDateTime());
                    transactions.add(transaction);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return transactions;
    }
    
    public void read_Transaction_data(Connection conn, User user) {
        String table_name = transactionsTableName;
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("UserId") + " ");
                System.out.print(rs.getString("transactionId") + " ");
                System.out.print(rs.getDouble("amount") + " ");
                System.out.print(rs.getString("location") + " ");
                System.out.print(rs.getTimestamp("transactionDate") + " ");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }    

    public void update_transaction(Connection conn, User user, UUID transactionId, double newAmount) {
        String table_name = transactionsTableName;
        Statement statement;
        try {
            String query = String.format("UPDATE %s SET amount=%f WHERE transactionId='%s' AND UserId='%s'", table_name, newAmount, transactionId, user.getUserId());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void search_by_transactionId(Connection conn, User user, UUID transactionId) {
        String table_name = transactionsTableName;
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM %s WHERE transactionId='%s' AND UserId='%s'", table_name, transactionId, user.getUserId());
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("UserId") + " ");
                System.out.print(rs.getString("transactionId") + " ");
                System.out.print(rs.getDouble("amount") + " ");
                System.out.print(rs.getString("location") + " ");
                System.out.print(rs.getTimestamp("transactionDate") + " ");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete_row_by_transactionId(Connection conn, User user, UUID transactionId) {
        String table_name = transactionsTableName;
        Statement statement;
        try {
            String query = String.format("DELETE FROM %s WHERE transactionId='%s'AND UserId='%s'", table_name, transactionId, user.getUserId());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }    

    public void delete_Transactions_table(Connection conn, User user) {
        String table_name = transactionsTableName;
        Statement statement;
        try {
            String query = String.format("drop table %s", table_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------

    public void createRemindersTable(Connection conn) {
        String table_name = reminderTableName;
        Statement statement;
        try {
            String query = "CREATE TABLE IF NOT EXISTS " + table_name
                    + "(UserId UUID,"
                    + "reminderId UUID,"
                    + "reminderTime TIMESTAMP,"
                    + "reminderMessage VARCHAR(200),"
                    + "isCompleted BOOLEAN,"
                    + "PRIMARY KEY(reminderId));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Reminder Table Created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert_reminder(Connection conn, User user, Reminder reminder) {
        String table_name = reminderTableName;
        Statement statement;
        try {
            String query = String.format("INSERT INTO %s(UserId, reminderId, reminderTime, reminderMessage, isCompleted) VALUES('%s', '%s', '%s', '%s', %b);", 
                table_name, user.getUserId(), reminder.getId(), reminder.getDate(), reminder.getReminder(), reminder.getIsCompleted());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Reminder> retrieve_reminders(Connection conn, User user) {
        String table_name = reminderTableName;
        Statement statement;
        ResultSet rs = null;
        List<Reminder> reminders = new ArrayList<Reminder>();
        try {
            String query = String.format("SELECT * FROM %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                if (rs.getString("UserId").equals(user.getUserId().toString())) {
                    Reminder reminder = new Reminder("reminder", LocalDateTime.now());
                    reminder.setId(UUID.fromString(rs.getString("reminderId")));
                    reminder.setDate(rs.getTimestamp("reminderTime").toLocalDateTime());
                    reminder.setReminder(rs.getString("reminderMessage"));
                    reminder.setIsCompleted(rs.getBoolean("isCompleted"));
                    reminders.add(reminder);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return reminders;
    }

    public void read_Reminder_data(Connection conn, User user) {
        String table_name = reminderTableName;
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("UserId") + " ");
                System.out.print(rs.getString("reminderId") + " ");
                System.out.print(rs.getTimestamp("reminderTime") + " ");
                System.out.print(rs.getString("reminderMessage") + " ");
                System.out.print(rs.getBoolean("isCompleted") + " ");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update_reminder(Connection conn, User user, UUID reminderId, boolean isCompleted) {
        String table_name = reminderTableName;
        Statement statement;
        try {
            String query = String.format("UPDATE %s SET isCompleted=%b WHERE reminderId='%s' AND UserId='%s'", table_name, isCompleted, reminderId, user.getUserId());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void search_by_reminderId(Connection conn, User user, UUID reminderId) {
        String table_name = reminderTableName;
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM %s WHERE reminderId='%s' AND UserId='%s'", table_name, reminderId, user.getUserId());
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.print(rs.getString("UserId") + " ");
                System.out.print(rs.getString("reminderId") + " ");
                System.out.print(rs.getTimestamp("reminderTime") + " ");
                System.out.print(rs.getString("reminderMessage") + " ");
                System.out.print(rs.getBoolean("isCompleted") + " ");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete_row_by_reminderId(Connection conn, User user, UUID reminderId) {
        String table_name = reminderTableName;
        Statement statement;
        try {
            String query = String.format("DELETE FROM %s WHERE reminderId='%s'AND UserId='%s'", table_name, reminderId, user.getUserId());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete_Reminders_table(Connection conn, User user) {
        String table_name = reminderTableName;
        Statement statement;
        try {
            String query = String.format("drop table %s", table_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------

    public void createUserTable(Connection conn) {
        Statement statement;
        try {
            String query = "CREATE TABLE IF NOT EXISTS \"user\""
                    + "(username VARCHAR(200),"
                    + "userID UUID,"
                    + "password VARCHAR(200),"
                    + "email VARCHAR(200),"
                    + "phone VARCHAR(200),"
                    + "PRIMARY KEY(username));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("User Table Created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<User> readUserTable(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        List<User> users = new ArrayList<User>();
        try {
            String query = "SELECT * FROM \"user\"";
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                User user = new User("username", "email", "phone", "password");
                user.setUserId(UUID.fromString(rs.getString("userID")));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone"));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }

    public void insert_user(Connection conn, User user) {
        Statement statement;
        try {
            String query = String.format("INSERT INTO \"user\"(username, userID, password, email, phone) VALUES('%s', '%s', '%s', '%s', '%s');", 
                user.getUsername(), user.getUserId(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean check_user(Connection conn, String username, String password) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM \"user\" WHERE username='%s' AND password='%s'", username, password);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean user_exists(Connection conn, String username) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("SELECT * FROM \"user\" WHERE username='%s'", username);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public User retrieve_user(Connection conn, String username, String password) {
        Statement statement;
        ResultSet rs = null;
        User user = new User("username", "email", "phone", "password");
        try {
            String query = String.format("SELECT * FROM \"user\" WHERE username='%s' AND password='%s'", username, password);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                if (rs.getString("username").equals(username) && rs.getString("password").equals(password)) {
                    user.setUserId(UUID.fromString(rs.getString("userID")));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoneNumber(rs.getString("phone"));
                }
            }

            if (user.getUsername().equals("username")) {
                return null;
            }
            user.setTransactionsAll(retrieve_transactions(conn, user));
            System.out.println(user.getTransactionsAll());
            user.setRemindersAll(retrieve_reminders(conn, user));
            System.out.println(user.getRemindersAll());
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public void delete_user(Connection conn, User user) {
        delete_Transactions_table(conn, user);
        delete_Reminders_table(conn, user);
    }


    // -----------------------------------------------------------------------------------------------------------------------
}
