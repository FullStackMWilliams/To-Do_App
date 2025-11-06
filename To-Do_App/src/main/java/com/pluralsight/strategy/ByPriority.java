package com.pluralsight.strategy;
import com.pluralsight.model.Task;
import java.util.Comparator;

public class ByPriority implements TaskSortStrategy {
    public Comparator<Task> getComparator() { return Comparator.comparing(Task::getPriority).reversed(); }
    public String getName() { return "By Priority"; }
}
