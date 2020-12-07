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
}
