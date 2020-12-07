package View;

import Model.ColourIT;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class ViewProjectController
{
  @FXML private Label title;

  private Region root;
  private ColourIT colourIT;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ColourIT colourIT, Region root)
  {
    this.root = root;
    this.colourIT = colourIT;
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
