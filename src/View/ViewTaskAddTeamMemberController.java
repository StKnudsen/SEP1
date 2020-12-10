package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewTaskAddTeamMemberController
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
    //
    // VIGTIGT.. getSelectedProject, kan lige pt. være et andet project!!!
    //
    //  Her er Stefan...
    //

    System.out.println(
        viewHandler.getModelManager().getSelectedTask().getTitle()
    );
    System.out.println(
        viewHandler.getModelManager().getSelectedTask().getProjectTitle()
    );

    chooseEmployee.getItems().removeAll(
        viewHandler.getModelManager()
            .searchProject(
                viewHandler.getModelManager().getSelectedTask().getProjectTitle()
            )
            .getTeamMemberList()
    );

    chooseEmployee.getItems().addAll(
        viewHandler.getModelManager()
            .searchProject(
                viewHandler.getModelManager().getSelectedTask().getProjectTitle()
            )
            .getTeamMemberList());

    missingInputLabel.setText("");
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
