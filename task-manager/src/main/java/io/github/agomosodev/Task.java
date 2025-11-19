package io.github.agomosodev;

import java.time.LocalDate;

public class Task {

    // Attributes
    private int id;
    private String title;
    private String description;
    private String state;
    private LocalDate dueDate;
    private boolean completed;
    

    // Constructor
    public Task() {
    }
    public Task(String title, String description, String state, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.completed = false;

        if(!checkdate(dueDate)) {
            throw new IllegalArgumentException("Due date cannot be in the past.");
        }
        this.dueDate = dueDate;


        if( this.state == null || this.state.isEmpty() || state.equalsIgnoreCase("pending")) {
            this.state = "pending";
        } else if( state.equalsIgnoreCase("do")) {
            this.state = state;
        } else if( state.equalsIgnoreCase("done")) {
            this.state = state;
            this.completed = true;
        }else{
            throw new IllegalArgumentException("Invalid state. Allowed values are: pending, do, done.");
        }
    }
    public Task(int id, String title, String description, String state, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.dueDate = dueDate;
        this.completed = false;
    }
    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        if(!checkdate(dueDate)) {
            throw new IllegalArgumentException("Due date cannot be in the past.");
        }

        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Task\n Title:" + this.title + "\\n" + //
                        " Description: " + this.description + "\\n" + //
                        "\\n" + //
                        " State: " + this.state + "\\n" + //
                        "\\n" + //
                        " Due Date: " + this.dueDate
                        + "\\n" + //
                        " Completed:" + this.completed + ", toString()=" + super.toString();
    }
    
    public boolean checkdate(LocalDate today) {
        return !today.isBefore(LocalDate.now());

    }
}
