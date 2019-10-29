package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Publishers {
    private transient SimpleIntegerProperty id;
    private transient SimpleIntegerProperty bookID;
    private transient SimpleStringProperty publisherName;
    private transient SimpleStringProperty adress;

    public Publishers(int id , int bookID, String publisherName, String adress) {
        this.id = new SimpleIntegerProperty(id);
        this.bookID = new SimpleIntegerProperty(bookID);
        this.publisherName = new SimpleStringProperty(publisherName);
        this.adress = new SimpleStringProperty(adress);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getBookID() {
        return bookID.get();
    }

    public SimpleIntegerProperty bookIDProperty() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID.set(bookID);
    }

    public String getPublisherName() {
        return publisherName.get();
    }

    public SimpleStringProperty publisherNameProperty() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName.set(publisherName);
    }

    public String getAdress() {
        return adress.get();
    }

    public SimpleStringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }
}


