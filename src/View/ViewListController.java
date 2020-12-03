package View;

import Model.Model;
import Model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

import java.util.ArrayList;

public class ViewListController
{
  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  private ArrayList<Project> dummyProjects;

  @FXML private TableView projectsTable;
  @FXML private TableColumn<String, String> projectsTitle;
  @FXML private TableColumn<String, String> projectsStatus;

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.root = root;
    this.model = model;
    this.viewHandler = viewHandler;
    dummyProjects = new ArrayList<>();

    dummyProjects.add(new Project("Best Project ever!", "Hah, you wish"));
    dummyProjects.add(new Project("Colour IT PMS", "Started"));

    populateProjectsTable();
  }

  public void reset()
  {
    // ...
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewLogin()
  {
    viewHandler.openView("viewLogin");
  }

  public void populateProjectsTable()
  {
    // Hvilke data felter vi vil tilknytte column cellerne
    projectsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    projectsStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    // Lav vores liste (ArrayList) til observable liste
    ObservableList<Project> list = FXCollections.observableArrayList(dummyProjects);

    // Indsæt data i tabellen
    projectsTable.getItems().addAll(list);
  }

  public void populateRequirementsTable()
  {

  }

  public void populateTasksTable()
  {

  }
}
