package Model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Job implements Serializable
{
  String title, projectTitle, description, status = "Not Started";
  int priority;
  MyDate deadline;
  TeamMember responsibleTeamMember;

  public Job(String title, String projectTitle, TeamMember responsibleTeamMember, int priority)
  {
    this.title = title;
    this.projectTitle = projectTitle;
    this.responsibleTeamMember = responsibleTeamMember;
    this.priority = priority;
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

  public void setDeadline(MyDate deadline)
  {
    this.deadline = deadline;
  }


  public void setDeadline(LocalDate deadline)
  {
    this.deadline = new MyDate(deadline);
  }

  public MyDate getDeadline()
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
