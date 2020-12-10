package Model;

import java.util.ArrayList;

public class Task extends Job
{
  private String requirementTitle;

  //  Er denne ikke i 'TeamMember' nu?
  //  Mener vi aftalte composition - association på team members
  //  således at tiden opdateres på denne kopi af team member
  //  dermed slipper vi for at holde styr på to arrays
  //  på tasks (1. team member, 2. timeSpentPe...)
  //private ArrayList<Integer> timeSpentPerTeamMember;
  private ArrayList<TeamMember> teamMemberList;

  /*
  public Task(String title, TeamMember responsibleTeamMember, int priority)
  {
    super(title, responsibleTeamMember, priority);

    teamMemberList = new ArrayList<>();

    System.out.println("Task: " + title + " priority: " + priority + " - oprettet af " + responsibleTeamMember + ". (Hilsen: Task constructor)");
  }*/

  // Nyeste nye inden for Task constructor 10-12-2020 11:46
  /*public Task(String title, String projectTitle , TeamMember responsibleTeamMember, int priority)
  {
    super(title, projectTitle, responsibleTeamMember, priority);

    teamMemberList = new ArrayList<>();

    System.out.println("Task: " + title + " priority: " + priority + " på project: "+ projectTitle + " - oprettet af " + responsibleTeamMember + ". (Hilsen: Task constructor)");
  }*/

  // Nyeste nye inden for Task constructor 10-12-2020 12:34
  public Task(String title, String projectTitle, String requirementTitle, TeamMember responsibleTeamMember, int priority)
  {
    super(title, projectTitle, responsibleTeamMember, priority);
    this.requirementTitle = requirementTitle;

    teamMemberList = new ArrayList<>();

    System.out.println("Task: " + title + " priority: " + priority + " på project: "+ projectTitle + " - oprettet af " + responsibleTeamMember + ". (Hilsen: Task constructor)");
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

  public void addTeamMember(TeamMember teammember){
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
}
