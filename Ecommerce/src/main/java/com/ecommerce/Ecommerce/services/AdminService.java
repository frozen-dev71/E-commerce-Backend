package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.AdminException;
import com.ecommerce.Ecommerce.exceptions.CustomerException;
import com.ecommerce.Ecommerce.models.Admin;
import com.ecommerce.Ecommerce.models.Customer;

import java.util.List;

public interface AdminService {
    public Customer createCustomer(Customer customer) throws CustomerException;

    public Admin createAdmin(Admin admin) throws AdminException;

    public Admin updateAdmin(Admin admin, String key) throws AdminException;

    public Customer updateCustomer(Customer customer, String key) throws CustomerException;

    public String deleteAdminById(Integer adminId) throws AdminException;

    public List<Admin> getAllAdminDetails() throws AdminException;
}
