package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.WorkWithDB;
import model.WorkWithDB2;


import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class Main  extends Application {
    public static ObjectOutputStream coos;
    public static ObjectInputStream cois;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../view/AuthorizationPage.fxml"));
        Scene scene = new Scene ( root, 700 , 400);
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) throws ClassNotFoundException , SQLException {

        try {
            Socket clientSocket = new Socket("127.0.0.1", 2222);
            coos = new ObjectOutputStream(clientSocket.getOutputStream());
            cois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        WorkWithDB.Conn();
        WorkWithDB2.Conn();

       launch(args);

    }
}
