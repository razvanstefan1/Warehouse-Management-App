package dataaccess;
import model.AuxString;
import model.Comanda;
import javax.swing.*;

/**
 * Aceasta clasa contine metodele pentru apelarea metodelor din clasa parinte pentru interactiunea cu tabelul Comanda din baza de date.
 */
public class ComandaDAO extends AbstractDAO<Comanda> {
    public void insertO(Comanda c){ insert(c);}
    public JScrollPane viewC1(){ return view(new Comanda(),false,null);}
    public void viewC2(AuxString au){ view(new Comanda(),true,au);  }
}
