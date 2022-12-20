package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.exceptions.LoginException;
import com.ecommerce.Ecommerce.models.AdminLoginDTO;
import com.ecommerce.Ecommerce.services.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLogin;

    @PostMapping("/login")
    public ResponseEntity<String> logInAdmin(@RequestBody AdminLoginDTO dto) throws LoginException {
        String result = adminLogin.logIntoAccount(dto);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public String logoutAdmin(@RequestParam(required = false) String key) throws LoginException {
        return adminLogin.logOutFromAccount(key);
    }
}
