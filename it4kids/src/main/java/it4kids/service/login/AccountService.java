package it4kids.service.login;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it4kids.dao.AccountDAO;
import it4kids.domain.login.Account;

/**
 * Created by Gabi on 3/15/2017.
 */
@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private AccountDAO<T extends Account> dao;

    public Collection<Account> listAll() {
    	LOGGER.debug("Listing accounts ");
        return dao.getAll();
    }

    public void saveAccount(Account account, int id) {
        LOGGER.debug("Saving: " + account + " with id " + id);
        dao.add(account, id);
    }
}
