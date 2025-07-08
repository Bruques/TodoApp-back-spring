package com.example.todo_app_spring.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo_app_spring.models.TaskModel;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private List<TaskModel> taskList = new ArrayList<>();

    @GetMapping("/get-tasks")
    public List<TaskModel> getTasksList() {
        return taskList;
    }

    @PostMapping("/new-task")
    public void createTask(@RequestBody TaskModel entity) {
        taskList.add(entity);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable int id, @RequestBody TaskModel entity) {
        for (TaskModel task : taskList) {
            if (task.getId() == id) {
                task.setTitle(entity.getTitle());
                task.setDescription(entity.getDescription());
                task.setId(entity.getId());
            }
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        for (TaskModel taskModel : taskList) {
            if (taskModel.getId() == id) {
                taskList.remove(taskModel);
            }
        }
    }
}
