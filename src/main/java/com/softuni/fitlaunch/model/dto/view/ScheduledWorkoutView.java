package com.softuni.fitlaunch.model.dto.view;

import java.time.LocalDateTime;

public class ScheduledWorkoutView {

    private Long id;
    private String clientName;
    private String scheduledDateTime;


    public ScheduledWorkoutView(Long id, String clientName, String scheduledDateTime) {
        this.id = id;
        this.clientName = clientName;
        this.scheduledDateTime = scheduledDateTime;
    }

    public Long getId() {
        return id;
    }

    public ScheduledWorkoutView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public ScheduledWorkoutView setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public String getScheduledDateTime() {
        return scheduledDateTime;
    }

    public ScheduledWorkoutView setScheduledDateTime(String scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
        return this;
    }
}
