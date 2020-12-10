package View;

import Model.Job;
import Model.Requirement;
import Model.TeamMember;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ViewEditRequirementController
{
  @FXML private TextArea requirementDescription;
  @FXML private TextField requirementTitleInput;
  @FXML private TextField estimatedTimeInput;
  @FXML private ChoiceBox chooseResponsibleTeamMember;
  @FXML private ChoiceBox chooseType;
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
    chooseResponsibleTeamMember.getItems().removeAll(viewHandler.getModelManager().searchProject(viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()).getTeamMemberList());
    chooseResponsibleTeamMember.getItems().addAll(viewHandler.getModelManager().searchProject(viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()).getTeamMemberList());
    chooseType.getItems().addAll(viewHandler.getModelManager().getRequirementTypes());
    chooseStatus.getItems().addAll(viewHandler.getModelManager().getAllStatus());
  }

  public void updateRequirement() throws IOException
  {
    try
    {

      if (!chooseType.getValue().equals("") && !chooseResponsibleTeamMember.getValue().equals("") && !chooseStatus.getValue().equals(""))
      {
        //Hvis titelfelt udfyldes, opdater titel
        if(!requirementTitleInput.getText().equals(""))
        {
          viewHandler.getModelManager().getSelectedRequirement().setTitle(requirementTitleInput.getText());
        }
        //Hvis titelfelt ikke udfyldes, titel forbliver den samme
        else if(requirementTitleInput.getText().equals(""))
        {
          viewHandler.getModelManager().getSelectedRequirement().setTitle(viewHandler.getModelManager().getSelectedRequirement().getTitle());
        }
        viewHandler.getModelManager().setRequirementDescription(requirementDescription.getText());
        viewHandler.getModelManager().getSelectedRequirement().setType((String) chooseType.getValue());
        viewHandler.getModelManager().getSelectedRequirement().setEstimatedTime(Integer.parseInt(estimatedTimeInput.getText()));
        //LocalDate skal konverteres til Date
       //viewHandler.getModelManager().getSelectedRequirement().setDeadline(chooseDeadline.getValue().VIRKER IKKE DET LORT);
        viewHandler.getModelManager().getSelectedRequirement().setResponsibleTeamMember((TeamMember) chooseResponsibleTeamMember.getValue());
        viewHandler.getModelManager().getSelectedRequirement().updateStatus((String) chooseStatus.getValue());
      }
      viewHandler.openView("viewList");
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }
    missingInputLabel.setText("Udfyld venligst alt...");

      viewHandler.getModelManager().updateRequirement(
          viewHandler.getModelManager().searchProject(
              viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()),
          viewHandler.getModelManager().getSelectedRequirement(),
          viewHandler.getModelManager().getSelectedRequirement().getTitle(),
          viewHandler.getModelManager().getRequirementDescription(),
          viewHandler.getModelManager().getSelectedRequirement().getType(),
          viewHandler.getModelManager().getSelectedRequirement().getEstimatedTime(),
          viewHandler.getModelManager().getSelectedRequirement().getDeadline(),
          viewHandler.getModelManager().getSelectedRequirement().getResponsibleTeamMember());
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewLogin()
  {
    viewHandler.openView("viewLogin");
  }
}