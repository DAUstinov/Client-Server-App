package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Users {
    private transient SimpleIntegerProperty role;
    private transient SimpleStringProperty login;
    private transient SimpleStringProperty password;

    public Users(int role, String login, String password) {
        this.role = new SimpleIntegerProperty(role);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
    }

    public int getRole() {
        return role.get();
    }

    public SimpleIntegerProperty roleProperty() {
        return role;
    }

    public void setRole(int role) {
        this.role.set(role);
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
