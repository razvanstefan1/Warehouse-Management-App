package dataaccess;
import model.AuxString;
import model.Client;
import javax.swing.*;

/**
 * Aceasta clasa contine metodele pentru apelarea metodelor din clasa parinte pentru interactiunea cu tabelul Client din baza de date.
 */
public class ClientDAO extends AbstractDAO<Client>{
    public Client findC(int id){ Client c = findById(id," "); return c;}
    public void insertC(Client c){ insert(c);}
    public void editC(String field, String val, int id){edit(field,val,id);}
    public void deleteC(int id){ delete(id);}
    public JScrollPane viewC(){ return view(new Client(),false,null);}
    public void viewCl1(AuxString au){ view(new Client(),true,au);  }
}
