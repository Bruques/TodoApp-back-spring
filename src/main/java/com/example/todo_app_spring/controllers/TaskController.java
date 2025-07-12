package com.example.todo_app_spring.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todo_app_spring.models.TaskModel;
import com.example.todo_app_spring.services.TaskService;

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
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/get-tasks")
    public ResponseEntity<List<TaskModel>> getTasksList() {
        return ResponseEntity.ok().body(service.getTaskList());
    }

    @PostMapping("/new-task")
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel entity) {
        service.addNewTaskOnList(entity);
        return ResponseEntity.created(null).body(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable String id, @RequestBody TaskModel entity) {
        TaskModel updatedTask = service.updateTaskById(id, entity);
        if (updatedTask != null) {
            return ResponseEntity.ok().body(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskModel> deleteTask(@PathVariable String id) {
        TaskModel removedTask = service.removeTaskFromListById(id);
        if (removedTask != null) {
            return ResponseEntity.ok().body(removedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
