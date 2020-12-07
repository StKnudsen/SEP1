package Model;

import java.util.ArrayList;

public class Requirement extends Job
{
  private String type;
  private ArrayList<Task> taskList;

  public Requirement(String title, String status, String type)
  {
    super(title, status);
    this.type = type;
    taskList = new ArrayList<>();
  }

  public Requirement(String title, String status)
  {
    super(title, status);
  }

  public void addTask(Task task)
  {
    taskList.add(task);
  }

  public ArrayList<Task> getTasks()
  {
    return taskList;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Requirement))
    {
      return false;
    }
    Requirement other = (Requirement) obj;
    return title.equals(other.getTitle()) && status.equals(other.status) && type
        .equals(other.type);
  }

  public void setType(String type)
  {
    this.type = type;
  }

}
