package com.taskmanager.controller;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskStatus;
import com.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody Task task) {

        return taskService.updateTask(id, task);
    }

    @PatchMapping("/{id}/status")
    public Task changeStatus(
            @PathVariable Long id,
            @RequestParam TaskStatus status) {

        return taskService.changeStatus(id, status);
    }
    @GetMapping("/{id}/overdue")
    public boolean isOverdue(@PathVariable Long id) {
        return taskService.isOverdue(taskService.getTaskById(id));
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
}