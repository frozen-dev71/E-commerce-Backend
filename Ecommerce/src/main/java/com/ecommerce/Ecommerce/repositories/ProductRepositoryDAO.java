package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryDAO extends JpaRepository<Product,Integer> {
    public List<Product> findByProductName(String name);

    public List<Product> findByProductId(String type);
}
