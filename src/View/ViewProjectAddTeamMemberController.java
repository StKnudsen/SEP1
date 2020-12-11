package View;

import Model.TeamMember;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewProjectAddTeamMemberController
{
  @FXML private Label missingInputLabel;
  @FXML private ChoiceBox chooseEmployee;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    chooseEmployee.getItems().removeAll(viewHandler.getModelManager().getEmployees());
    chooseEmployee.getItems().addAll(viewHandler.getModelManager().getEmployees());
    missingInputLabel.setText("");
  }

  public void addTeamMemberToProject()
  {
    try
    {
      if (!chooseEmployee.getValue().equals(""))
      {
        viewHandler.getModelManager().addNewTeamMemberToProject((TeamMember) chooseEmployee.getValue(), viewHandler.getModelManager()
            .getSelectedProject());

        viewHandler.openView("viewList");
      }
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }
    missingInputLabel.setText("Vælg bruger");
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewList()
  {
    viewHandler.openView("viewList");
  }
}
