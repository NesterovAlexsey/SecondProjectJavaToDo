package src;

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

  public static int getNextTaskId() {
    return ++taskId;
  }

  public static RegularTask createRegularTask() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /*int taskId = 0;*/
    int hours = 0;
    int minutes = 0;
    String taskTitle = null;
    boolean validInput = false;
    while (!validInput) {
      try {
        System.out.print("Enter title: ");
        taskTitle = br.readLine();
        getNextTaskId();
        System.out.print("Enter the number of hours: ");
        hours = Integer.parseInt(br.readLine());
        if (hours < 0) {
          throw new IllegalArgumentException("The number of hours cannot be negative.!"
                  + "you entered [" + hours + "]");
        }
        System.out.print("Enter the number of minutes: ");
        minutes = Integer.parseInt(br.readLine());
        if (minutes < 0) {
          throw new IllegalArgumentException("The number of minutes cannot be negative!"
                  + "you entered [" + minutes + "]");
        }

        System.out.println();

        validInput = true;
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        System.out.println("Enter the data correctly!");
      }
    }

    return new RegularTask(taskId, hours, minutes, taskTitle);
  }

  public static void main(String[] args) throws IOException {
    System.out.println();
    System.out.println(" ==== Per aspera ad astra ====");

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
          case CORRECT_LIST -> currentToDoList.correctTask();
          case SORT_LIST -> currentToDoList.sortTasks();
          case EXPORT_LIST -> currentToDoList.exportTaskList();
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        System.out.println("Enter the data correctly!");
      }
      command = readCommand();
    }
    System.out.println("See you!");
  }

  public static Command readCommand() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.println();
    System.out.println("List of commands:");
    pintSortedCommands();
    System.out.println();
    System.out.println("Enter the name or number of the command: ");
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
