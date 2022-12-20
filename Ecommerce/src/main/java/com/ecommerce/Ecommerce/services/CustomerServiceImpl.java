package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.AdminException;
import com.ecommerce.Ecommerce.exceptions.CustomerException;
import com.ecommerce.Ecommerce.models.CurrentAdminSession;
import com.ecommerce.Ecommerce.models.CurrentCustomerSession;
import com.ecommerce.Ecommerce.models.Customer;
import com.ecommerce.Ecommerce.repositories.AdminSessionDAO;
import com.ecommerce.Ecommerce.repositories.CustomerDAO;
import com.ecommerce.Ecommerce.repositories.CustomerSessionDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerSessionDao customerSessionDao;

    @Autowired
    private AdminSessionDAO adminSessionDAO;

    @Override
    public Customer createCustomer(Customer customer) throws CustomerException {
        Customer existingCustomer = customerDAO.findByEmail(customer.getEmail());

        if (existingCustomer != null)
            throw new CustomerException("Customer already registered with this Email");

        return customerDAO.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, String key) throws CustomerException {
        CurrentCustomerSession loggedInUser = customerSessionDao.findByUuid(key);

        if (loggedInUser == null) {
            throw new CustomerException("Please provide a valid key to update a customer");
        }

        if (customer.getCustomerId() == loggedInUser.getUserId()) {
            // If LoggedInUser id is same as the id of supplied Customer which we want to
            // update
            return customerDAO.save(customer);
        } else
            throw new CustomerException("Invalid Customer Details, please login first");
    }

    @Override
    public String deleteCustomerById(Integer customerId) throws CustomerException {
        Optional<Customer> opt = customerDAO.findById(customerId);

        if (opt.isPresent()) {
            Customer customer = opt.get();
            customerDAO.delete(customer);
            return "Customer deleted successfully";
        } else {
            throw new CustomerException("No customer available with this id");
        }
    }

    @Override
    public List<Customer> getAllCustomersDetails(String key) throws CustomerException, AdminException {
        CurrentAdminSession casDao = adminSessionDAO.findByUuid(key);

        if (casDao != null) {

            List<Customer> list = customerDAO.findAll();
            if (list.size() != 0) {
                return list;
            } else {
                throw new CustomerException("List is empty.");
            }
        } else {
            throw new AdminException("Wrong key.");
        }
    }
}
