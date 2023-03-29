package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

  enum Command {
    HELP, //помощь
    NEW_LIST, // добавить
    CHECK_LIST, // вывести
    CORRECT_LIST, // править
    SORT_LIST, // сортировать
    EXPORT_LIST, // экспорт
    EXIT, // выход из программы
  }

  private static final Map<Command, String> commands = new HashMap<>();

  static {
    commands.put(Command.HELP, "Список команд");
    commands.put(Command.NEW_LIST, "Новая запись");
    commands.put(Command.CHECK_LIST, "Проверить запись");
    commands.put(Command.CORRECT_LIST, "Править запись");
    commands.put(Command.SORT_LIST, "Сортировать записи");
    commands.put(Command.EXPORT_LIST, "Экспортировать запись");
    commands.put(Command.EXIT, "ВЫХОД");
  }

  public static void main(String[] args) throws IOException {
    ToDoList currentToDoList = new ToDoList();

    Command command = readCommand();
    while (command != Command.EXIT) {
      switch (command) {
        case HELP -> printMenu(); // TODO
        case NEW_LIST -> {
          RegularTask testTask = new RegularTask(1,3,4, "Пробный таск");
          currentToDoList.newTask(testTask);

        } // TODO newTask (передать объект класса таск)
        case CHECK_LIST -> currentToDoList.checkList(); // TODO checkList
        case CORRECT_LIST -> currentToDoList.correctTask(); // TODO correctTask
        case SORT_LIST -> currentToDoList.sortTasks(); // TODO sortTasks
        case EXPORT_LIST -> currentToDoList.exportTaskList (); // TODO exportTaskList
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
    System.out.println("Введите команду: ");
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
