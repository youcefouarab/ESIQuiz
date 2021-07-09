package sample;



import javafx.event.ActionEvent;

import java.awt.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

    public Button button ;
    public void handleButtonClick () {
        //button = (Button )ev.getSource();
        System.out.println("button clické");
    }
}
