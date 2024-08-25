package com.tasksmanagement.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class TaskStatusDeserializer extends JsonDeserializer<TaskStatus> {

	@Override
	public TaskStatus deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = p.getText().toUpperCase();
		return TaskStatus.valueOf(value);
	}
}
