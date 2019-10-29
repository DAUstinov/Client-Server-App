package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Readers {
    private transient SimpleIntegerProperty id;
    private transient SimpleStringProperty FIO;
    private transient SimpleStringProperty dateOfBirth;
    private transient SimpleStringProperty phoneNumber;

    public Readers(int id, String FIO, String dateOfBirth, String phoneNumber) {
        this.id =  new SimpleIntegerProperty(id);
        this.FIO = new SimpleStringProperty(FIO) ;
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.phoneNumber =  new SimpleStringProperty(phoneNumber);
    }

    public int getid() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setid(int bookID) {
        this.id.set(bookID);
    }

    public String getFIO() {
        return FIO.get();
    }

    public SimpleStringProperty FIOProperty() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO.set(FIO);
    }

    public String getdateOfBirth() {
        return dateOfBirth.get();
    }

    public SimpleStringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setdateOfBirth(String dob) {
        this.dateOfBirth.set(dob);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }
}


