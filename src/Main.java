package src;

import java.io.IOException;
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
        case HELP -> (); // TODO
        case NEW_LIST -> (); // TODO
        case CHECK_LIST -> (); // TODO
        case CORRECT_LIST -> (); // TODO
        case SORT_LIST -> (); // TODO
        case EXPORT_LIST -> (); // TODO
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

}
