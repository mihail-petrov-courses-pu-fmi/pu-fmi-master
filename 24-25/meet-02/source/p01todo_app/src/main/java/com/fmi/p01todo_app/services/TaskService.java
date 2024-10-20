package com.fmi.p01todo_app.services;

import com.fmi.p01todo_app.models.TaskModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private ArrayList<TaskModel> taskCollection = new ArrayList<>();

    private SequanceGenerator sequanceGenerator;

    public TaskService(SequanceGenerator sequanceGenerator) {
        this.sequanceGenerator = sequanceGenerator;
    }

    public TaskModel getTask(int id) {

        for(TaskModel task : taskCollection) {
            if(task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    public ArrayList<TaskModel> getAllTasks() {
        return this.taskCollection;
    }

    public TaskModel addTask(TaskModel task) {

        task.setId(this.sequanceGenerator.getNextId());
        this.taskCollection.add(task);

        return task;
    }

    public void updateTask(TaskModel task) {

        for(int i = 0; i < this.taskCollection.size(); i++) {
            TaskModel selectedTask = this.taskCollection.get(i);

            if(selectedTask.getId() == task.getId()) {
                this.taskCollection.set(i, task);
            }
        }
    }

    public void deleteTask(int id) {

        for(int i = 0; i < this.taskCollection.size(); i++) {
            TaskModel selectedTask = this.taskCollection.get(i);

            if(selectedTask.getId() == id) {
                this.taskCollection.set(i, null);
            }
        }
    }
}
