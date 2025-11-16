package com.fmi.todoapp.models;

public class TodoItem {

    private int id;
    private String taskDescription;
    private int priority;

    public TodoItem(int id, String taskDescription, int priority) {
        this.id = id;
        this.taskDescription = taskDescription;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
