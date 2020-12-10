package View;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ViewTaskController
{
  @FXML private Label taskTitle;
  @FXML private Label responsibleTeamMember;
  @FXML private Label taskStatus;
  @FXML private ListView teamMemberList;
  @FXML private TextFlow taskDescriptionTextFlow;

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
    responsibleTeamMember.setText(viewHandler.getModelManager().getSelectedTask().getResponsibleTeamMember().getName());
    taskStatus.setText(viewHandler.getModelManager().getSelectedTask().getStatus());

    teamMemberList.getItems().removeAll(viewHandler.getModelManager().getSelectedTask().getTeamMemberList());
    teamMemberList.getItems().addAll(viewHandler.getModelManager().getSelectedTask().getTeamMemberList());

    taskDescriptionTextFlow.getChildren().addAll(new Text(viewHandler.getModelManager().getTaskDescription()));

  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewLogin()
  {
    viewHandler.openView("viewLogin");
  }

  public void editTask()
  {
    viewHandler.openView("viewEditTask");
  }

  public void updateTaskTime()
  {
    viewHandler.openView("viewTaskUpdateTime");
  }

  public void addTeamMember()
  {
    viewHandler.openView("viewTaskAddTeamMember");
  }
}
