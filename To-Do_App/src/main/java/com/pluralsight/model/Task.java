package com.pluralsight.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {
    private final UUID id = UUID.randomUUID();
    private String title;
    private String description;
    private Priority priority = Priority.MEDIUM;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime dueAt;
    private Status status = Status.PENDING;

    public Task(String title, String description, Priority priority, LocalDateTime dueAt) {
        this.title = title;
        this.description = description;
        this.priority = priority != null ? priority : Priority.MEDIUM;
        this.dueAt = dueAt;
    }

    // Getters & Setters
    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Priority getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getDueAt() { return dueAt; }
    public Status getStatus() { return status; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setDueAt(LocalDateTime dueAt) { this.dueAt = dueAt; }

    public void markDone() { this.status = Status.DONE; }

    @Override
    public String toString() {
        return String.format("%s (%s) [%s]", title, priority, status);
    }
}
