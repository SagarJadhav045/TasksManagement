package com.tasksmanagement.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tasks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String description;

	@Enumerated(EnumType.STRING)
	@JsonDeserialize(using = TaskStatusDeserializer.class)
	private TaskStatus status;

	private String priority;

	
	private LocalDateTime dueDate;

	
	private LocalDateTime createdAt;

	
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
