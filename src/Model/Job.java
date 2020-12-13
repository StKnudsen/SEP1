package Model;

import java.util.Date;

public abstract class Job
{
  int priority;
  String title, projectTitle, description, status;
  Date deadline;
  TeamMember responsibleTeamMember;

  // NYT! Nu med responsible Team Member
  public Job(String title, String projectTitle, TeamMember responsibleTeamMember, int priority)
  {
    this.title = title;
    this.projectTitle = projectTitle;
    this.responsibleTeamMember = responsibleTeamMember;
    this.priority = priority;
    this.status = "Not Started";
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getTitle()
  {
    return title;
  }

  public String getProjectTitle()
  {
    return projectTitle;
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

  public String getPriority()
  {
    return Integer.toString(priority);
  }

  public void updateStatus(String status)
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

  public void setResponsibleTeamMember(TeamMember responsibleTeamMember)
  {
    this.responsibleTeamMember = responsibleTeamMember;
  }

  public TeamMember getResponsibleTeamMember()
  {
    return responsibleTeamMember;
  }
}
