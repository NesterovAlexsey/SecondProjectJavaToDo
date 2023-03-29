package src;

import java.util.Comparator;
import java.util.Map;

public class idComparator implements Comparator<Map.Entry<Integer, Test>> {

  @Override
  public int compare(Map.Entry<Integer, Test> o1, Map.Entry<Integer, Test> o2) {
    return o1.getKey() - o2.getKey();
  }
}
