package com.spenditure;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class User {
    private UUID userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private double accountBalance;
    private List<Transaction> transactions;
    private List<Reminder> reminders;

    public User(String username, String email, String phoneNumber, String password) {
        this.userId = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountBalance = 0.0;
        this.transactions = new ArrayList<Transaction>();
        this.reminders = new ArrayList<Reminder>();
    }

    // ------------------------------------------------------------------------------------------------------------------

    // Getters and setters for each field

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    // ------------------------------------------------------------------------------------------------------------------

    // Method to calculate money in the user's account balance
    public void calculateAccountBalance() {
        double balance = 0.0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        this.accountBalance = balance;
    }

    // ------------------------------------------------------------------------------------------------------------------

    // Method to add a transaction to the user's list of transactions
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        accountBalance += transaction.getAmount();
    }

    public void setTransactionsAll(List<Transaction> transactions) {
        this.transactions = transactions;
        accountBalance = 0.0;
        for (Transaction transaction : transactions) {
            accountBalance += transaction.getAmount();
        }
    }

    // Method to remove a transaction from the user's list of transactions
    public void removeTransaction(UUID transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId().equals(transactionId)) {
                transactions.remove(transaction);
                accountBalance -= transaction.getAmount();
                break;
            }
        }
    }

    // Method to update a transaction in the user's list of transactions
    public void updateTransaction(UUID transactionId, double newAmount) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId().equals(transactionId)) {
                accountBalance += newAmount - transaction.getAmount();
                transaction.setAmount(newAmount);
                break;
            }
        }
    }

    // Method to get a transaction from the user's list of transactions
    public Transaction getTransaction(UUID transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId().equals(transactionId)) {
                return transaction;
            }
        }
        return null;
    }

    // Method to get all transactions from the user's list of transactions
    public List<Transaction> getTransactionsAll() {
        return transactions;
    }

    // Method to get all transactions of a certain amount from the user's list of transactions
    public List<Transaction> getTransactionsByAmount(double amount, int comparison) {
        List<Transaction> transactionsByAmount = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (comparison == 0) {
                if (transaction.getAmount() == amount) {
                    transactionsByAmount.add(transaction);
                }
            } else if (comparison == 1) {
                if (transaction.getAmount() > amount) {
                    transactionsByAmount.add(transaction);
                }
            } else if (comparison == -1) {
                if (transaction.getAmount() < amount) {
                    transactionsByAmount.add(transaction);
                }
            }
        }
        return transactionsByAmount;
    }

    // Method to get all transactions of a certain location from the user's list of transactions
    public List<Transaction> getTransactionsByLocation(String location) {
        List<Transaction> transactionsByLocation = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (transaction.getLocation().equals(location)) {
                transactionsByLocation.add(transaction);
            }
        }
        return transactionsByLocation;
    }

    // Method to get all transactions of a certain date from the user's list of transactions
    public List<Transaction> getTransactionsByDate(LocalDateTime transactionDate, int comparison) {
        List<Transaction> transactionsByDate = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (comparison == 0) {
                if (transaction.getTransactionDate().equals(transactionDate)) {
                    transactionsByDate.add(transaction);
                }
            } else if (comparison == 1) {
                if (transaction.getTransactionDate().isAfter(transactionDate)) {
                    transactionsByDate.add(transaction);
                }
            } else if (comparison == -1) {
                if (transaction.getTransactionDate().isBefore(transactionDate)) {
                    transactionsByDate.add(transaction);
                }
            }
        }
        return transactionsByDate;
    }

    // ------------------------------------------------------------------------------------------------------------------

    // Method to add a reminder to the user's list of reminders
    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    public void activateReminder(Reminder reminder) {
        reminder.setIsCompleted(true);
    }

    public void deactivateReminder(Reminder reminder) {
        reminder.setIsCompleted(false);
    }

    // Method to remove a reminder from the user's list of reminders
    public void deleteReminder(Reminder reminder) {
        reminders.remove(reminder);
    }

    // Method to get all reminders from the user's list of reminders
    public List<Reminder> getRemindersAll() {
        return reminders;
    }

    public void setRemindersAll(List<Reminder> reminders) {
        this.reminders = reminders;
    }
    // ------------------------------------------------------------------------------------------------------------------
}