package com.projetoTodolist.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoTodolist.model.Task;

public interface TaskDAO extends JpaRepository<Task, Long> {
    
    
}
