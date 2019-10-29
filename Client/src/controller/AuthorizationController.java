package controller;

import java.io.IOException;
import model.WorkWithDB;

import model.WorkWithDB2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javafx.stage.Window;
import model.*;

public class AuthorizationController {

    @FXML
    TableView<Books> TableBook;

    @FXML
    TableView<Readers> TableReaders;

    @FXML
    TableView<Formulars> TableForm;

    @FXML
    TableView<Publishers> TablePublisher;

    @FXML
    TableColumn<Publishers, Integer> pid;

    @FXML
    TableColumn<Publishers, Integer> bookId;

    @FXML
    TableColumn<Publishers, String> publisherName;

    @FXML
    TableColumn<Publishers, String> adress;

    @FXML
    TableColumn<Formulars, Integer> readerID;

    @FXML
    TableColumn<Formulars, Integer> bookID;

    @FXML
    TableColumn<Formulars, String> dateOfIssue;

    @FXML
    TableColumn<Formulars, String> returnDate;

    @FXML
    TableColumn<Books, String> iName;

    @FXML
    TableColumn <Books,Integer> iID;

    @FXML
    TableColumn <Books, Integer> iYear;

    @FXML
    TableColumn <Books, String> iAuthor;

    @FXML
    TableColumn <Books, String> iGenre;

    @FXML
    TableColumn <Books, Boolean> iUser;

    @FXML
    private BarChart<String , Integer> barChart;

    @FXML
    private BarChart<String , Integer> bar1Chart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private TableColumn<Readers, Integer> id;

    @FXML
    private TableColumn<Readers, String> dateOfBirth;

    @FXML
    private TableColumn<Readers, String> FIO;

    @FXML
    private TableColumn<Readers, String> phoneNumber;

    @FXML
    private AnchorPane authorization;

    @FXML
    private Tab tab1;

    @FXML
    private Tab tab2;

    @FXML
    private Tab tab3;

    @FXML
    private AnchorPane registration;

    @FXML
    private TextField nameField;

    @FXML
    private TextField loginregField;

    @FXML
    private TextField surnameField;

    @FXML
    private PasswordField PasswordregField;

    @FXML
    private Button regregButton;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private TextField loginField;

    @FXML
    private Button logInButton;

    @FXML
    private Button regButton;

    @FXML
    private Button FormClick;

    @FXML
    private AnchorPane Mainpage;

    @FXML
    private AnchorPane Form;

    @FXML
    private Button buttonview;

    @FXML
    private Button buttonfind;

    @FXML
    private Button Count;

    @FXML
    private Button buttonclose;

    @FXML
    private AnchorPane grafik;

    @FXML
    private AnchorPane grafik1;

    @FXML
    private AnchorPane Library;
    private Window dialogStage;

    //Переход на окно авторизации
    @FXML
    public void onClick(){
            authorization.setVisible(false);
            registration.setVisible(true);

    }

    //-----ГРАФИКИ-----

    private ObservableList<String> genreList = FXCollections.observableArrayList();
    private ObservableList<Integer> freqCount = FXCollections.observableArrayList();
    private ObservableList<String> publisherList = FXCollections.observableArrayList();
    private ObservableList<Integer> freq1Count = FXCollections.observableArrayList();

    @FXML
    public void grafik1Click(){ArrayList<Publishers> publishers=new ArrayList<>(WorkWithDB2.DBTablePublisher());

        for(Publishers publisher:publishers){
            publisherList.add(publisher.getPublisherName());
        }

        HashSet<String> nameSet=new HashSet<>(publisherList);

        System.out.println(nameSet);

        for(String str2:nameSet){
            freq1Count.add(Collections.frequency(publisherList,str2));
        }
        publisherList = FXCollections.observableArrayList(publisherList);

        XYChart.Series<String , Integer> series = new XYChart.Series<>() ;
        for(int j=0;j<freq1Count.size();j++){
            series.getData().add(new XYChart.Data<>(publisherList.get(j),freq1Count.get(j)));

        }

        bar1Chart.getData().add(series);
        grafik1.setVisible(true);
        Mainpage.setVisible(false);

    }

    @FXML
    public void grafikClick(){
        ArrayList<Books> books=new ArrayList<>(WorkWithDB.DBTable());

        for(Books book:books){
            genreList.add(book.getrGENRE());
        }

        HashSet<String> genreSet=new HashSet<>(genreList);

        System.out.println(genreSet);

        for(String str1:genreSet){
            freqCount.add(Collections.frequency(genreList,str1));
        }
        genreList = FXCollections.observableArrayList(genreList);

        XYChart.Series<String , Integer> series = new XYChart.Series<>();
        for(int i=0;i<freqCount.size();i++){
            series.getData().add(new XYChart.Data<>(genreList.get(i),freqCount.get(i)));

        }

        barChart.getData().add(series);
        grafik.setVisible(true);
        Mainpage.setVisible(false);
    }

//Проверка полей при регистрации
    @FXML
    public void onClickreg(){
        String name = nameField.getText().trim();
        String surname = surnameField.getText().trim();
        String loginreg = loginregField.getText().trim();
        String passwordreg = PasswordregField.getText().trim();

        if(!name.equals("") && !surname.equals("") && !loginreg.equals("") && !passwordreg.equals("")){
            RegUser(name , surname , loginreg , passwordreg);
            authorization.setVisible(true);
            registration.setVisible(false);
        }
        else{System.out.println("error");}
    }

//Переход на главное окно и проверка на заполнение полей при логине
    @FXML
    public void LogClick(){
        String login = loginField.getText().trim();
        String password = PasswordField.getText().trim();
        String errorMessage = "";

        if(!login.isEmpty() && !password.isEmpty()){
            if(WorkWithDB2.DBTableUsers(login,password)==2){
                errorMessage ="Книга не найдена";
            }else{
                authorization.setVisible(false);
                Mainpage.setVisible(true);
            }
        }

        if (errorMessage.length() == 0) {
            authorization.setVisible(false);
            Mainpage.setVisible(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("Неверные данных");
            alert.setHeaderText("Проверьте корректность введенных данных");
            alert.setContentText(errorMessage);

            alert.showAndWait();

        }

    }

//Просмотр данных
    @FXML
    public void viewLibraryClick(){
        Mainpage.setVisible(false);
        Library.setVisible(true);

        ObservableList<Books> data = FXCollections.observableArrayList();
        tab();
        TableBook.setItems(WorkWithDB.DBTable());
        ObservableList<Readers> readers = FXCollections.observableArrayList();
        tab1();
        TableReaders.setItems(WorkWithDB2.DBTableReader());
        ObservableList<Formulars> forms1 = FXCollections.observableArrayList();
        tab2();
        TableForm.setItems(WorkWithDB2.DBTableForm());
        ObservableList<Publishers> publishers =FXCollections.observableArrayList();
        tab3();
        TablePublisher.setItems(WorkWithDB2.DBTablePublisher());
    }

    private void tab() {
        iID.setCellValueFactory(new PropertyValueFactory<Books, Integer>("rID"));
        iName.setCellValueFactory(new PropertyValueFactory<Books, String>("rNAME"));
        iYear.setCellValueFactory(new PropertyValueFactory<Books, Integer>("rYEAR"));
        iAuthor.setCellValueFactory(new PropertyValueFactory<Books, String>("rAUTHOR"));
        iGenre.setCellValueFactory(new PropertyValueFactory<Books, String>("rGENRE"));
        iUser.setCellValueFactory(new PropertyValueFactory<Books, Boolean>("rUSER"));
    }

    private void tab1(){
        id.setCellValueFactory(new PropertyValueFactory<Readers, Integer>("id"));
        FIO.setCellValueFactory(new PropertyValueFactory<Readers, String>("FIO"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<Readers, String>("dateOfBirth"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Readers, String>("phoneNumber"));
    }

    private void tab2(){
        readerID.setCellValueFactory(new PropertyValueFactory<Formulars, Integer>("readerID"));
        bookID.setCellValueFactory(new PropertyValueFactory<Formulars, Integer>("bookID"));
        dateOfIssue.setCellValueFactory(new PropertyValueFactory<Formulars, String>("dateOfIssue"));
        returnDate.setCellValueFactory(new PropertyValueFactory<Formulars, String>("returnDate"));
    }

    private void tab3(){
        pid.setCellValueFactory(new PropertyValueFactory<Publishers, Integer>("id"));
        bookId.setCellValueFactory(new PropertyValueFactory<Publishers, Integer>("bookID"));
        publisherName.setCellValueFactory(new PropertyValueFactory<Publishers, String>("publisherName"));
        adress.setCellValueFactory(new PropertyValueFactory<Publishers, String>("adress"));
    }

    //Подсчет количества книг
    @FXML
    public void countClick() throws ClassNotFoundException, SQLException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Count.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }



    //Переход назад к главной странице
    @FXML
    public void backClick(){
        Mainpage.setVisible(true);
        grafik.setVisible(false);
        grafik1.setVisible(false
        );
        Library.setVisible(false);

    }


//Открытие окна добавления
    @FXML
    public void addClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/add1.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void addFormClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AddReader.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void FormAddClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AddForm.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void addPublisherClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AddPublisher.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

//открытие окна удаления
    @FXML
    public void deleteClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/delete.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void deleteReaderClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/deleteReader.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void deleteFormClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/deleteForm.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void deletePublisherClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/deletePublisher.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

//Открытие окна обновления
    @FXML
    public void updateClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/update.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void updateReaderClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/UpdateReader.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void updatePublisherClick(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/updatePublisher.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    //Выход
    @FXML
    public void clickClose(){
        Stage stage = (Stage) buttonclose.getScene().getWindow();
        stage.close();

    }


    private void RegUser(String name, String surname, String loginreg, String passwordreg) {
    }

    private void loginUser(String login, String password) {
    }

}

