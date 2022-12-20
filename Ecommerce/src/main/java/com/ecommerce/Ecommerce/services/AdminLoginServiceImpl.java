package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.LoginException;
import com.ecommerce.Ecommerce.models.Admin;
import com.ecommerce.Ecommerce.models.AdminLoginDTO;
import com.ecommerce.Ecommerce.models.CurrentAdminSession;
import com.ecommerce.Ecommerce.repositories.AdminDAO;
import com.ecommerce.Ecommerce.repositories.AdminSessionDAO;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class AdminLoginServiceImpl implements AdminLoginService{

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private AdminSessionDAO adminSessionDAO;

    @Override
    public String logIntoAccount(AdminLoginDTO dto) throws LoginException {
        Optional<Admin> opt = adminDAO.findById(dto.getAdminId());
        Admin existAdmin = opt.get();

        if (existAdmin == null) {

            throw new LoginException("Please Enter a valid AdminId");

        }

        Optional<CurrentAdminSession> validAdminSessionOpt = adminSessionDAO.findById(existAdmin.getAdminId());

        if (validAdminSessionOpt.isPresent()) {
            throw new LoginException("Admin already logged-in with this AdminId");
        }

        if (existAdmin.getAdminPassword().equals(dto.getPassword())) {

            String key = RandomString.make(6);

            CurrentAdminSession currentAdminSession = new CurrentAdminSession(existAdmin.getAdminId(), key,
                    LocalDateTime.now());

            adminSessionDAO.save(currentAdminSession);
            return currentAdminSession.toString();
        } else
            throw new LoginException("Please Enter a valid Admin password");
    }

    @Override
    public String logOutFromAccount(String key) throws LoginException {
        CurrentAdminSession validAdminSession = adminSessionDAO.findByUuid(key);
        if (validAdminSession == null) {
            throw new LoginException("Admin was not logged-in with this AdminId");

        }
        adminSessionDAO.delete(validAdminSession);
        return "Admin Logged Out !";
    }
}
