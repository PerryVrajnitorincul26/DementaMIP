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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "app_product_id")
    private AppProduct appProduct;

    protected AppSale() {}

    public AppSale(int quantitySold, double totalPrice, AppProduct product, AppProduct appProduct) {
        this.quantitySold = quantitySold;
        this.totalPrice = totalPrice;
        this.product = product;
        this.appProduct = appProduct;
    }

    public AppProduct getAppProduct() {
        return appProduct;
    }

    public void setAppProduct(AppProduct appProduct) {
        this.appProduct = appProduct;
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
