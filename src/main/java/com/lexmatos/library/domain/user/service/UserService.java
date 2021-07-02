package com.lexmatos.library.domain.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lexmatos.library.domain.user.entity.User;
import com.lexmatos.library.domain.user.enums.Action;
import com.lexmatos.library.domain.user.repository.UserRepository;

@Service
public class UserService {
	
    @Autowired
    private UserRepository urepository;

    public List<User> findAll(){
    	
    	User user = new User();
    	user.setAction(Action.E);
		Example <User> example = Example.of(user);
		
		List <User> users = urepository.findAll(example,(Sort.by(Sort.Direction.DESC, "date")));
		
		return users;
    }
    
    public List<User> disable(Long id){
    	
    	User user = urepository.getById(id);
    	user.setAction(Action.D);
    	urepository.save(user);
    	
    	List <User> users = findAll();
    	
		return users;
    }
}
