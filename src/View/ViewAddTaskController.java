package View;

import Model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ViewAddTaskController
{
  @FXML private Label missingInputLabel;
  @FXML private TextField taskTitleInput;
  @FXML private ChoiceBox chooseProject;

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

  //Problem med at der ikke nødvendigvis er en selectedProject, hvis user tilgår addTask direkte gennem et requirement.
  public void addTask()
  {
    try {
      if(!viewHandler.getModelManager().getSelectedProject().equals(""))
      {
        if (!taskTitleInput.getText().equalsIgnoreCase(""))
        {
          viewHandler.getModelManager()
              .addTask(viewHandler.getModelManager().getSelectedProject(),
                  viewHandler.getModelManager().getSelectedRequirement(), new Task(taskTitleInput.getText(), "Not Started"));

          viewHandler.openView("viewList");
        }
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
