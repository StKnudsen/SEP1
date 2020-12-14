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
    //chooseEmployee.getItems().removeAll(viewHandler.getModelManager().getEmployees());
    missingInputLabel.setText("");
    updateTimeInput.setText("");
    chooseEmployee.getItems().clear();
    chooseEmployee.getItems().addAll(viewHandler.getModelManager().getEmployees());
  }

  public void updateTime()
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

  public Region getRoot()
  {
    return root;
  }

  public void goToViewTask()
  {
    viewHandler.openView("viewTask");
  }
}
