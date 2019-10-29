package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkWithDB;

public class DeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void okClick() {
        int id = Integer.parseInt(idField.getText());
        WorkWithDB.deletFromDB(id);

        cancClick();


    }


}