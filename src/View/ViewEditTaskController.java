package View;

import Model.TeamMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.io.IOException;

public class ViewEditTaskController
{
  @FXML private Label taskPriority;
  @FXML private TextArea taskDescription;
  @FXML private TextField taskTitleInput;
  @FXML private TextField estimatedTimeInput;
  @FXML private ChoiceBox chooseResponsibleTeamMember;
  @FXML private ChoiceBox chooseStatus;
  @FXML private Label missingInputLabel;
  @FXML private DatePicker chooseDeadline;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    taskTitleInput
        .setText(viewHandler.getModelManager().getSelectedTask().getTitle());
    taskPriority
        .setText(viewHandler.getModelManager().getSelectedTask().getPriority());
    taskDescription.setText(
        viewHandler.getModelManager().getSelectedTask().getDescription());

    chooseResponsibleTeamMember.getItems().removeAll(
        viewHandler.getModelManager().searchProject(
            viewHandler.getModelManager().getSelectedTask().getProjectTitle())
            .getTeamMemberList());
    chooseResponsibleTeamMember.getItems().addAll(viewHandler.getModelManager()
        .searchProject(
            viewHandler.getModelManager().getSelectedTask().getProjectTitle())
        .getTeamMemberList());
    //chooseResponsibleTeamMember.getSelectionModel().select(viewHandler.getModelManager().getSelectedTask().getResponsibleTeamMember());

    chooseStatus.getItems()
        .removeAll(viewHandler.getModelManager().getAllStatus());
    chooseStatus.getItems()
        .addAll(viewHandler.getModelManager().getAllStatus());

    taskDescription.setText(
        viewHandler.getModelManager().getSelectedTask().getDescription());
    taskTitleInput
        .setText(viewHandler.getModelManager().getSelectedTask().getTitle());
  }

  public void changePriority(ActionEvent actionEvent)
  {
    //  Handling mu kun udføres af  scrum master.. If statement er nu i view task controller!
    if (viewHandler.getModelManager().getSelectedTask().getResponsibleTeamMember().equals(viewHandler.getModelManager().getCurrentUser()))
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

      viewHandler.openView("viewList");
    }
    else
    {
      missingInputLabel.setText("Handlingen må kun udføres af " + viewHandler.getModelManager().getSelectedTask().getResponsibleTeamMember());
    }
  }

  public void updateTask() throws IOException
  {
    try
    {
      if (!chooseResponsibleTeamMember.getValue().equals("") && !chooseStatus
          .getValue().equals(""))
      {
        if (!taskTitleInput.getText().equals(""))
        {
          viewHandler.getModelManager().getSelectedTask()
              .setTitle(taskTitleInput.getText());
        }
        viewHandler.getModelManager().getSelectedTask()
            .setDescription(taskDescription.getText());
        viewHandler.getModelManager().getSelectedTask()
            .setEstimatedTime(Integer.parseInt(estimatedTimeInput.getText()));
        viewHandler.getModelManager().getSelectedTask().setDeadline(chooseDeadline.getValue());
        viewHandler.getModelManager().getSelectedTask()
            .setResponsibleTeamMember(
                (TeamMember) chooseResponsibleTeamMember.getValue());
        viewHandler.getModelManager().getSelectedTask()
            .updateStatus((String) chooseStatus.getValue());
      }
      viewHandler.openView("viewList");
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }
    missingInputLabel.setText("Udfyld venligst alt...");

    viewHandler.getModelManager().updateTask(viewHandler.getModelManager()
            .searchProject(
                viewHandler.getModelManager().getSelectedTask().getProjectTitle()),
        viewHandler.getModelManager().searchProject(
            viewHandler.getModelManager().getSelectedTask().getProjectTitle())
            .searchRequirement(viewHandler.getModelManager().getSelectedTask()
                .getRequirementTitle()),
        viewHandler.getModelManager().getSelectedTask(),
        viewHandler.getModelManager().getSelectedTask().getTitle(),
        viewHandler.getModelManager().getSelectedTask().getDescription(),
        Integer.parseInt(viewHandler.getModelManager().getSelectedTask().getEstimatedTime()),
        viewHandler.getModelManager().getSelectedTask().getDeadline(),
        viewHandler.getModelManager().getSelectedTask()
            .getResponsibleTeamMember());
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
