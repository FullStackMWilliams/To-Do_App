package com.pluralsight.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class TaskList {
    private final UUID id = UUID.randomUUID();
    private String name;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LinkedList<Task> tasks = new LinkedList<>();

    public TaskList(String name) { this.name = name; }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<Task> getTasks() { return tasks; }

    public void addTask(Task task) { tasks.add(task); }
    public void removeTask(UUID taskId) { tasks.removeIf(t -> t.getId().equals(taskId)); }
}
