package io.github.agomosodev;

import java.time.LocalDate;
import java.util.Scanner;

public class TaskManagerApp {
    public static void printmenu() {
        System.out.println("======= Task Manager =======");
        System.out.println(" 1. Add Task ");
        System.out.println(" 2. List Tasks ");
        System.out.println(" 3. Update Task ");
        System.out.println(" 4. Delete Task ");
        System.out.println(" 5. Exit");
        System.out.println("============================");
        System.out.print("Choose an option: ");
    }
    public static Task AddTask(Scanner sc) {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Description: ");
        String description = sc.nextLine();
        System.out.print("Enter State(defect is pending/do/done): ");
        String state = sc.nextLine();
        System.out.print("Enter Due Date(YYYY-MM-DD): ");
        String dueDateStr = sc.nextLine();
        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(dueDateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format! Please use YYYY-MM-DD");
            return null;
        }
        return new Task(title, description, state, dueDate);
    }
    
    public static Task UpdTask(Scanner sc) {

        sc.nextLine();
        System.out.print("Enter new Title: ");
        String newTitle = sc.nextLine();
        System.out.print("Enter new Description: ");
        String newDescription = sc.nextLine();
        System.out.print("Enter new State: ");
        String newState = sc.nextLine();
        System.out.print("Enter new Due Date: ");
        String dueDateStr = sc.nextLine();
        LocalDate newDueDate;
        try {
            newDueDate = LocalDate.parse(dueDateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format! Please use YYYY-MM-DD");
            return null;
        }
        return new Task(newTitle, newDescription, newState, newDueDate);
    }
    public static void main(String[] args) {
        TaskService taskService = new TaskServiceImpl();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while(!exit) {
            printmenu();
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    Task newTask = AddTask(sc);
                    taskService.addTask(newTask);
                    System.out.println("Task added successfully!");
                    System.out.println("---------------------------");
                    taskService.listTasks();
                    System.out.println("---------------------------");
                    break;
                case 2:
                    taskService.listTasks();
                    break;
                case 3:
                    System.out.print("Enter Task ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    Task updatedTask = UpdTask(sc);
                    taskService.updateTask(updateId, updatedTask);
                    break;
                case 4:
                    System.out.print("Enter Task ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();
                    Task taskToDelete = taskService.getTask(deleteId);
                    taskService.deleteTask(deleteId, taskToDelete);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting Task Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
}
