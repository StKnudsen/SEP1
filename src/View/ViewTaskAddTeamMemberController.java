package View;

import Model.TeamMember;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

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
    chooseEmployee.getItems().removeAll(
      viewHandler.getModelManager()
        .searchProject(viewHandler.getModelManager().getSelectedTask().getProjectTitle())
        .getTeamMemberList()
    );

    chooseEmployee.getItems().addAll(
      viewHandler.getModelManager()
        .searchProject(viewHandler.getModelManager().getSelectedTask().getProjectTitle())
        .getTeamMemberList()
    );

    missingInputLabel.setText("");
  }

  public void addTeamMemberToTask()
  {
    try
    {
      if (!chooseEmployee.getValue().equals(""))
      {
        viewHandler.getModelManager().addTeamMemberToTask(
            viewHandler.getModelManager().searchProject(
                viewHandler.getModelManager().getSelectedTask().getProjectTitle()
            ),
            viewHandler.getModelManager().searchProject(
                viewHandler.getModelManager().getSelectedTask().getProjectTitle()
            ).searchRequirement(
                viewHandler.getModelManager().getSelectedTask().getRequirementTitle()
            ),
            viewHandler.getModelManager().getSelectedTask(),
            (TeamMember) chooseEmployee.getValue()
        );

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

  public void gotoViewTask()
  {
    viewHandler.openView("viewTask");
  }
}
