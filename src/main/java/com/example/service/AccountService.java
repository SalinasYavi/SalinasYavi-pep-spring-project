package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

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

    public Account loginAccount(Account account){
        return accountRepository.findAccountByUserNameAndPassword(account.getUsername(), account.getPassword());
    }
    
}
