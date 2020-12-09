package View;

import Model.TeamMember;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Region;

public class ViewLoginController
{
  @FXML ChoiceBox chooseUser;
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
  }

  public Region getRoot()
  {
    return root;
  }

  public void loginButtonPressed()
  {
    //System.out.println(chooseUser.getValue());
    viewHandler.getModelManager().setCurrentUser((TeamMember) chooseUser.getValue());

    viewHandler.openView("viewList");
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
