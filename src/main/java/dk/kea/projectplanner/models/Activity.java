package dk.kea.projectplanner.models;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public abstract class Activity {
    private long id;
    private long activityId;
    private String name;
    private long dateTimeId;
    private LocalDateTime plannedStartDate;
    private LocalDateTime actualStartDate;
    private LocalDateTime deadline;
    private LocalDateTime actualEndDate;

    public Activity(String name, LocalDateTime plannedStartDate, LocalDateTime deadline) {
        this.name = name;
        this.plannedStartDate = plannedStartDate;
        this.deadline = deadline;
    }

    public Activity() {
    }

    public long getId() {
        return id;
    }

    public long getActivityId() {
        return activityId;
    }

    public String getName() {
        return name;
    }

    public long getDateTimeId() {
        return dateTimeId;
    }

    public LocalDateTime getPlannedStartDate() {
        return plannedStartDate;
    }

    public LocalDateTime getActualStartDate() {
        return actualStartDate;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public LocalDateTime getActualEndDate() {
        return actualEndDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateTimeId(long dateTimeId) {
        this.dateTimeId = dateTimeId;
    }

    public void setPlannedStartDate(LocalDateTime plannedStartDate) {
        this.plannedStartDate = plannedStartDate;
    }

    public void setActualStartDate(LocalDateTime actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setActualEndDate(LocalDateTime actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id && activityId == activity.activityId && dateTimeId == activity.dateTimeId && Objects.equals(name, activity.name) && Objects.equals(plannedStartDate, activity.plannedStartDate) && Objects.equals(actualStartDate, activity.actualStartDate) && Objects.equals(deadline, activity.deadline) && Objects.equals(actualEndDate, activity.actualEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activityId, name, dateTimeId, plannedStartDate, actualStartDate, deadline, actualEndDate);
    }
}

//TODO: If there is time, add Discipline to ActivityImpl and Person in order to find qualified personnel for activities
