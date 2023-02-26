package businesslogic.validators;

import model.Client;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client c) {
        if(!(c.getId()>=0))
            throw new IllegalArgumentException("Client id is not valid!");

        for(int i=0;i<c.getName().length();i++){
            if(Character.isDigit(c.getName().charAt(i)))
                throw new IllegalArgumentException("Client Name is not valid!");
        }

    }
}
