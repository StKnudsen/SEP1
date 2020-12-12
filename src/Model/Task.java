package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Task extends Job implements Serializable
{
  private String requirementTitle;
  private int estimatedTime;
  private ArrayList<TeamMember> teamMemberList;

  public Task(String title, String requirementTitle, String projectTitle,
      TeamMember responsibleTeamMember, int priority)
  {
    super(title, projectTitle, responsibleTeamMember, priority);
    this.requirementTitle = requirementTitle;
    teamMemberList = new ArrayList<>();
  }

  public String getRequirementTitle()
  {
    return requirementTitle;
  }

  public void updateTime(TeamMember teamMember, int time)
  {

  }

  public void registerTime(TeamMember teamMember, int time)
  {

  }

  public void addTeamMember(TeamMember teammember)
  {
    teamMemberList.add(teammember);
  }

  public TeamMember getTeamMember(TeamMember teamMember)
  {
    for (TeamMember member : teamMemberList)
    {
      if (member.equals(teamMember))
        return teamMember;
    }
    return null;
  }

  public ArrayList<TeamMember> getTeamMemberList()
  {
    return teamMemberList;
  }

  /* PT kan man ikke slette en team member!
   public void deleteTeamMember(TeamMember teamMember){
     teamMemberList.remove(teamMember);
   }
    */

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Task))
    {
      return false;
    }
    Task other = (Task) obj;
    return title.equals(other.getTitle()) && status.equals(other.status);
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

  public void setEstimatedTime(int hours)
  {
    this.estimatedTime = hours;
  }

  public String getEstimatedTime()
  {
    return Integer.toString(estimatedTime);
  }
}
