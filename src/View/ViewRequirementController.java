package View;

import Model.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ViewRequirementController
{
  @FXML private Label projectTitle;
  @FXML private Label requirementTitle;
  @FXML private Label responsibleTeamMember;
  @FXML private Label requirementType;
  @FXML private Label requirementStatus;
  @FXML private Label deadlineLabel;
  @FXML private TextFlow requirementDescriptionTextFlow;

  @FXML private TableView taskTable;
  @FXML private TableColumn taskPriotity;
  @FXML private TableColumn taskTitle;

  private ViewHandler viewHandler;
  private Region root;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.viewHandler = viewHandler;
    this.root = root;
  }

  public void reset()
  {
    requirementTitle.setText(viewHandler.getModelManager().getSelectedRequirement().getTitle());
    projectTitle.setText(viewHandler.getModelManager().getSelectedRequirement().getProjectTitle());
    responsibleTeamMember.setText(viewHandler.getModelManager().getSelectedRequirement().getResponsibleTeamMember().getName());
    requirementType.setText(viewHandler.getModelManager().getSelectedRequirement().getType());
    requirementStatus.setText(viewHandler.getModelManager().getSelectedRequirement().getStatus());
    if(viewHandler.getModelManager().getSelectedRequirement().getDeadline() != null)
    {
      deadlineLabel.setText(
          viewHandler.getModelManager().getSelectedRequirement().getDeadline().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
    }

    requirementDescriptionTextFlow.getChildren().clear();
    requirementDescriptionTextFlow.getChildren().addAll(new Text(viewHandler.getModelManager().getSelectedRequirement().getDescription()));

    populateTaskTable();
  }


  public Region getRoot()
  {
    return root;
  }

  public void gotoViewList()
  {
    viewHandler.openView("viewList");
  }

  public void addTask()
  {
    viewHandler.openView("viewAddTask");
  }

  public void populateTaskTable()
  {
    // Hvilke data felter vi vil tilknytte column cellerne
    taskPriotity.setCellValueFactory(new PropertyValueFactory<>("priority"));
    taskTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

    // nulstil
    taskTable.getItems().clear();

    // Indsæt data i tabellen
    taskTable.getItems().addAll(
        viewHandler.getModelManager().getSelectedRequirement().getTeamMemberTasks(
            viewHandler.getModelManager().getCurrentUser()
        )
    );

    // Fang klik på 'row' og åben den valgte requirement
    taskTable.setOnMousePressed(new EventHandler<>()
    {
      @Override public void handle(javafx.scene.input.MouseEvent mouseEvent)
      {
        viewHandler.getModelManager().setSelectedTask(
            (Task) taskTable.getSelectionModel().getSelectedItem()
        );

        viewHandler.openView("viewTask");
      }
    });
  }

  public void editRequirement()
  {
    viewHandler.openView("viewEditRequirement");
  }
}
