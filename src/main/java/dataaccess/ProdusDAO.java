package dataaccess;

import model.AuxString;
import model.Produs;

import javax.swing.*;

/**
 * Aceasta clasa contine metodele pentru apelarea metodelor din clasa parinte pentru interactiunea cu tabelul Produs din baza de date.
 */
public class ProdusDAO extends AbstractDAO<Produs>{
    public Produs findP(int id, String str){ Produs c = findById(id, str); return c;}
    public void insertP(Produs c){ insert(c);}
    public void editP(String field, String val, int id){edit(field,val,id);}
    public void deleteP(int id){ delete(id);}
    public JScrollPane viewP(){ return view(new Produs(),false,null);}
    public void viewPr2(AuxString au){ view(new Produs(),true,au);  }
}
