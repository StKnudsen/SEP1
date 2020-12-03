package View;

import Model.Task;
import Model.Model;
import Model.Project;
import Model.Requirement;
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
  private ArrayList<Requirement> dummyRequirements;
  private ArrayList<Task> dummyTasks;

  @FXML private TableView projectsTable;
  @FXML private TableColumn<String, String> projectsTitle;
  @FXML private TableColumn<String, String> projectsStatus;

  @FXML private TableView requirementsTable;
  @FXML private TableColumn<String, String> requirementsTitle;
  @FXML private TableColumn<String, String> requirementsStatus;

  @FXML private TableView tasksTable;
  @FXML private TableColumn<String, String> tasksTitle;
  @FXML private TableColumn<String, String> tasksStatus;

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.root = root;
    this.model = model;
    this.viewHandler = viewHandler;

    dummyProjects = new ArrayList<>();
    dummyRequirements = new ArrayList<>();
    dummyTasks = new ArrayList<>();

    populateProjectsTable();
    populateRequirementsTable();
    populateTasksTable();
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
    dummyProjects.add(new Project("Best Project ever!", "Hah, you wish"));
    dummyProjects.add(new Project("Colour IT PMS", "Started"));

    // Hvilke data felter vi vil tilknytte column cellerne
    projectsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    projectsStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    // Lav vores liste (ArrayList) til observable liste
    ObservableList<Project> projectsList = FXCollections.observableArrayList(dummyProjects);

    // Indsæt data i tabellen
    projectsTable.getItems().addAll(projectsList);

    System.out.println((projectsTable.getSelectionModel().getSelectedItem()));
  }

  public void populateRequirementsTable()
  {
    dummyRequirements.add(new Requirement("Find a fish", "Started"));
    dummyRequirements.add(new Requirement("Slap someone with fish", "Not started"));

    // Hvilke data felter vi vil tilknytte column cellerne
    requirementsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    requirementsStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    // Lav vores liste (ArrayList) til observable liste
    ObservableList<Requirement> requirementsList = FXCollections.observableArrayList(dummyRequirements);

    // Indsæt data i tabellen
    requirementsTable.getItems().addAll(requirementsList);

    System.out.println((requirementsTable.getSelectionModel().getSelectedItem()));
  }

  public void populateTasksTable()
  {
    dummyTasks.add(new Task("Find a fishing hole", "Hah, you wish"));
    dummyTasks.add(new Task("Remember fishing pole", "F*****ck"));

    // Hvilke data felter vi vil tilknytte column cellerne
    tasksTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    tasksStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    // Lav vores liste (ArrayList) til observable liste
    ObservableList<Task> taskList = FXCollections.observableArrayList(dummyTasks);

    // Indsæt data i tabellen
    tasksTable.getItems().addAll(taskList);

    System.out.println((tasksTable.getSelectionModel().getSelectedItem()));
  }
}
