package com.example.mipexfr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductStockTestBean {

    private final AppProductRepo productRepo;
    private final AppStockRepo stockRepo;
    private final AppTableRepo tableRepo;
    private final AppUserRepo userRepo;

    @Autowired
    public ProductStockTestBean(AppProductRepo productRepo, AppStockRepo stockRepo, AppTableRepo tableRepo, AppUserRepo userRepo) {
        this.productRepo = productRepo;
        this.stockRepo = stockRepo;
        this.tableRepo = tableRepo;
        this.userRepo = userRepo;
        //testAddProductAndStock();
        //testRemoveProductAndStock();
        testAddRemoveTable();
    }

    public void testAddProductAndStock() {
        // Create a new product
        AppProduct product = new AppProduct();
        product.setName("Test Product");

        // Create stock for the product
        AppStock stock = new AppStock(10, 25.99, product);

        // Save the product and stock to the database
        productRepo.save(product);
        stockRepo.save(stock);

        System.out.println("Product and Stock added successfully.");
    }

    public void testRemoveProductAndStock() {
        // Find a product and its associated stock
        AppProduct product = productRepo.findById(1L).orElse(null);
        if (product != null) {
            // Delete the product and its associated stock
            productRepo.delete(product);

            System.out.println("Product and Stock removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void testAddRemoveTable(){
        userRepo.save(new AppUser("matei","servus",true));
        AppUser AppUser = userRepo.findByUsername("matei").get(0);
        var tTable=new AppTable(10, 10, AppUser);
        tableRepo.save(tTable);
        tableRepo.save(new AppTable(12,13,AppUser));
        tableRepo.delete(tTable);
    }
}
