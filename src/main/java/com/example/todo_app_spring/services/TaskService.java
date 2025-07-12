package com.example.todo_app_spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_app_spring.models.TaskModel;
import com.example.todo_app_spring.repositories.TaskRepository;

@Service
public class TaskService {
    public TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    //MARK: - get task by id
    public TaskModel getTaskById(String id) {
        TaskModel selectedTask = null;
        for (TaskModel taskModel : repository.findAll()) {
            if (taskModel.getId().equals(id)) {
                selectedTask = taskModel;
            }
        }
        return selectedTask;
    }

    public List<TaskModel> getTaskList() {
        return repository.findAll();
    }

    public TaskModel addNewTaskOnList(TaskModel task) {
        // taskList.add(task);
        repository.insert(task);
        return task;
    }

    public TaskModel removeTaskFromListById(String id) {
        TaskModel task = getTaskById(id);
        // taskList.remove(task);
        repository.delete(task);
        return task;
    }

    public TaskModel updateTaskById(String id, TaskModel entity) {
        TaskModel task = getTaskById(id);
        if (task != null) {
            task.setTitle(entity.getTitle());
            task.setDescription(entity.getDescription());
            repository.save(task);
            return task;
        } else {
            return null;
        }
    }
}
