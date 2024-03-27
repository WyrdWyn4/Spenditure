package com.spenditure;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reminder {
    private UUID id;
    private String reminder;
    private LocalDateTime Date;
    private boolean isCompleted;

    public Reminder(String reminder, LocalDateTime date2) {
        this.id = UUID.randomUUID();
        this.reminder = reminder;
        this.Date = date2;
        this.isCompleted = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime Date) {
        this.Date = Date;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }
}
