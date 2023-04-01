package src.toDoList;

import java.util.Comparator;
import java.util.Map;

public class timeComparator implements Comparator<Map.Entry<Integer, RegularTask>> {

  @Override
  public int compare(Map.Entry<Integer, RegularTask> o1, Map.Entry<Integer, RegularTask> o2) {
    if (o1.getValue().getHours() != o2.getValue().getHours()) {
      return o1.getValue().getHours() - o2.getValue().getHours();
    }
    return o1.getValue().getMinutes() - o2.getValue().getMinutes();
  }
}
