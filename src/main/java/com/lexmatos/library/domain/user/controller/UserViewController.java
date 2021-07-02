package com.lexmatos.library.domain.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lexmatos.library.domain.user.entity.User;
import com.lexmatos.library.domain.user.enums.Action;
import com.lexmatos.library.domain.user.repository.UserRepository;
import com.lexmatos.library.domain.user.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserViewController {
	
    @Autowired
    private UserService uservice;    
    
    @GetMapping
	public ModelAndView getUsers() {
		ModelAndView mav = new ModelAndView("users");
		
		List <User> users = uservice.findAll();
		
		mav.addObject("users",users);
		
		return mav;
	}
    
    @GetMapping(value = "disable/{id}")
    public ModelAndView disable(@PathVariable("id") Long id) {
    	ModelAndView mav = new ModelAndView("users");
    	
    	List <User> users = uservice.disable(id);
		
		mav.addObject("users",users);
		
		return mav;
    }
}
