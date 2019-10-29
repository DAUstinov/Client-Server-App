package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkWithDB2;

public class AddPublisherController {

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField publisherNameField;

    @FXML
    private TextField pidField;

    @FXML
    private TextField adressField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void cancClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void okClick() {
        int bookID = Integer.parseInt(bookIdField.getText());
        String publisherName = publisherNameField.getText();
        String adress = adressField.getText();
        WorkWithDB2.insertIntoDBPublisher(bookID, publisherName , adress);

        cancClick();

    }

}
