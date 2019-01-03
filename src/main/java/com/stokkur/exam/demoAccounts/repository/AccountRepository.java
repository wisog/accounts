package com.stokkur.exam.demoAccounts.repository;

import com.stokkur.exam.demoAccounts.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findAccountByUsername(String username);
}
