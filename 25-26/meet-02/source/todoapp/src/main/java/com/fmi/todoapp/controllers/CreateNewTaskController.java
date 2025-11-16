package com.fmi.todoapp.controllers;

import com.fmi.todoapp.models.TodoItem;
import com.fmi.todoapp.services.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CreateNewTaskController {

    private TodoService todoService;

    public CreateNewTaskController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/tasks")
    public ArrayList<TodoItem> fetchAllTasks() {
        return this.todoService.getAllTasks();
    }


    @PostMapping("/tasks")
    public TodoItem createTask(@RequestBody TodoItem todoItem) {

        var resultItem =  this.todoService.createNewTask(
                todoItem.getTaskDescription(),
                todoItem.getPriority()
        );

        return resultItem;
    }
}
