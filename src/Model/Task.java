package Model;

import java.util.ArrayList;

public class Task extends Job
{
  private String requirementTitle;
  private int estimatedTime;
  private ArrayList<TeamMember> teamMemberList;

  public Task(String title, String requirementTitle, String projectTitle, TeamMember responsibleTeamMember, int priority)
  {
    super(title, projectTitle, responsibleTeamMember, priority);
    this.requirementTitle = requirementTitle;
    teamMemberList = new ArrayList<>();
    addTeamMember(responsibleTeamMember);
  }

  // Bruges til Requirement.resortTasks()
  public Task(String title, String requirementTitle, String projectTitle, TeamMember responsibleTeamMember, int priority, ArrayList<TeamMember> teamMemberList)
  {
    super(title, projectTitle, responsibleTeamMember, priority);
    this.requirementTitle = requirementTitle;
    this.teamMemberList = teamMemberList;
  }

  public String getRequirementTitle()
  {
    return requirementTitle;
  }

  public void setEstimatedTime(int hours)
  {
    this.estimatedTime = hours;
  }

  public String getEstimatedTime()
  {
    return Integer.toString(estimatedTime);
  }

  public void updateTime(TeamMember teamMember, int time)
  {
    for (int i = 0; i < teamMemberList.size(); i++)
    {
      if (teamMemberList.get(i).equals(teamMember))
      {
        teamMemberList.get(i).updateTime(time);
      }
    }
  }

  public int getTimeSpent()
  {
    int timeSpent = 0;

    for (TeamMember teamMember : teamMemberList)
    {
      timeSpent += teamMember.getTimeSpent();
    }

    return timeSpent;
  }

  public void addTeamMember(TeamMember teamMember)
  {
    if (!teamMemberList.contains(teamMember))
    {
      teamMemberList.add(teamMember);
    }
  }

  public ArrayList<TeamMember> getTeamMemberList()
  {
    ArrayList<TeamMember> teamMemberArrayList = new ArrayList<>();

    for (int i = 0; i < teamMemberList.size(); i++)
    {
      teamMemberArrayList.add(teamMemberList.get(i).copy());
      teamMemberArrayList.get(i).updateTime(teamMemberList.get(i).getTimeSpent());
    }

    return teamMemberArrayList;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Task))
    {
      return false;
    }
    Task other = (Task) obj;
    return title.equals(other.getTitle()) && status.equals(other.status);
  }
}
