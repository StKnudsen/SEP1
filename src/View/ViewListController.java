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
    projectsTable.getItems().addAll(viewHandler.getModelManager().getProjects());

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
    requirementsTable.getItems().addAll(viewHandler.getModelManager().getAllRequirements());

    // Fang klik på 'row' og åben den valgte requirement
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
    tasksTable.getItems().removeAll(viewHandler.getModelManager().getAllTasks(
        viewHandler.getModelManager().getSelectedProject()));

    // Indsæt data i tabellen
    tasksTable.getItems().addAll(viewHandler.getModelManager().getAllTasks(viewHandler.getModelManager().getSelectedProject()));

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

  public void addProjectButton()
  {
    viewHandler.openView("viewAddProject");
  }
}
