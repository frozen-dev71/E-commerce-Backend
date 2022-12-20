package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends JpaRepository<Admin, Integer> {
    Admin findByAdminId(Integer adminId);
}
