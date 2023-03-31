package src;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class idComparatorTests {

  @Test
  public void checkTheIdComparator() {
    Map<Integer, RegularTask> testMap = new HashMap<>();
    RegularTask testTask1 = new RegularTask(1, 1, 0, "First");
    RegularTask testTask2 = new RegularTask(2, 2, 0, "Second");

    testMap.put(testTask1.getTaskId(), testTask1);
    testMap.put(testTask2.getTaskId(), testTask2);

    List<Map.Entry<Integer, RegularTask>> testList = new ArrayList<>();
    testList.addAll(testMap.entrySet());

//    int result = idComparator.compare(testList.get(1), testList.get(2));
  }
}
