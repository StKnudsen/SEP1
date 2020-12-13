package View;

import Model.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

public class ViewListController
{
  private Region root;
  private ViewHandler viewHandler;

  @FXML private TableView projectsTable;
  @FXML private TableColumn<String, String> projectsTitle;

  @FXML private TableView requirementsTable;
  @FXML private TableColumn<String, String> requirementsPriority;
  @FXML private TableColumn<String, String> requirementsTitle;
  @FXML private TableColumn<String, String> requirementsStatus;

  @FXML private TableView tasksTable;
  @FXML private TableColumn<String, String> tasksPriority;
  @FXML private TableColumn<String, String> tasksTitle;
  @FXML private TableColumn<String, String> tasksStatus;

  @FXML private TableView employeeTable;
  @FXML private TableColumn<String, String> employeeName;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void reset()
  {
    populateProjectsTable();
    populateRequirementsTable();
    populateTasksTable();
    populateEmployeeTable();
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

    // nulstil
    projectsTable.getItems().removeAll(viewHandler.getModelManager().getProjects());

    // Indsæt data i tabellen
    projectsTable.getItems().addAll(viewHandler.getModelManager().getProjectsCurrentUser(
        viewHandler.getModelManager().getCurrentUser()
    ));
    // Fang klik på 'row' og åben det valgte projekt
    projectsTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        if (!(projectsTable.getSelectionModel().getSelectedItem() == null))
        {
          viewHandler.getModelManager().setSelectedProject(
            (Project) projectsTable.getSelectionModel().getSelectedItem()
          );

          viewHandler.openView("viewProject");
        }
      }
    });
  }

  public void populateRequirementsTable()
  {
    // Hvilke data felter vi vil tilknytte column cellerne
    requirementsPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
    requirementsTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    requirementsStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    // nulstil
    requirementsTable.getItems().removeAll(viewHandler.getModelManager().getAllRequirements());

    // Indsæt data i tabellen
    requirementsTable.getItems().addAll(viewHandler.getModelManager().getAllRequirementsCurrentUser(
        viewHandler.getModelManager().getCurrentUser()
    ));

    // Fang klik på 'row' og åbn den valgte requirement
    requirementsTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        if (!(requirementsTable.getSelectionModel().getSelectedItem() == null))
        {
          viewHandler.getModelManager().setSelectedRequirement(
              (Requirement) requirementsTable.getSelectionModel().getSelectedItem()
          );

          viewHandler.openView("viewRequirement");
        }
      }
    });
  }

  public void populateTasksTable()
  {
    // Hvilke data felter vi vil tilknytte column cellerne
    tasksPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
    tasksTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
    tasksStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    // nulstil
    tasksTable.getItems().clear();

    // Indsæt data i tabellen
    tasksTable.getItems().addAll(
        viewHandler.getModelManager().getAllTeamMemberTasks(
            viewHandler.getModelManager().getCurrentUser()
        )
    );

    // Fang klik på 'row' og åben den valgte requirement
    tasksTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        if (!( tasksTable.getSelectionModel().getSelectedItem() == null))
        {
          viewHandler.getModelManager().setSelectedTask(
              (Task) tasksTable.getSelectionModel().getSelectedItem()
          );

          viewHandler.openView("viewTask");
        }
      }
    });
  }

  public void populateEmployeeTable()
  {
    // Hvilke data felter vi vil tilknytte column cellerne
    employeeName.setCellValueFactory(new PropertyValueFactory<>("name"));


    // nulstil
    employeeTable.getItems().clear();

    // Indsæt data i tabellen
    employeeTable.getItems().addAll(
      viewHandler.getModelManager().getEmployees()
    );

    // Fang klik på 'row' og åben den valgte requirement
    employeeTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        if (!( employeeTable.getSelectionModel().getSelectedItem() == null))
        {
          viewHandler.getModelManager().setSelectedTeamMember(
              (TeamMember) employeeTable.getSelectionModel().getSelectedItem()
          );

          viewHandler.openView("viewTeamMember");
        }
      }
    });
  }

  public void addProjectButton()
  {
    viewHandler.openView("viewAddProject");
  }
}
