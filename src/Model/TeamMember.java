package Model;

import java.io.Serializable;

public class TeamMember implements Serializable
{
  private String name;
  private int timeSpentOnTask;

  public TeamMember(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  public void updateTime(int time)
  {
    if ((timeSpentOnTask + time) >= 0)
    {
      timeSpentOnTask += time;
    }
  }

  public int getTimeSpent()
  {
    return timeSpentOnTask;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof TeamMember))
    {
      return false;
    }
    TeamMember other = (TeamMember) obj;

    return name.equals(other.name);
  }

  public TeamMember copy()
  {
    TeamMember other;
    other = new TeamMember(name);
    return other;
  }

  public String toString()
  {
    return name;
  }

}
