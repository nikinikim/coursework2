import task.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static Calendar calendar;
    public static void main(String[] args) {
        calendar = new Calendar();
            try (Scanner scanner = new Scanner(System.in)) {
                label:
                while (true) {
                    printMenu();
                    System.out.print("Выберите пункт меню: ");
                    if (scanner.hasNextInt()) {
                        int menu = scanner.nextInt();
                        switch (menu) {
                            case 1:
                                inputTask(scanner);
                                break;
                            case 2:
                                getTasksForDay(scanner);
                                break;
                            case 3:
                                deleteTask(scanner);
                                break;
                            case 0:
                                break label;
                        }
                    } else {
                        scanner.next();
                        System.out.println("Выберите пункт меню из списка!");
                    }
                }
            }
        }

        private static void inputTask(Scanner scanner) {
            scanner.nextLine();
            System.out.print("Введите название задачи: ");
            String title = scanner.nextLine();
            // todo
            System.out.println("Введите описание задачи: ");
            String description = scanner.nextLine();
            boolean isWork;
            System.out.println("Задача в работе? ");
            switch (scanner.nextLine()) {
                case "да":
                case "yes":
                case "Да":
                case "Yes":
                case "ДА":
                case "YES":
                    isWork = true;
                    break;
                default:
                    isWork = false;
            }
            LocalDateTime date = null;
            System.out.println("Введите дату и время задачи (дд.мм.гггг чч:мм:cc): ");
            boolean shouldEnterAgain = true;
            while (shouldEnterAgain) {
                try {
                    date = LocalDateTime.parse(scanner.nextLine(), Task.DATE_TIME_FORMATTER);
                    shouldEnterAgain = false;
                } catch (DateTimeParseException e) {
                    System.out.println("Неверный формат");
                }
            }
            Task task;
            System.out.println("Повторяемость задания: ");
            System.out.println("\t 0 - не повторяется (default)");
            System.out.println("\t 1 - ежедневно");
            System.out.println("\t 2 - еженедельно");
            System.out.println("\t 3 - ежемесячно");
            System.out.println("\t 4 - ежегодно");
            switch (scanner.next()) {
                case "1":
                    task = new DailyTask(title, description, isWork, date);
                    break;
                case "2":
                    task = new WeeklyTask(title, description, isWork, date);
                case "3":
                    task = new MonthlyTask(title, description, isWork, date);
                case "4":
                    task = new YearlyTask(title, description, isWork, date);
                default:
                    task = new SingleTask(title, description, isWork, date);
            }
            calendar.addTask(task);
        }

    public static void getTasksForDay(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите дату: ");
        LocalDate date = null;
        boolean shouldEnterAgain = true;
        while (shouldEnterAgain) {
            try {
                date = LocalDate.parse(scanner.nextLine(), Task.DATE_FORMATTER);
                shouldEnterAgain = false;
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат");
            }
        }
        System.out.println(calendar.getTasksForOneDay(date));
    }

    public static void deleteTask(Scanner scanner) {
        int id = enterId(scanner);
        if (calendar.deleteTask(id)) {
            System.out.println("Задача удалена");
        }else {
            System.out.println("Задача не существует");
        }
    }

    public static int enterId(Scanner scanner) {
        System.out.println("Введите Id задачи: ");
        boolean shouldEnterAgain = true;
        int id = -1;
        while (shouldEnterAgain) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                shouldEnterAgain = false;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат");
            }
        }
        return id;
    }

        private static void printMenu() {
            System.out.println(
                    "1. Добавить задачу\n" +
                    "2. Получить задачу на указанный день\n" +
                    "3. Удалить задачу\n" +
                    "0. Выход");
        }
}