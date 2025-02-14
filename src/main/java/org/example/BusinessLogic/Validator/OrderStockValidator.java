package org.example.BusinessLogic.Validator;
import java.util.List;
import org.example.BusinessLogic.ExceptiePentruStock;
import org.example.DataAccess.ProductDAO;
import org.example.Model.Product;
import org.example.Model.Orders;

/**
 * The type Order stock validator.
 */
public class OrderStockValidator implements Validator<Orders> {

    @Override
    public void validate(Orders t) throws ExceptiePentruStock {
        List<Product> st = (new ProductDAO()).findById("id",((Integer)(t.getIdProduct())).toString());
        if(st.get(0).getStock()<t.getQunatity()) {
            throw new ExceptiePentruStock("Produsul nu mai este in stoc.");
        }
    }
}