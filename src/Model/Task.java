package Model;

import java.util.ArrayList;

public class Task extends Job
{
  private ArrayList<Integer> timeSpentPerTeamMember;

  public Task(String title, String status)
  {
    super(title, status);
  }

  public void updateTime(TeamMember teamMember, int time)
  {

  }

  public void registerTime(TeamMember teamMember, int time)
  {

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
