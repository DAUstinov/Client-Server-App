package model;

import main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Formulars;
import model.Publishers;
import model.Readers;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithDB2 {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:myDB.s3db");
        statmt = conn.createStatement();

        System.out.println("База читателей Подключена!");
    }

    public static void CreateDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        //statmt.execute("CREATE TABLE if not exists 'readers' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'year' INT, 'genre' text, 'author' text, 'user' BOOLEAN);");

        //System.out.println("Таблица создана или уже существует.");
    }

    public static void insertIntoDBReaders(String FIO, String dateOfbirth, String phoneNumber){

        try {
            Main.coos.writeObject("addReader");
            Main.coos.writeObject(FIO+"&"+dateOfbirth+"&"+phoneNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteFromDBReaders(int id){

        try {
            Main.coos.writeObject("deleteReader");
            Main.coos.writeObject(id+"&");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateDBReaders(int id , String FIO , String dateOfbirth ,  String phoneNumber){

        try {
            Main.coos.writeObject("updateReader");
            Main.coos.writeObject(id+"&"+FIO+"&"+dateOfbirth+"&"+phoneNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Readers> DBTableReader(){
        List<Readers> readers = new ArrayList<>();
        String answer;
        try{
            Main.coos.writeObject("readReader");
            while (!(answer = (String)Main.cois.readObject()).equals("end")){
                String [] answerArr = answer.split("&");
                readers.add(new Readers(Integer.parseInt(answerArr[0]) , answerArr[1], answerArr[2] , answerArr[3]));
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Readers> reader1 = FXCollections.observableArrayList(readers);
        return reader1;
    }

    public static int DBTableUsers(String login, String password){

        int answer=2;
        try{
            Main.coos.writeObject("Authorization");

            Main.coos.writeObject(login);
            Main.coos.writeObject(password);
            answer = (int)Main.cois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public static void insertIntoDBForm(int bookID , int readerID , String dateOfIssue , String returnDate){

        try {
            Main.coos.writeObject("addForm");
            Main.coos.writeObject(bookID+"&"+readerID+"&"+dateOfIssue+"&"+returnDate);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteFromDBForm(int bookID){
        try {
            Main.coos.writeObject("deleteForm");
            Main.coos.writeObject(bookID+"&");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Formulars> DBTableForm(){
        List<Formulars> forms = new ArrayList<>();
        String answer;
        try {
            Main.coos.writeObject("readForm");
            while (!(answer = (String)Main.cois.readObject()).equals("end")) {

                String [] answerArr = answer.split("&");
                forms.add(new Formulars(Integer.parseInt(answerArr[0]), Integer.parseInt(answerArr[1]), answerArr[2], answerArr[3]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Formulars> forms1 = FXCollections.observableArrayList(forms);
        return forms1;
    }

    public static void insertIntoDBPublisher(int bookID , String publisherName , String adress){

        try {
            Main.coos.writeObject("addPublisher");
            Main.coos.writeObject(bookID+"&"+publisherName+"&"+adress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFromDBPublisher(int id){

        try {
            Main.coos.writeObject("deletePublisher");
            Main.coos.writeObject(id+"&");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void updateDBPublisher(int id , int bookID , String publisherName , String adress){
        try {
            Main.coos.writeObject("updatePublisher");
            Main.coos.writeObject(id+"&"+bookID+"&"+publisherName+"&"+adress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Publishers> DBTablePublisher(){
        List<Publishers> publishers = new ArrayList<>();
        String answer;
        try {
            Main.coos.writeObject("readPublisher");
            while (!(answer = (String)Main.cois.readObject()).equals("end")){

                String [] answerArr = answer.split("&");
                publishers.add(new Publishers(Integer.parseInt(answerArr[0]),Integer.parseInt(answerArr[1]), answerArr[2], answerArr[3]));
            }

        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Publishers> publishers1 = FXCollections.observableArrayList(publishers);
        return publishers1;
    }

}
