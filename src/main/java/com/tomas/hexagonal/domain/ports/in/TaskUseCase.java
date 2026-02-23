package com.tomas.hexagonal.domain.ports.in;

import com.tomas.hexagonal.domain.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskUseCase {
    Task createTask(Task task);
    Optional<Task> getTask(Long id);
    List<Task> getAllTasks();
    Task updateTask(Long id, Task task);
    boolean deleteTask(Long id);
}
