package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkWithDB;

public class UpdateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField authorField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField idUpField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    void initialize() {

    }

    @FXML
    public void okClick(){
        int id = Integer.parseInt(idUpField.getText());
        String fname = nameField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());
        String genre = genreField.getText();
        WorkWithDB.updateInDB(id , fname , year , genre , author , false);


        cancClick();
    }

    @FXML
    public void cancClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}


