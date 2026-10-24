package com.taskmanager.service;

import com.taskmanager.model.ActivityLog;
import com.taskmanager.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public void logActivity(Long taskId, String userName, String action) {

        ActivityLog log = new ActivityLog(
                taskId,
                userName,
                action,
                LocalDateTime.now()
        );

        activityLogRepository.save(log);
    }
}