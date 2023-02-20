package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller{

    @FXML//Αλλαγή παραθύρου και εκκίνηση του SinglePlayer mode
    private void SinglePlayer(ActionEvent event) throws IOException {
        Parent singlePlayerParent = FXMLLoader.load(getClass().getResource("SinglePlayer.fxml"));
        Scene singlePlayerScene = new Scene(singlePlayerParent,683, 373.6);

        //This line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(singlePlayerScene);
        window.setTitle("Single Player");
        window.show();
    }

    @FXML
    private void TwoPlayers(ActionEvent event) throws IOException{

        Parent singlePlayerParent = FXMLLoader.load(getClass().getResource("TwoPlayers.fxml"));
        Scene singlePlayerScene = new Scene(singlePlayerParent,683, 373.6);

        //This line gets the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(singlePlayerScene);
        window.setTitle("Two Players");
        window.show();

    }

    @FXML
    private void exit(ActionEvent event){
        Platform.exit();
    }
}