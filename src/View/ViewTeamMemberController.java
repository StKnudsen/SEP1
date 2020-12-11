package View;

import Model.Project;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class ViewTeamMemberController
{
  //Skal vi overhovedet have estimatedTime på teamMember???
  @FXML private Label estimatedTimeLabel;
  @FXML private Label spentTimeLabel;
  @FXML private Label teamMemberNameLabel;
  @FXML private ListView<Project> projectListView;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    spentTimeLabel.setText(viewHandler.getModelManager()
        .getTimeSpentForTeamMember(
            viewHandler.getModelManager().getSelectedTeamMember()));
    teamMemberNameLabel.setText(
        viewHandler.getModelManager().getSelectedTeamMember().getName());

    populateProjectList();
  }

  public void populateProjectList()
  {
    projectListView.getItems().clear();
    projectListView.getItems().addAll(viewHandler.getModelManager()
        .getTeamMemberProjects(
            viewHandler.getModelManager().getSelectedTeamMember()));
    projectListView.setOnMousePressed(new EventHandler<MouseEvent>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        if (projectListView.getSelectionModel().getSelectedItem() != null)
        {
          viewHandler.getModelManager().setSelectedProject(
              (Project) projectListView.getSelectionModel().getSelectedItem());
          viewHandler.openView("viewProject");
        }
      }
    });
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewList()
  {
    viewHandler.openView("viewList");
  }
}
