package com.projetoTodolist.service;

import com.projetoTodolist.model.Task;
import com.projetoTodolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
    
    public Task save(Task task) {
        if (task.getStatus() == null) {
            task.setStatus("Nova");
        }
        return taskRepository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public Task update(Long id, Task task) {
        Optional<Task> existingTask = taskRepository.findById(id);
        return existingTask.map(t -> {
            t.setTitle(task.getTitle());
            t.setDescription(task.getDescription());
            t.setStatus(task.getStatus());
            return taskRepository.save(t);
        }).orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
    
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
