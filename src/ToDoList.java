package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToDoList {

  private final Map<Integer, Test> current = new HashMap<>();

  public void newTask(Test task) { //добавить задачу в текущий список дел
    current.put(task.getId(), task);
  }

  public void checkList() { //показать экран
    for (Map.Entry<Integer, Test> paar : current.entrySet()) {
      System.out.println(paar.getValue()); //todo дописать сортировку для вывода по порядку
    }
  }

  //откорректировать задачу по id, должен получать номер задачи, коммуникация с пользователем
  // должна быть через мейн?
  public void correctTask() throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    System.out.print("Please, enter the task number:");
    int id = Integer.parseInt(read.readLine()); //todo словить ошибки ввода

    System.out.print("What do you want to change?");
    String forChange = read.readLine(); //todo сделать енам для выбора и while true

    switch (forChange) { //todo добавить все варианты изменения
      case ("title"):
        System.out.print("Please, enter new title:");
        String newTitle = read.readLine();
        current.get(id).setTaskName(newTitle);

    }
  }
}
