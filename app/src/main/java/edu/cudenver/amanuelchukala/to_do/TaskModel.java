package edu.cudenver.amanuelchukala.to_do;

public class TaskModel {
    private static int id;
    private String taskTitle;
    private String taskDescription;
    private boolean isActive;

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public TaskModel(int id, String taskTitle, String taskDescription, boolean isActive) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.isActive = isActive;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public static int getId() {
        return id;
    }
}