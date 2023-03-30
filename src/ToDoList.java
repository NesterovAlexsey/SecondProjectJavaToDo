package src;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ToDoList {

  enum Correction {
    CHANGE_TITLE, //коррекция название
    CHANGE_HOURS, // коррекция часов
    CHANGE_MINUTES, // коррекция минут
    DELETE_TASK, // удаление
    FINISH, // закончить коррекцию
  }

  private static final List<Correction> corrections = new ArrayList<>();

  static {
    corrections.add(Correction.CHANGE_TITLE);
    corrections.add(Correction.CHANGE_HOURS);
    corrections.add(Correction.CHANGE_MINUTES);
    corrections.add(Correction.DELETE_TASK);
    corrections.add(Correction.FINISH);
  }

  private final Map<Integer, RegularTask> current = new HashMap<>();

  private List<Map.Entry<Integer, RegularTask>> listToDo() {
    List<Map.Entry<Integer, RegularTask>> listToDo = new ArrayList<>();
    listToDo.addAll(current.entrySet());
    return listToDo;
  }

  //метод, вызываемыйе в мейн, добавить задачу в текущий список дел
  public void newTask(RegularTask task) {
    current.put(task.getTaskId(), task);
    checkList();
  }

  //метод, вызываемыйе в мейн
  public void checkList() { //показать экран
    List<Map.Entry<Integer, RegularTask>> listOfTaskWithId = listToDo();
    listOfTaskWithId.sort(new idComparator());
    printToDoList(listOfTaskWithId);
  }

  private void printToDoList(List<Map.Entry<Integer, RegularTask>> listOfTaskWithId) {
    for (Map.Entry<Integer, RegularTask> paar : listOfTaskWithId) {
      RegularTask temp = paar.getValue();
      System.out.println(temp);
    }
  }

  //метод, вызываемыйе в мейн
  //todo доделать
  public void correctTask() throws IOException { //корректировать задачу
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please, enter the task number:");
    int id = Integer.parseInt(read.readLine()); //todo словить ошибки ввода

    Correction correction = readCorrection();

    switch (correction) {
      case CHANGE_TITLE -> changeTitleInTask(id, read);
      case CHANGE_HOURS -> changeHoursInTask(id, read);
      case CHANGE_MINUTES -> changeMinutesInTask(id, read);
      case DELETE_TASK -> deleteTask(id);
      case FINISH -> checkList(); //todo усовершенствовать вариант финиш
    }
  }

  //todo - словить ошибки у методов коррекции
  private void changeTitleInTask(Integer id, BufferedReader read) throws IOException {
    System.out.print("Please, enter new title:");
    String newTitle = read.readLine();
    current.get(id).setTaskTitle(newTitle);
  }

  private void changeHoursInTask(Integer id, BufferedReader read) throws IOException {
    System.out.print("Please, enter new hours:");
    int temp = Integer.parseInt(read.readLine());
    current.get(id).setHours(temp);
  }

  private void changeMinutesInTask(Integer id, BufferedReader read) throws IOException {
    System.out.print("Please, enter new minutes:");
    int temp = Integer.parseInt(read.readLine());
    current.get(id).setMinutes(temp);
  }

  private void deleteTask(int id) {
    current.remove(id);
  }

  private Correction readCorrection() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    printMenu();
    System.out.println("What do you want to change?");
    String correction = br.readLine().toUpperCase();

    Correction result = null;
    while (result == null) {
      try {
        result = Correction.valueOf(correction);
      } catch (IllegalArgumentException e) {
        System.out.println("Некорректная команда: " + correction);
        System.out.println("Введите корректную команду: ");
        correction = br.readLine().toUpperCase();
      }
    }

    System.out.println();
    return result;
  }

  private void printMenu() {
    System.out.println();
    System.out.println("Список возможных корректировок: ");
    for (Correction correction : corrections) {
      System.out.println(correction);
    }
  }

  //метод, вызываемыйе в мейн
  //Todo создать отдельный Comparator, здесь вызвать
  public void sortTasks() { //сортировать задачи
  }

  //метод, вызываемыйе в мейн, экспорт данных в файл
  //Todo записать данные в отсортированном формате
  public void exportTaskList() throws IOException {

    FileWriter ToDoList = new FileWriter("res/ToDoList.txt", true);

    for (Map.Entry<Integer, RegularTask> paar : current.entrySet()) {

      String forPrint = paar.getValue().getTaskId() + " " + paar.getValue().getTaskTitle() + " " +
          paar.getValue().getHours() + "h, " + paar.getValue().getMinutes() + "min.\n";

      ToDoList.write(forPrint);

    }
    ToDoList.close();
  }
}
