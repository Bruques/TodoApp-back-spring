package com.example.todo_app_spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.todo_app_spring.models.TaskModel;

@Repository
public interface TaskRepository extends MongoRepository<TaskModel, String> {  
}
