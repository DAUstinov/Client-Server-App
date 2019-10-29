package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkWithDB2;

public class UpdateReaderController {

    @FXML
    private TextField dobField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField FIOField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField idUpField;

    @FXML
    public void cancClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void okClick() {
        int id = Integer.parseInt(idUpField.getText());
        String FIO = FIOField.getText();
        String dateOfbirth = dobField.getText();
        String phoneNumber = phoneNumberField.getText();
        WorkWithDB2.updateDBReaders(id , FIO, dateOfbirth , phoneNumber);

        cancClick();

    }

}
