package src;

import java.time.LocalDate;

public class RegularTask  {
  private final int taskId;
  private final int hours;
  private final int minutes;
  private String taskTitle;
  public RegularTask(int taskId, int hours, int minutes, String taskTitle) {
    this.taskId = taskId;
    this.hours = hours;
    this.minutes = minutes;
    this.taskTitle = taskTitle;
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

  @Override
  public String toString() {
    return "Task " + taskId + " - " + taskTitle + " (" + hours + "h " + minutes + "m)";
  }
  public void setTaskHours
          (int taskHours) {
  }

  public void setTaskMinutes(int taskMinutes) {
  }

  public void setTaskTitle(String taskTitle) {
    this.taskTitle = taskTitle;
  }
}
