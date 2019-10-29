package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkWithDB2;

public class DeleteFormController {

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
        int bookID = Integer.parseInt(idField.getText());
        WorkWithDB2.deleteFromDBForm(bookID);

        cancClick();


    }


}