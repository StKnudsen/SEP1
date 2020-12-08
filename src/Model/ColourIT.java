package Model;

import java.util.ArrayList;
import java.util.Date;

public class ColourIT
{
  // Bruges lige til opsætning af View Handler

  private TeamMember currentUser;

  private ProjectList projectList;
  private CustomerList customerList;
  private EmployeeList employeeList;

  /*
   *  Herunder er nye fields vi bruger for at få data til views!
   */
  private Project selectedProject;
  private Requirement selectedRequirement;
  private Task selectedTask;

  public ColourIT()
  {
    //  Når ColourIT PMS starter, si initialiseres
    //  vores listehaløjer således de kan bruges
    projectList = new ProjectList();
    customerList = new CustomerList();
    employeeList = new EmployeeList();

    /*
     *  Når vi har lavet persist data med filer
     *  så skal data vel indlæses her ;)
     */
  }

  public TeamMember getCurrentUser()
  {
    return currentUser;
  }

  public void setCurrentUser(TeamMember currentUser)
  {
    this.currentUser = currentUser;
  }

  public void createNewProject(String title, Customer customer,
      TeamMember projectCreator)
  {
    projectList.createNewProject(title, customer, projectCreator);
  }

  public void addNewTeamMemberToProject(TeamMember teamMember, Project project)
  {
    projectList.addNewTeamMemberToProject(teamMember, project);
  }

  public void addRequirement(Project project, Requirement requirement)
  {
    projectList.addRequirement(project, requirement);
  }

  public void addTask(Project project, Requirement requirement, Task task)
  {
    projectList.addTask(project, requirement, task);
  }

  public void addTeamMemberToTask(Project project, Requirement requirement, Task task, TeamMember teamMember)
  {
    projectList.addTeamMemberToTask(project, requirement, task, teamMember);
  }

  public void prioritizeRequirement(String value)
  {
    projectList.prioritizeRequirement(value);
  }

  public void prioritizeTask(String value)
  {
    projectList.prioritizeTask(value);
  }

  public void approveRequirement(Project project, Requirement requirement, String value)
  {
    projectList.approveRequirement(project, requirement, value);
  }

  public void addRoleToProject(TeamMember teamMember, Project project)
  {
    projectList.addRoleToProject(teamMember, project);
  }

  public ArrayList<Task> getAllTasks(Project project, Requirement requirement)
  {
    return projectList.getAllTasks(project, requirement);
  }

  public ArrayList<Task> getAllTasks(Project project)
  {
    return projectList.getAllTasks(project);
  }

  // TODO getAllTasks(TeamMember teamMember)

  public void registerTime(TeamMember teamMember, Task task, int time)
  {
    // TODO projectList.registerTime(teamMember, task, time);
    teamMember.registerTime(time);
  }

  public  void updateTime(TeamMember teamMember, Task task, int time)
  {
    // TODO projectList.updateTime(teamMember, task, time)
    teamMember.updateTime(time);
  }

  public void updateStatus(Project project, Requirement requirement, Task task,
      String value)
  {
    projectList.updateStatus(project, requirement, task, value);
  }

  public Project searchProject(String title)
  {
    return projectList.searchProject(title);
  }

  public void updateRequirement(Project project, Requirement requirement,
      String title, String description, String type, int estimatedTime,
      Date deadline, TeamMember responsibleTeamMember)
  {
    projectList.updateRequirement(project, requirement, title, description, type,
        estimatedTime, deadline, responsibleTeamMember);
  }

  public ArrayList<Task> searchTask(Project project, Requirement requirement, String title)
  {
    return projectList.searchTask(project, requirement, title);
  }

  public ArrayList<TeamMember> searchEmployee(String name)
  {
    // TODO der foregår et eller andet her
    return employeeList.searchEmployee(name);
  }

  public void updateTask(Project project, Requirement requirement, Task task,
      String title, String description, int estimatedTime, Date deadline, TeamMember responsibleTeamMember)
  {
    projectList.updateTask(project, requirement, task, title, description, estimatedTime
    , deadline, responsibleTeamMember);
  }

  /*
   *  Herunder er methoder vi bruger for at få data til views!
   */
  public void setSelectedProject(Project selectedProject)
  {
    this.selectedProject = selectedProject;
  }

  public Project getSelectedProject()
  {
    return selectedProject;
  }

  public void setSelectedRequirement(Requirement requirement)
  {
    this.selectedRequirement = requirement;
  }

  public Requirement getSelectedRequirement()
  {
    return selectedRequirement;
  }

  public Task getSelectedTask()
  {
    return selectedTask;
  }

  public void setSelectedTask(Task selectedTask)
  {
    this.selectedTask = selectedTask;
  }
}
