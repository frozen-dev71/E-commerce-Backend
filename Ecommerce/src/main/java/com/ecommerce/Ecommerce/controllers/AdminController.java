package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.exceptions.AdminException;
import com.ecommerce.Ecommerce.exceptions.CustomerException;
import com.ecommerce.Ecommerce.models.Admin;
import com.ecommerce.Ecommerce.models.CurrentAdminSession;
import com.ecommerce.Ecommerce.models.Customer;
import com.ecommerce.Ecommerce.repositories.AdminSessionDAO;
import com.ecommerce.Ecommerce.services.AdminService;
import com.ecommerce.Ecommerce.services.CustomerService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminSessionDAO adminSessionDAO;

    @PostMapping("/createadmin")
    public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) throws AdminException {
        Admin savedAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<Admin>(savedAdmin, HttpStatus.CREATED);
    }

    @PostMapping("/customers/{adminkey}")
    public ResponseEntity<Customer> saveCustomer(@PathVariable("adminkey") String key,
                                                 @Valid @RequestBody Customer customer) throws CustomerException, AdminException {

        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);
        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key");
        } else {
            Customer savedCustomer = adminService.createCustomer(customer);
            return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
        }
    }


    @PutMapping("/customers")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,
                                                   @RequestParam(required = false) String key) throws CustomerException, AdminException {

        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);

        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key");
        } else {

            Customer updatedCustomer = adminService.updateCustomer(customer, key);

            return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);

        }

    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId,
                                                 @RequestParam(required = false) String key) throws CustomerException, AdminException {
        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);

        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key");
        } else {

            String message = customerService.deleteCustomerById(customerId);

            return new ResponseEntity<String>(message, HttpStatus.OK);

        }
    }

    @GetMapping("/getcustomers/{adminkey}")
    public ResponseEntity<List<Customer>> getAllCustomers(@PathVariable("adminkey") String key)
            throws CustomerException, AdminException {

        CurrentAdminSession loggedInAdmin = adminSessionDAO.findByUuid(key);

        if (loggedInAdmin == null) {
            throw new AdminException("Please provide a valid key");
        } else {

            List<Customer> list = customerService.getAllCustomersDetails(key);

            return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);

        }

    }


}
