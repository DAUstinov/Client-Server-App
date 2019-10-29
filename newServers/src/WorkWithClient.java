import model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

class WorkWithClient implements Runnable
{
    private final Socket clientAccepted;
    private final ObjectInputStream sois;
    private final ObjectOutputStream soos;

    public WorkWithClient(Socket clientAccepted, ObjectInputStream sois, ObjectOutputStream soos) {
        this.clientAccepted = clientAccepted;
        this.sois = sois;
        this.soos = soos;
    }

    public void run()
    {
        try {
            Conn.Conn();
            Conn.CreateDataBase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        Conn.ReadDB();
        System.out.println(" " + clientAccepted.getInetAddress() + " " + clientAccepted.getLocalPort() + " " + clientAccepted.getPort() );
        try {
            String clientMessageRecieved = (String) sois.readObject();
            while (!clientMessageRecieved.equals("0")){

                System.out.println("clients message: " + clientMessageRecieved);
                switch (clientMessageRecieved){
                    case "readDBBook": {
                        ArrayList<Books> books = new ArrayList<>(Conn.DBTableBook());
                        for (Books book : books) {
                            System.out.println(book.getrNAME());
                            soos.writeObject(book.getrID()+"&"+book.getrNAME()+"&"+book.getrYEAR()+"&"+book.getrAUTHOR()+"&"+book.getrGENRE()+"&"+book.isrUSER());
                            soos.flush();
                        }
                        soos.writeObject("end");
                        soos.flush();
                        break;
                    }
                    case "findBook":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        ArrayList<Books> books = new ArrayList<>(Conn.DBTableBookFind(answer[0], answer[1 ] , answer[2]));
                        for (Books book : books) {
                            System.out.println(book.getrNAME());
                            soos.writeObject(book.getrID()+"&"+book.getrNAME()+"&"+book.getrYEAR()+"&"+book.getrAUTHOR()+"&"+book.getrGENRE()+"&"+book.isrUSER());
                            soos.flush();
                        }
                        soos.writeObject("end");
                        soos.flush();
                        break;
                    }
                    case "insertBook":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.insertIntoDB(answer[0], Integer.parseInt(answer[1]), answer[2], answer[3],false);
                        break;
                    }
                    case "deleteBook":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.deletFromDB(Integer.parseInt(answer[0]) );
                        break;
                    }

                    case "updateBook":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.updateInDB(Integer.parseInt(answer[0]) , answer[1] , Integer.parseInt(answer[2]) , answer[3] , answer[4] , false);
                        break;
                    }
                    case "getEntities":{
                        soos.writeObject(""+Conn.getEntitiesQt());
                        break;
                    }
                    case "addReader":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.insertIntoDBReaders(answer[0], answer[1], answer[2]);
                        //Conn.insertIntoDBForm(answer[0], Integer.parseInt(answer[3]));
                        break;
                    }
                    case "readReader": {
                        ArrayList<Readers> readers = new ArrayList<>(Conn.DBTableReader());
                        for (Readers reader : readers){
                            soos.writeObject(reader.getid()+"&"+reader.getdateOfBirth()+"&"+reader.getFIO()+"&"+reader.getPhoneNumber());
                            soos.flush();
                    }
                        soos.writeObject("end");
                        soos.flush();
                        break;
                    }
                    case "updateReader":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.updateInDBReaders(Integer.parseInt(answer[0]) , answer[1] ,answer[2] , answer[3]);
                        break;
                    }
                    case "deleteReader":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.deletFromDBReaders(Integer.parseInt(answer[0]) );
                        break;
                    }
                    case "addForm":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.insertIntoDBForm( Integer.parseInt(answer[0]) , Integer.parseInt(answer[1]) , answer[2] , answer[3]);
                        break;

                    }
                    case "deleteForm" :{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.deletFromDBForm(Integer.parseInt(answer[0]) );
                        break;
                    }
                    case "readForm":{
                        ArrayList<Formulars> forms = new ArrayList<>(Conn.DBTableForms());
                        for (Formulars form : forms) {
                            soos.writeObject(form.getReaderID()+"&"+form.getBookID()+"&"+form.getDateOfIssue()+"&"+form.getReturnDate());
                            soos.flush();
                        }
                        soos.writeObject("end");
                        soos.flush();
                        break;
                    }
                    case "addPublisher":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.insertIntoDBPublisher(Integer.parseInt(answer[0]) , answer[1],answer[2]);
                        break;
                    }
                    case "updatePublisher":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.updateInDBPublisher(Integer.parseInt(answer[0]) , Integer.parseInt(answer[1]) , answer[2] , answer[3]);
                        break;
                    }
                    case "deletePublisher":{
                        String [] answer = ((String)sois.readObject()).split("&");
                        Conn.deletFromDBPublisher(Integer.parseInt(answer[0]) );
                        break;
                    }
                    case "readPublisher":{
                        ArrayList<Publishers> publishers = new ArrayList<>(Conn.DBTablePublisher());
                        for (Publishers publisher : publishers ){
                            soos.writeObject(publisher.getId()+"&"+publisher.getBookID()+"&"+publisher.getPublisherName()+"&"+publisher.getAdress());
                            soos.flush();
                        }
                        soos.writeObject("end");
                        soos.flush();
                        break;
                    }
                    case "getEntitiesReader":{
                        soos.writeObject(""+Conn.getEntitiesReader());
                        break;
                    }
                    case "getEntitiesForm":{
                        soos.writeObject(""+Conn.getEntitiesForm());
                        break;
                    }
                    case "getEntitiesPublisher":{
                        soos.writeObject(""+Conn.getEntitiesPublisher());
                        break;
                    }
                    case "Authorization":{
                        String login = (String)sois.readObject();
                        String password = (String)sois.readObject();
                        soos.writeObject(autorization(login,password));
                        soos.flush();
                        break;
                    }
                }

                clientMessageRecieved = (String) sois.readObject();
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int autorization(String login, String password){
        ArrayList<Users> user=new ArrayList<>(Conn.DBTableUsers());
        int role=2;
        for(Users user2:user){
            if ((login.equals(user2.getLogin()))&&(password.equals(user2.getPassword()))){
                role=user2.getRole();
                break;
            }
        }

        return role;
    }

}
