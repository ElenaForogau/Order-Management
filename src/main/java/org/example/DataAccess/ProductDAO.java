package org.example.DataAccess;

import java.util.List;
import org.example.Model.Product;

/**
 * The type Product dao.
 */
public class ProductDAO extends AbstractDAO<Product> {

    public void delete(String seteazaColoana, String seteazaValoarea) {
        super.delete(seteazaColoana, seteazaValoarea);
    }

    public void update(String seteazaColoana, String seteazaValoaarea, String seteazaColoana2, String setVal1) {
        super.update(seteazaColoana, seteazaValoaarea, seteazaColoana2, setVal1);
    }


    public List<Product> findById(String seteazaColoana, String valoare) {
        return super.findById(seteazaColoana, valoare);
    }

    public void insert(Object object) {
        super.insert(object);
    }

    public List<Product> findAll(){
        return super.findAll();
    }
}