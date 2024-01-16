package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    MessageRepository messageRepository;

   

    public Message persitMessage(Message message){
       Optional<Account> account = accountRepository.findById(message.getPosted_by());
        if(account.isPresent()){
            if(!message.getMessage_text().trim().isBlank() && message.getMessage_text().trim().length() < 255 ){
                return messageRepository.save(message);
            }
        }
        return null;
    }

}
