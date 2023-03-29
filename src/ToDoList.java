package src;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToDoList {

  private final Map<Integer, RegularTask> current = new HashMap<>();

  public void newTask(RegularTask task) { //добавить задачу в текущий список дел
    current.put(task.getTaskId(), task);
  }

  public void checkList() { //показать экран
    List<Map.Entry<Integer, RegularTask>> listOfTaskWithId = new ArrayList<>();

    listOfTaskWithId.addAll(current.entrySet());
    listOfTaskWithId.sort(new idComparator());

    printToDoList(listOfTaskWithId);
  }

  private void printToDoList(List<Map.Entry<Integer, RegularTask>> listOfTaskWithId) {
    for (Map.Entry<Integer, RegularTask> paar : listOfTaskWithId) {
      RegularTask temp = paar.getValue();
      System.out.println(temp);
    }
  }

  public void correctTask() throws IOException { //корректировать задачу
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please, enter the task number:");
    int id = Integer.parseInt(read.readLine()); //todo словить ошибки ввода

    System.out.print("What do you want to change?");
    String forChange = read.readLine(); //todo сделать енам для выбора и while true

    switch (forChange) { //todo добавить все варианты изменения
      case ("title"):
        System.out.print("Please, enter new title:");
        String newTitle = read.readLine();
        current.get(id).setTaskTitle(newTitle);
    }
  }

  public void sortTasks() { //сортировать задачи
    //Todo создать отдельный Comparator, здесь вызвать
  }

  public void exportTaskList() throws IOException { // экспортировать задачи в файл
    //todo записать дату в файл, записать мапу в нужном формате, закрыть файл
    FileWriter ToDoList = new FileWriter("res/ToDoList.txt", true);

    for (Map.Entry<Integer, RegularTask> paar : current.entrySet()) {

      String forPrint = paar.getValue().getTaskId() + " " + paar.getValue().getTaskTitle() + " " +
          paar.getValue().getTaskType() + "\n";

      ToDoList.write(forPrint);

    }
    ToDoList.close();
  }
}
