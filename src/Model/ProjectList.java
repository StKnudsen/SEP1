package Model;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Date;

public class ProjectList
{
  private ArrayList<Project> projectList;

  public ProjectList()
  {
    projectList = new ArrayList<>();
  }

  public void createNewProject(String title, Customer customer,
      TeamMember projectCreator)
  {
    //Vi ønsker ikke, at to projekter har samme titel. Derfor valideres input.
    boolean exists = false;
    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).getTitle().equalsIgnoreCase(title))
        exists = true;
    }
    if (exists == false)
    {
      projectList.add(new Project(title, customer, projectCreator));
    }
  }

  public void addNewTeamMemberToProject(TeamMember teamMember, Project project)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).equals(project))
      {
        projectList.get(i).addTeamMember(teamMember);
      }
    }
  }

  public void addRequirement(Project project, Requirement requirement)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).equals(project))
      {
        projectList.get(i).addRequirement(requirement);
      }
    }
  }

  public void addTask(Project project, Requirement requirement, Task task)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).equals(project))
      {
        for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
        {
          if (projectList.get(i).getRequirementList().get(j)
              .equals(requirement))
            projectList.get(i).getRequirementList().get(j).addTask(task);
        }
      }
    }
  }

  public void addTeamMemberToTask(Project project, Requirement requirement,
      Task task, TeamMember teamMember)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
      {
        for (int k = 0;
             k < projectList.get(i).getRequirementList().get(j).getTasks()
                 .size(); k++)
        {
          if (projectList.get(i).getRequirementList().get(j).getTasks().get(k)
              .equals(task))
          {
            projectList.get(i).getRequirementList().get(j).getTasks().get(k)
                .addTeamMember(teamMember);
          }
        }
      }
    }
  }

  public void prioritizeRequirement(String value)
  {
    //Senere
  }

  public void prioritizeTask(String value)
  {
    //Senere
  }

  public void approveRequirement(Project project, Requirement requirement,
      String value)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
      {
        if (projectList.get(i).getRequirementList().get(j).equals(requirement))
        {
          projectList.get(i).getRequirementList().get(j).updateStatus(value);
        }
      }
    }
  }

  public void addRoleToProject(TeamMember teamMember, Project project)
  {
    //Måske er det project owner, der menes? Altså addProjectOwner
  }

  public ArrayList<Task> getAllTasks(Project project, Requirement requirement)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
      {
        if (projectList.get(i).getRequirementList().get(j).equals(requirement))
        {
          return projectList.get(i).getRequirementList().get(j).getTasks();
        }
      }
    }
    return null;
  }

  public ArrayList<Task> getAllTasks(Project project)
  {
    ArrayList<Task> allTasksForProject = new ArrayList<>();

    for (int i = 0; i < projectList.size(); i++)
    {
      for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
      {
        for (int k = 0;
             k < projectList.get(i).getRequirementList().get(j).getTasks()
                 .size(); k++)
        {
          allTasksForProject.add(
              projectList.get(i).getRequirementList().get(j).getTasks().get(k));
        }
      }
    }

    return allTasksForProject;
  }

  public void updateStatus(Project project, Requirement requirement, Task task,
      String value)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).equals(project))
      {
        for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
        {
          if (projectList.get(i).getRequirementList().get(j)
              .equals(requirement))
          {
            for (int k = 0;
                 k < projectList.get(i).getRequirementList().get(j).getTasks()
                     .size(); k++)
            {
              if (projectList.get(i).getRequirementList().get(j).getTasks()
                  .get(k).equals(task))
              {
                projectList.get(i).getRequirementList().get(j).getTasks().get(k)
                    .updateStatus(value);
              }
            }
          }
        }
      }
    }
  }

  public Project searchProject(String title)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      if (title.equalsIgnoreCase(projectList.get(i).getTitle()))
      {
        return projectList.get(i);
      }
    }
    return null;
  }

  public void updateRequirement(Project project, Requirement requirement,
      String title, String description, String type, int estimatedTime,
      Date deadline, TeamMember responsibleTeamMember)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).equals(project))
      {
        for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
        {
          if (projectList.get(i).getRequirementList().get(j)
              .equals(requirement))
          {
            requirement.setTitle(title);
            requirement.setDescription(description);
            requirement.setType(type);
            requirement.setEstimatedTime(estimatedTime);
            requirement.setDeadline(deadline);
            requirement.setResponsibleTeamMember(responsibleTeamMember);
          }
        }
      }
    }
  }

  public ArrayList<Task> searchTask(Project project, Requirement requirement,
      String title)
  {
    ArrayList<Task> tasks = new ArrayList<>();

    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).equals(project))
      {
        for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
        {
          if (projectList.get(i).getRequirementList().get(j)
              .equals(requirement))
          {
            for (int k = 0;
                 k < projectList.get(i).getRequirementList().get(j).getTasks()
                     .size(); k++)
            {
              if (projectList.get(i).getRequirementList().get(j).getTasks()
                  .get(k).getTitle().equals(title))
              {
                tasks.add(
                    projectList.get(i).getRequirementList().get(j).getTasks()
                        .get(k));
              }
            }
          }
        }
      }
    }
    return tasks;
  }

  public void updateTask(Project project, Requirement requirement, Task task, String title, String description, int estimatedTime, Date deadline, TeamMember responsibleTeamMember)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).equals(project))
      {
        for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
        {
          if (projectList.get(i).getRequirementList().get(j)
              .equals(requirement))
          {
            for (int k = 0;
                 k < projectList.get(i).getRequirementList().get(j).getTasks()
                     .size(); k++)
            {
              if (projectList.get(i).getRequirementList().get(j).getTasks()
                  .get(k).equals(task))
              {
                task.setTitle(title);
                task.setDescription(description);
                task.setEstimatedTime(estimatedTime);
                task.setDeadline(deadline);
                task.setResponsibleTeamMember(responsibleTeamMember);
              }
            }
          }
        }
      }
    }
  }


  /*
   *  Nyeste nye, få en liste med projekter
   */
  public ArrayList<Project> getProjects()
  {
    return projectList;
  }

  public ArrayList<Requirement> getRequirements()
  {
    ArrayList<Requirement> allRequirements = new ArrayList<>();

    for (Project project : projectList)
    {
      allRequirements.addAll(project.getRequirementList());
    }

    return allRequirements;
  }
}