package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

  enum Command {
    HELP(9), //помощь
    NEW_LIST(1), // добавить
    CHECK_LIST(2), // вывести
    CORRECT_LIST(3), // править
    SORT_LIST(4), // сортировать
    EXPORT_LIST(5), // экспорт
    EXIT(0); // выход из программы
    private final int number;
    Command(int number) {
      this.number = number;
    }

    public int getNumber() {
      return number;
    }
    public static Command fromNumber(int number) {
      for (Command command : Command.values()) {
        if (command.getNumber() == number) {
          return command;
        }
      }
      throw new IllegalArgumentException("Команды с номером [" + number + "] не существует");
    }
  }

  private static final Map<Command, String> commands = new HashMap<>();

  static {
    commands.put(Command.HELP, "[9] Список команд");
    commands.put(Command.NEW_LIST, "[1] Новая запись");
    commands.put(Command.CHECK_LIST, "[2] Проверить запись");
    commands.put(Command.CORRECT_LIST, "[3] Править запись");
    commands.put(Command.SORT_LIST, "[4] Сортировать записи");
    commands.put(Command.EXPORT_LIST, "[5] Экспортировать запись");
    commands.put(Command.EXIT, "[0] ВЫХОД");
  }

  public static RegularTask createRegularTask() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int taskId = 0;
    int hours = 0;
    int minutes = 0;
    String taskTitle = null;
    boolean validInput = false;
    while (!validInput) {
      try {
        System.out.println("Введите номер задачи:");
        taskId = Integer.parseInt(br.readLine());
        if (taskId < 0) {
          throw new IllegalArgumentException("Номер задачи должен быть положительным!"
          + "Вы ввели [" + taskId + "]");
        }
        System.out.println("Введите количество часов: ");
        hours = Integer.parseInt(br.readLine());
        if (hours < 0) {
          throw new IllegalArgumentException("Кол-во часов должно быть положительным!"
                  + "Вы ввели [" + hours + "]");
        }
        System.out.println("Введите количество минут:");
        minutes = Integer.parseInt(br.readLine());
        if (minutes < 0) {
          throw new IllegalArgumentException("Кол-во минут должно быть положительным!"
                  + "Вы ввели [" + minutes + "]");
        }
        System.out.println("Введите название:");
        taskTitle = br.readLine();

        validInput = true;
      } catch (IllegalArgumentException e) {
        System.out.println("Ошибка: " + e.getMessage());
        System.out.println("Введите данные корректно!");
      }
    }

    return new RegularTask(taskId, hours, minutes, taskTitle);
  }

  public static void main(String[] args) throws IOException {
    ToDoList currentToDoList = new ToDoList();

    Command command = readCommand();
    while (command != Command.EXIT) {
      switch (command) {
        case HELP -> printMenu();
        case NEW_LIST -> {
          RegularTask testTask = createRegularTask();
          currentToDoList.newTask(testTask);
        }
        case CHECK_LIST -> currentToDoList.checkList();
        case CORRECT_LIST -> currentToDoList.correctTask();
        case SORT_LIST -> currentToDoList.sortTasks();
        case EXPORT_LIST -> currentToDoList.exportTaskList();
      }
      command = readCommand();
    }
    System.out.println("До свиданья!");
  }

  public static void printMenu() {
    System.out.println();
    System.out.println("Список Команд: ");
    for (Command command : commands.keySet()) {
      System.out.printf("- %s: %s%n", command, commands.get(command));
    }
  }

  public static Command readCommand() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    printMenu();
    System.out.println("Введите номер команды: ");
    String command = br.readLine().toUpperCase();

    Command result = null;
    while (result == null) {
      try {
        result = Command.valueOf(command);
      } catch (IllegalArgumentException e) {
        System.out.println("Некорректная команда: " + command);
        System.out.println("Введите корректную команду: ");
        command = br.readLine().toUpperCase();
      }
    }

    System.out.println();
    return result;
  }
}
