package com.example.todo_app_spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_app_spring.models.TaskModel;

@Service
public class TaskService {
    private List<TaskModel> taskList = new ArrayList<>();

    //MARK: - get task by id
    public TaskModel getTaskById(int id) {
        TaskModel selectedTask = null;
        for (TaskModel taskModel : taskList) {
            if (taskModel.getId() == id) {
                selectedTask = taskModel;
            }
        }
        return selectedTask;
    }

    public List<TaskModel> getTaskList() {
        return taskList;
    }

    public TaskModel addNewTaskOnList(TaskModel task) {
        taskList.add(task);
        return task;
    }

    public TaskModel removeTaskFromListById(int id) {
        TaskModel task = getTaskById(id);
        taskList.remove(task);
        return task;
    }

    public TaskModel updateTaskById(int id, TaskModel entity) {
        TaskModel task = getTaskById(id);
        if (task != null) {
            task.setTitle(entity.getTitle());
            task.setDescription(entity.getDescription());
            task.setId(entity.getId());
            return task;
        } else {
            return null;
        }
    }
}
