package Model;

import java.util.ArrayList;

public class Requirement extends Job
{
  private String type;
  private String projectTitle;
  private ArrayList<Task> taskList;

  // Husk at vi skal opdatere denne i UML Class Diagram.
  public Requirement(String title, String type, String projectTitle, TeamMember responsibleTeamMember, int priority)
  {
    super(title, responsibleTeamMember, priority);
    this.type = type;
    this.projectTitle = projectTitle;
    taskList = new ArrayList<>();
    System.out.println("Requirement: " + title + " with priority: " + priority + " - oprettet af " + responsibleTeamMember + ". (Hilsen: Requirement constructor)");
  }

  // I ViewAddRequirement, skal vi have ændret til at benytte den anden constructor!
  public Requirement(String title, String status, String type, String projectTitle)
  {
    super(title, status);
    this.type = type;
    this.projectTitle = projectTitle;
    taskList = new ArrayList<>();
  }

  public void addTask(Task task)
  {
    taskList.add(task);
  }

  // Ny fancy dansy udgave af.... opret en task!
  public void addTask(String taskTitle, TeamMember responsibleTeamMember)
  {
    Task newTask = new Task(
        taskTitle, responsibleTeamMember, taskList.size() + 1
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
    return title.equals(other.getTitle()); //&& status.equals(other.status);// && type.equals(other.type);
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
  }

  public String getProjectTitle()
  {
    return projectTitle;
  }

}
