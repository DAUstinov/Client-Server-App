package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkWithDB2;

public class AddReaderController {

    @FXML
    private TextField dobField;

    @FXML
    private TextField PNField;

    @FXML
    private TextField FIOField;


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
    public void okClick() {

        String FIO = FIOField.getText();
        String dateOfbirth = dobField.getText();
        String phoneNumber = PNField.getText();
        WorkWithDB2.insertIntoDBReaders(FIO, dateOfbirth , phoneNumber);

        cancClick();

    }

}
