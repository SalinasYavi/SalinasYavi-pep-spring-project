package com.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("FROM Account WHERE username = :nameVar")
    Account findAccountByUserName(@Param("nameVar") String username);

    @Query("FROM Account WHERE username = :usernameVar AND password = :passwordVar")
    Account findAccountByUserNameAndPassword(@Param("usernameVar") String username, @Param("passwordVar") String password);

}
