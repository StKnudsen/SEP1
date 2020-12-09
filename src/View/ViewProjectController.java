package View;

import Model.ColourIT;
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
  @FXML private Label title;
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
    title.setText(viewHandler.getModelManager().getSelectedProject().getTitle());
    teamMemberList.getItems().removeAll(viewHandler.getModelManager().getSelectedProject().getTeamMemberList());
    teamMemberList.getItems().addAll(viewHandler.getModelManager().getSelectedProject().getTeamMemberList());

    populateRequirementsTable();
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

  public void populateRequirementsTable()
  {
    // Hvilke data felter vi vil tilknytte column cellerne
    prioritering.setCellValueFactory(new PropertyValueFactory<>("priority"));
    requirementTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

    // nulstil
    requirementsTable.getItems().removeAll(
        viewHandler.getModelManager().getSelectedProject().getRequirementList()
    );

    // Indsæt data i tabellen
    requirementsTable.getItems().addAll(
        viewHandler.getModelManager().getSelectedProject().getRequirementList()
    );

    // Fang klik på 'row' og åben den valgte requirement
    requirementsTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        viewHandler.getModelManager().setSelectedRequirement(
            (Requirement) requirementsTable.getSelectionModel().getSelectedItem()
        );

        viewHandler.openView("viewRequirement");
      }
    });
  }
}
