package com.pluralsight.repo;

public class RepositoryFactory {
    public static TaskRepository createRepository() {
        return new InMemoryTaskRepository();
    }
}

