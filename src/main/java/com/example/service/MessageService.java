package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class MessageService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    MessageRepository messageRepository;

   
    /**
     * @param message Message to be inserted into Database
     * @return if succesfull, persit message to database and return message object. Else return null
     */
    public Message persitMessage(Message message){
       Optional<Account> account = accountRepository.findById(message.getPosted_by());
        if(account.isPresent()){
            if(!message.getMessage_text().trim().isBlank() && message.getMessage_text().trim().length() < 255 ){
                return messageRepository.save(message);
            }
        }
        return null;
    }

    /**
     * @return List of all Messages
     */

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    /**
     * @param message_id message id to identify message object
     * @return if successful return message object else null
     */

    public Message getMessageById(int message_id){
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        }else{
            return null;
        }
    }

    /**
     * @param message_id message id to identify message object
     * @return If message is deleted, return 1 else 0
     */
    public Integer deleteMessageByMessageId(Integer message_id){
        if(messageRepository.existsById(message_id)){
            messageRepository.deleteById(message_id);
            return 1;
        }
        return 0;

    }

    /**
     * @param posted_by identifies account_id that posted message
     * @return List of messages posted by account_id
     */

    public List<Message> getAllMessagesPostedBy(int posted_by){
     return messageRepository.getAllMessagesByPostedBy(posted_by);
       
    }

    /**
     * @param message_id message id to identify message object
     * @param message_text  message text body to update message 
     * @return 1 if successful 0 if not
     */

    @Transactional
    public int updateMessageByMessageId(int message_id, String message_text){
        Optional<Message> message = messageRepository.findById(message_id);
        if(message.isPresent()){
            Message updatedMessage = message.get();
            if(!message_text.trim().isBlank() && message_text.trim().length() < 255){
                updatedMessage.setMessage_text(message_text);
                messageRepository.save(updatedMessage);
                return 1;
               
            }
           
        }
        return 0;
}

}
