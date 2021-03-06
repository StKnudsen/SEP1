package View;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ViewAddTaskController
{
  @FXML private Label missingInputLabel;
  @FXML private TextField taskTitleInput;

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
    taskTitleInput.setText("");
  }

  public void addTask()
  {
    try
    {
      if (!taskTitleInput.getText().equalsIgnoreCase(""))
      {
        viewHandler.getModelManager().addTask(
          viewHandler.getModelManager().searchProject(
            viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()
          ),
          viewHandler.getModelManager().getSelectedRequirement(),
          taskTitleInput.getText(), viewHandler.getModelManager().getCurrentUser()
        );

        viewHandler.openView("viewRequirement");
      }
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }

    missingInputLabel.setText("Indtast venligst titel...");
  }

  public Region getRoot()
  {
    return root;
  }

  public void goToViewList()
  {
    viewHandler.openView("viewList");
  }
}