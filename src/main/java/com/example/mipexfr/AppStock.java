package com.example.mipexfr;
import jakarta.persistence.*;

@Entity
public class AppStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double price;

    @OneToOne
    @JoinColumn(name = "product_id")
    private AppProduct product;

    protected AppStock(){}
    public AppStock(int quantity, double price, AppProduct product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AppProduct getProduct() {
        return product;
    }

    public void setProduct(AppProduct product) {
        this.product = product;
    }
// Constructors, getters, setters, and other methods
}
