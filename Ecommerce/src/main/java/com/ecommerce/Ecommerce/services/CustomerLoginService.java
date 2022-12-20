package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.LoginException;
import com.ecommerce.Ecommerce.models.CustomerLoginDTO;

public interface CustomerLoginService {

    public String logIntoAccount(CustomerLoginDTO dto) throws LoginException;
    public String logOutFromAccount(String key) throws LoginException;
}
