package com.pluralsight.repo;

import com.pluralsight.model.Task;
import com.pluralsight.model.TaskList;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTaskRepository implements TaskRepository {
    private final Map<UUID, TaskList> storage = new ConcurrentHashMap<>();

    @Override
    public TaskList createList(String name) {
        TaskList list = new TaskList(name);
        storage.put(list.getId(), list);
        return list;
    }

    @Override
    public Optional<TaskList> findList(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<TaskList> findAllLists() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteList(UUID id) {
        storage.remove(id);
    }

    @Override
    public void addTask(UUID listId, Task task) {
        storage.get(listId).addTask(task);
    }

    @Override
    public void removeTask(UUID listId, UUID taskId) {
        storage.get(listId).removeTask(taskId);
    }
}
