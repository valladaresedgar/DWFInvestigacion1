package com.tomas.hexagonal.infrastructure.persistence;

import com.tomas.hexagonal.domain.model.Task;
import com.tomas.hexagonal.domain.ports.out.TaskRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskPersistenceAdapter implements TaskRepositoryPort {

    private final JpaTaskRepository jpaTaskRepository;

    @Override
    public Task save(Task task) {
        TaskEntity entity = new TaskEntity(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
        TaskEntity savedEntity = jpaTaskRepository.save(entity);
        return new Task(savedEntity.getId(), savedEntity.getTitle(), savedEntity.getDescription(), savedEntity.isCompleted());
    }

    @Override
    public Optional<Task> findById(Long id) {
        return jpaTaskRepository.findById(id)
                .map(entity -> new Task(entity.getId(), entity.getTitle(), entity.getDescription(), entity.isCompleted()));
    }

    @Override
    public List<Task> findAll() {
        return jpaTaskRepository.findAll().stream()
                .map(entity -> new Task(entity.getId(), entity.getTitle(), entity.getDescription(), entity.isCompleted()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaTaskRepository.deleteById(id);
    }
}
