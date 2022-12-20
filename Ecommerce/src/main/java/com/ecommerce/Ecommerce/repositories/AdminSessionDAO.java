package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.models.CurrentAdminSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminSessionDAO extends JpaRepository<CurrentAdminSession, Integer> {
    public CurrentAdminSession findByUuid(String uuid);
}
