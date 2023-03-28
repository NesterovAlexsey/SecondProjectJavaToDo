package src;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

  private final List<Test> current = new ArrayList<>();

  public void newTask(Test task) {
    current.add(task);
  }

  public void checkList() { //показать экран

  }

  public void correctTask() { //откорректировать задачу

  }
}
