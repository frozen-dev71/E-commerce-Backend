package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.LoginException;
import com.ecommerce.Ecommerce.models.AdminLoginDTO;

public interface AdminLoginService {
    public String logIntoAccount(AdminLoginDTO dto) throws LoginException;

    public String logOutFromAccount(String key) throws LoginException;
}
