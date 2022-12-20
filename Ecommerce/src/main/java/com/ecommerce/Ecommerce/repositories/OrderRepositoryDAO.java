package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositoryDAO extends JpaRepository<Orders,Integer> {
    public List<Orders> findBySessionId(String CustomerKey);
    public void deleteBySessionId(String customerKey);
}
