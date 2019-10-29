package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Books implements Serializable {
    private transient SimpleIntegerProperty rID;
    private transient SimpleStringProperty rNAME;
    private transient SimpleIntegerProperty rYEAR;
    private transient SimpleStringProperty rAUTHOR;
    private transient SimpleStringProperty rGENRE;
    private transient SimpleBooleanProperty rUSER;

    public Books(int sID, String sNAME, int sYEAR, String sAUTOR, String sGENRE, boolean sUser) {
        this.rID = new SimpleIntegerProperty(sID);
        this.rNAME = new SimpleStringProperty(sNAME);
        this.rYEAR = new SimpleIntegerProperty(sYEAR);
        this.rAUTHOR = new SimpleStringProperty(sAUTOR);
        this.rGENRE = new SimpleStringProperty(sGENRE);
        this.rUSER = new SimpleBooleanProperty(sUser);
    }

    public int getrID() {
        return rID.get();
    }

    public SimpleIntegerProperty rIDProperty() {
        return rID;
    }

    public void setrID(int rID) {
        this.rID.set(rID);
    }

    public String getrNAME() {
        return rNAME.get();
    }

    public SimpleStringProperty rNAMEProperty() {
        return rNAME;
    }

    public void setrNAME(String rNAME) {
        this.rNAME.set(rNAME);
    }

    public int getrYEAR() {
        return rYEAR.get();
    }

    public SimpleIntegerProperty rYEARProperty() {
        return rYEAR;
    }

    public void setrYEAR(int rYEAR) {
        this.rYEAR.set(rYEAR);
    }

    public String getrAUTHOR() {
        return rAUTHOR.get();
    }

    public SimpleStringProperty rAUTHORProperty() {
        return rAUTHOR;
    }

    public void setrAUTHOR(String rAUTHOR) {
        this.rAUTHOR.set(rAUTHOR);
    }

    public String getrGENRE() {
        return rGENRE.get();
    }

    public SimpleStringProperty rGENREProperty() {
        return rGENRE;
    }

    public void setrGENRE(String rGENRE) {
        this.rGENRE.set(rGENRE);
    }

    public boolean isrUSER() {
        return rUSER.get();
    }

    public SimpleBooleanProperty rUSERProperty() {
        return rUSER;
    }

    public void setrUSER(boolean rUSER) {
        this.rUSER.set(rUSER);
    }



    public void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(getrID());
        s.writeObject(getrNAME());
        s.writeInt(getrYEAR());
        s.writeObject(getrAUTHOR());
        s.writeObject(getrGENRE());
        s.writeBoolean(isrUSER());
    }

    public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException  {
        rID = new SimpleIntegerProperty(s.readInt());
        rNAME = new SimpleStringProperty((String)s.readObject());
        rYEAR = new SimpleIntegerProperty(s.readInt());
        rAUTHOR = new SimpleStringProperty((String)s.readObject());
        rGENRE = new SimpleStringProperty((String)s.readObject());
        rUSER = new SimpleBooleanProperty(s.readBoolean());
    }
}
