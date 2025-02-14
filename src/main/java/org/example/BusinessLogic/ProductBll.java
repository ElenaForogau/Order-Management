package org.example.BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.example.BusinessLogic.Validator.Validator;
import org.example.Model.Product;
import org.example.DataAccess.ProductDAO;

/**
 * The type Product bll.
 */
public class ProductBll {
    private final List<Validator<Product>> validators;

    public ProductBll() {
        validators = new ArrayList<>();
    }

    public List<Product> findAll(){
        List<Product> produse;
        produse=(new ProductDAO()).findAll();
        return produse;
    }

    public List<Product> findProduct(String seteazaColoana, String valoare) {
        List<Product> produse = (new ProductDAO()).findById(seteazaColoana,valoare);
        if (produse == null) {
            throw new NoSuchElementException("Produsul cu tare"+ seteazaColoana+"=" + valoare + " nu a fost gasit.");
        }
        return produse;
    }

    public void updateProduct(String seteazaColoana, String seteazaValoarea, String seteazaColoana2, String seteazaValoarea2) {
        (new ProductDAO()).update(seteazaColoana, seteazaValoarea, seteazaColoana2, seteazaValoarea2);

    }
    public void insertProduct(Product product) throws Exception {
        for (Validator<Product> validator : validators) {
            validator.validate(product);
        }
        (new ProductDAO()).insert(product);
    }

    public void deleteProduct(String seteazaColoana, String seteazaValoarea) {
        (new ProductDAO()).delete(seteazaColoana, seteazaValoarea);
    }
}