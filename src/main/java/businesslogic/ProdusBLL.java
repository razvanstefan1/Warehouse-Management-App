package businesslogic;
import businesslogic.validators.ProdusValidator;
import dataaccess.ProdusDAO;
import model.Produs;
import java.util.NoSuchElementException;

public class ProdusBLL {
    ProdusValidator v;
    ProdusDAO c;
    public ProdusBLL(){
        v = new ProdusValidator();
        c=new ProdusDAO();
    }

    public Produs findProdusBLL(int id,String str){
        Produs pr = c.findP(id,str);
        if(pr==null)
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        return pr;
    }
    public void insertProdusBLL(Produs produs){
        v.validate(produs);
        c.insertP(produs);
    }
    public void editProdusBLL(String field, String val, int id){
        findProdusBLL(id," ");  //daca nu gaseste arunca exception si nu se executa edit
        c.editP(field,val,id);
    }
    public void deleteProdusBLL(int id){
        findProdusBLL(id," ");
        c.deleteP(id);
    }
}

