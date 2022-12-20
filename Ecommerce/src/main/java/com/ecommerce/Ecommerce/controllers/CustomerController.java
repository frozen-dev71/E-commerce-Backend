package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.exceptions.CustomerException;
import com.ecommerce.Ecommerce.models.Customer;
import com.ecommerce.Ecommerce.services.CustomerService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
        Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
    }
    @PutMapping("/customers")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,
                                                   @RequestParam(required = false) String key) throws CustomerException {
        Customer updatedCustomer = customerService.updateCustomer(customer, key);
        return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
    }
}
