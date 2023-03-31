package src;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class timeComparatorTests {

  private final Comparator<Map.Entry<Integer, RegularTask>> comparator = new idComparator();

  @Test
  public void checkTheHours() {
    Map<Integer, RegularTask> testMap = new HashMap<>();
    RegularTask testTask1 = new RegularTask(1, 1, 0, "First");
    RegularTask testTask2 = new RegularTask(2, 2, 0, "Second");
    RegularTask testTask3 = new RegularTask(2, 2, 0, "Second");

    testMap.put(testTask1.getTaskId(), testTask1);
    testMap.put(testTask2.getTaskId(), testTask2);
    testMap.put(testTask3.getTaskId(), testTask2);

    List<Map.Entry<Integer, RegularTask>> testList = new ArrayList<>(testMap.entrySet());

    int result = comparator.compare(testList.get(0), testList.get(1));
    int result2 = comparator.compare(testList.get(1), testList.get(0));

    assertTrue(result < 0);
    assertTrue(result2 > 0);
  }
}
