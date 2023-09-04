package com.example.mipexfr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppProductRepo extends JpaRepository<AppProduct, Long> {
    // You can add custom query methods if needed
}
