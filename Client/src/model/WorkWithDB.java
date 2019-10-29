package model;

import main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Books;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithDB {

    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:myDB.s3db");

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'books' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'year' INT, 'genre' text, 'author' text, 'user' BOOLEAN);");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException
    {
        statmt.execute("INSERT INTO 'books' ('name', 'phone') VALUES ('Petya', 125453); ");
        statmt.execute("INSERT INTO 'books' ('name', 'phone') VALUES ('Vasya', 321789); ");
        statmt.execute("INSERT INTO 'books' ('name', 'phone') VALUES ('Masha', 456123); ");

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
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
                System.out.println("ID = " + id);
                System.out.println("name = " + name);
                System.out.println("year = " + year);
                System.out.println("genre = " + genre);
                System.out.println("author = " + author);
                System.out.println("user = " + user);
                System.out.println();
            }

            System.out.println("Таблица выведена");
        } catch (SQLException ex) {

        }
    }

    // -------- Вывод таблицы--------
    public static ObservableList<Books> DBTable() {
        List<Books> books = new ArrayList<>();
        String answer;
        try {
            Main.coos.writeObject("readDBBook");
            while (!(answer = (String)Main.cois.readObject()).equals("end")) {

                String [] answerArr = answer.split("&");
                books.add(new Books(Integer.parseInt(answerArr[0]), answerArr[1], Integer.parseInt(answerArr[2]), answerArr[3], answerArr[4], Boolean.parseBoolean(answerArr[5])));

            }
            for (Books book : books) {
                System.out.println(book.getrNAME());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Books> books1 = FXCollections.observableArrayList(books);
        return books1;
    }

    // -------- Поиск в таблице--------
    public static ObservableList<Books> DBTableFind(String fname, int fyear) {
        List<Books> books = new ArrayList<>();
        String answer;
        try {
            Main.coos.writeObject("findBook");
            Main.coos.writeObject(fname+"&"+fyear);
            while (!(answer = (String)Main.cois.readObject()).equals("end")) {

                String [] answerArr = answer.split("&");
                books.add(new Books(Integer.parseInt(answerArr[0]), answerArr[1], Integer.parseInt(answerArr[2]), answerArr[3], answerArr[4], Boolean.parseBoolean(answerArr[5])));

            }
            for (Books book : books) {
                System.out.println(book.getrNAME());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<Books> books1 = FXCollections.observableArrayList(books);
        return books1;
    }

    public static void insertIntoDB(String fname, int year, String genre, String author, boolean us){

        try {
            Main.coos.writeObject("insertBook");
            Main.coos.writeObject(fname+"&"+year+"&"+genre+"&"+author+"&"+us);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deletFromDB(int id) {

        try {
            Main.coos.writeObject("deleteBook");
            Main.coos.writeObject(id +"&");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateInDB(int id ,String fname, int year, String genre, String author ,Boolean us)
    {
        try{
            Main.coos.writeObject("updateBook");
            Main.coos.writeObject(id+"&"+fname+"&"+year+"&"+genre+"&"+author+"&"+us);
            //resSet = statmt.executeQuery("UPDATE users SET name = '"+fname+"', year = '"+year+"', genre = '"+genre+"', user= 'us' , author = '"+author+"' WHERE id = "+id);
        }catch (IOException e){
            e.printStackTrace();}
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

    public static int getEntitiesQt() throws ClassNotFoundException
    {
        String s = "";
        try {
            Main.coos.writeObject("getEntities");
            s = (String)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(s);
    }

    public static int getEntitiesForm() throws ClassNotFoundException
    {
        String s = "";
        try {
            Main.coos.writeObject("getEntitiesForm");
            s = (String)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(s);

    }

    public static int getEntitiesPublisher() throws ClassNotFoundException
    {
        String s = "";
        try {
            Main.coos.writeObject("getEntitiesPublisher");
            s = (String)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(s);

    }

    public static int getEntitiesReader() throws ClassNotFoundException
    {
        String s = "";
        try {
            Main.coos.writeObject("getEntitiesReader");
            s = (String)Main.cois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(s);

    }

}
