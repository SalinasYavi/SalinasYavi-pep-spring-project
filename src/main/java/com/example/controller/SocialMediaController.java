package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

@PostMapping(value = "/register")
@ResponseBody
public Account postAccount(@RequestBody Account account){
    Account newAccount = accountService.persistAccount(account);
    if(newAccount == null){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password.");
    } else if(newAccount.getUsername() == null){
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists.");
    }
    return newAccount;
}

@PostMapping(value = "/login")
@ResponseBody
public Account postLogin(@RequestBody Account account){
    Account loginAccount = accountService.loginAccount(account);
    if(loginAccount == null){
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect username or password");

    }
    return loginAccount;
}


}
