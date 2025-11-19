package io.github.agomosodev;

import java.time.LocalDate;
import java.util.Scanner;

public class TaskManagerApp {

    public static void printMenu() {
        System.out.println("======= Task Manager =======");
        System.out.println(" 1. Add Task");
        System.out.println(" 2. List Tasks");
        System.out.println(" 3. Update Task");
        System.out.println(" 4. Delete Task");
        System.out.println(" 5. Add Category");
        System.out.println(" 6. List Categories");
        System.out.println(" 7. Exit");
        System.out.println("============================");
        System.out.print("Choose an option: ");
    }

    public static String chooseCategory(Scanner sc, TaskService taskService) {
        System.out.println("Available Categories:");
        taskService.getCategories().forEach(c -> System.out.println(" - " + c));

        System.out.print("Choose a category: ");
        return sc.nextLine();
    }

    public static Task AddTask(Scanner sc, TaskService taskService) {

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        System.out.print("Enter State (pending/do/done): ");
        String state = sc.nextLine();

        String category;
        while (true) {
            System.out.print("Enter task category:");
            String categories = sc.nextLine();

            String checkcategory = taskService.categoryExists(categories);

            if (checkcategory != null) {
                category = checkcategory;
                break;
            }

            System.out.println("Category does not exist. Choose one of: " + taskService.getCategories());
        }
        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        String dueDateStr = sc.nextLine();

        LocalDate dueDate;
        try {
            dueDate = LocalDate.parse(dueDateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format! Please use YYYY-MM-DD");
            return null;
        }

        return new Task(title, description, state, dueDate, category);
    }

    public static Task UpdTask(Scanner sc, TaskService taskService) {

        System.out.print("Enter new Title: ");
        String newTitle = sc.nextLine();

        System.out.print("Enter new Description: ");
        String newDescription = sc.nextLine();

        System.out.print("Enter new State (pending/do/done): ");
        String newState = sc.nextLine();

        String newCategory = chooseCategory(sc, taskService);

        System.out.print("Enter new Due Date (YYYY-MM-DD): ");
        String dueDateStr = sc.nextLine();

        LocalDate newDueDate;
        try {
            newDueDate = LocalDate.parse(dueDateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format! Please use YYYY-MM-DD");
            return null;
        }

        return new Task(newTitle, newDescription, newState, newDueDate, newCategory);
    }

    public static void main(String[] args) {

        TaskService taskService = new TaskServiceImpl();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();

            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input!");
                sc.nextLine();
                continue;
            }

            sc.nextLine();

            switch (choice) {

                case 1:
                    Task newTask = AddTask(sc, taskService);
                    if (newTask != null) {
                        taskService.addTask(newTask);
                        System.out.println("Task added successfully!");
                    }
                    break;

                case 2:
                    taskService.listTasks();
                    break;

                case 3:
                    System.out.print("Enter Task ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    Task updatedTask = UpdTask(sc, taskService);
                    taskService.updateTask(updateId, updatedTask);
                    break;

                case 4:
                    System.out.print("Enter Task ID to delete: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();
                    Task toDelete = taskService.getTask(deleteId);
                    taskService.deleteTask(deleteId, toDelete);
                    break;

                case 5:
                    System.out.print("Enter new category: ");
                    String newCat = sc.nextLine();

                    try {
                        taskService.addCategory(newCat);
                        System.out.println("Category added!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Current Categories:");
                    taskService.getCategories().forEach(System.out::println);
                    break;

                case 7:
                    exit = true;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        sc.close();
    }
}
