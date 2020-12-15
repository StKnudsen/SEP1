package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectList implements Serializable
{
  private ArrayList<Project> projectList;

  public ProjectList()
  {
    projectList = new ArrayList<>();
  }

  public void createNewProject(String title, Customer customer, TeamMember projectCreator)
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
    for (Project projectElement : projectList)
    {
      if (projectElement.equals(project))
      {
        projectElement.addTeamMemberToTask(requirement, task, teamMember);
      }
    }
  }

  public String getTimeSpentForTeamMember(TeamMember selectedTeamMember)
  {
    int spentHours = 0;

    // for hvert project, se om teammember er på og tilføj tid, hvis det er sandt
    for (Project project : projectList)
    {
      for (Requirement requirement : project.getRequirementList())
      {
        for (Task task : requirement.getTasks())
        {
          for (int i = 0; i < task.getTeamMemberList().size(); i++)
          {
            if (task.getTeamMemberList().get(i).equals(selectedTeamMember))
            {
              spentHours += task.getTeamMemberList().get(i).getTimeSpent();
            }
          }
        }
      }
    }

    /*
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
    }*/

    return Integer.toString(spentHours);
    //return Integer.toString(987);
  }

  public void updateStatus(Project project, Requirement requirement, Task task, String value)
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

  public void updateRequirement(Project project, Requirement requirement, String title, String description, String type, LocalDate deadline, TeamMember responsibleTeamMember)
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

  public void updateTask(Project project, Requirement requirement, Task task, String title, String description, int estimatedTime, MyDate deadline, TeamMember responsibleTeamMember)
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
          if (!projectList.get(i).getRequirementList().get(j).getStatus().equals("Approved"))
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
    }

    return allTeamMemberTasks;
  }

  public ArrayList<Project> getTeamMemberProjects(TeamMember selectedTeamMember)
  {
    ArrayList<Project> projectArrayList = new ArrayList<>();

    for (int i = 0; i < projectList.size(); i++)
    {
      for (int j = 0; j < projectList.get(i).getTeamMemberList().size(); j++)
      {
        if (projectList.get(i).getResponsibleTeamMember().equals(selectedTeamMember))
        {
          projectArrayList.add(projectList.get(i));
        }
        else if(projectList.get(i).getTeamMemberList().get(j).equals(selectedTeamMember))
        {
          projectArrayList.add(projectList.get(i));
        }
      }
    }
    return projectArrayList;
  }

  public void checkIfAllTasksIsDoneForRequirement(Project project, String requirementTitle)
  {
    for (Project projectElement : projectList)
    {
      if (projectElement.equals(project))
      {
        projectElement.checkIfAllTasksIsDoneForRequirement(requirementTitle);
        System.out.println("requirement: " + requirementTitle);
      }
    }
  }

  public void prioritizeRequirement(Project project, Requirement requirement, String value)
  {
    for (Project projectElement : projectList)
    {
      if (projectElement.equals(project))
      {
        projectElement.prioritize(requirement, value);
      }
    }
  }

  public void prioritizeTask(String value, Task task, Requirement requirement, Project project)
  {
    for (Project projectElement : projectList)
    {
      if (projectElement.equals(project))
      {
        projectElement.prioritizeTask(requirement, task, value);

      }
    }
  }

  public ArrayList<Task> searchTask(Project project, Requirement requirement, String title)
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

  public ArrayList<Project> searchProjectList(String text, TeamMember teamMember)
  {
    ArrayList<Project> projectArrayList = new ArrayList<>();

    for (Project project : projectList)
    {
      if (project.getTitle().contains(text) && project.getResponsibleTeamMember().equals(teamMember.getName()))
      {
        projectArrayList.add(project);
      }
    }

    return projectArrayList;
  }

  public ArrayList<Requirement> searchRequirementList(String searchText, TeamMember currentUser)
  {
    ArrayList<Requirement> requirementArrayList = new ArrayList<>();

    for (Project project : projectList)
    {
      for (Requirement requirement : project.getRequirementList())
      {
        if (requirement.getTitle().contains(searchText) && requirement.getResponsibleTeamMember().equals(currentUser))
        {
          requirementArrayList.add(requirement);
        }
      }
    }

    return requirementArrayList;
  }

  public ArrayList<Task> searchTaskList(String searchText, TeamMember currentUser)
  {
    ArrayList<Task> taskArrayList = new ArrayList<>();

    for (Project project : projectList)
    {
      taskArrayList.addAll(project.searchTaskList(searchText, currentUser));
    }

    return taskArrayList;
  }
}