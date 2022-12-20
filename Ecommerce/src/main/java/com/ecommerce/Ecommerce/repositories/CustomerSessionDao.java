package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.models.CurrentCustomerSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSessionDao extends JpaRepository<CurrentCustomerSession, Integer> {
    public CurrentCustomerSession findByUuid(String uuid);
}
