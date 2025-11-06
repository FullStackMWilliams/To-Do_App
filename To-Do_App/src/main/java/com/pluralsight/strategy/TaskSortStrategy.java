package com.pluralsight.strategy;

import com.pluralsight.model.Task;
import java.util.Comparator;

public interface TaskSortStrategy {
    Comparator<Task> getComparator();
    String getName();
}
