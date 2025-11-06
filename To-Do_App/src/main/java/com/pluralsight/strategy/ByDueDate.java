package com.pluralsight.strategy;
import com.pluralsight.model.Task;
import java.util.Comparator;


public class ByDueDate implements TaskSortStrategy {
    public Comparator<Task> getComparator() { return Comparator.comparing(Task::getDueAt, Comparator.nullsLast(Comparator.naturalOrder())); }
    public String getName() { return "By Due Date"; }
}