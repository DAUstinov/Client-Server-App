package model;

import model.WorkWithDB;

import java.sql.SQLException;

public class DataBase {

    public void start() throws ClassNotFoundException, SQLException {
        WorkWithDB.Conn();
        WorkWithDB.CreateDB();
        WorkWithDB.WriteDB();

    }

    public void view() throws SQLException {

        WorkWithDB.ReadDB();
    }

}
