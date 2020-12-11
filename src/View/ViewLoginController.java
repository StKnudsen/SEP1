package View;

import Model.TeamMember;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class ViewLoginController
{
  @FXML ChoiceBox chooseUser;
  @FXML Label missingInputLabel;
  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    chooseUser.getItems().removeAll(viewHandler.getModelManager().getEmployees());
    chooseUser.getItems().addAll(viewHandler.getModelManager().getEmployees());
    missingInputLabel.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

  public void loginButtonPressed()
  {
    try
    {
      if (!chooseUser.getValue().equals(""))
      {
        viewHandler.getModelManager().setCurrentUser((TeamMember) chooseUser.getValue());

        viewHandler.openView("viewList");
      }
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }
    missingInputLabel.setText("Vælg bruger");
  }

  public void addCustomer()
  {
    viewHandler.openView("viewAddCustomer");
  }

  public void addEmployee()
  {
    viewHandler.openView("viewAddEmployee");
  }
}
