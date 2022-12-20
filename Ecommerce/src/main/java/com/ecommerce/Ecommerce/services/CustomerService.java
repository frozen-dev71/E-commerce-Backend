package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.AdminException;
import com.ecommerce.Ecommerce.exceptions.CustomerException;
import com.ecommerce.Ecommerce.models.Customer;

import java.util.List;

public interface CustomerService {
    public Customer createCustomer(Customer customer) throws CustomerException;

    public Customer updateCustomer(Customer customer, String key) throws CustomerException;

    public String deleteCustomerById(Integer customerId) throws CustomerException;

    public List<Customer> getAllCustomersDetails(String key) throws CustomerException, AdminException;
}
