package com.example.mipexfr;
import jakarta.persistence.*;

@Entity
public class AppSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int quantitySold;
    @Column(nullable = false)
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private AppProduct product;


    protected AppSale() {}

    public AppSale(int quantitySold, double totalPrice, AppProduct product) {
        this.quantitySold = quantitySold;
        this.totalPrice = totalPrice;
        this.product = product;
    }

    public AppProduct getProduct() {
        return product;
    }

    public void setProduct(AppProduct product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
