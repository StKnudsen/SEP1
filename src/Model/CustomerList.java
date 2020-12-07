package Model;

import java.util.ArrayList;

public class CustomerList
{
  private ArrayList<Customer> customerList;

  public CustomerList()
  {
    customerList = new ArrayList<>();
  }

  public void addCustomer(Customer customer)
  {
    customerList.add(customer);
  }

  public Customer getCustomer(Customer customer)
  {
    for (int i = 0; i < customerList.size(); i++)
    {
      if(customerList.get(i).equals(customer))
        return customer;
    }
    return null;
  }

  public ArrayList<Customer> getCustomers()
  {
    return customerList;
  }


}
