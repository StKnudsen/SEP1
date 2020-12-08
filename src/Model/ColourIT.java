package Model;

public class ColourIT
{
  // Bruges lige til opsætning af View Handler

  private TeamMember currentUser;

  private ProjectList projectList;
  private CustomerList customerList;
  private EmployeeList employeeList;

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
    //Det sejler - vi ramte muren :'(
    //ProjectList.createNewProject(title,customer,projectCreator);

    projectList.createNewProject(title, customer, projectCreator);
  }


}
