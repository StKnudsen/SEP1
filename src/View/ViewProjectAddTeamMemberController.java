package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewProjectAddTeamMemberController
{
  @FXML private Button closeButton;

  private Region root;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
  }

  public void closeButtonAction()
  {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
  }

  public void reset()
  {
    //
  }

  public Region getRoot()
  {
    return root;
  }

  public void gotoViewLogin()
  {
    viewHandler.openView("viewLogin");
  }
}
