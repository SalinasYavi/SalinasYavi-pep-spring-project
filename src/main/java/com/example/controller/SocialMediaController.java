package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private MessageService messageService;

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

@PostMapping(value = "/messages")
@ResponseBody
public Message postMessage(@RequestBody Message message){
    Message newMessage = messageService.persitMessage(message);
    if(newMessage == null){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "posted_by doesn't exist");
    }
    return newMessage;
}

@GetMapping(value = "/messages")
@ResponseBody
public List<Message> getAllMessages(){
    return messageService.getAllMessages();
}

@GetMapping("/messages/{message_id}")
@ResponseBody
public Message getMessageByMessageId(@PathVariable int message_id){
    return messageService.getMessageById(message_id);
}

@GetMapping("/accounts/{account_id}/messages")
@ResponseBody
public List<Message> getAllMessagesByAccountId(@PathVariable int account_id){
    return messageService.getAllMessagesPostedBy(account_id);
}

@DeleteMapping("/messages/{message_id}")
@ResponseBody
public Integer deleteMessageByMessageId(@PathVariable Integer message_id){
    int rowsAffected = messageService.deleteMessageByMessageId(message_id);
    if(rowsAffected == 0){
        return null;
    }
    return rowsAffected;
}

@RequestMapping(value = "/messages/{message_id}", method = RequestMethod.PATCH)
@ResponseBody
public int patchMessageByMessageId(@PathVariable int message_id, @RequestBody Message messageUpdate) {
    int rowsAffected = messageService.updateMessageByMessageId(message_id, messageUpdate.getMessage_text());
    if(rowsAffected > 0){
        return rowsAffected;
    }
     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message doesn't exist");
 
}



}
