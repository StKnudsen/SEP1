package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable
{
  private String title;
  private String description;
  private Customer customer;
  private TeamMember projectCreator;
  private ArrayList<TeamMember> teamMemberList;
  private ArrayList<Requirement> requirementList;

  public Project(String title, Customer customer, TeamMember projectCreator)
  {
    this.title = title;
    this.customer = customer;
    this.projectCreator = projectCreator;

    teamMemberList = new ArrayList<>();
    requirementList = new ArrayList<>();
  }

  public void addDescription(String description)
  {
    this.description = description;
  }

  public String getTitle()
  {
    return title;
  }

  public String getDescription()
  {
    return description;
  }

  public TeamMember getProjectCreator()
  {
    return projectCreator;
  }

  public String getCustomer()
  {
    return customer.toString();
  }

  public void addTeamMember(TeamMember teamMember)
  {
    teamMemberList.add(teamMember);
  }

  public ArrayList<TeamMember> getTeamMemberList()
  {
    return teamMemberList;
  }

  // Breaking News! addRequirement tager nu parametere i stedet for object
  public void addRequirement(String title, String type, String projectTitle, TeamMember responsibleTeamMember)
  {
    // Her oprettes requirement, så kan vi også sætte prioriteringen!
    Requirement newRequirement = new Requirement(
        title, type, projectTitle, responsibleTeamMember, requirementList.size() +1
    );

    requirementList.add(newRequirement);
  }

  public void addRequirement(Requirement requirement)
  {
    requirementList.add(requirement);
  }

  public ArrayList<Requirement> getRequirementList()
  {
    return requirementList;
  }

  public Requirement searchRequirement(String requirementTitle)
  {
    for (Requirement requirement : requirementList)
    {
      if (requirementTitle.equalsIgnoreCase(requirement.getTitle()))
      {
        return requirement;
      }
    }

    return null;
  }


  //Der sammenlignes kun de tre værdier fra constructoren,
  // fordi det ikke er muligt at oprette et projekt med
  // samme titel men fx forskellige beskrivelser
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Project))
    {
      return false;
    }
    Project other = (Project) obj;
    return title.equals(other.getTitle()) && customer.equals(other.customer) && projectCreator.equals(other.projectCreator);
  }

  public String getEstimate()
  {
    int estimate = 0;

    for (Requirement requirement : requirementList )
    {
      for (Task task : requirement.getTasks())
      {
        estimate += Integer.parseInt(task.getEstimatedTime());
      }
    }

    return Integer.toString(estimate);
  }

  public String getTimeSpent()
  {
    int timeSpent = 0;

    for (Requirement requirement : requirementList )
    {
      for (Task task : requirement.getTasks())
      {
        timeSpent += task.getTimeSpent();
      }
    }

    return Integer.toString(timeSpent);
  }

  public String getResponsibleTeamMember()
  {
    return projectCreator.getName();
  }

  public ArrayList<Requirement> getRequirementsForCurrentUser(TeamMember currentUser)
  {
    ArrayList<Requirement> requirementArrayList = new ArrayList<>();

    for (Requirement requirement : requirementList)
    {
      if (requirement.getResponsibleTeamMember().equals(currentUser))
      {
        requirementArrayList.add(requirement);
      }
    }

    return requirementArrayList;
  }

  public String toString()
  {
    return title;
  }

  public void addTeamMemberToTask(Requirement requirement, Task task, TeamMember teamMember)
  {
    for (Requirement requirementElement : requirementList)
    {
      if (requirementElement.equals(requirement))
      {
        requirementElement.addTeamMemberToTask(task, teamMember);
      }
    }
  }

  public void prioritize(Requirement requirement, String value)
  {
    try
    {
      for (Requirement requirementElement : requirementList)
      {
        if (requirementElement.equals(requirement))
        {
          int requirementPriority = Integer.parseInt(requirementElement.getPriority());
          int requirementAmount = requirementList.size();

          if (value.equals("OP") && requirementPriority > 1)
          {
            requirementList.add(requirementPriority - 2, requirementList.remove(requirementPriority - 1));
          }

          if (value.equals("NED") && requirementPriority < requirementAmount)
          {
            requirementList.add(requirementPriority, requirementList.remove(requirementPriority - 1));
          }

          if (value.equals("TIL TOP") && requirementPriority > 1)
          {
            requirementList.add(0, requirementList.remove(requirementPriority - 1));
          }

          if (value.equals("TIL BUND") && requirementPriority != requirementAmount)
          {
            requirementList.add(requirementAmount - 1, requirementList.remove(requirementPriority - 1));
          }

          resortRequirements();
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void resortRequirements()
  {
    ArrayList<Requirement> oldRequirementList = copyRequirementList();

    requirementList.clear();

    for (int i = 0; i < oldRequirementList.size(); i++)
    {
      requirementList.add(i,
          new Requirement(
              oldRequirementList.get(i).getTitle(),
              oldRequirementList.get(i).getProjectTitle(),
              oldRequirementList.get(i).getType(),
              oldRequirementList.get(i).getResponsibleTeamMember(),
              i+1,
              oldRequirementList.get(i).getTasks()
          )
      );
    }

  }

  private ArrayList<Requirement> copyRequirementList()
  {
    ArrayList<Requirement> copy = new ArrayList<>();

    copy.addAll(requirementList);

    return copy;
  }

  public void prioritizeTask(Requirement requirement, Task task, String value)
  {
    for (Requirement requirementElement : requirementList)
    {
      if (requirementElement.equals(requirement))
      {
        requirementElement.prioritize(task, value);
      }
    }
  }

  public void checkIfAllTasksIsDoneForRequirement(String requirementTitle)
  {
    for (Requirement requirementElement : requirementList)
    {
      if (requirementElement.getProjectTitle().equals(requirementTitle))
      {
        boolean allTasksIsDone = true;
        for (Task task : requirementElement.getTasks())
        {
          if (!task.getStatus().equals("Ended"))
          {
            allTasksIsDone = false;
          }
        }
        if (allTasksIsDone)
        {
          requirementElement.updateStatus("Ended");
        }
      }
    }
  }
}
