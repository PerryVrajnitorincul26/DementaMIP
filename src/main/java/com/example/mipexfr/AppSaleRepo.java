package com.example.mipexfr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppSaleRepo extends JpaRepository<AppSale, Long> {
    // You can add custom query methods if needed
}
