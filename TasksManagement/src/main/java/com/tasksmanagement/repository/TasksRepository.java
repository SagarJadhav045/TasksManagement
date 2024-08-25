package com.tasksmanagement.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tasksmanagement.model.TaskStatus;
import com.tasksmanagement.model.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long>{
	
    List<Tasks> findByStatus(TaskStatus status);
    
    List<Tasks> findByPriority(String priority);
    
    List<Tasks> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
  
}
