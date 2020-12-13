package Model;

import java.util.ArrayList;

public class EmployeeList
{
  private ArrayList<TeamMember> employeeList;

  public EmployeeList()
  {
    employeeList = new ArrayList<>();
  }

  public ArrayList searchEmployee(String name)
  {
    ArrayList<TeamMember> results = new ArrayList<>();

    for (int i = 0; i < employeeList.size(); i++)
    {
      if(name.equalsIgnoreCase(employeeList.get(i).getName()))
        results.add(employeeList.get(i));
    }

    return results;
  }

//Husk getAllTasks(TeamMember teamMember)

  /*
   *  Skal tilføjes til UML classe diagram!
   */
  //public ArrayList<TeamMember> getEmployeeList()
  //{
  //  return employeeList;
  //}

  public void addEmployee(TeamMember teamMember)
  {
    employeeList.add(teamMember);
  }

  public ArrayList<TeamMember> getEmployees()
  {
    return employeeList;
  }
}
