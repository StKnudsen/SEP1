package View;

import Model.ColourIT;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class ViewRequirementController
{
  @FXML private Label title;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
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
}
