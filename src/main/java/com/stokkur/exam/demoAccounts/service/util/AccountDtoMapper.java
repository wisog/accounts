package com.stokkur.exam.demoAccounts.service.util;


import com.stokkur.exam.demoAccounts.dto.AccountDto;
import com.stokkur.exam.demoAccounts.model.Account;
import com.stokkur.exam.demoAccounts.model.Role;

public class AccountDtoMapper {

    private AccountDtoMapper() {
    }


    /**
     * Mapper for convert Repository entity to AccountDto
     *
     * @param entity An Account object
     * @return AccountDto
     */
    public static AccountDto toDto(Account entity) {
        AccountDto dto = new AccountDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setCreateTime(entity.getCreate_time().toString());
        dto.setRole(RoleDtoMapper.toDto(entity.getRole()));
        return dto;
    }

    public static Account toModel(AccountDto dto) {
        Account account = new Account();
        try { // should validate custom requirements here
            account.setPassword(dto.getPassword());
            account.setUsername(dto.getUsername());
            account.setEmail(dto.getEmail());
            account.setActive(true);
            account.setCreate_time(new java.sql.Timestamp(System.currentTimeMillis()));

            Role role = new Role();
            role.setId(2); // only creates user accounts
            account.setRole(role);
        }catch (Exception e){
            return null;
        }
        return account;
    }
}