package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.exceptions.LoginException;
import com.ecommerce.Ecommerce.models.CustomerLoginDTO;
import com.ecommerce.Ecommerce.services.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerLoginController {

    @Autowired
    private CustomerLoginService customerLogin;

    @PostMapping("/login")
    public ResponseEntity<String> logInCustomer(@RequestBody CustomerLoginDTO dto) throws LoginException {

        String result = customerLogin.logIntoAccount(dto);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public String logoutCustomer(@RequestParam(required = false) String key) throws LoginException {
        return customerLogin.logOutFromAccount(key);
    }
}
