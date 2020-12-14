package View;

import Model.MyDate;
import Model.TeamMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;

public class ViewEditRequirementController
{
  @FXML private TextArea requirementDescription;
  @FXML private TextField requirementTitleInput;
  @FXML private Label requirementPriority;
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
    chooseResponsibleTeamMember.getItems().clear();
    chooseResponsibleTeamMember.getItems().addAll(
        viewHandler.getModelManager().searchProject(
            viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()
        ).getTeamMemberList()
    );

    chooseResponsibleTeamMember.getSelectionModel().select(getResponsibleTeamMemberIndex());

    chooseType.getItems().clear();
    chooseType.getItems().addAll(viewHandler.getModelManager().getRequirementTypes());
    chooseType.getSelectionModel().select(getTypeIndex());

    chooseStatus.getItems().clear();
    chooseStatus.getItems().addAll(viewHandler.getModelManager().getAllStatus());
    chooseStatus.getSelectionModel().select(getStatusIndex());

    if(viewHandler.getModelManager().getSelectedRequirement().getDeadline() == null)
    {
      chooseDeadline.setValue(null);
    }
    else
    {
      chooseDeadline.setValue(LocalDate.parse(viewHandler.getModelManager().getSelectedRequirement().getDeadline().toString()));
    }

    requirementDescription.setText(viewHandler.getModelManager().getSelectedRequirement().getDescription());
    requirementTitleInput.setText(viewHandler.getModelManager().getSelectedRequirement().getTitle());
    requirementPriority.setText(viewHandler.getModelManager().getSelectedRequirement().getPriority());
    missingInputLabel.setText("");
  }

  private int getStatusIndex()
  {
    for (int i = 0; i < viewHandler.getModelManager().getAllStatus().length; i++)
    {
      if (viewHandler.getModelManager().getAllStatus()[i].equals(viewHandler.getModelManager().getSelectedRequirement().getStatus()))
      {
        return i;
      }
    }
    return 0;
  }

  private int getResponsibleTeamMemberIndex()
  {
    for (int i = 0; i < viewHandler.getModelManager().searchProject(viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()).getTeamMemberList().size(); i++)
    {
      if (viewHandler.getModelManager().searchProject(viewHandler.getModelManager().getSelectedRequirement().getProjectTitle()).getTeamMemberList().get(i).equals(viewHandler.getModelManager().getSelectedRequirement().getResponsibleTeamMember()))
      {
        return i;
      }
    }
    return 0;
  }

  private int getTypeIndex()
  {
    for (int i = 0; i < viewHandler.getModelManager().getRequirementTypes().length; i++)
    {
      if (viewHandler.getModelManager().getRequirementTypes()[i].equals(viewHandler.getModelManager().getSelectedRequirement().getType()))
      {
        return i;
      }
    }
    return 0;
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
      if (!chooseType.getValue().equals(""))
      {
        //Hvis titelfelt udfyldes, opdater titel
        if(!requirementTitleInput.getText().equals(""))
        {
          viewHandler.getModelManager().getSelectedRequirement().setTitle(requirementTitleInput.getText());
        }

        viewHandler.getModelManager().getSelectedRequirement().setDescription(requirementDescription.getText());

        //  Type skal være sat
        viewHandler.getModelManager().getSelectedRequirement().setType((String) chooseType.getValue());
        viewHandler.getModelManager().getSelectedRequirement().setDeadline(chooseDeadline.getValue());
        if(chooseResponsibleTeamMember.getValue() != null)
        {
          // Hvis der er sat tid på en responsible team member, så forsvinder den nok her...
          TeamMember responsibleTeamMemberValue = (TeamMember) chooseResponsibleTeamMember.getValue();

          viewHandler.getModelManager().getSelectedRequirement()
              .setResponsibleTeamMember(responsibleTeamMemberValue.copy()
          );
        }
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