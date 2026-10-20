package com.projetoTodolist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

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

    @Test 
    void deveLancarExcecaoAoTentarAtualizarTaskInexistente() {
        Long idInexistente = 99L;
        Task taskAtualizada = new Task();
        taskAtualizada.setTitle("Qualquer titulo");
        
        when(taskRepository.findById(idInexistente)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.update(idInexistente, taskAtualizada);
        });
        assertEquals("Task not found with id: 99", exception.getMessage());
    }
}

