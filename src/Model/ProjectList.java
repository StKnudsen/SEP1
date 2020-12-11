package Model;

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

  public void addRequirement(String title, String type, String projectTitle, TeamMember responsibleTeamMember)
  {
    // Find project..
    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).getTitle().equals(projectTitle))
      {
        projectList.get(i).addRequirement(title, projectTitle, type, responsibleTeamMember);
      }
    }
  }

  /*
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
  */

  // Ny delux udgave..
  public void addTask(Project project, Requirement requirement, String taskTitle, TeamMember responsibleTeamMember)
  {
    for (int i = 0; i < projectList.size(); i++)
    {
      if (projectList.get(i).equals(project))
      {
        for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
        {
          if (projectList.get(i).getRequirementList().get(j)
              .equals(requirement))
            projectList.get(i).getRequirementList().get(j).addTask(
                taskTitle, requirement.getTitle(), project.getTitle(), responsibleTeamMember
            );
        }
      }
    }
  }

  public void addTeamMemberToTask(Project project, Requirement requirement, Task task, TeamMember teamMember)
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
                .addTeamMember(teamMember.copy());
          }
        }
      }
    }
  }

  public void prioritizeRequirement(String value)
  {
    //Senere
  }

  public void prioritizeTask(String value, Task task, Requirement requirement, Project project)
  {
    for (Project item : projectList)
    {
      if (item.equals(project))
      {
        for (int j = 0; j < item.getRequirementList().size(); j++)
        {
          if (item.getRequirementList().get(j).equals(requirement))
          {
            for (int k = 0;
                 k < item.getRequirementList().get(j).getTasks().size(); k++)
            {
              if (item.getRequirementList().get(j).getTasks().get(k).equals(task))
              {
                int taskPriority = Integer.parseInt(item.getRequirementList().get(j).getTasks().get(k).getPriority());
                int taskAmount = item.getRequirementList().get(j).getTasks().size();

                if (value.equals("OP") && taskPriority > 1)
                {
                  item.getRequirementList().get(j).getTasks().add(taskPriority - 2, item.getRequirementList().get(j).getTasks().remove(taskPriority - 1));
                  item.getRequirementList().get(j).resortTasks();
                }

                if (value.equals("NED") && taskPriority < taskAmount)
                {
                  item.getRequirementList().get(j).getTasks().add(taskPriority, item.getRequirementList().get(j).getTasks().remove(taskPriority - 1));
                  item.getRequirementList().get(j).resortTasks();
                  for (int i = 0; i < item.getRequirementList().get(j).getTasks().size(); i++)
                  {
                    System.out.println(item.getRequirementList().get(j).getTasks().get(i).getTitle());
                  }
                }

                if (value.equals("TIL TOP") && taskPriority != 1)
                {
                  item.getRequirementList().get(j).getTasks().add(0, item.getRequirementList().get(j).getTasks().remove(taskPriority - 1));
                  item.getRequirementList().get(j).resortTasks();
                }

                if (value.equals("TIL BUND") && taskPriority != taskAmount)
                {
                  item.getRequirementList().get(j).getTasks().add(taskAmount - 1, item.getRequirementList().get(j).getTasks().remove(taskPriority - 1));
                  item.getRequirementList().get(j).resortTasks();
                }
              }
            }
          }
        }
      }
    }

    /*for (Project projectElement : projectList)
    {
      if (projectElement.equals(project))
      {
        for (Requirement requirementElement : projectElement.getRequirementList())
        {
          if (requirementElement.equals(requirement))
          {
            int numberOfTasks = requirementElement.getTasks().size();

            for (Task taskElement : requirementElement.getTasks())
            {
              if (taskElement.equals(task))
              {
                int taskPriority = Integer.parseInt(taskElement.getPriority());
                if (value.equals("OP") && taskPriority > 1)
                {
                  taskElement.setPriority(taskPriority - 1); // Prioriterings index, ikke array index
                  requirementElement.getTasks().add(
                      taskPriority - 2, requirementElement.getTasks().remove(taskPriority-1)
                      );
                  requirementElement.resortTasks();
                }
              }
            }
          }
        }
      }
    }*/
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
      String title, String description, String type,
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

  public ArrayList<Project> getProjectsCurrentUser(TeamMember currentUser)
  {
    ArrayList<Project> projectArrayList = new ArrayList<>();
    for (int i = 0; i < projectList.size(); i++)
    {
      if(projectList.get(i).getResponsibleTeamMember().equals(currentUser.getName()))
      {
        projectArrayList.add(projectList.get(i));
      }
    }
    return projectArrayList;
  }

  public ArrayList<Requirement> getAllRequirementsCurrentUser(TeamMember currentUser)
  {
    ArrayList<Requirement> requirementArrayList = new ArrayList<>();
    for (int i = 0; i < projectList.size(); i++)
    {
      for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
      {
        if(projectList.get(i).getRequirementList().get(j).getResponsibleTeamMember().equals(currentUser))
        {
          requirementArrayList.add(projectList.get(i).getRequirementList().get(j));
        }
      }
    }
    return requirementArrayList;
  }

  public ArrayList<Task> getAllTeamMemberTasks(TeamMember currentUser)
  {
    ArrayList<Task> allTeamMemberTasks = new ArrayList<>();

    for (int i = 0; i < projectList.size(); i++)
    {
      for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
      {
        for (int k = 0; k < projectList.get(i).getRequirementList().get(j).getTasks().size(); k++)
        {
          //  Hvis currentUser er responsibleTeamMember
          if (projectList.get(i).getRequirementList().get(j).getTasks().get(k).getResponsibleTeamMember().equals(currentUser))
          {
            allTeamMemberTasks.add(projectList.get(i).getRequirementList().get(j).getTasks().get(k));
          }
          //  Hvis currentUser er team member ud over responsibleTeamMember
          else if (projectList.get(i).getRequirementList().get(j).getTasks().get(k).getTeamMemberList().contains(currentUser))
          {
            allTeamMemberTasks.add(projectList.get(i).getRequirementList().get(j).getTasks().get(k));
          }
        }
      }
    }

    return allTeamMemberTasks;
  }

  public String getTimeSpentForTeamMember(TeamMember selectedTeamMember)
  {
    int spentHours = 0;
    for (int i = 0; i < projectList.size(); i++)
    {
      for (int j = 0; j < projectList.get(i).getRequirementList().size(); j++)
      {
        for (int k = 0; k < projectList.get(i).getRequirementList().get(j).getTasks().size(); k++)
        {
          for (int l = 0; l < projectList.get(i).getRequirementList().get(j).getTasks().get(k).getTeamMemberList().size(); l++)
          {
            if(projectList.get(i).getRequirementList().get(j).getTasks().get(k).getTeamMemberList().get(l).equals(selectedTeamMember))
            {
              spentHours += projectList.get(i).getRequirementList().get(j).getTasks().get(k).getTeamMemberList().get(l).getTimeSpent();
            }
          }
        }
      }
    }
    return Integer.toString(spentHours);
  }

  public ArrayList<Project> getTeamMemberProjects(TeamMember selectedTeamMember)
  {
    ArrayList<Project> projectArrayList = new ArrayList<>();
    for (int i = 0; i < projectList.size(); i++)
    {
      for (int j = 0; j < projectList.get(i).getTeamMemberList().size(); j++)
      {
        if(projectList.get(i).getTeamMemberList().get(j).equals(selectedTeamMember))
        {
          projectArrayList.add(projectList.get(i));
        }
      }
    }
    return projectArrayList;
  }
}