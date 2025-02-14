package org.example.DataAccess;

import java.util.List;
import org.example.Model.Orders;

/**
 * The type Orders dao.
 */
public class OrdersDAO extends AbstractDAO<Orders>{


    public List<Orders> findById(String seteazaColoana, String valoare) {
        return super.findById(seteazaColoana, valoare);
    }

    public List<Orders> findAll(){
        return super.findAll();
    }


    public void insert(Object object) {
        super.insert(object);
    }
}