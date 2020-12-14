package Model;

import parser.ParserException;
import parser.XmlJsonParser;
import java.io.File;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import  util.FileHandler;


public class ColourIT implements Serializable
{
  // Bruges lige til opsætning af View Handler

  private TeamMember currentUser;
  private Project selectedProject;
  private Requirement selectedRequirement;
  private Task selectedTask;
  private TeamMember selectedTeamMember;
  private XmlJsonParser theParser;
  private String requirementDescription;
  private String taskDescription;


  private ProjectList projectList;
  private CustomerList customerList;
  private EmployeeList employeeList;
  final private String[] typeList = {"Functional", "Non-functional", "Project requirement"};
  final private String[] statusList = {"Not started", "Started", "Ended","Approved", "Rejected"};
  final private String[] taskStatusList = {"Not started", "Started", "Ended"};

  public ColourIT()
  {
    projectList = new ProjectList();
    customerList = new CustomerList();
    employeeList = new EmployeeList();

    try{
      projectList = FileHandler.projectListLoad();
      customerList = FileHandler.customerListLoad();
      employeeList = FileHandler.employeeListLoad();
    }catch (IOException | ClassNotFoundException frick) {
      frick.printStackTrace();
    }
    // vi starter også lige en parser, det kan vi godt lide
    theParser = new XmlJsonParser();

    /*
     *  Når vi har lavet persist data med filer
     *  så skal data vel indlæses her ;)
     */
    settetTest(); // Vores dummy data
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

  /*
  public void addTask(Project project, Requirement requirement, Task task)
  {
    projectList.addTask(project, requirement, task);
  } */

  // Ny udgave med responsible team member...
  public void addTask(Project project, Requirement requirement, String taskTitle, TeamMember responsibleTeamMember)
  {
    projectList.addTask(project, requirement, taskTitle, responsibleTeamMember);
  }

  public void addTeamMemberToTask(Project project, Requirement requirement, Task task, TeamMember teamMember)
  {
    projectList.addTeamMemberToTask(project, requirement, task, teamMember);
  }

  public void prioritizeRequirement(Project project, Requirement requirement, String value)
  {
    projectList.prioritizeRequirement(project, requirement, value);
  }

  public void prioritizeTask(String value, Task task, Requirement requirement, Project project)
  {
    projectList.prioritizeTask(value, task, requirement, project);
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
      String title, String description, String type,
      LocalDate deadline, TeamMember responsibleTeamMember)
  {
    projectList.updateRequirement(project, requirement, title, description, type, deadline, responsibleTeamMember);
  }

  public ArrayList<Task> searchTask(Project project, Requirement requirement, String title)
  {
    return projectList.searchTask(project, requirement, title);
  }

  public ProjectList getProjectList()
  {
    return projectList;
  }

  public CustomerList getCustomerList()
  {
    return customerList;
  }

  public EmployeeList getEmployeeList()
  {
    return employeeList;
  }

  public ArrayList<TeamMember> searchEmployee(String searchName)
  {
    return employeeList.searchEmployee(searchName);
  }

  public void updateTask(Project project, Requirement requirement, Task task,
      String title, String description, int estimatedTime, MyDate deadline, TeamMember responsibleTeamMember)
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

  public TeamMember getSelectedTeamMember()
  {
    return selectedTeamMember;
  }

  public void setSelectedTeamMember(TeamMember selectedTeamMember)
  {
    this.selectedTeamMember = selectedTeamMember;
  }

  public ArrayList<Project> getProjects()
  {
    return projectList.getProjects();
  }

  public ArrayList<Requirement> getAllRequirements()
  {
    return projectList.getRequirements();
  }

  public ArrayList<TeamMember> getEmployees()
  {
    return employeeList.getEmployees();
  }


  /*
   *  DUMMY D>TA
   */
  public void settetTest()
  {
    // Nogle kunder
    Customer customer1 = new Customer("Jimifer Jensen");
    Customer customer2 = new Customer("Jack Black");
    customerList.addCustomer(customer1);
    customerList.addCustomer(customer2);

    // Nogle medarbejdere
    TeamMember teamMember1 = new TeamMember("John Doe");
    TeamMember teamMember2 = new TeamMember("Jane Doe");
    employeeList.addEmployee(teamMember1);
    employeeList.addEmployee(teamMember2);

    // Nogle projekter
    this.createNewProject("Best Project ever!", customer1, teamMember1);
    this.createNewProject("Colour IT PMS", customer2, teamMember2);
    //  Tilføj Team Members
    this.addNewTeamMemberToProject(teamMember2, projectList.searchProject("Best Project ever!"));
    this.addNewTeamMemberToProject(teamMember1, projectList.searchProject("Best Project ever!"));
    this.addNewTeamMemberToProject(teamMember1, projectList.searchProject("Colour IT PMS"));
    this.addNewTeamMemberToProject(teamMember2, projectList.searchProject("Colour IT PMS"));

    //  Nogle requirements
    // To til projekt 1
    this.addRequirement("Find a fish", "Functional Requirement", projectList.getProjects().get(0).getTitle(), teamMember1);
    this.addRequirement("Slap someone with fish", "Functional Requirement", projectList.getProjects().get(0).getTitle(), teamMember1);
    // To til projekt 2
    this.addRequirement("Blow a rainbow", "Functional Requirement", projectList.getProjects().get(1).getTitle(), teamMember2);
    this.addRequirement("Colour that rainbow", "Colour that rainbow", projectList.getProjects().get(1).getTitle(), teamMember2);

    // Tasks til requirements
    // projekt 1 requirement 1
    this.addTask(
        projectList.searchProject("Best Project ever!"),
        projectList.searchProject("Best Project ever!").getRequirementList().get(0),
        "Find a fishing hole", teamMember1
    );
    this.addTask(
        projectList.searchProject("Best Project ever!"),
        projectList.searchProject("Best Project ever!").getRequirementList().get(0),
        "Remember fishing pole", teamMember1
    );
    // projekt 1 requirement 2
    this.addTask(
        projectList.searchProject("Best Project ever!"),
        projectList.searchProject("Best Project ever!").getRequirementList().get(1),
        "Find someone to slap with a fish", teamMember1
    );
    this.addTask(
        projectList.searchProject("Best Project ever!"),
        projectList.searchProject("Best Project ever!").getRequirementList().get(1),
        "Someone found you first! Doh!", teamMember1
    );
    // projekt 2 requirement 1
    this.addTask(
        projectList.searchProject("Colour IT PMS"),
        projectList.searchProject("Colour IT PMS").getRequirementList().get(0),
        "Find someone who is abel to blow a rainbow", teamMember2
    );
    this.addTask(
        projectList.searchProject("Colour IT PMS"),
        projectList.searchProject("Colour IT PMS").getRequirementList().get(0),
        "Bribe that person with candy", teamMember2
    );
    // projekt 2 requirement 1
    this.addTask(
        projectList.searchProject("Colour IT PMS"),
        projectList.searchProject("Colour IT PMS").getRequirementList().get(1),
        "Take a red crayon", teamMember2
    );
    this.addTask(
        projectList.getProjects().get(1),
        projectList.getProjects().get(1).getRequirementList().get(1),
        "Find a rainbow coloured crayon! YAY.. YOU WIN!", teamMember2
    );
  }

  public void addRequirement(String title, String type, String projectTitle,
      TeamMember teamMember)
  {
    projectList.addRequirement(title, type, projectTitle, teamMember);
  }

  public void addNewTeamMember(TeamMember newTeamMember)
  {
    employeeList.addEmployee(newTeamMember);
  }

  public void addNewCustomer(Customer customer)
  {
    customerList.addCustomer(customer);
  }

  public ArrayList<Customer> getCustomers()
  {
    return customerList.getCustomers();
  }

  public String[] getRequirementTypes()
  {
    return typeList;
  }

  public String[] getAllStatus() {return statusList;}

  public String[] getTaskStatusList()
  {
    return taskStatusList;
  }

  public ArrayList<Project> getProjectsCurrentUser(TeamMember currentUser)
  {
    return projectList.getProjectsCurrentUser(currentUser);
  }

  public ArrayList<Requirement> getAllRequirementsCurrentUser(TeamMember currentUser)
  {
    return projectList.getAllRequirementsCurrentUser(currentUser);
  }

  public ArrayList<Task> getAllTeamMemberTasks(TeamMember currentUser)
  {
    return projectList.getAllTeamMemberTasks(currentUser);
  }

  public String getTimeSpentForTeamMember(TeamMember selectedTeamMember)
  {
    return projectList.getTimeSpentForTeamMember(selectedTeamMember);
  }

  public ArrayList<Project> getTeamMemberProjects(TeamMember selectedTeamMember)
  {
    return projectList.getTeamMemberProjects(selectedTeamMember);
  }

  public void checkIfAllTasksIsDoneForRequirement(Project project, String requirementTitle)
  {
    projectList.checkIfAllTasksIsDoneForRequirement(project, requirementTitle);
    System.out.println("project: " + project.getTitle());
  }

  public ArrayList<Project> searchProjectList(String searchText, TeamMember teamMember)
  {
    return projectList.searchProjectList(searchText, teamMember);
  }

  public ArrayList<Requirement> searchRequirementList(String searchText, TeamMember currentUser)
  {
    return projectList.searchRequirementList(searchText, currentUser);
  }

  public ArrayList<Task> searchTaskField(String searchText, TeamMember currentUser)
  {
    return projectList.searchTaskList(searchText, currentUser);
  }
}
