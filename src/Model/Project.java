package Model;

import java.util.ArrayList;

public class Project
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

  public String getTitle()
  {
    return title;
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
}
