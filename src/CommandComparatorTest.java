package src;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandComparatorTest {

  @Test
  public void testComparator() {
    Map<Main.Command, String> commands = new HashMap<>();

    commands.put(Main.Command.HELP, "[9] List of commands, txt: ");
    commands.put(Main.Command.NEW_LIST, "[1] New task, txt: ");
    commands.put(Main.Command.CHECK_LIST, "[2] Check task, txt: ");
    commands.put(Main.Command.CORRECT_LIST, "[3] Edit task, txt: ");
    commands.put(Main.Command.SORT_LIST, "[4] Sort task, txt: ");
    commands.put(Main.Command.EXPORT_LIST, "[5] Export task, txt: ");
    commands.put(Main.Command.EXIT, "[0] EXIT, txt: ");

    List<Map.Entry<Main.Command, String>> entries = new ArrayList<>(commands.entrySet());
    entries.sort(new CommandComparator());


    assertEquals(Main.Command.EXIT, entries.get(0).getKey());
    assertEquals(Main.Command.NEW_LIST, entries.get(1).getKey());
    assertEquals(Main.Command.CHECK_LIST, entries.get(2).getKey());
    assertEquals(Main.Command.CORRECT_LIST, entries.get(3).getKey());
    assertEquals(Main.Command.SORT_LIST, entries.get(4).getKey());
    assertEquals(Main.Command.EXPORT_LIST, entries.get(5).getKey());
    assertEquals(Main.Command.HELP, entries.get(6).getKey());
  }
}
