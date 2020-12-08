package Model;

import java.util.ArrayList;

public class Task extends Job
{
  //  Er denne ikke i 'TeamMember' nu?
  //  Mener vi aftalte composition - association på team members
  //  således at tiden opdateres på denne kopi af team member
  //  dermed slipper vi for at holde styr på to arrays
  //  på tasks (1. team member, 2. timeSpentPe...)
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
