package org.example.Model;

/**
 * The type Orders.
 */
public class Orders {
    private int idOrder;
    private int idClient;
    private String nameClient;
    private int idProduct;
    private String nameProduct;
    private int quantity;

    public Orders(int idOrder,int idClient, String nameClient, int idProduct, String nameProduct, int quantity) {
        this.idOrder =idOrder;
        this.idClient=idClient;
        this.nameClient=nameClient;
        this.idProduct=idProduct;
        this.nameProduct=nameProduct;
        this.quantity=quantity;
    }

    public int getQunatity() {
        return this.quantity;
    }

    public void setQunatity(int cantitate) {
        quantity=cantitate;
    }

    public int getIdProduct() {
        return this.idProduct;
    }


    public void setIdProduct(int productId) {
        idProduct=productId;
    }

    public int getIdClient() {
        return this.idClient;
    }

    public void setIdClient(int clientId) {
        idClient=clientId;
    }

    public int getIdOrder() {
        return this.idOrder;
    }

    public void setIdOrder(int orderId) {
        idOrder=orderId;
    }

    public String getNameClient() {
        return this.nameClient;
    }

    public void setNameClient(String clientName) {
        this.nameClient=clientName;
    }

    public String getNameProduct() {
        return this.nameProduct;
    }

    public void setNameProduct(String productName) {
        this.nameProduct=productName;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "idOrder=" + idOrder +
                ", idClient=" + idClient +
                ", nameClient='" + nameClient + '\'' +
                ", idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}