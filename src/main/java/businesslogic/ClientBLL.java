package businesslogic;

import businesslogic.validators.ClientValidator;
import dataaccess.ClientDAO;
import model.Client;
import java.util.NoSuchElementException;

public class ClientBLL {
    ClientValidator v;
    ClientDAO c;
    public ClientBLL(){
        v = new ClientValidator();
        c=new ClientDAO();
    }

    public Client findClientBLL(int id){
       Client cl = c.findC(id);
       if(cl==null)
           throw new NoSuchElementException("The client with id =" + id + " was not found!");
       return cl;
    }
    public void insertClientBLL(Client client){
        v.validate(client);
        c.insertC(client);
    }
    public void editClientBLL(String field, String val, int id){
        findClientBLL(id);  //daca nu gaseste arunca exception si nu se executa edit
        c.editC(field,val,id);
    }
    public void deletetClientBLL(int id){
        findClientBLL(id);
        c.deleteC(id);
    }
}
