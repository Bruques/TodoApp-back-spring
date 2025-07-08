package com.example.todo_app_spring.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo_app_spring.models.TaskModel;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel entity) {
        taskList.add(entity);
        return ResponseEntity.created(null).body(entity);
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable int id, @RequestBody TaskModel entity) {
        TaskModel selectedTask = getTaskById(id);
        if (selectedTask != null) {
            selectedTask.setTitle(entity.getTitle());
            selectedTask.setDescription(entity.getDescription());
            selectedTask.setId(entity.getId());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        TaskModel selectedTask = getTaskById(id);
        if (selectedTask != null) {
            taskList.remove(selectedTask);
        }
    }

    private TaskModel getTaskById(int id) {
        TaskModel selectedTask = null;
        for (TaskModel taskModel : taskList) {
            if (taskModel.getId() == id) {
                selectedTask = taskModel;
            }
        }
        return selectedTask;
    }
}
