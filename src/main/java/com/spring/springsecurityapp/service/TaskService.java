package com.spring.springsecurityapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.springsecurityapp.model.Task;
import com.spring.springsecurityapp.repository.TaskRepository;
import java.util.List;
import org.springframework.security.access.AccessDeniedException;
import com.spring.springsecurityapp.exception.ResourceNotFoundException;




@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByUser_Id(userId);
    }

    public void deleteTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        if (!task.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("Unauthorized access");
        }
        taskRepository.delete(task);
    }
}

