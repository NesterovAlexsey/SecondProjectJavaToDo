package src;

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
}
