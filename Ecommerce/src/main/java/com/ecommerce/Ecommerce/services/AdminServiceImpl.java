package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.AdminException;
import com.ecommerce.Ecommerce.exceptions.CustomerException;
import com.ecommerce.Ecommerce.models.Admin;
import com.ecommerce.Ecommerce.models.CurrentAdminSession;
import com.ecommerce.Ecommerce.models.Customer;
import com.ecommerce.Ecommerce.repositories.AdminDAO;
import com.ecommerce.Ecommerce.repositories.AdminSessionDAO;
import com.ecommerce.Ecommerce.repositories.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private AdminSessionDAO adminSessionDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public Customer createCustomer(Customer customer) throws CustomerException {
        Customer existingCustomer = customerDAO.findByEmail(customer.getEmail());

        if (existingCustomer != null)
            throw new CustomerException("Customer Already Registered with this Email");

        return customerDAO.save(customer);
    }

    @Override
    public Admin createAdmin(Admin admin) throws AdminException {
        Admin existingAdmin = adminDAO.findByAdminId(admin.getAdminId());

        if (existingAdmin != null)
            throw new AdminException("Admin Already Registered with this AdminId");

        return adminDAO.save(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin, String key) throws AdminException {
        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);

        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key to update a Admin");
        }

        if (admin.getAdminId() == loggedInAdmin.getUserId()) {
            // If LoggedInUser id is same as the id of supplied Customer which we want to
            // update
            return adminDAO.save(admin);
        } else
            throw new AdminException("Invalid Admin Details, please login first");
    }

    @Override
    public Customer updateCustomer(Customer customer, String key) throws CustomerException {
        CurrentAdminSession loggedInUser = adminSessionDAO.findByUuid(key);

        if (loggedInUser == null) {
            throw new CustomerException("Please provide a valid key to update this customer");
        }

        return customerDAO.save(customer);
    }

    @Override
    public String deleteAdminById(Integer adminId) throws AdminException {
        Optional<Admin> opt = adminDAO.findById(adminId);

        if (opt.isPresent()) {
            Admin admin = opt.get();
            adminDAO.delete(admin);
            return "Admin deleted successfully";
        } else {
            throw new AdminException("No admin available with this Admin id");
        }
    }

    @Override
    public List<Admin> getAllAdminDetails() throws AdminException {
        List<Admin> list = adminDAO.findAll();
        if (list.size() != 0) {
            return list;
        } else {
            throw new AdminException("List is empty..!");
        }
    }
}
