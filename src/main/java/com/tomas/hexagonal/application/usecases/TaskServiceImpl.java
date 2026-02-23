package com.tomas.hexagonal.application.usecases;

import com.tomas.hexagonal.domain.model.Task;
import com.tomas.hexagonal.domain.ports.in.TaskUseCase;
import com.tomas.hexagonal.domain.ports.out.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TaskServiceImpl implements TaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    @Override
    public Task createTask(Task task) {
        return taskRepositoryPort.save(task);
    }

    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepositoryPort.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepositoryPort.findAll();
    }

    @Override
    public Task updateTask(Long id, Task task) {
        return taskRepositoryPort.findById(id).map(existingTask -> {
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setCompleted(task.isCompleted());
            return taskRepositoryPort.save(existingTask);
        }).orElse(null);
    }

    @Override
    public boolean deleteTask(Long id) {
        if (taskRepositoryPort.findById(id).isPresent()) {
            taskRepositoryPort.deleteById(id);
            return true;
        }
        return false;
    }
}
