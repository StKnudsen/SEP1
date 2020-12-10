package View;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

public class ViewTaskController
{
  @FXML private Label taskProjectTitle;
  @FXML private Label taskRequirementTitle;
  @FXML private Label taskTitle;
  @FXML private Label responsibleTeamMember;
  @FXML private Label taskStatus;
  @FXML private Label taskDescription;
  @FXML private ListView teamMemberList;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    taskProjectTitle.setText(viewHandler.getModelManager().getSelectedTask().getProjectTitle());
    taskRequirementTitle.setText(viewHandler.getModelManager().getSelectedTask().getRequirementTitle());
    taskTitle.setText(viewHandler.getModelManager().getSelectedTask().getTitle());
    responsibleTeamMember.setText(viewHandler.getModelManager().getSelectedTask().getResponsibleTeamMember().getName());
    taskStatus.setText(viewHandler.getModelManager().getSelectedTask().getStatus());
    //taskDescription.getChildren().add(new Text(viewHandler.getModelManager().getSelectedTask().getDescription()));
    taskDescription.setText(viewHandler.getModelManager().getSelectedTask().getDescription());
    teamMemberList.getItems().removeAll(viewHandler.getModelManager().getSelectedTask().getTeamMemberList());
    teamMemberList.getItems().addAll(viewHandler.getModelManager().getSelectedTask().getTeamMemberList());

  }

  public Region getRoot()
  {
    return root;
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

  public void gotoViewList()
  {
    viewHandler.openView("viewList");
  }
}
