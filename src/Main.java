package src;

import java.io.IOException;

public class Main {

  enum Command {
    HELP, //помощь
    ADD, // добавить товар (строку) в чек
    REPORT, // вывести отчёт
    EXIT, // выход из программы
  }

//  public static void main(String[] args) throws IOException {
//    Register cashRegister = new Register();
//
//    Command command = readCommand();
//    while (command != Command.EXIT) { // основной рабочий цикл программы, обрабатывающий команды
//      switch (command) {
//        case ADD -> {
//          ReceiptLine line = ReceiptLine.readReceiptLine();
//          cashRegister.addLine(line);
//        }
//        case NEW -> cashRegister.newReceipt();
//        case REPORT -> cashRegister.printReport();
//      }
//      command = readCommand(); // команда EXIT просто завершит цикл
//    }
//    System.out.println("До свидания!");
//  }

//  public static Command readCommand() throws IOException {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//    printMenu();
//    System.out.println(); // пустая строка для красоты
//    System.out.print("Введите команду: ");
//    String command = br.readLine().toUpperCase();
//
//    Command result = null;
//    while (result == null) { // пока команда не установлена
//      try {
//        result = Command.valueOf(command); // пытаемся установить команду
//      } catch (IllegalArgumentException e) {
//        System.out.println("Некорректная команда: " + command);
//        System.out.print("Введите корректную команду: ");
//        command = br.readLine().toUpperCase();
//      }
//    }
//
//    System.out.println(); // пустая строка для красоты
//    return result;
//  }
}
