package View;

import Model.ColourIT;
import Model.TeamMember;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ViewAddEmployeeController
{
  @FXML private Button closeButton;
  @FXML private TextField employeeNameInput;
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
    employeeNameInput.setText("");
    missingInputLabel.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

  public void addEmployee()
  {
    String missingNameWarningText = "Indtast venligst et navn..";
    if (!employeeNameInput.getText().equalsIgnoreCase(""))
    {
      TeamMember newTeamMember = new TeamMember(employeeNameInput.getText());
      viewHandler.getModelManager().addNewTeamMember(newTeamMember);

      viewHandler.openView("viewLogin");
    }

    missingInputLabel.setText(missingNameWarningText);
  }

  public void gotoViewLogin()
  {
    viewHandler.openView("viewLogin");
  }
}
