package io.github.agomosodev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskServiceImpl implements TaskService {

    private Map<Integer, Task> tasks = new HashMap<>();
    private static int idCounter = 1; // To generate unique IDs
    private List<String> categories = new ArrayList<>(List.of("Work", "Home", "Study"));


    @Override
    public void addTask(Task task) {
        if(task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        task.setId(idCounter++);
        tasks.put(task.getId(), task);
    }

    @Override
    public void listTasks() {
        if(tasks.isEmpty()) {
            System.out.println("No tasks available.");
        }else {
            tasks.values().forEach(task -> {
                System.out.println("ID: " + task.getId());
                System.out.println("Title: " + task.getTitle());
                System.out.println("Description: " + task.getDescription());
                System.out.println("State: " + task.getState());
                System.out.println("Category: " + task.getCategory());
                System.out.println("Due Date: " + task.getDueDate());
                System.out.println("Completed: " + task.isCompleted());
                System.out.println("---------------------------");
            });
        }
    }

    @Override
    public void deleteTask(int id, Task task) {
        if(!taskExists(id)) {
            NotExistsMessage(id);
        }else {
            tasks.remove(id);
            System.out.println("Task with ID " + id + " has been deleted.");
        }
    }

    @Override
    public void updateTask(int id, Task task) {
        if(!taskExists(id)) {
            NotExistsMessage(id);
        }else {
            task.setId(id);
            tasks.put(id, task);
            System.out.println("Task with ID " + id + " has been updated.");
        }
    }

    @Override
    public Task getTask(int id) {
        if(!taskExists(id)) {
            NotExistsMessage(id);
        }else{
            return tasks.get(id);
        }
        return null;
    }

    // Helper methods
    public boolean taskExists(int id) {
        return  tasks.containsKey(id);
    }

    public void NotExistsMessage(int id) {
        System.out.println("Task with ID " + id + " does not exist.");
    }
    public List<String> getCategories() {
        return categories;
    }
    public void addCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category cannot be empty.");
        }

        if (categories.contains(category)) {
            throw new IllegalArgumentException("Category already exists.");
        }

        categories.add(category);
    }

    @Override
    public String categoryExists(String category) {
        for (String c : categories) {
            if (c.equalsIgnoreCase(category)) {
                return c;
            }
        }
        return null;
    }

}
