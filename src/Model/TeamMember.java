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

  //Scrum master kan både lægge timer til og trække fra
  public void updateTime(int time)
  {
    timeSpentOnTask += time;
  }

  //Team member kan lægge timer til
  public void registerTime(int time)
  {
    if(time > 0)
    {
      timeSpentOnTask += time;
    }
  }

  public TeamMember copy()
  {
    TeamMember other;
    other = new TeamMember(name);
    return other;
  }

  //  Denne er nødvendig for at navne vises i choicebox på login
  public String toString()
  {
    return name;
  }

  public int getTimeSpent()
  {
    return timeSpentOnTask;
  }
}
