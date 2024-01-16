package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * @param usernanme of account
     * @return Account associated with username
     */
    @Query("FROM Account WHERE username = :usernameVar")
    Account findAccountByUserName(@Param("usernameVar") String username);

    
    /**
     * @param username of account
     * @param password of account
     * @return Account associate with username and password
     */
    @Query("FROM Account WHERE username = :usernameVar AND password = :passwordVar")
    Account findAccountByUserNameAndPassword(@Param("usernameVar") String username, @Param("passwordVar") String password);

}
