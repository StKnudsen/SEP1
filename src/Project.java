import java.util.ArrayList;

public class Project
{
  private int id, customerId;
  private String title, description;
  private TeamMember projectCreator;
  private ArrayList<TeamMember> TeamMemberList;
  private ArrayList<Requirement> requirements;

  public Project(int id, int customerId, String title, String description,
      TeamMember projectCreator, ArrayList<TeamMember> teamMemberList)
  {
    this.id = id;
    this.customerId = customerId;
    this.title = title;
    this.description = description;
    this.projectCreator = projectCreator;
    TeamMemberList = teamMemberList;
  }

  public Project(int id, int customerId)
  {
    this.id = id;
    this.customerId = customerId;
    this.title = "No title";
  }

  //TODO komme denne her getter på UML
  public String getTitle()
  {
    return title;
  }

  public void addTeamMember(TeamMember member)
  {
    TeamMemberList.add(member);
  }

  public ArrayList<TeamMember> getTeamMembers()
  {
    return TeamMemberList;
  }

}
