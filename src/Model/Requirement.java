package Model;

import java.util.ArrayList;

public class Requirement extends Job
{
  private String type;
  private ArrayList<Task> taskList;

  public Requirement(String title, String projectTitle, String type, TeamMember responsibleTeamMember, int priority)
  {
    super(title, projectTitle, responsibleTeamMember, priority);
    this.type = type;
    taskList = new ArrayList<>();
  }

  public void addTask(String taskTitle, String requirementTitle, String projectTitle, TeamMember responsibleTeamMember)
  {
    Task newTask = new Task(
        taskTitle, requirementTitle, projectTitle, responsibleTeamMember, taskList.size() + 1
    );
    taskList.add(newTask);
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
    return title.equals(other.getTitle()) && status.equals(other.status) && type.equals(other.type);
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
  }
}
