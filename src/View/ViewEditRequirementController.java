package View;

import Model.TeamMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

import java.io.IOException;

public class ViewEditRequirementController
{
  @FXML private TextArea requirementDescription;
  @FXML private TextField requirementTitleInput;
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
    /*chooseResponsibleTeamMember.getItems().removeAll(
        viewHandler.getModelManager().searchProject(
            viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()
        ).getTeamMemberList()
    );*/
    chooseResponsibleTeamMember.getItems().clear();
    chooseResponsibleTeamMember.getItems().addAll(
        viewHandler.getModelManager().searchProject(
            viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()
        ).getTeamMemberList()
    );

    chooseType.getItems().removeAll(viewHandler.getModelManager().getRequirementTypes());
    chooseType.getItems().addAll(viewHandler.getModelManager().getRequirementTypes());

    chooseStatus.getItems().removeAll(viewHandler.getModelManager().getAllStatus());
    chooseStatus.getItems().addAll(viewHandler.getModelManager().getAllStatus());

    requirementDescription.setText(viewHandler.getModelManager().getSelectedRequirement().getDescription());
    requirementTitleInput.setText(viewHandler.getModelManager().getSelectedRequirement().getTitle());
  }

  public void changePriority(ActionEvent actionEvent)
  {
    viewHandler.getModelManager().prioritizeRequirement(
      viewHandler.getModelManager().searchProject(
        viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()),
      viewHandler.getModelManager().getSelectedRequirement(),
      ((Button) actionEvent.getSource()).getText()
    );

    viewHandler.openView("viewList");
  }

  public void updateRequirement() throws IOException
  {
    try
    {
      if (!chooseType.getValue().equals("") && !chooseStatus.getValue().equals(""))
      {
        //Hvis titelfelt udfyldes, opdater titel
        if(!requirementTitleInput.getText().equals(""))
        {
          viewHandler.getModelManager().getSelectedRequirement().setTitle(requirementTitleInput.getText());
        }
        if(!requirementDescription.getText().equals(""))
        {
          viewHandler.getModelManager().getSelectedRequirement().setDescription(requirementDescription.getText());
        }
        //  Type skal være sat
        viewHandler.getModelManager().getSelectedRequirement().setType((String) chooseType.getValue());
        viewHandler.getModelManager().getSelectedRequirement().setDeadline(chooseDeadline.getValue());
        if(chooseResponsibleTeamMember.getValue() != null)
        {
          viewHandler.getModelManager().getSelectedRequirement()
              .setResponsibleTeamMember((TeamMember) chooseResponsibleTeamMember.getValue());
        }
        /*else
        {
          viewHandler.getModelManager().getSelectedRequirement().setResponsibleTeamMember(
              (viewHandler.getModelManager().getSelectedRequirement().getResponsibleTeamMember())
          );
        }*/
        //  Status skal være sat
        viewHandler.getModelManager().getSelectedRequirement().updateStatus((String) chooseStatus.getValue());
      }

      viewHandler.openView("viewList");
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }
    missingInputLabel.setText("Udfyld venligst alt...");

    //Kan dette koges ned ved at slå det sammen med koden i try? Altså i stedet for først at sette alting ovenfor og derefter gette dem her... (Se ViewTaskUpdateTimeController for reference)
     /* viewHandler.getModelManager().updateRequirement(
          viewHandler.getModelManager().searchProject(
              viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()),
          viewHandler.getModelManager().getSelectedRequirement(),
          viewHandler.getModelManager().getSelectedRequirement().getTitle(),
          viewHandler.getModelManager().getSelectedRequirement().getDescription(),
          viewHandler.getModelManager().getSelectedRequirement().getType(),
          viewHandler.getModelManager().getSelectedRequirement().getDeadline(),
          viewHandler.getModelManager().getSelectedRequirement().getResponsibleTeamMember()); */
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewRequirement()
  {
    viewHandler.openView("viewRequirement");
  }
}