package Model;

import javax.management.modelmbean.ModelMBean;


public class ColourIT
{
  // Bruges lige til opsætning af View Handler

  private TeamMember currentUser;
  private ProjectList projectList;

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

  }


}
