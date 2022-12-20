package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.exceptions.LoginException;
import com.ecommerce.Ecommerce.models.CurrentCustomerSession;
import com.ecommerce.Ecommerce.models.Customer;
import com.ecommerce.Ecommerce.models.CustomerLoginDTO;
import com.ecommerce.Ecommerce.repositories.CustomerDAO;
import com.ecommerce.Ecommerce.repositories.CustomerSessionDao;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CustomerLoginServiceImpl implements CustomerLoginService{

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private CustomerSessionDao customerSessionDao;

    @Override
    public String logIntoAccount(CustomerLoginDTO dto) throws LoginException {
        Customer existingCustomer = customerDAO.findByEmail(dto.getEmail());

        if (existingCustomer == null) {

            throw new LoginException("Please Enter a valid Email");

        }

        Optional<CurrentCustomerSession> validCustomerSessionOpt = customerSessionDao.findById(existingCustomer.getCustomerId());

        if (validCustomerSessionOpt.isPresent()) {

            throw new LoginException("User already Logged In with this Email");

        }

        if (existingCustomer.getPassword().equals(dto.getPassword())) {

            String key = RandomString.make(6);

            CurrentCustomerSession currentUserSession = new CurrentCustomerSession(existingCustomer.getCustomerId(),
                    key, LocalDateTime.now());

            customerSessionDao.save(currentUserSession);

            return currentUserSession.toString();
        } else
            throw new LoginException("Please Enter a valid password");
    }

    @Override
    public String logOutFromAccount(String key) throws LoginException {
        CurrentCustomerSession validCustomerSession = customerSessionDao.findByUuid(key);

        if (validCustomerSession == null) {
            throw new LoginException("User Not Logged In with this Email");

        }

        customerSessionDao.delete(validCustomerSession);

        return "Logged Out !";
    }
}
