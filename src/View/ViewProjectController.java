package View;

import Model.ColourIT;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

public class ViewProjectController
{
  @FXML private Label title;
  @FXML private ListView teamMemberList;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    title.setText(viewHandler.getModelManager().getSelectedProject().getTitle());
    teamMemberList.getItems().removeAll(viewHandler.getModelManager().getSelectedProject().getTeamMemberList());
    teamMemberList.getItems().addAll(viewHandler.getModelManager().getSelectedProject().getTeamMemberList());
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewLogin()
  {
    viewHandler.openView("viewLogin");
  }

  public void addRequirement()
  {
    viewHandler.openView("viewAddRequirement");
  }

  public void addTeamMember()
  {
    viewHandler.openView("viewProjectAddTeamMember");
  }
}
