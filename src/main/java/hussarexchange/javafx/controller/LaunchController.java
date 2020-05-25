package hussarexchange.javafx.controller;

import hussarexchange.javafx.main.MyApplication;
import hussarexchange.javafx.model.Table;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.IOException;

@Slf4j
public class LaunchController {

    @Inject
    private FXMLLoader fxmlLoader;

    @FXML
    private TextField playerNameTextField;

    @FXML
    private Label errorLabel;


    public void startAction(ActionEvent actionEvent) throws IOException {
        if (playerNameTextField.getText().isEmpty()) {
            errorLabel.setText("Írd be a neved!");
            log.error("Miss the name!", playerNameTextField.getText());
        } else {
            Parent page = (Parent) fxmlLoader.load(getClass().getResource("/fxml/GameScene.fxml"));
            MyApplication.stage.getScene().setRoot(page);
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    Table.currentState[i][j] = Table.startState[i][j];
                }
                System.out.println();
            }            /*
            fxmlLoader.<GameController>getController().setPlayerName(playerNameTextField.getText());
            log.info("The players name is set to {}, loading game scene", playerNameTextField.getText());
            */
        }
    }

}