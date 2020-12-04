package View;

import Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class ViewProjectController
{
  @FXML private Label title;

  private Region root;
  private Model model;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Model model, Region root)
  {
    this.root = root;
    this.model = model;
    this.viewHandler = viewHandler;
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

  public void setTitleLabel(String text)
  {
    title.setText(text);
  }
}