package View;

import Model.Customer;
import Model.Project;
import Model.TeamMember;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewAddProjectController
{
  @FXML private Button closeButton;
  @FXML private ChoiceBox chooseCustomer;
  @FXML private Label missingInputLabel;
  @FXML private TextField projectTitleInput;


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
    chooseCustomer.getItems().removeAll(viewHandler.getModelManager().getCustomers());
    chooseCustomer.getItems().addAll(viewHandler.getModelManager().getCustomers());
    missingInputLabel.setText("");
    projectTitleInput.setText("");
  }

  public void addProject()
  {
    String missingNameWarningText = "Vælg en kunde og indtast venligst et navn..";
    try {
      if (!chooseCustomer.getValue().equals(""))
      {
        if (!projectTitleInput.getText().equalsIgnoreCase(""))
        {
          viewHandler.getModelManager()
              .createNewProject(projectTitleInput.getText(), (Customer) chooseCustomer.getValue(),
                  viewHandler.getModelManager().getCurrentUser());

          viewHandler.openView("viewLogin");
        }
      }
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }

    missingInputLabel.setText(missingNameWarningText);
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewLogin()
  {
    viewHandler.openView("viewLogin");
  }
}
