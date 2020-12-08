package View;

import Model.ColourIT;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private ColourIT colourIT;

  private Scene currentScene;
  private Stage primaryStage;

  // Tilføj alle view controllers her
  private ViewLoginController viewLoginController;
  private ViewListController viewListController;
  private ViewProjectController viewProjectController;
  private ViewRequirementController viewRequirementController;
  private ViewTaskController viewTaskController;
  private ViewAddProjectController viewAddProjectController;
  private ViewAddTaskController viewAddTaskController;
  private ViewAddRequirementController viewAddRequirementController;
  private ViewAddCustomerController viewAddCustomerController;
  private ViewAddEmployeeController viewAddEmployeeController;
  private ViewEditTaskController viewEditTaskController;
  private ViewEditRequirementController viewEditRequirementController;
  private ViewTaskUpdateTimeController viewTaskUpdateTimeController;
  private ViewProjectAddTeamMemberController viewProjectAddTeamMemberController;
  private ViewTaskAddTeamMemberController viewTaskAddTeamMemberController;
  private ViewTeamMemberController viewTeamMemberController;

  public ColourIT getModelManager()
  {
    return colourIT;
  }

  public ViewHandler(ColourIT colourIT)
  {
    this.colourIT = colourIT;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("viewLogin");
  }

  public void openView(String id)
  {
    Region root = null;

    switch (id)
    {
      case "viewLogin":
        root = loadViewLogin();
        break;
      case "viewList":
        root = loadViewList();
        break;
      case "viewProject":
        root = loadViewProject();
        break;
      case "viewRequirement":
        root = loadViewRequirement();
        break;
      case "viewTask":
        root = loadViewTask();
        break;
      case "viewAddProject":
        root = loadViewAddProject();
        break;
      case "viewAddTask":
        root = loadViewAddTask();
        break;
      case "viewAddRequirement":
        root = loadViewAddRequirement();
        break;
      case "viewAddCustomer":
        root = loadViewAddCustomer();
        break;
      case "viewAddEmployee":
        root = loadViewAddEmployee();
        break;
      case "viewEditTask":
        root = loadViewEditTask();
        break;
      case "viewEditRequirement":
        root = loadViewEditRequirement();
        break;
      case "viewTaskUpdateTime":
        root = loadViewTaskUpdateTime();
        break;
      case "viewProjectAddTeamMember":
        root = loadViewProjectAddTeamMember();
        break;
      case "viewTaskAddTeamMember":
        root = loadViewTaskAddTeamMember();
        break;
      case "viewTeamMember":
        root = loadViewTeamMember();
        break;
    }

    currentScene.setRoot(root);
    primaryStage.setTitle((String) root.getUserData());
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  public void closeView()
  {
    primaryStage.close();
  }

  private Region loadViewLogin()
  {
    if (viewLoginController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewLogin.fxml"));
        Region root = loader.load();
        viewLoginController = loader.getController();
        viewLoginController.init(this, colourIT, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewLoginController.reset();

    return viewLoginController.getRoot();
  }

  private Region loadViewList()
  {
    if (viewListController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewList.fxml"));
        Region root = loader.load();
        viewListController = loader.getController();
        viewListController.init(this, colourIT, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewListController.reset();

    return viewListController.getRoot();
  }

  private Region loadViewProject()
  {
    if (viewProjectController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewProject.fxml"));
        Region root = loader.load();
        viewProjectController = loader.getController();
        viewProjectController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewProjectController.reset();

    return viewProjectController.getRoot();
  }

  private Region loadViewRequirement()
  {
    if (viewRequirementController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewRequirement.fxml"));
        Region root = loader.load();
        viewRequirementController = loader.getController();
        viewRequirementController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewRequirementController.reset();

    return viewRequirementController.getRoot();
  }

  private Region loadViewTask()
  {
    if (viewTaskController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewTask.fxml"));
        Region root = loader.load();
        viewTaskController = loader.getController();
        viewTaskController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewTaskController.reset();

    return viewTaskController.getRoot();
  }

  private Region loadViewAddProject()
  {
    if (viewAddProjectController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewAddProject.fxml"));
        Region root = loader.load();
        viewAddProjectController = loader.getController();
        viewAddProjectController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewAddProjectController.reset();

    return viewAddProjectController.getRoot();
  }

  private Region loadViewAddTask()
  {
    if (viewAddTaskController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewAddTask.fxml"));
        Region root = loader.load();
        viewAddTaskController = loader.getController();
        viewAddTaskController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewAddTaskController.reset();

    return viewAddTaskController.getRoot();
  }

  private Region loadViewAddRequirement()
  {
    if (viewAddRequirementController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewAddRequirement.fxml"));
        Region root = loader.load();
        viewAddRequirementController = loader.getController();
        viewAddRequirementController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewAddRequirementController.reset();

    return viewAddRequirementController.getRoot();
  }

  private Region loadViewAddCustomer()
  {
    if (viewAddCustomerController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewAddCustomer.fxml"));
        Region root = loader.load();
        viewAddCustomerController = loader.getController();
        viewAddCustomerController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewAddCustomerController.reset();

    return viewAddCustomerController.getRoot();
  }

  private Region loadViewAddEmployee()
  {
    if (viewAddEmployeeController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewAddEmployee.fxml"));
        Region root = loader.load();
        viewAddEmployeeController = loader.getController();
        viewAddEmployeeController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewAddEmployeeController.reset();

    return viewAddEmployeeController.getRoot();
  }

  private Region loadViewEditTask()
  {
    if (viewEditTaskController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewEditTask.fxml"));
        Region root = loader.load();
        viewEditTaskController = loader.getController();
        viewEditTaskController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewEditTaskController.reset();

    return viewEditTaskController.getRoot();
  }

  private Region loadViewEditRequirement()
  {
    if (viewEditRequirementController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewEditRequirement.fxml"));
        Region root = loader.load();
        viewEditRequirementController = loader.getController();
        viewEditRequirementController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewEditRequirementController.reset();

    return viewEditRequirementController.getRoot();
  }

  private Region loadViewTaskUpdateTime()
  {
    if (viewTaskUpdateTimeController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewTaskUpdateTime.fxml"));
        Region root = loader.load();
        viewTaskUpdateTimeController = loader.getController();
        viewTaskUpdateTimeController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewTaskUpdateTimeController.reset();

    return viewTaskUpdateTimeController.getRoot();
  }

  private Region loadViewProjectAddTeamMember()
  {
    if (viewProjectAddTeamMemberController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewProjectAddTeamMember.fxml"));
        Region root = loader.load();
        viewProjectAddTeamMemberController = loader.getController();
        viewProjectAddTeamMemberController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewProjectAddTeamMemberController.reset();

    return viewProjectAddTeamMemberController.getRoot();
  }

  private Region loadViewTaskAddTeamMember()
  {
    if (viewTaskAddTeamMemberController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewTaskAddTeamMember.fxml"));
        Region root = loader.load();
        viewTaskAddTeamMemberController = loader.getController();
        viewTaskAddTeamMemberController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewTaskAddTeamMemberController.reset();

    return viewTaskAddTeamMemberController.getRoot();
  }

  private Region loadViewTeamMember()
  {
    if (viewTeamMemberController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewTeamMember.fxml"));
        Region root = loader.load();
        viewTeamMemberController = loader.getController();
        viewTeamMemberController.init(this, root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    viewTeamMemberController.reset();

    return viewTeamMemberController.getRoot();
  }
}
