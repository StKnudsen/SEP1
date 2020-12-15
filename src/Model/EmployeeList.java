package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeList implements Serializable
{
  private ArrayList<TeamMember> employeeList;

  public EmployeeList()
  {
    employeeList = new ArrayList<>();
  }

  public void addEmployee(TeamMember teamMember)
  {
    employeeList.add(teamMember);
  }

  public ArrayList<TeamMember> getEmployees()
  {
    return employeeList;
  }

  public ArrayList<TeamMember> searchEmployee(String searchName)
  {
    ArrayList<TeamMember> results = new ArrayList<>();

    for (int i = 0; i < employeeList.size(); i++)
    {
      if(employeeList.get(i).getName().contains(searchName))
      {
        results.add(employeeList.get(i));
      }
    }

    return results;
  }
}
