package com.pluralsight.service;

import com.pluralsight.model.*;
import com.pluralsight.repo.TaskRepository;
import com.pluralsight.strategy.TaskSortStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class ToDoServiceImpl implements ToDoService {
    private final TaskRepository repo;

    public ToDoServiceImpl(TaskRepository repo) { this.repo = repo; }

    @Override
    public TaskList createList(String name) { return repo.createList(name); }

    @Override
    public List<TaskList> allLists() { return repo.findAllLists(); }

    @Override
    public void deleteList(UUID listId) { repo.deleteList(listId); }

    @Override
    public void addTask(UUID listId, String title, String description, Priority p) {
        repo.addTask(listId, new Task(title, description, p, null));
    }

    @Override
    public List<Task> getTasks(UUID listId, TaskSortStrategy strategy) {
        return repo.findList(listId)
                .map(TaskList::getTasks)
                .stream()
                .flatMap(Collection::stream)
                .sorted(strategy.getComparator())
                .collect(Collectors.toList());
    }

    @Override
    public void completeTask(UUID listId, UUID taskId) {
        repo.findList(listId).ifPresent(l -> l.getTasks()
                .stream().filter(t -> t.getId().equals(taskId))
                .findFirst().ifPresent(Task::markDone));
    }
}
