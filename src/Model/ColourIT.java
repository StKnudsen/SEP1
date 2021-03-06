package Model;

import parser.XmlJsonParser;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import  util.FileHandler;


public class ColourIT
{
  private TeamMember currentUser;
  private Project selectedProject;
  private Requirement selectedRequirement;
  private Task selectedTask;
  private TeamMember selectedTeamMember;
  private XmlJsonParser theParser;
  
  private ProjectList projectList;
  private CustomerList customerList;
  private EmployeeList employeeList;
  final private String[] TYPE_LIST = {"Functional", "Non-functional", "Project requirement"};
  final private String[] STATUS_LIST = {"Not started", "Started", "Ended","Approved", "Rejected"};
  final private String[] TASK_STATUS_LIST = {"Not started", "Started", "Ended"};

  public ColourIT()
  {
    projectList = new ProjectList();
    customerList = new CustomerList();
    employeeList = new EmployeeList();

    try{
      projectList = FileHandler.projectListLoad();
      customerList = FileHandler.customerListLoad();
      employeeList = FileHandler.employeeListLoad();
    }catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    // vi starter også lige en parser, det kan vi godt lide
    theParser = new XmlJsonParser();

    /*
     *  Når vi har lavet persist data med filer
     *  så skal data vel indlæses her ;)
     */
    //settetTest(); // Vores dummy data
  }

  public void setCurrentUser(TeamMember currentUser)
  {
    this.currentUser = currentUser;
  }

  public TeamMember getCurrentUser()
  {
    return currentUser;
  }

  public void setSelectedTeamMember(TeamMember selectedTeamMember)
  {
    this.selectedTeamMember = selectedTeamMember;
  }

  public TeamMember getSelectedTeamMember()
  {
    return selectedTeamMember;
  }

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

  public void setSelectedTask(Task selectedTask)
  {
    this.selectedTask = selectedTask;
  }

  public Task getSelectedTask()
  {
    return selectedTask;
  }

  public String[] getRequirementTypes()
  {
    return TYPE_LIST;
  }

  public String[] getAllStatus() {return STATUS_LIST;}

  public String[] getTaskStatusList()
  {
    return TASK_STATUS_LIST;
  }

  public void addNewCustomer(Customer customer)
  {
    customerList.addCustomer(customer);
  }

  public ArrayList<Customer> getCustomers()
  {
    return customerList.getCustomers();
  }

  public CustomerList getCustomerList()
  {
    return customerList;
  }

  public void addNewTeamMember(TeamMember newTeamMember)
  {
    employeeList.addEmployee(newTeamMember);
  }

  public ArrayList<TeamMember> getEmployees()
  {
    return employeeList.getEmployees();
  }

  public EmployeeList getEmployeeList()   //  til File Handler
  {
    return employeeList;
  }

  public void createNewProject(String title, Customer customer, TeamMember projectCreator)
  {
    projectList.createNewProject(title, customer, projectCreator);
  }

  public ArrayList<Project> getProjects()
  {
    return projectList.getProjects();
  }

  public ProjectList getProjectList()   // til File Handler
  {
    return projectList;
  }

  public void addRequirement(String title, String type, String projectTitle, TeamMember teamMember)
  {
    projectList.addRequirement(title, type, projectTitle, teamMember);
  }

  public ArrayList<Requirement> getAllRequirements()
  {
    return projectList.getRequirements();
  }

  public void addTask(Project project, Requirement requirement, String taskTitle, TeamMember responsibleTeamMember)
  {
    projectList.addTask(project, requirement, taskTitle, responsibleTeamMember);
  }

  public void updateTask(Project project, Requirement requirement, Task task,
      String title, String description, int estimatedTime, MyDate deadline, TeamMember responsibleTeamMember)
  {
    projectList.updateTask(project, requirement, task, title, description, estimatedTime
        , deadline, responsibleTeamMember);
  }

  public void addNewTeamMemberToProject(TeamMember teamMember, Project project)
  {
    projectList.addNewTeamMemberToProject(teamMember, project);
  }

  public void addTeamMemberToTask(Project project, Requirement requirement, Task task, TeamMember teamMember)
  {
    projectList.addTeamMemberToTask(project, requirement, task, teamMember);
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

  public ArrayList<Project> getTeamMemberProjects(TeamMember selectedTeamMember)
  {
    return projectList.getTeamMemberProjects(selectedTeamMember);
  }

  public String getTimeSpentForTeamMember(TeamMember selectedTeamMember)
  {
    return projectList.getTimeSpentForTeamMember(selectedTeamMember);
  }

  public void checkIfAllTasksIsDoneForRequirement(Project project, String requirementTitle)
  {
    projectList.checkIfAllTasksIsDoneForRequirement(project, requirementTitle);
  }

  public void prioritizeRequirement(Project project, Requirement requirement, String value)
  {
    projectList.prioritizeRequirement(project, requirement, value);
  }

  public void prioritizeTask(String value, Task task, Requirement requirement, Project project)
  {
    projectList.prioritizeTask(value, task, requirement, project);
  }

  public Project searchProject(String title)
  {
    return projectList.searchProject(title);
  }

  public ArrayList<TeamMember> searchEmployee(String searchName)
  {
    return employeeList.searchEmployee(searchName);
  }

  public ArrayList<Project> searchProjectList(String searchText, TeamMember currentUser)
  {
    return projectList.searchProjectList(searchText, currentUser);
  }

  public ArrayList<Requirement> searchRequirementList(String searchText, TeamMember currentUser)
  {
    return projectList.searchRequirementList(searchText, currentUser);
  }

  public ArrayList<Task> searchTaskList(String searchText, TeamMember currentUser)
  {
    return projectList.searchTaskList(searchText, currentUser);
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
}
