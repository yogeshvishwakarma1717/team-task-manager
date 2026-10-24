package com.taskmanager.controller;

import com.taskmanager.model.ActivityLog;
import com.taskmanager.repository.ActivityLogRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
@CrossOrigin(origins = "*")
public class ActivityLogController {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogController(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    @GetMapping
    public List<ActivityLog> getAllLogs() {
        return activityLogRepository.findAll();
    }
}