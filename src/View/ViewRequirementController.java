package View;

import Model.ColourIT;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class ViewRequirementController
{
  @FXML private Label title;

  private ViewHandler viewHandler;
  private Region root;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.viewHandler = viewHandler;
    this.root = root;
  }

  public void reset()
  {
    title.setText(viewHandler.getModelManager().getSelectedRequirement().getTitle());
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewLogin()
  {
    viewHandler.openView("viewLogin");
  }

  public void addTask()
  {
    viewHandler.openView("viewAddTask");
  }

  public void editRequirement()
  {
    viewHandler.openView("viewEditRequirement");
  }
}
