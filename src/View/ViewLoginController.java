package View;

import Model.Model;
import javafx.scene.layout.Region;

public class ViewLoginController
{
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

  public void loginButtonPressed()
  {
    viewHandler.openView("viewList");
  }
}
