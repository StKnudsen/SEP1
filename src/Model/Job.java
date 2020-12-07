package Model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Job
{
  int id, projectId, priority, estimatedTime;
  String title, description, status;
  Date deadline;
  TeamMember responsibleTeamMember;
  ArrayList<TeamMember> teamMemberList;

  public Job(String title, String status)
  {
    this.title = title;
    this.status = status;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getTitle()
  {
    return title;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getDescription()
  {
    return description;
  }

  public void setPriority(int priority)
  {
    this.priority = priority;
  }

  public int getPriority()
  {
    return priority;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public String getStatus()
  {
    return status;
  }

  public void setDeadline(Date deadline)
  {
    this.deadline = deadline;
  }

  public Date getDeadline()
  {
    return deadline;
  }

  public void addTeamMembers(TeamMember teammember){
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

  /* PT kan man ikke slette en team member!
   public void deleteTeamMember(TeamMember teamMember){
     teamMemberList.remove(teamMember);
   }
    */

  public void setResponsibleTeamMember(TeamMember responsibleTeamMember)
  {
    this.responsibleTeamMember = responsibleTeamMember;
  }

  public TeamMember getResponsibleTeamMember()
  {
    return responsibleTeamMember;
  }

}
