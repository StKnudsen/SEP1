package View;

import Model.ColourIT;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{

  @Override public void start(Stage primaryStage)
  {
    ColourIT colourIT = new ColourIT();
    ViewHandler viewHandler = new ViewHandler(colourIT);
    viewHandler.start(primaryStage);

    primaryStage.setOnCloseRequest(e -> {
      try
      {
        viewHandler.closeView();
      }
      catch (IOException ioException)
      {
        ioException.printStackTrace();
      }
    });
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
