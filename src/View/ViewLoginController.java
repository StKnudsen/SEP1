package View;

import Model.ColourIT;
import javafx.scene.layout.Region;

public class ViewLoginController
{
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

  public void loginButtonPressed()
  {
    viewHandler.openView("viewList");
  }

  public void addCustomer()
  {
    viewHandler.openView("viewAddCustomer");
  }

  public void addEmployee()
  {
    viewHandler.openView("viewAddEmployee");
  }
}
