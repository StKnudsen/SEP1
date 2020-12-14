package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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

  //  Bliver brugt i Project.resort()
  public Requirement(String title, String projectTitle, String type, TeamMember responsibleTeamMember, int priority, ArrayList<Task> taskList)
  {
    super(title, projectTitle, responsibleTeamMember, priority);
    this.type = type;
    this.taskList = taskList;
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
              i+1,
              oldTaskList.get(i).getTeamMemberList()
          )
      );
    }
  }

  private ArrayList<Task> copyTaskList()
  {
    ArrayList<Task> copy = new ArrayList<>();

    copy.addAll(taskList);

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

  public void addTeamMemberToTask(Task task, TeamMember teamMember)
  {
    for (Task taskElement : taskList)
    {
      if (taskElement.equals(task))
      {
        taskElement.addTeamMember(teamMember);
      }
    }
  }

  public void prioritize(Task task, String value)
  {
    try
    {
      for (Task taskElement : taskList)
      {
        if (taskElement.equals(task))
        {
          int taskPriority = Integer.parseInt(taskElement.getPriority());
          int taskAmount = taskList.size();

          if (value.equals("OP") && taskPriority > 1)
          {
            taskList.add(taskPriority - 2, taskList.remove(taskPriority - 1));
          }

          if (value.equals("NED") && taskPriority < taskAmount)
          {
            taskList.add(taskPriority, taskList.remove(taskPriority - 1));
          }

          if (value.equals("TIL TOP") && taskPriority > 1)
          {
            taskList.add(0, taskList.remove(taskPriority - 1));
          }

          if (value.equals("TIL BUND") && taskPriority != taskAmount)
          {
            taskList.add(taskAmount - 1, taskList.remove(taskPriority - 1));
          }

          resortTasks();
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public ArrayList<Task> searchTaskList(String searchText, TeamMember currentUser)
  {
    ArrayList<Task> taskArrayList = new ArrayList<>();

    for (Task task : taskList)
    {
      if (task.getTitle().contains(searchText) && task.getTeamMemberList().contains(currentUser))
      {
        taskArrayList.add(task);
      }
    }

    return taskArrayList;
  }
}
