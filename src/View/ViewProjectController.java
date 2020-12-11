package View;

import Model.Requirement;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ViewProjectController
{
  @FXML private Label projectTitle;
  @FXML private Label projectCustomer;
  @FXML private Label projectEstimate;
  @FXML private Label projectTimeSpent;
  @FXML private Label projectResponsibleTeamMember;

  @FXML private ListView teamMemberList;
  @FXML private TableView requirementsTable;
  @FXML private TableColumn prioritering;
  @FXML private TableColumn requirementTitle;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    projectTitle
        .setText(viewHandler.getModelManager().getSelectedProject().getTitle());
    projectCustomer.setText(
        viewHandler.getModelManager().getSelectedProject().getCustomer());
    projectEstimate.setText(
        viewHandler.getModelManager().getSelectedProject().getEstimate());
    projectTimeSpent.setText(
        viewHandler.getModelManager().getSelectedProject().getTimeSpent());
    projectResponsibleTeamMember.setText(
        viewHandler.getModelManager().getSelectedProject()
            .getResponsibleTeamMember());

    teamMemberList.getItems().removeAll(
        viewHandler.getModelManager().getSelectedProject().getTeamMemberList());
    teamMemberList.getItems().addAll(
        viewHandler.getModelManager().getSelectedProject().getTeamMemberList());

    populateRequirementsTable();
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewList()
  {
    viewHandler.openView("viewList");
  }

  public void addRequirement()
  {
    viewHandler.openView("viewAddRequirement");
  }

  public void addTeamMember()
  {
    viewHandler.openView("viewProjectAddTeamMember");
  }

  public void populateRequirementsTable()
  {
    // Hvilke data felter vi vil tilknytte column cellerne
    prioritering.setCellValueFactory(new PropertyValueFactory<>("priority"));
    requirementTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

    // nulstil
    requirementsTable.getItems().clear();

    // Indsæt data i tabellen */
    requirementsTable.getItems().addAll(
        viewHandler.getModelManager().getSelectedProject().getRequirementsForCurrentUser(
                viewHandler.getModelManager().getCurrentUser()
        )
    );

    // Fang klik på 'row' og åbn den valgte requirement
    requirementsTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        viewHandler.getModelManager().setSelectedRequirement(
            (Requirement) requirementsTable.getSelectionModel()
                .getSelectedItem());

        viewHandler.openView("viewRequirement");
      }
    });
  }
}
