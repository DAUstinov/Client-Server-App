package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkWithDB2;

public class AddFormController {

    @FXML
    private TextField ReaderIDFormDield;

    @FXML
    private TextField dateOfIssueFiled;

    @FXML
    private TextField BookIDFormDield;

    @FXML
    private TextField returnDateField;

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
        int bookID = Integer.parseInt(BookIDFormDield.getText());
        int readerID = Integer.parseInt(ReaderIDFormDield.getText());
        String dateOfIssue = dateOfIssueFiled.getText();
        String returnDate = returnDateField.getText();
        WorkWithDB2.insertIntoDBForm(bookID ,readerID , dateOfIssue , returnDate);

        cancClick();

    }

}
