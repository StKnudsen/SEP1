package View;

import Model.ColourIT;
import javafx.application.Application;
import javafx.stage.Stage;
import parser.ParserException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws ParserException
    {
        ColourIT colourIT = new ColourIT();
        ViewHandler viewHandler = new ViewHandler(colourIT);
        viewHandler.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
