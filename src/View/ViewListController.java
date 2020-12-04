package View;

import com.sun.javafx.collections.ObservableListWrapper;

import Model.Task;
import Model.Model;
import Model.Project;
import Model.Requirement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
  @FXML private TableColumn<String, String> tasksPriority;
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
    // Dummy data
    dummyProjects.add(new Project("Best Project ever!", "Hah, you wish"));
    dummyProjects.add(new Project("Colour IT PMS", "Started"));

    // Hvilke data felter vi vil tilknytte column cellerne
    projectsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    projectsStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    // Indsæt data i tabellen
    projectsTable.getItems().addAll(dummyProjects);

    // Fang klik på 'row' og åben det valgte projekt
    projectsTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        try
        {
          Project project = (Project) projectsTable.getSelectionModel()
              .getSelectedItem();
          FXMLLoader loader = new FXMLLoader(
              getClass().getResource("ViewProject.fxml"));
          Parent root = loader.load();

          //Følgende to linjer er et krav for at kunne sende data videre til ViewProjectController
          ViewProjectController viewProjectController = loader.getController();
          viewProjectController.setTitleLabel(project.getTitle());

          Stage stage = new Stage();
          stage.setScene(new Scene(root));
          stage.setTitle("Colour IT - Projekt: " + project.getTitle());
          stage.show();

        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    });

    //System.out.println((projectsTable.getSelectionModel().getSelectedItem()));
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
        try
        {
          Requirement requirement = (Requirement) requirementsTable.getSelectionModel().getSelectedItem();
          FXMLLoader loader = new FXMLLoader(
              getClass().getResource("ViewRequirement.fxml"));
          Parent root = loader.load();

          //Følgende to linjer er et krav for at kunne sende data videre til ViewProjectController
          ViewRequirementController viewRequirementController = loader.getController();
          viewRequirementController.setTitleLabel(requirement.getTitle());

          Stage stage = new Stage();
          stage.setScene(new Scene(root));
          stage.setTitle("Colour IT - Requirement: " + requirement.getTitle());
          stage.show();

        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
      });

    //System.out.println((requirementsTable.getSelectionModel().getSelectedItem()));
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
        try
        {
          Task task = (Task) tasksTable.getSelectionModel().getSelectedItem();
          FXMLLoader loader = new FXMLLoader(
              getClass().getResource("ViewTask.fxml"));
          Parent root = loader.load();

          //Følgende to linjer er et krav for at kunne sende data videre til ViewProjectController
          ViewTaskController viewTaskController = loader.getController();
          viewTaskController.setTitleLabel(task.getTitle());

          Stage stage = new Stage();
          stage.setScene(new Scene(root));
          stage.setTitle("Colour IT - Task: " + task.getTitle());
          stage.show();

        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    });

    //System.out.println((tasksTable.getSelectionModel().getSelectedItem()));
  }

  @FXML public void addProjectButton()
  {
    try
    {
      //Nedenstående skal bruges til at overføre en bruger senere
      //Task task = (Task) tasksTable.getSelectionModel().getSelectedItem();
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("ViewAddProject.fxml"));
      Parent root = loader.load();

      //Følgende to linjer er et krav for at kunne sende data videre til ViewProjectController
      //ViewAddProjectController viewAddProjectController = loader.getController();
      //viewAddProjectController.setTitleLabel(task.getTitle());

      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.setTitle("Colour IT - Nyt projekt: ");
      stage.show();

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }


  @FXML public void ClickItem(MouseEvent event)
  {
    if (event.getClickCount() == 1)
    {
      System.out.println(projectsTable.getSelectionModel().getSelectedItem());
    }
  }
}
