package View;

import Model.Customer;
import Model.TeamMember;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ViewAddCustomerController
{
  @FXML private Button closeButton;
  @FXML private TextField customerNameInput;
  @FXML private Label missingInputLabel;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void closeButtonAction()
  {
    viewHandler.closeView();
  }

  public void reset()
  {
    customerNameInput.setText("");
    missingInputLabel.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

  public void addCustomer()
  {
    String missingNameWarningText = "Indtast venligst et navn..";
    if (!customerNameInput.getText().equalsIgnoreCase(""))
    {
      Customer newCustomer = new Customer(customerNameInput.getText());
      viewHandler.getModelManager().addNewCustomer(newCustomer);

      for (Customer customer:viewHandler.getModelManager().getCustomers())
      {
        System.out.println(customer.toString());
      }

      viewHandler.openView("viewLogin");
    }

    missingInputLabel.setText(missingNameWarningText);
  }

  public void gotoViewLogin()
  {
    viewHandler.openView("viewLogin");
  }
}
