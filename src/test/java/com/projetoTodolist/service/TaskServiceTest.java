package com.projetoTodolist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.projetoTodolist.model.Task;
import com.projetoTodolist.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void deveDefinirStatusComoNovaQuandoStatusForNulo() {
        Task task = new Task();
        task.setTitle("Estudar Testes Unitarios");
        task.setStatus(null);

        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task resultado = taskService.save(task);

        assertNotNull(resultado);
        assertEquals("Nova", resultado.getStatus());
    }
}

