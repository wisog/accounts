package com.stokkur.exam.demoAccounts.service;

import com.stokkur.exam.demoAccounts.dto.AccountDto;

import com.stokkur.exam.demoAccounts.rest.util.ResponseWrapper;

public interface AccountService {

    ResponseWrapper save(AccountDto accountDto);

    ResponseWrapper findById(String id);

    ResponseWrapper findAll();

    ResponseWrapper delete(String id);

    ResponseWrapper update(AccountDto accountDto);

}