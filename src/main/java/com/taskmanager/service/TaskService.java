package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ActivityLogService activityLogService;

    public TaskService(TaskRepository taskRepository,
                       ActivityLogService activityLogService) {
        this.taskRepository = taskRepository;
        this.activityLogService = activityLogService;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }



    public Task createTask(Task task) {

        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {

        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setAssignee(updatedTask.getAssignee());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setPriority(updatedTask.getPriority());

        return taskRepository.save(existingTask);
    }

    public Task changeStatus(Long id, TaskStatus newStatus) {
        Task task = getTaskById(id);

        if (newStatus == null) {
            throw new RuntimeException("Status cannot be empty");
        }

        if (task.getStatus() == newStatus) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Task is already in this status"
            );
        }

        task.setStatus(newStatus);

        Task savedTask = taskRepository.save(task);

        activityLogService.logActivity(
                id,
                "System",
                "Status changed to " + newStatus
        );

        return savedTask;
    }

    public boolean isOverdue(Task task) {
        if (task.getDueDate() == null) {
            return false;
        }

        return task.getDueDate().isBefore(LocalDate.now())
                && task.getStatus() != TaskStatus.DONE;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}