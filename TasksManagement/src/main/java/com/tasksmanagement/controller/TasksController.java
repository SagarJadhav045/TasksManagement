package com.tasksmanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasksmanagement.model.TaskStatus;
import com.tasksmanagement.model.Tasks;
import com.tasksmanagement.service.TasksService;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {
	
	@Autowired
    private TasksService taskService;

    @PostMapping
    public ResponseEntity<Tasks> createTask(@RequestBody Tasks task) {
        Tasks createdTask = taskService.createTask(task);
        return ResponseEntity.status(201).body(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable Long id) {
        Optional<Tasks> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tasks> updateTask(@PathVariable Long id, @RequestBody Tasks task) {
        Tasks updatedTask = taskService.updateTask(id, task);
        return updatedTask != null ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter/status")
    public ResponseEntity<List<Tasks>> getTasksByStatus(@RequestParam TaskStatus status) {
        List<Tasks> tasks = taskService.getTasksByStatus(status);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/filter/priority")
    public ResponseEntity<List<Tasks>> getTasksByPriority(@RequestParam String priority) {
        List<Tasks> tasks = taskService.getTasksByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/filter/dueDate")
    public ResponseEntity<List<Tasks>> getTasksByDueDateRange(@RequestParam("start") LocalDateTime startDate,
                                                              @RequestParam("end") LocalDateTime endDate) {
        List<Tasks> tasks = taskService.getTasksByDueDateRange(startDate, endDate);
        return ResponseEntity.ok(tasks);
    }

}
