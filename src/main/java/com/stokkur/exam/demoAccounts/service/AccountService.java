package com.stokkur.exam.demoAccounts.service;

import com.stokkur.exam.demoAccounts.dto.AccountDto;

import com.stokkur.exam.demoAccounts.rest.util.ResponseWrapper;

public interface AccountService {

    public ResponseWrapper save(AccountDto accountDto);

    public ResponseWrapper findById(String id);

    public ResponseWrapper findAll();

    public ResponseWrapper delete(String id);

    public ResponseWrapper update(AccountDto accountDto);

}