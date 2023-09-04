package com.example.mipexfr;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private AppStock stock;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<AppSale> appSales;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppStock getStock() {
        return stock;
    }

    public void setStock(AppStock stock) {
        this.stock = stock;
    }

    public List<AppSale> getAppSales() {
        return appSales;
    }

    public void setAppSales(List<AppSale> appSales) {
        this.appSales = appSales;
    }
// Constructors, getters, setters, and other methods
}

