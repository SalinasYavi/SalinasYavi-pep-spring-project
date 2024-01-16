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
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int message_id){
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }else{
            return null;
        }
    }

    public Integer deleteMessageByMessageId(Integer message_id){
        if(messageRepository.existsById(message_id)){
            messageRepository.deleteById(message_id);
            return 1;
        }
        return 0;

    }

}
