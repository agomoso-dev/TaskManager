package io.github.agomosodev;

import java.util.List;

interface TaskService {
    void addTask(Task task);
    void listTasks();
    void deleteTask(int id, Task task);
    void updateTask(int id, Task task);
    Task getTask(int id);
    List<String> getCategories();
    void addCategory(String category);
    String categoryExists(String category);

}
