package businesslogic.validators;

import model.Comanda;

public class ComandaValidator implements Validator<Comanda>{
    @Override
    public void validate(Comanda comanda) {
        if(!(comanda.getId()>=0))
            throw new IllegalArgumentException("Order id is not valid!");
        if(!(comanda.getIdProd()>=0))
            throw new IllegalArgumentException("Order IdProd is not valid!");
        if(!(comanda.getIdClient()>=0))
            throw new IllegalArgumentException("Order IdClient is not valid!");
        if(comanda.getCantitate()<0)
            throw new IllegalArgumentException("Order Amount is not valid!");

    }
}
