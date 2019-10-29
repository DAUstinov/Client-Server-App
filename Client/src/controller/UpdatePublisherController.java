package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.WorkWithDB2;

public class UpdatePublisherController {

    @FXML
    private TextField bookIDField;

    @FXML
    private TextField adressField;

    @FXML
    private TextField publisherNameField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField idUpField;

    @FXML
    public void okClick(){
        int id = Integer.parseInt(idUpField.getText());
        int bookID = Integer.parseInt(bookIDField.getText());
        String publisherName = publisherNameField.getText();
        String adress = adressField.getText();

        WorkWithDB2.updateDBPublisher(id , bookID , publisherName , adress);


        cancClick();
    }

    @FXML
    public void cancClick(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
