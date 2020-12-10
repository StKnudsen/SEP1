package View;

import Model.TeamMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

public class ViewEditTaskController
{
  @FXML private TextField taskTitle;
  @FXML private Label taskPriority;
  @FXML private TextArea taskDescription;
  @FXML private TextField taskEstimate;
  @FXML private ChoiceBox taskTeamMembers;
  @FXML private ChoiceBox taskStatus;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    taskStatus.getItems().addAll(viewHandler.getModelManager().getTaskStatusList());
  }

  public void reset()
  {
    taskTitle.setText(viewHandler.getModelManager().getSelectedTask().getTitle());
    taskPriority.setText(viewHandler.getModelManager().getSelectedTask().getPriority());
    taskDescription.setText(viewHandler.getModelManager().getSelectedTask().getDescription());
    taskEstimate.setText(viewHandler.getModelManager().getSelectedTask().getEstimatedTime());
    // mangler deadline!
    taskTeamMembers.getItems().removeAll(viewHandler.getModelManager().searchProject(viewHandler.getModelManager().getSelectedTask().getProjectTitle()).getTeamMemberList());
    taskTeamMembers.getItems().addAll(viewHandler.getModelManager().searchProject(viewHandler.getModelManager().getSelectedTask().getProjectTitle()).getTeamMemberList());
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

  public void updateTask()
  {
    if (!taskTitle.getText().equals(""))
    {
      viewHandler.getModelManager().getSelectedTask().setTitle(taskTitle.getText());
    }

    try // Beskrivelse er null som standard, lad os lige fange det
    {
      if (!taskDescription.getText().equals(""))
      {
        viewHandler.getModelManager().getSelectedTask().setDescription(taskDescription.getText());
      }
    }
    catch (NullPointerException e)
    {
      //e.printStackTrace();
    }

    if (Integer.parseInt(taskEstimate.getText()) > 0)
    {
      viewHandler.getModelManager().getSelectedTask().setEstimatedTime(Integer.parseInt(taskEstimate.getText()));
    }

    try {
      if (!taskTeamMembers.getValue().equals(""))
      {
        viewHandler.getModelManager().getSelectedTask().setResponsibleTeamMember(
            (TeamMember) taskTeamMembers.getValue()
        );
      }
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }

    try {
      if (!taskStatus.getValue().equals(""))
      {
        viewHandler.getModelManager().getSelectedTask().updateStatus(
            (String) taskStatus.getValue()
        );
      }
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }

    //missingInputLabel.setText(missingNameWarningText);

    viewHandler.openView("viewTask");
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
