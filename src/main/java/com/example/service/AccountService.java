package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    /**
     * @param account accout to be persited into database
     * @return If succesffull, return same account and persit data to database. Else return a blank account
     */
    public Account persistAccount(Account account){
        if(!account.getUsername().trim().isBlank() && account.getPassword().trim().length() > 3 ){
            if(accountRepository.findAccountByUserName(account.getUsername()) == null){
                return accountRepository.save(account);
            } else{
                return new Account();
            }

        }

        return null;
    }

    /**
     * @param account Account who is trying to login
     * @return  if succesfull Account object else null
     */

    public Account loginAccount(Account account){
        return accountRepository.findAccountByUserNameAndPassword(account.getUsername(), account.getPassword());
    }
    
}
