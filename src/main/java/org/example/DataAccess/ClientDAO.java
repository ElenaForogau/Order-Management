package org.example.DataAccess;

import java.util.List;
import org.example.Model.Client;

/**
 * The type Client dao.
 */
public class ClientDAO extends AbstractDAO<Client> {

    public List<Client> findById(String seteazaColoana, String valoare) {
        return super.findById(seteazaColoana, valoare);
    }

    public void update (String seteazaColoana, String seteazaValoaarea, String seteazaColoana2, String setVal1) {
        super.update(seteazaColoana, seteazaValoaarea, seteazaColoana2, setVal1);
    }

    public void insert(Object object) {
        super.insert(object);
    }

    public List<Client> findAll(){
        return super.findAll();
    }

    public void delete (String seteazaColoana, String seteazaValoarea) {
        super.delete(seteazaColoana, seteazaValoarea);
    }
}