package model;

/**
 * Aceasta clasa contine datele produselor.
 */
public class Produs {
    private int id;
    private String name;
    private double Price;
    private int quantity=0;

    public Produs(int id, String name, double price, int qty) {
        this.id = id;
        this.name = name;
        Price = price;
        quantity=qty;
    }

    public Produs() {
        this.id = -1;
        this.name = null;
        Price = -1;
        quantity=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
