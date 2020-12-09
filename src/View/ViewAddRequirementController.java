package View;

import Model.Customer;
import Model.Requirement;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ViewAddRequirementController
{
  @FXML private ChoiceBox chooseRequirementType;
  @FXML private Label missingInputLabel;
  @FXML private TextField requirementTitleInput;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    chooseRequirementType.getItems().addAll(viewHandler.getModelManager().getRequirementTypes());
  }

  public void reset()
  {
    missingInputLabel.setText("");
  }

  public void addRequirement()
  {
    String missingInputWarningText = "Vælg en type og indtast venligst en titel..";
    try {
      if (!chooseRequirementType.getValue().equals(""))
      {
        if (!requirementTitleInput.getText().equalsIgnoreCase(""))
        {
          viewHandler.getModelManager()
              .addRequirement(viewHandler.getModelManager().getSelectedProject(),
                  new Requirement(
                      requirementTitleInput.getText(),
                      "Not Started", (String) chooseRequirementType.getValue(),
                      viewHandler.getModelManager().getSelectedProject().getTitle()
                  )
              );

          viewHandler.openView("viewList");
        }
      }
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
    }

    missingInputLabel.setText(missingInputWarningText);
  }

  public Region getRoot()
  {
    return root;
  }

  public void goToViewList()
  {
    viewHandler.openView("viewList");
  }
}
