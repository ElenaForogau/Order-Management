package org.example.BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.example.BusinessLogic.Validator.Validator;
import org.example.BusinessLogic.Validator.ClientAgeValidator;
import org.example.DataAccess.ClientDAO;
import org.example.Model.Client;

/**
 * The type Client bll.
 */
public class  ClientBll {

    private final List<Validator<Client>> validators;
    public ClientBll() {
        validators = new ArrayList<>();
        validators.add(new ClientAgeValidator());
    }

    public List<Client> findClient(String seteazaColoana,String valoare) {
        List<Client> clienti = (new ClientDAO()).findById(seteazaColoana,valoare);
        if (clienti == null) {
            throw new NoSuchElementException("Clientul cu tare: "+ seteazaColoana+" =" + valoare + "nu a fost gasit.");
        }
        return clienti;
    }
    public void updateClient(String seteazaColoana, String seteazaValoarea, String seteazaColoana2, String seteazaValoarea2) {
        (new ClientDAO()).update(seteazaColoana, seteazaValoarea, seteazaColoana2, seteazaValoarea2);
    }
    public List<Client> findAll(){
        List<Client> l;
        l=(new ClientDAO()).findAll();
        return l;
    }

    public void insertClient(Client client) throws Exception {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        (new ClientDAO()).insert(client);
    }

    public void deleteClient(String seteazaColoana, String seteazaValoare) {
        (new ClientDAO()).delete(seteazaColoana, seteazaValoare);
    }
}