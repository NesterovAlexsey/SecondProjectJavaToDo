package src.toDoList;

import java.util.Comparator;
import java.util.Map;

public class idComparator implements Comparator<Map.Entry<Integer, RegularTask>> {

  @Override
  public int compare(Map.Entry<Integer, RegularTask> o1, Map.Entry<Integer, RegularTask> o2) {
    return o1.getKey() - o2.getKey();
  }
}
