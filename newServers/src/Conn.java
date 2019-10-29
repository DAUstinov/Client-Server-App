

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;
import java.util.ArrayList;

public class Conn {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:myDB.s3db");

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDataBase() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'books' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'year' INT, 'genre' text, 'author' text, 'user' BOOLEAN);");
        statmt.execute("CREATE TABLE if not exists 'readers' ('ID' INTEGER PRIMARY KEY AUTOINCREMENT, 'FIO' text, 'dateOfBirth' text , 'phoneNumber' text ); ");
        statmt.execute("CREATE table if not exists 'form' ('bookID' int , 'readerID' int , 'dateOfIssue' text , 'returnDate' text );");
        statmt.execute("CREATE TABLE if not exists 'publisher' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'bookID' int , 'publisherName' text , 'adress' text);");
        statmt.execute("CREATE TABLE if not exists 'users'('role' INTEGER PRIMARY KEY , 'login' text NOT NULL  , 'password' text NOT NULL )");

    }


    public static void ReadDB() {
        try {
            resSet = statmt.executeQuery("SELECT * FROM books");

            while (resSet.next()) {
                int id = resSet.getInt("id");
                String name = resSet.getString("name");
                int year = resSet.getInt("year");
                String genre = resSet.getString("genre");
                String author = resSet.getString("author");
                String user = resSet.getString("user");
            }

        } catch (SQLException ex) {

        }
    }


    public static ArrayList<Books> DBTableBook() {
        int id = 0;
        String name = " ";
        int year = 0;
        String genre = " ";
        String author = " ";
        Boolean us = true;
        ArrayList<Books> books = new ArrayList<>();
        try {
            resSet = statmt.executeQuery("SELECT * FROM books");

            while (resSet.next()) {
                id = resSet.getInt("id");
                name = resSet.getString("name");
                year = resSet.getInt("year");
                genre = resSet.getString("genre");
                author = resSet.getString("author");
                us = resSet.getBoolean("user");
                books.add(new Books(id, name, year, author, genre, us));
                System.out.print(name);
            }

            System.out.println("Таблица выведена");
        } catch (SQLException ex) { }
        return books;
    }


    public static ObservableList<Books> DBTableBookFind(String bname, String bauthor , String bgenre) {
        int id = 0;
        String name = "";
        int year = 0;
        String genre = "";
        String author = "";
        int kk=1;
        Boolean us = true;
        ObservableList<Books> books = FXCollections.observableArrayList();

        try {
            resSet = statmt.executeQuery("SELECT * FROM books WHERE (name LIKE '"+bname+"') OR (author LIKE '"+bauthor+"') OR (genre LIKE '"+bgenre+"')");

            while (resSet.next()) {

                id = resSet.getInt("id");
                name = resSet.getString("name");
                year = resSet.getInt("year");
                genre = resSet.getString("genre");
                author = resSet.getString("author");
                us = resSet.getBoolean("user");
                books.add(new Books(id, name, year, author, genre, us));
            }


        } catch (SQLException ex) { }
        return books;
    }

    public static void insertIntoDB(String bname, int year, String bgenre, String bauthor, boolean us){

        try {
            resSet = statmt.executeQuery("INSERT INTO books(name, year, genre, author, user) VALUES ('"+bname+"', '"+year+"', '"+bgenre+"', '"+bauthor+"', 'false')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deletFromDB(int id){

        try{
            resSet = statmt.executeQuery("DELETE FROM books WHERE id = " + id );
        }catch (SQLException e){
            e.printStackTrace();}
    }

    public static void updateInDB(int id ,String fname, int year, String genre, String author ,Boolean us)
    {
        try{
            resSet = statmt.executeQuery("UPDATE books SET name = '"+fname+"', year = '"+year+"', genre = '"+genre+"', user= 'false' , author = '"+author+"' WHERE id = "+id);
        }catch (SQLException e){
            e.printStackTrace();}
    }

    public static void insertIntoDBReaders(String FIO,String dateOfBirth,String phoneNumber){

        try{
            resSet=statmt.executeQuery("INSERT INTO readers(FIO, dateOfBirth, phoneNumber) VALUES ('"+FIO+"', '"+dateOfBirth+"', '"+phoneNumber+"')");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static void deletFromDBReaders(int id){

        try{
            resSet = statmt.executeQuery("DELETE FROM readers WHERE id = " + id );
        }catch (SQLException e){
            e.printStackTrace();}
    }

    public static void updateInDBReaders(int id ,String FIO,String dateOfBirth,String phoneNumber)
    {
        try{
            resSet = statmt.executeQuery("UPDATE readers SET FIO = '"+FIO+"', dateOfBirth = '"+dateOfBirth+"', phoneNumber = '"+phoneNumber+"' WHERE id = "+id);
        }catch (SQLException e){
            e.printStackTrace();}
    }

    public static ArrayList<Readers> DBTableReader(){
        int id=0;
        String FIO="";
        String dateOfBirth="";
        String phoneNumber=" ";
        ArrayList<Readers> readers=new ArrayList<>();
        try{
            resSet=statmt.executeQuery("SELECT * FROM readers ");
            while(resSet.next()){
                id=resSet.getInt("id");
                FIO=resSet.getString("FIO");
                dateOfBirth=resSet.getString("dateOfBirth");
                phoneNumber=resSet.getString("phoneNumber");
                readers.add(new Readers(id,FIO,dateOfBirth,phoneNumber));
            }
        }catch(SQLException ex){ex.printStackTrace();}
        return readers;
    }

    public static void insertIntoDBForm(int bookID , int readerID , String dateOfIssue , String returnDate){

        try {
            resSet = statmt.executeQuery("INSERT INTO form( bookID, readerID, dateOfIssue , returnDate) VALUES ( '"+bookID+"', '"+readerID+"', '"+dateOfIssue+"', '"+returnDate+"' )");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deletFromDBForm(int bookID){
        try {
            resSet = statmt.executeQuery("DELETE FROM form WHERE bookID = " +bookID );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Formulars> DBTableForms() {
        int readerID = 0;
        int bookID = 0;
        String dateOfIssue = "";
        String returnDate = "";
        ArrayList<Formulars> forms = new ArrayList<>();
        try {
            resSet = statmt.executeQuery("SELECT * FROM form");

            while (resSet.next()) {
                readerID = resSet.getInt("readerID");
                bookID = resSet.getInt("bookID");
                dateOfIssue = resSet.getString("dateOfIssue");
                returnDate = resSet.getString("returnDate");
                forms.add(new Formulars(readerID, bookID, dateOfIssue, returnDate));
                System.out.print(dateOfIssue);
            }

            System.out.println("Таблица выведена");
        } catch (SQLException ex) { ex.printStackTrace(); }
        return forms;
    }

    public static void insertIntoDBPublisher(int bookID , String publisherName , String adress){

        try {
            resSet = statmt.executeQuery("INSERT INTO publisher( bookID, publisherName , adress) VALUES ( '"+bookID+"', '"+publisherName+"', '"+adress+"' )");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deletFromDBPublisher(int id){
        try {
            resSet = statmt.executeQuery("DELETE FROM publisher WHERE id = " +id );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateInDBPublisher(int id , int bookID , String publisherName , String adress ){
        try {
            resSet = statmt.executeQuery("UPDATE publisher SET bookID = '"+bookID+"' , publisherName = '"+publisherName+"' , adress = '"+adress+"' WHERE id =" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Publishers> DBTablePublisher(){
        int id = 0;
        int bookID = 0;
        String publisherName = "";
        String adress = "";
        ArrayList<Publishers> publishers = new ArrayList<>();
        try{
            resSet = statmt.executeQuery("SELECT * FROM publisher");
            while (resSet.next()){
                id = resSet.getInt("id");
                bookID = resSet.getInt("bookID");
                publisherName = resSet.getString("publisherName");
                adress = resSet.getString("adress");
                publishers.add(new Publishers(id , bookID , publisherName , adress));
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return publishers;
    }

    public static ArrayList<Users> DBTableUsers(){
        int role = 0;
        String login = "";
        String password = "";
        ArrayList<Users> user = new ArrayList<>();
            try {
                resSet = statmt.executeQuery("SELECT * FROM users");
                while (resSet.next()){
                    role = resSet.getInt("role");
                    login = resSet.getString("login");
                    password = resSet.getString("password");
                    user.add(new Users(role , login , password));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return user;
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

    public static int getEntitiesQt() throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT COUNT(*) FROM books ");
        System.out.println("getEntitiesQt");
        return resSet.getInt(1);
    }

    public static int getEntitiesReader() throws SQLException
    {
        resSet = statmt.executeQuery("SELECT COUNT (*) FROM readers ");
        return  resSet.getInt(1);
    }

    public static int getEntitiesForm() throws SQLException
    {
        resSet = statmt.executeQuery("SELECT COUNT (*) FROM form ");
        return  resSet.getInt(1);
    }

    public static int getEntitiesPublisher() throws SQLException
    {
        resSet = statmt.executeQuery("SELECT COUNT (*) FROM publisher ");
        return  resSet.getInt(1);
    }

}