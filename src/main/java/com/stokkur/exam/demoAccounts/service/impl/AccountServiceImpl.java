package com.stokkur.exam.demoAccounts.service.impl;

import com.stokkur.exam.demoAccounts.dto.AccountDto;
import com.stokkur.exam.demoAccounts.model.Account;
import com.stokkur.exam.demoAccounts.repository.AccountRepository;
import com.stokkur.exam.demoAccounts.rest.util.ErrorConstants;
import com.stokkur.exam.demoAccounts.rest.util.ResponseWrapper;
import com.stokkur.exam.demoAccounts.service.AccountService;
import com.stokkur.exam.demoAccounts.service.util.AccountDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Class for managing accounts.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final Logger LOG = LoggerFactory.getLogger(AccountService.class);
    private ResponseWrapper response;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    private Boolean alreadyExists(String username){
        Account account = accountRepository.findAccountByUsername(username);
        return (account != null) ? true : false;
    }

    @Override
    public ResponseWrapper findAll(){
        response = new ResponseWrapper();
        List<AccountDto> accounts = new ArrayList<>();
        for (Account account: accountRepository.findAll() ) {
            accounts.add(AccountDtoMapper.toDto(account));
        }

        response.setResponseCode(HttpStatus.OK);
        response.setResponseObject(accounts);
        return response;
    }

    @Override
    public ResponseWrapper findById(String id) {

        response = new ResponseWrapper();
        Optional<Account> account = accountRepository.findById(Integer.valueOf(id));

        if (account.isPresent()) {
            response.setResponseCode(HttpStatus.OK);
            AccountDto oneAccount = AccountDtoMapper.toDto(account.get());
            response.setResponseObject(oneAccount);
        } else {
            response.setErrorMessage(ErrorConstants.ERR_RECORD_NOT_FOUND);
            response.setResponseCode(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseWrapper delete(String id) {
        response = new ResponseWrapper();
        Optional<Account> account = accountRepository.findById(Integer.valueOf(id));

        if (account.isPresent()) {
            accountRepository.delete(account.get());
            response.setResponseCode(HttpStatus.OK);
        } else {
            response.setErrorMessage(ErrorConstants.ERR_RECORD_NOT_FOUND);
            response.setResponseCode(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseWrapper save(AccountDto accountDto) {
        response = new ResponseWrapper();

        if ( alreadyExists(accountDto.getUsername())){
            response.setErrorMessage(ErrorConstants.ERR_DUPLICATED);
            response.setResponseCode(HttpStatus.BAD_REQUEST);
            return response;
        }

        accountDto.setPassword( passwordEncoder.encode(accountDto.getPassword()) );
        Account account = AccountDtoMapper.toModel(accountDto);
        if (account != null) {
            accountRepository.save(account);
            response.setResponseCode(HttpStatus.OK);
            response.setResponseObject(account);
        } else
            response.setResponseCode(HttpStatus.BAD_REQUEST);

        return response;
    }

    @Override
    public ResponseWrapper update(AccountDto accountDto) {
        response = new ResponseWrapper();

        if ( alreadyExists(accountDto.getUsername())){ //existing account
            Account account = accountRepository.findAccountByUsername(accountDto.getUsername());
            //at this time, only email & password can be updated
            account.setEmail(accountDto.getEmail());
            account.setPassword( passwordEncoder.encode(accountDto.getPassword()) );
            accountRepository.save(account);
            response.setResponseCode(HttpStatus.OK);
            response.setResponseObject(account);
        } else
            response.setResponseCode(HttpStatus.BAD_REQUEST);

        return response;
    }
}
