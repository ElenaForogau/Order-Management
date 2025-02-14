package org.example.BusinessLogic;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.example.BusinessLogic.Validator.Validator;
import org.example.BusinessLogic.Validator.OrderStockValidator;
import org.example.DataAccess.ClientDAO;
import org.example.DataAccess.OrdersDAO;
import org.example.DataAccess.ProductDAO;
import org.example.Model.Client;
import org.example.Model.Orders;
import org.example.Model.Product;

/**
 * The type Orders bll.
 */
public class OrdersBll {
    private final List<Validator<Orders>> validators;

    public void insertOrder(Orders order) throws Exception {
        for (Validator<Orders> validator : validators) {
            validator.validate(order);
        }
        List<Product> produs = (new ProductDAO()).findById("id",((Integer)(order.getIdProduct())).toString());
        List<Client> client = (new ClientDAO()).findById("id",((Integer)(order.getIdClient())).toString());
        (new ProductBll()).updateProduct("stock",((Integer)(produs.get(0).getStock()-order.getQunatity())).toString() , "id",((Integer)(order.getIdProduct())).toString());
        (new OrdersDAO()).insert(order);

        try {
            PrintWriter writer = new PrintWriter("DateDespreFacturare.txt", StandardCharsets.UTF_8);
            writer.println("Factura este:");
            writer.println("Detaliile clientului de facturare sunt: "); writer.println(client.get(0).toString());
            writer.println("Produsul care a fost comandat este: \n"); writer.println(produs.get(0).toString()+"\n");
            writer.println("Numarul de produse pe care clientul le-a cumparat este:"+order.getQunatity());
            writer.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public OrdersBll() {
        validators = new ArrayList<>();
        validators.add(new OrderStockValidator());
    }

    public List<Orders> findAll(){
        List<Orders> l;
        l=(new OrdersDAO()).findAll();
        return l;
    }
}