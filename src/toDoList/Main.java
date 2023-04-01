package src.toDoList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
      throw new IllegalArgumentException("Command with number [" + number + "] does not exist");
    }
  }

  private static final Map<Command, String> commands = new HashMap<>();

  static {
    commands.put(Command.HELP, "[9] List of commands, txt: ");
    commands.put(Command.NEW_LIST, "[1] New task, txt: ");
    commands.put(Command.CHECK_LIST, "[2] Check task, txt: ");
    commands.put(Command.CORRECT_LIST, "[3] Edit task, txt: ");
    commands.put(Command.SORT_LIST, "[4] Sort task, txt: ");
    commands.put(Command.EXPORT_LIST, "[5] Export task, txt: ");
    commands.put(Command.EXIT, "[0] EXIT, txt: ");
  }

  public static void pintSortedCommands() {
    List<Map.Entry<Command, String>> sortedCommands = new ArrayList<>(commands.entrySet());
    sortedCommands.sort(new CommandComparator());

    for (Map.Entry<Command, String> entry : sortedCommands) {
      System.out.println(entry.getValue() + entry.getKey());
    }
  }

  private static int taskId = 0;

  public static void getNextTaskId() {
    ++taskId;
  }

  public static RegularTask createRegularTask() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int hours = 0;
    int minutes = 0;
    String taskTitle;

    System.out.print("Enter title: ");
    taskTitle = br.readLine();
    getNextTaskId();

    boolean validHours = false;
    while (!validHours) {
      try {
        System.out.print("Enter the number of hours: ");
        hours = Integer.parseInt(br.readLine());
        if (hours < 0) {
          throw new IllegalArgumentException("The number of hours cannot be negative! "
                  + "You entered: [" + hours + "]");
        }
        validHours = true;
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid number: " + e.getMessage());
        System.out.println("Enter correct number!");
      }
    }

    boolean validMinutes = false;
    while (!validMinutes) {
      try {
        System.out.print("Enter the number of minutes: ");
        minutes = Integer.parseInt(br.readLine());
        if (minutes < 0) {
          throw new IllegalArgumentException("The number of minutes cannot be negative! "
                  + "You entered: [" + minutes + "]");
        }
        validMinutes = true;
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid number: " + e.getMessage());
        System.out.println("Enter correct number!");
      }
    }
    System.out.println();
    return new RegularTask(taskId, hours, minutes, taskTitle);
  }

  public static void main(String[] args) throws IOException {
    System.out.println();
    System.out.println("\033[1;33m==== Per aspera ad astra ====\033[0m");

    ToDoList currentToDoList = new ToDoList();

    Command command = readCommand();
    while (command != Command.EXIT) {
      try {
        switch (command) {
          case HELP -> pintSortedCommands();
          case NEW_LIST -> {
            RegularTask testTask = createRegularTask();
            currentToDoList.newTask(testTask);
          }
          case CHECK_LIST -> currentToDoList.checkList();
          case CORRECT_LIST -> currentToDoList.correctTask(taskId);
          case SORT_LIST -> currentToDoList.sortTasks();
          case EXPORT_LIST -> currentToDoList.exportTaskList();
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        System.out.println("Enter the data correctly!");
      }
      command = readCommand();
    }
    System.out.println("\033[1;33mSee you!");
  }

  public static Command readCommand() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println();
    System.out.println("\033[1m\033[4mList of commands:\033[0m");
    pintSortedCommands();
    System.out.println();
    System.out.println("\033[1m\033[4mEnter the name or number of the command:\033[0m ");
    String commandInput = br.readLine().toUpperCase();

    Command result = null;
    while (result == null) {
      try {
        int command = Integer.parseInt(commandInput);
        result = Command.fromNumber(command);
      } catch (NumberFormatException e) {
        try {
          result = Command.valueOf(commandInput);
        } catch (IllegalArgumentException exception) {
          System.out.println(" Incorrect command: " + commandInput);
        }
      } catch (IllegalArgumentException e) {
        System.out.println(" Incorrect command: " + commandInput);
      }
      if (result == null) {
        System.out.println("Enter a valid command: ");
        commandInput = br.readLine().toUpperCase();
      }
    }

    System.out.println();
    return result;
  }
}
