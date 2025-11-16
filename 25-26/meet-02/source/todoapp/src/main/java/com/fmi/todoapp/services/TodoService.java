package com.fmi.todoapp.services;

import com.fmi.todoapp.models.TodoItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TodoService {

    private ArrayList<TodoItem> taskCollection = new ArrayList<>();
    private SequenceGeneratorService generatorService;

    public TodoService(SequenceGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    public ArrayList<TodoItem> getAllTasks() {
        return taskCollection;
    }

    public TodoItem createNewTask(String taskDescription, int priority) {

        int newId = this.generatorService.getNext();
        TodoItem newItem = new TodoItem(newId, taskDescription, priority);
        taskCollection.add(newItem);

        return newItem;
    }
}