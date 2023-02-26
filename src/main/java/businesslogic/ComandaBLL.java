package businesslogic;
import businesslogic.validators.ComandaValidator;
import dataaccess.ComandaDAO;
import model.Comanda;

public class ComandaBLL {
    ComandaValidator v;
    ComandaDAO c;
    public ComandaBLL(){
        v = new ComandaValidator();
        c=new ComandaDAO();
    }

    public void insertOrderBLL(Comanda comanda ){
        v.validate(comanda);
        c.insertO(comanda);
    }


}


