package View;

import Model.TeamMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.io.IOException;

public class ViewEditTaskController
{
  @FXML private TextField taskTitle;
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

    //System.out.println(viewHandler.getModelManager().getSelectedTask().getPriority());

    chooseResponsibleTeamMember.getItems().removeAll(
        viewHandler.getModelManager().searchProject(
            viewHandler.getModelManager().getSelectedTask().getProjectTitle())
            .getTeamMemberList());
    chooseResponsibleTeamMember.getItems().addAll(viewHandler.getModelManager()
        .searchProject(
            viewHandler.getModelManager().getSelectedTask().getProjectTitle())
        .getTeamMemberList());

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

  public void updateTask() throws IOException
  {
    try
    {
      if (!chooseResponsibleTeamMember.getValue().equals("") && !chooseStatus
          .getValue().equals(""))
      {
        //Hvis titelfelt udfyldes, opdater titel - ER DETTE STADIG NØDVENDIGT EFTER STEFANS NY TAKTIK MED AT LADE TITLEN VÆRE SKREVET IND PÅ FORHÅND?
        if (!taskTitleInput.getText().equals(""))
        {
          viewHandler.getModelManager().getSelectedTask()
              .setTitle(taskTitleInput.getText());
        }
        //Hvis titelfelt ikke udfyldes, titel forbliver den samme
        else if (taskTitleInput.getText().equals(""))
        {
          viewHandler.getModelManager().getSelectedTask().setTitle(
              viewHandler.getModelManager().getSelectedTask().getTitle());
        }
        viewHandler.getModelManager()
            .setTaskDescription(taskDescription.getText());
        viewHandler.getModelManager().getSelectedTask()
            .setEstimatedTime(Integer.parseInt(estimatedTimeInput.getText()));
        //LocalDate skal konverteres til Date
        //viewHandler.getModelManager().getSelectedTask().setDeadline(chooseDeadline.getValue().VIRKER IKKE DET LORT);
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
        viewHandler.getModelManager().getTaskDescription(),
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
