package src;

import java.lang.reflect.Type;

public class RegularTask implements TypaskType {
  private final int taskId;
  private final int hours;
  private final int minutes;
  private final String taskTitle;
  private final Type taskType;
  public RegularTask(int taskId, int hours, int minutes, String taskTitle, Type taskType) {
    this.taskId = taskId;
    this.hours = hours;
    this.minutes = minutes;
    this.taskTitle = taskTitle;
    this.taskType = taskType;
  }
  public int getTaskId() {
    return taskId;
  }
  public int getHours() {
    return hours;
  }
  public int getMinutes() {
    return minutes;
  }
  public String getTaskTitle() {
    return taskTitle;
  }
  public Type getTaskType() {
    return taskType;
  }
  @Override
  public String toString() {
    return "Task " + taskId + " - " + taskTitle + " (" + hours + "h " + minutes + "m) " +
            "[" + taskType + "]";
  }
}
