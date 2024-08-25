package com.tasksmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasksmanagement.model.TaskStatus;
import com.tasksmanagement.model.Tasks;
import com.tasksmanagement.repository.TasksRepository;

@Service
public class TasksService {

	@Autowired
	private TasksRepository taskRepository;

	public Tasks createTask(Tasks task) {
		task.setCreatedAt(LocalDateTime.now());
		task.setUpdatedAt(LocalDateTime.now());
		return taskRepository.save(task);
	}

	public Optional<Tasks> getTaskById(Long id) {
		return taskRepository.findById(id);
	}

	public Tasks updateTask(Long id, Tasks task) {
		if (taskRepository.existsById(id)) {
			task.setId(id);
			task.setUpdatedAt(LocalDateTime.now());
			return taskRepository.save(task);
		}
		return null;
	}

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}

	public List<Tasks> getTasksByStatus(TaskStatus status) {
		return taskRepository.findByStatus(status);
	}

	public List<Tasks> getTasksByPriority(String priority) {
		return taskRepository.findByPriority(priority);
	}

	public List<Tasks> getTasksByDueDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		return taskRepository.findByDueDateBetween(startDate, endDate);
	}
}
