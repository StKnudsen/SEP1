package Model;

import java.util.ArrayList;

public class Requirement extends Job
{
  private String type;
  private String projectTitle;
  private ArrayList<Task> taskList;

  /*
  public Requirement(String title, String status, String type)
  {
    super(title, status);
    this.type = type;
    //super.priority = .size();
    taskList = new ArrayList<>();
  } */

  // Husk at vi skal opdatere denne i UML Class Diagram
  public Requirement(String title, String status, String type, String projectTitle)
  {
    super(title, status);
    this.type = type;
    this.projectTitle = projectTitle;
    //super.priority = .size();
    taskList = new ArrayList<>();
  }

  //  Denne "constructor" skal fjernes, når vi går væk fra dummy data
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
    return title.equals(other.getTitle()) && status.equals(other.status);// && type.equals(other.type);
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getProjectTitle()
  {
    return projectTitle;
  }

}
