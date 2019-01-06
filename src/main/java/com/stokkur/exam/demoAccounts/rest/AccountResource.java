package com.stokkur.exam.demoAccounts.rest;

import static com.stokkur.exam.demoAccounts.rest.util.ApiConstant.API_PATH;

import com.stokkur.exam.demoAccounts.dto.AccountDto;
import com.stokkur.exam.demoAccounts.rest.util.HeaderUtil;
import com.stokkur.exam.demoAccounts.rest.util.ResponseWrapper;
import com.stokkur.exam.demoAccounts.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Rest Controller for managing API accounts
 */
@RestController
@RequestMapping(API_PATH)
public class AccountResource {

    private final Logger LOG = LoggerFactory.getLogger(AccountResource.class);

    @Autowired
    private final AccountService accountService;

    private static final String ENTITY_NAME = "accounts";

    public AccountResource(AccountService accountService){
        this.accountService = accountService;
    }

    /**
     * GET /account : get an account's info.
     *
     * @return the ResponseEntity with status 200 (OK) and the account
     *         body, or status 500 (Internal Server Error) if the account couldn't
     *         be returned
     */
    @GetMapping(value = {"/accounts/{id}"})
    public ResponseEntity getAccount(@PathVariable(value = "id", required = true) String id) {
        ResponseWrapper responseData;

        responseData = accountService.findById(id);

        if (responseData.getResponseCode() == HttpStatus.OK)
            return ResponseEntity.status(responseData.getResponseCode()).body(responseData.getResponseObject());

        return ResponseEntity.notFound()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "err_get",  responseData.getErrorMessage()))
                .build();
    }

    @GetMapping("/accounts/")
    public ResponseEntity getAllAccounts() {
        ResponseWrapper responseData;

        responseData = accountService.findAll();

        return ResponseEntity.status(responseData.getResponseCode()).body(responseData.getResponseObject());
    }

    @PostMapping("/accounts")
    public ResponseEntity createAccount(@RequestBody AccountDto account){
        ResponseWrapper responseData;
        responseData = accountService.save(account);

        if (responseData.getResponseCode() == HttpStatus.OK)
            return ResponseEntity.status(responseData.getResponseCode()).body(responseData.getResponseObject());

        return ResponseEntity.status((responseData.getResponseCode()))
                .body(responseData.getErrorMessage());
    }

    @PutMapping("/accounts")
    public ResponseEntity updateAccount(@RequestBody AccountDto account){
        ResponseWrapper responseData;
        responseData = accountService.update(account);

        if (responseData.getResponseCode() == HttpStatus.OK)
            return ResponseEntity.status(responseData.getResponseCode()).body(responseData.getResponseObject());

        return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "err_update", responseData.getErrorMessage()))
                .build();
    }

    @DeleteMapping(value = {"/accounts/{id}"} )
    public ResponseEntity deleteAccount(@PathVariable(value = "id", required = true) String id) {
        ResponseWrapper responseData = accountService.delete(id);
        if (responseData.getResponseCode() == HttpStatus.OK)
            return ResponseEntity.status(responseData.getResponseCode()).body(responseData.getResponseObject());

        return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "err_delete",  responseData.getErrorMessage()))
                .build();
    }

}
