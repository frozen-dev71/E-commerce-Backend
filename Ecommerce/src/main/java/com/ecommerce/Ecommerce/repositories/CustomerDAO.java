package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO  extends JpaRepository<Customer, Integer> {
    public Customer findByEmail(String email);
}
