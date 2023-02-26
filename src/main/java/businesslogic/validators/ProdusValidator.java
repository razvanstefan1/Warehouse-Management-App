package businesslogic.validators;

import model.Produs;

public class ProdusValidator implements Validator<Produs> {
    @Override
    public void validate(Produs produs) {
        if(!(produs.getId()>=0))
            throw new IllegalArgumentException("Product id is not valid!");

        if(!(produs.getPrice()>0))
            throw new IllegalArgumentException("Product price is not valid!");


    }
}
