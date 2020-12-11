package View;

import Model.TeamMember;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ViewTaskController
{
  @FXML private Label taskProjectTitle;
  @FXML private Label taskRequirementTitle;
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
    try
    {
      taskProjectTitle.setText(
          viewHandler.getModelManager().getSelectedTask().getProjectTitle());
      taskRequirementTitle.setText(
          viewHandler.getModelManager().getSelectedTask()
              .getRequirementTitle());
      taskTitle
          .setText(viewHandler.getModelManager().getSelectedTask().getTitle());
      responsibleTeamMember.setText(
          viewHandler.getModelManager().getSelectedTask()
              .getResponsibleTeamMember().getName());
      taskStatus
          .setText(viewHandler.getModelManager().getSelectedTask().getStatus());

      populateTeamMemberList();

      taskDescriptionTextFlow.getChildren()
          .addAll(new Text(viewHandler.getModelManager().getTaskDescription()));
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }
  }

  public void populateTeamMemberList()
  {
    teamMemberList.getItems().clear();
    teamMemberList.getItems().addAll(
        viewHandler.getModelManager().getSelectedTask().getTeamMemberList());
    teamMemberList.setOnMousePressed(new EventHandler<MouseEvent>()
       {
         @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
         {
            if(teamMemberList.getSelectionModel().getSelectedItem() != null)
            {
              viewHandler.getModelManager().setSelectedTeamMember(
                  (TeamMember) teamMemberList.getSelectionModel().getSelectedItem());
              viewHandler.openView("viewTeamMember");
            }
         }
       }
    );
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
