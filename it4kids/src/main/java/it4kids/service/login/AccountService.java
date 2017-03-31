package it4kids.service.login;

import it4kids.dao.BaseDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.domain.login.Account;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Gabi on 3/15/2017.
 */
@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private BaseDAO dao;
    
    @Autowired 
    private RegisteredUserDAO userDAO;

    public Collection<Account> listAll() {
    	LOGGER.debug("Listing accounts ");
        return dao.getAll();
    }
}