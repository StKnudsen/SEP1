package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ViewEditTaskController
{
  @FXML private TextField taskTitle;
  @FXML private Label taskPriority;
  @FXML private TextArea taskDescription;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    taskTitle.setText(viewHandler.getModelManager().getSelectedTask().getTitle());
    taskPriority.setText(viewHandler.getModelManager().getSelectedTask().getPriority());
    taskDescription.setText(viewHandler.getModelManager().getSelectedTask().getDescription());

    //System.out.println(viewHandler.getModelManager().getSelectedTask().getPriority());
  }

  public void changePriority(ActionEvent actionEvent)
  {
    viewHandler.getModelManager().prioritizeTask(
        ((Button) actionEvent.getSource()).getText(),
        viewHandler.getModelManager().getSelectedTask(),
        viewHandler.getModelManager().searchProject(
            viewHandler.getModelManager().getSelectedTask().getProjectTitle()
        ).searchRequirement(
            viewHandler.getModelManager().getSelectedTask().getRequirementTitle()
        ),
        viewHandler.getModelManager().searchProject(
            viewHandler.getModelManager().getSelectedTask().getProjectTitle()
        )
    );

    viewHandler.openView("viewEditTask");
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
