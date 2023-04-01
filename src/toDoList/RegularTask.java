package src.toDoList;
public class RegularTask {
  private final int taskId;
  private int hours;
  private  int minutes;
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

  public void setHours(int hours) {
    this.hours = hours;
  }

  public int getMinutes() {
    return minutes;
  }

  public void setMinutes(int minutes) {
    this.minutes = minutes;
  }

  public String getTaskTitle() {
    return taskTitle;
  }

  public void setTaskTitle(String taskTitle) {
    this.taskTitle = taskTitle;
  }

  @Override
  public String toString() {
    return "Task " + taskId + " - " + taskTitle + " (" + hours + "h " + minutes + "m)";
  }
}