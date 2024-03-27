package com.spenditure;
import java.util.UUID;
import java.time.LocalDateTime;

public class Transaction {
    private UUID transactionId;
    private double amount;
    private String location;
    private LocalDateTime transactionDate;

    public Transaction(double amount, String location, LocalDateTime transactionDate) {
        this.transactionId = UUID.randomUUID();
        this.amount = amount;
        this.location = location;
        this.transactionDate = transactionDate;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }


    public void printTransaction() {
        System.out.println("Transaction ID: " + this.transactionId);
        System.out.println("Amount: " + this.amount);
        System.out.println("Location: " + this.location);
        System.out.println("Transaction Date: " + this.transactionDate);
    }
}