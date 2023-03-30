package src;
/* План моей работы:
+1. Написать минимально работающий функционал для сшивки по всем методам
+2. Сшиться с ребятами, первая версия рабочей программы
3. Дописать try-catch, enum, while(true) где нужно
4. Попытаться разделить программу на разные классы
5. Написать Unit-test для своих классов
 */

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
  public void checkList() {
    List<Map.Entry<Integer, RegularTask>> listOfTaskWithId = listToDo();
    listOfTaskWithId.sort(new idComparator());
    printToDoList(listOfTaskWithId);
  }

  private void printToDoList(List<Map.Entry<Integer, RegularTask>> listOfTaskWithId) {
    System.out.println("");
    System.out.println("======= My ToDo List ======");
    for (Map.Entry<Integer, RegularTask> paar : listOfTaskWithId) {
      RegularTask temp = paar.getValue();
      System.out.println(temp);
    }
    System.out.println("");
  }

  private int getIdFromUser(BufferedReader read) throws IOException {
    System.out.print("Please, enter the task number: ");

    int id = 0;
    try {
      id = Integer.parseInt(read.readLine());
    } catch (NumberFormatException e) {
      System.err.println("Incorrect id number, should be Integer! " + e);
    }

    if (!current.containsKey(id)) {
      throw new IllegalArgumentException("There is NO task with such id");
    }

    return id;
  }

  //метод, вызываемыйе в мейн, корректировка задачи
  public void correctTask() throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    int id = getIdFromUser(read);
    Correction correction = readCorrection();

    while (correction != Correction.FINISH) {
      switch (correction) {
        case CHANGE_TITLE -> changeTitleInTask(id, read);
        case CHANGE_HOURS -> changeHoursInTask(id, read);
        case CHANGE_MINUTES -> changeMinutesInTask(id, read);
        case DELETE_TASK -> deleteTask(id);
      }

      if (!current.containsKey(id)) {
        correction = Correction.FINISH;
        checkList();
        continue;
      }

      System.out.println("If you want to continue with this task, enter next command!");
      System.out.println("When you done, enter FINISH");
      correction = readCorrection();
    }
  }

  private void changeTitleInTask(Integer id, BufferedReader read) throws IOException {
    System.out.print("Please, enter new title: ");
    String newTitle = read.readLine();
    if (newTitle.isEmpty()) {
      throw new IllegalArgumentException("Task title couldn't be empty");
    }
    current.get(id).setTaskTitle(newTitle);
    checkList();
  }

  private void changeHoursInTask(Integer id, BufferedReader read) throws IOException {
    System.out.print("Please, enter new hours: ");
    int temp;

    try {
      temp = Integer.parseInt(read.readLine());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Parameter Hours should be Integer!");
    }
    if (temp < 0) {
      throw new IllegalArgumentException("Parameter Hours should be NOT negative!");
    }

    current.get(id).setHours(temp);
    checkList();
  }

  private void changeMinutesInTask(Integer id, BufferedReader read) throws IOException {
    System.out.print("Please, enter new minutes: ");
    int temp;

    try {
      temp = Integer.parseInt(read.readLine());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Parameter Minutes should be Integer!");
    }
    if (temp < 0) {
      throw new IllegalArgumentException("Parameter Minutes should be NOT negative!");
    }

    current.get(id).setMinutes(temp);
    checkList();
  }

  private void deleteTask(int id) {
    current.remove(id);
  }

  private Correction readCorrection() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    printMenu();
    System.out.print("What do you want to change? ");
    String correction = br.readLine().toUpperCase();

    Correction result = null;
    while (result == null) {
      try {
        result = Correction.valueOf(correction);
      } catch (IllegalArgumentException e) {
        System.out.println("Incorrect command: " + correction);
        System.out.println("Please, enter correct one: ");
        correction = br.readLine().toUpperCase();
      }
    }

    System.out.println();
    return result;
  }

  private void printMenu() {
    System.out.println();
    System.out.println("List of possible corrections: ");
    for (Correction correction : corrections) {
      System.out.println(correction);
    }
  }

  //метод, вызываемыйе в мейн, сортировка задач
  //Todo try-catch, enum
  public void sortTasks() throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    List<Map.Entry<Integer, RegularTask>> listOfTaskWithTime = listToDo();

    System.out.println("" +
        "Вы можете отсортировать задачи по номеру задачи (id), или по продолжительности задачи " +
        "(time): ");
    String sortType = read.readLine();

    switch (sortType) {
      case ("id"):
        checkList();
        break;
      case ("time"):
        listOfTaskWithTime.sort(new timeComparator());
        printToDoList(listOfTaskWithTime);
    }

  }

  //метод, вызываемыйе в мейн, экспорт данных в файл
  //Todo записать данные в отсортированном формате, добавить текущую дату
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
