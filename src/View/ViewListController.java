package View;

import Model.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ViewListController
{
  private Region root;
  private ColourIT colourIT;
  private ViewHandler viewHandler;

  private ArrayList<Project> dummyProjects;
  private ArrayList<Requirement> dummyRequirements;
  private ArrayList<Task> dummyTasks;

  @FXML private TableView projectsTable;
  @FXML private TableColumn<String, String> projectsTitle;

  @FXML private TableView requirementsTable;
  @FXML private TableColumn<String, String> requirementsTitle;
  @FXML private TableColumn<String, String> requirementsStatus;

  @FXML private TableView tasksTable;
  @FXML private TableColumn<String, String> tasksPriority;
  @FXML private TableColumn<String, String> tasksTitle;
  @FXML private TableColumn<String, String> tasksStatus;

  public void init(ViewHandler viewHandler, ColourIT colourIT, Region root)
  {
    this.root = root;
    this.colourIT = colourIT;
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
    // Dummy data
    dummyProjects.add(new Project("Best Project ever!", new Customer("Jack"), new TeamMember("Black")));
    dummyProjects.add(new Project("Colour IT PMS", new Customer("Jimifer"), new TeamMember("Jensen")));

    // Hvilke data felter vi vil tilknytte column cellerne
    projectsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

    // Indsæt data i tabellen
    projectsTable.getItems().addAll(dummyProjects);

    // Fang klik på 'row' og åben det valgte projekt
    projectsTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        viewHandler.getModelManager().setSelectedProject(
            (Project) projectsTable.getSelectionModel().getSelectedItem()
        );

        viewHandler.openView("viewProject");
      }
    });
  }

  public void populateRequirementsTable()
  {
    // Dummy data
    dummyRequirements.add(new Requirement("Find a fish", "Started"));
    dummyRequirements
        .add(new Requirement("Slap someone with fish", "Not started"));

    // Hvilke data felter vi vil tilknytte column cellerne
    requirementsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    requirementsStatus
        .setCellValueFactory(new PropertyValueFactory<>("status"));

    // Indsæt data i tabellen
    requirementsTable.getItems().addAll(dummyRequirements);

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

  public void populateTasksTable()
  {
    // Dummy data
    Task task1 = new Task("Find a fishing hole", "Hah, you wish");
    task1.setPriority(dummyTasks.size() + 1);
    dummyTasks.add(task1);

    Task task2 = new Task("Remember fishing pole", "F*****ck");
    task2.setPriority(dummyTasks.size() + 1);
    dummyTasks.add(task2);

    // Hvilke data felter vi vil tilknytte column cellerne
    tasksPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
    tasksTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    tasksStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    // Indsæt data i tabellen
    tasksTable.getItems().addAll(dummyTasks);

    // Fang klik på 'row' og åben den valgte requirement
    tasksTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        viewHandler.getModelManager().setSelectedTask(
            (Task) tasksTable.getSelectionModel().getSelectedItem()
        );

        viewHandler.openView("viewTask");
      }
    });
  }

  public void addProjectButton()
  {
    viewHandler.openView("viewAddProject");
  }
}
