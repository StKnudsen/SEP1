package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Requirement extends Job implements Serializable
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

  public void resortTasks()
  {
    ArrayList<Task> oldTaskList = copyTaskList();

    taskList.clear();

    for (int i = 0; i < oldTaskList.size(); i++)
    {
      taskList.add(i,
          new Task(
              oldTaskList.get(i).getTitle(),
              oldTaskList.get(i).getRequirementTitle(),
              oldTaskList.get(i).getProjectTitle(),
              oldTaskList.get(i).getResponsibleTeamMember(),
              i+1
          )
      );
    }
  }

  private ArrayList<Task> copyTaskList()
  {
    ArrayList<Task> copy = new ArrayList<>();

    copy.addAll(taskList);
    /*for (int i = 0; i < taskList.size(); i++)
    {
      copy.add(taskList.get(i));
    }*/

    return copy;
  }

  public ArrayList<Task> getTeamMemberTasks(TeamMember teamMember)
  {
    ArrayList<Task> teamMemberTask = new ArrayList<>();

    for (Task task : taskList)
    {
      if (task.getResponsibleTeamMember().equals(teamMember) || task.getTeamMemberList().contains(teamMember))
      {
        teamMemberTask.add(task);
      }
    }

    return teamMemberTask;
  }
}
