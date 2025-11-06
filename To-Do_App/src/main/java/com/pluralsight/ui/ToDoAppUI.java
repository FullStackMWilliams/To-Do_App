package com.pluralsight.ui;

import com.pluralsight.model.Priority;
import com.pluralsight.model.Task;
import com.pluralsight.model.TaskList;
import com.pluralsight.repo.RepositoryFactory;
import com.pluralsight.service.ToDoService;
import com.pluralsight.service.ToDoServiceImpl;
import com.pluralsight.strategy.*;

import java.util.*;

public class ToDoAppUI {
    private final Scanner in = new Scanner(System.in);
    private final ToDoService service = new ToDoServiceImpl(RepositoryFactory.createRepository());

    public void run() {
        while (true) {
            System.out.println("\n=== 🧭 ToDo Backend (OOP Patterns) ===");
            System.out.println("1️⃣ Create list");
            System.out.println("2️⃣ View lists");
            System.out.println("3️⃣ Add task");
            System.out.println("4️⃣ View tasks");
            System.out.println("5️⃣ Complete task");
            System.out.println("0️⃣ Exit");
            System.out.print("Choice: ");

            switch (in.nextLine().trim()) {
                case "1" -> createList();
                case "2" -> showLists();
                case "3" -> addTask();
                case "4" -> viewTasks();
                case "5" -> completeTask();
                case "0" -> { System.out.println("👋 Goodbye!"); return; }
                default -> System.out.println("⚠️ Invalid choice.");
            }
        }
    }

    private void createList() {
        System.out.print("List name: ");
        TaskList list = service.createList(in.nextLine());
        System.out.println("Created " + list.getName());
    }

    private void showLists() {
        service.allLists().forEach(l -> System.out.println(l.getId() + " | " + l.getName()));
    }

    private Optional<UUID> selectList() {
        showLists();
        System.out.print("Enter list ID: ");
        try { return Optional.of(UUID.fromString(in.nextLine())); }
        catch (Exception e) { System.out.println("Invalid ID"); return Optional.empty(); }
    }

    private void addTask() {
        selectList().ifPresent(id -> {
            System.out.print("Title: ");
            String title = in.nextLine();
            System.out.print("Desc: ");
            String desc = in.nextLine();
            service.addTask(id, title, desc, Priority.MEDIUM);
            System.out.println("Added!");
        });
    }

    private void viewTasks() {
        selectList().ifPresent(id -> {
            TaskSortStrategy strategy = new ByPriority();
            service.getTasks(id, strategy).forEach(System.out::println);
        });
    }

    private void completeTask() {
        selectList().ifPresent(id -> {
            System.out.print("Enter Task ID: ");
            try {
                service.completeTask(id, UUID.fromString(in.nextLine()));
                System.out.println("Task marked done!");
            } catch (Exception e) { System.out.println("Invalid ID"); }
        });
    }
}

