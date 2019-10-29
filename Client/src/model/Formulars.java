package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Formulars {
    private transient SimpleIntegerProperty readerID;
    private transient SimpleIntegerProperty bookID;
    private transient SimpleStringProperty dateOfIssue;
    private transient SimpleStringProperty returnDate;

    public Formulars(int readerID, int bookID, String dateOfIssue, String returnDate) {
        this.readerID = new SimpleIntegerProperty(readerID);
        this.bookID =  new SimpleIntegerProperty(bookID);
        this.dateOfIssue = new SimpleStringProperty(dateOfIssue);
        this.returnDate = new SimpleStringProperty(returnDate);
    }

    public int getReaderID() {
        return readerID.get();
    }

    public SimpleIntegerProperty readerIDProperty() {
        return readerID;
    }

    public int getBookID() {
        return bookID.get();
    }

    public SimpleIntegerProperty bookIDProperty() {
        return bookID;
    }

    public String getDateOfIssue() {
        return dateOfIssue.get();
    }

    public SimpleStringProperty dateOfIssueProperty() {
        return dateOfIssue;
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public SimpleStringProperty returnDateProperty() {
        return returnDate;
    }

    public void setReaderID(int readerID) {
        this.readerID.set(readerID);
    }

    public void setBookID(int bookID) {
        this.bookID.set(bookID);
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue.set(dateOfIssue);
    }

    public void setReturnDate(String returnDate) {
        this.returnDate.set(returnDate);
    }
}
