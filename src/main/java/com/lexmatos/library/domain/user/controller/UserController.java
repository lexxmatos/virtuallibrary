package com.lexmatos.library.domain.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexmatos.library.domain.user.entity.User;
import com.lexmatos.library.domain.user.enums.Action;
import com.lexmatos.library.domain.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(value = "/restusers")
public class UserController {


    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity <List<User>> findAll(){
        
    	List <User> list = repository.findAll();
        
        log.debug("Lista de usuários:" + list);
        
        return ResponseEntity.ok(list);
    }

    @GetMapping(value="/date")
    public ResponseEntity <List<User>> findAllByOrderDate(){
    	
    	User user = new User();
    	user.setAction(Action.E);
    	
    	Example <User> example = Example.of(user);

    	List <User> users = repository.findAll(example,(Sort.by(Sort.Direction.DESC, "date")));
    	
    	if (users.isEmpty()) {
    		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    	}
    	
    	log.debug("Lista de usuários:" + users);
        
        return ResponseEntity.ok(users);
    }
}