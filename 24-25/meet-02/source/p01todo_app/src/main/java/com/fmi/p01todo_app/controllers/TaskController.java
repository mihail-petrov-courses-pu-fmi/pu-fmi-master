package com.fmi.p01todo_app.controllers;

import com.fmi.p01todo_app.models.TaskModel;
import com.fmi.p01todo_app.services.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Value("${example_user}")
    private String userName;

    @GetMapping("/tasks")
    public ArrayList<TaskModel> getTask() {
        return this.taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public TaskModel getTaskById(@PathVariable int id) {
        return this.taskService.getTask(id);
    }

    @PostMapping("/tasks")
    public String createNewTask(@RequestBody TaskModel task) {

        this.taskService.addTask(task);
        return "Task created";
    }

    @DeleteMapping("/tasks")
    public String deleteTask(
            @RequestParam( required = false ) int id
    ) {

        this.taskService.deleteTask(id);
        return "Task deleted";
    }
}
