package src;
/* План моей работы:
+1. Написать минимально работающий функционал для сшивки по всем методам
+2. Сшиться с ребятами, первая версия рабочей программы
+3. Дописать try-catch, enum, while(true) где нужно
+4. Попытаться разделить программу на разные классы или метдоы
5. Написать Unit-test для своих классов
 */

import java.io.*;
import java.text.SimpleDateFormat;
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

  //Новая задача
  public void newTask(RegularTask task) {
    current.put(task.getTaskId(), task);
    checkList();
  }

  //Проверка списка
  public void checkList() {
    List<Map.Entry<Integer, RegularTask>> listOfTaskWithId = listToDo();
    listOfTaskWithId.sort(new idComparator());
    printToDoList(listOfTaskWithId);
  }

  private void printToDoList(List<Map.Entry<Integer, RegularTask>> listOfTaskWithId) {
    System.out.println();
    System.out.println("======= My ToDo List ======");
    for (Map.Entry<Integer, RegularTask> paar : listOfTaskWithId) {
      RegularTask temp = paar.getValue();
      System.out.println(temp);
    }
    System.out.println();
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

  //Корректировка задачи
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

  //Сортировка задач
  public void sortTasks() throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    List<Map.Entry<Integer, RegularTask>> listOfTaskWithTime = listToDo();

    printSortTaskMenu();
    String sortType = read.readLine().toUpperCase();

    while (!sortType.equals("FINISH")) {
      switch (sortType) {
        case ("ID") -> checkList();
        case ("TIME") -> {
          listOfTaskWithTime.sort(new timeComparator());
          printToDoList(listOfTaskWithTime);
        }
      }
      printSortTaskMenu();
      sortType = read.readLine().toUpperCase();
    }
  }

  private static void printSortTaskMenu() {
    System.out.println("For sort TODO list with id enter 'ID'");
    System.out.println("For sort TODO list with time enter 'TIME'");
    System.out.println("For previous menu enter 'FINISH'");
  }

  //Экспорт данных в файл
  public void exportTaskList() throws IOException {
    try {
      FileWriter ToDoList = new FileWriter("res/ToDoList.txt", true);

      ToDoList.write(data() + "\n");
      for (Map.Entry<Integer, RegularTask> paar : current.entrySet()) {
        String forPrint =
            paar.getValue().getTaskId() + ". Task " + paar.getValue().getTaskTitle() +
                ", (" + paar.getValue().getHours() + "h, " + paar.getValue().getMinutes() + "min)" +
                ".\n";
        ToDoList.write(forPrint);
      }
        ToDoList.write("\n");
        ToDoList.close();

    } catch (FileNotFoundException | NullPointerException e) {
      System.err.println("File do not found, please, check if 'res' directory exist. " + e);
    }
  }

  private String data() {
    Date todayTime = new Date();
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String currentTime = formater.format(todayTime);
    return currentTime;
  }
}
