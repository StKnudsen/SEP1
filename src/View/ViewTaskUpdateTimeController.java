package View;

import Model.TeamMember;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ViewTaskUpdateTimeController
{
  @FXML private Label missingInputLabel;
  @FXML private TextField updateTimeInput;
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
    missingInputLabel.setText("");
    updateTimeInput.setText("");
    chooseEmployee.getItems().clear();
    chooseEmployee.getItems().addAll(viewHandler.getModelManager().getSelectedTask().getTeamMemberList());
    chooseEmployee.getSelectionModel().select(getTeamMemberIndex());
  }

  private int getTeamMemberIndex()
  {
    for (int i = 0; i < viewHandler.getModelManager().getEmployees().size(); i++)
    {
      if (viewHandler.getModelManager().getEmployees().get(i).equals(viewHandler.getModelManager().getCurrentUser()))
      {
        return i;
      }
    }
    return 0;
  }

  public void updateTime()
  {
    if (
        viewHandler.getModelManager().getSelectedTask().getResponsibleTeamMember().equals(viewHandler.getModelManager().getCurrentUser())
        || viewHandler.getModelManager().getCurrentUser().equals(chooseEmployee.getValue()))
    {
      try
      {
        if (!chooseEmployee.getValue().equals("") && !updateTimeInput.getText().equals(""))
        {
          viewHandler.getModelManager().getSelectedTask()
              .updateTime((TeamMember) chooseEmployee.getValue(), Integer.parseInt(updateTimeInput.getText()));
          viewHandler.openView("viewTask");
        }
      }
      catch (NullPointerException e)
      {
        e.printStackTrace();
      }
      missingInputLabel.setText("Udfyld venligst alt...");
    }
    else
    {
      missingInputLabel.setText("Handling må kun udføres af " + viewHandler.getModelManager().getSelectedTask().getResponsibleTeamMember());
    }
  }

  public Region getRoot()
  {
    return root;
  }

  public void goToViewTask()
  {
    viewHandler.openView("viewTask");
  }
}
