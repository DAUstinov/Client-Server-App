package controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.WorkWithDB;

import java.util.Timer;
import java.util.TimerTask;

public class CountController {

    @FXML
    private Button Form;

    @FXML
    private Button Book;

    @FXML
    private Button Publisher;

    @FXML
    private Button Reader;

    @FXML
    private Button cancelButton;

    Timer timer = new Timer();

    @FXML
    void bookCount() throws ClassNotFoundException {
        Book.setText(String.valueOf(WorkWithDB.getEntitiesQt()));
        TimerTask task = new TimerTask() {

            public void run() {
                Book.setText("");
            }
        };
        timer.schedule(task,500);
    }

    @FXML
    void cancClick() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void formCount() throws ClassNotFoundException {
        Form.setText(String.valueOf(WorkWithDB.getEntitiesForm()));
    }

    @FXML
    void publisherCount() throws ClassNotFoundException {
        Publisher.setText(String.valueOf(WorkWithDB.getEntitiesPublisher()));

    }

    @FXML
    void readerCount() throws ClassNotFoundException {
        Reader.setText(String.valueOf(WorkWithDB.getEntitiesReader()));

    }

}


