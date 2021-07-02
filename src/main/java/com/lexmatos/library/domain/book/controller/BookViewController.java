package com.lexmatos.library.domain.book.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lexmatos.library.domain.book.entity.BookVO;
import com.lexmatos.library.domain.book.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/books")
public class BookViewController {
	
    @Autowired
    private BookService bookService;	
	
    @GetMapping(value="/available")
    public ModelAndView findAllByAvailability(){
    	
    	ModelAndView mav = new ModelAndView("books");
  	
    	List <BookVO> bkvs = bookService.findAllByAvailability();
		
		mav.addObject("books",bkvs);
    	
    	
    	log.debug("Lista de livros:" + bkvs);
        
        return mav;
    } 
    
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
    	
    	bookService.remove(id);
    	
    	ModelAndView mav = new ModelAndView("books");
      	
    	List <BookVO> bkvs = bookService.findAllByAvailability();
		
		mav.addObject("books",bkvs);
		
    	return mav;
    }
    
    @GetMapping("/share/{id}")
    public ModelAndView share(@PathVariable("id") Long id) {
    	
    	bookService.share(id);
    	
    	ModelAndView mav = new ModelAndView("books");
      	
    	List <BookVO> bkvs = bookService.findAllByAvailability();
		
		mav.addObject("books",bkvs);
		
    	return mav;
    }
    
    @GetMapping("/returnBook/{id}")
    public ModelAndView returnBook(@PathVariable("id") Long id) {
    	
    	bookService.returnBook(id);
    	
    	ModelAndView mav = new ModelAndView("books");
      	
    	List <BookVO> bkvs = bookService.findAllByAvailability();
		
		mav.addObject("books",bkvs);
		
    	return mav;
    }  
    
    @PostMapping("/newBook")
    public String addBook(@Valid BookVO newBookVO, Errors errors) {
    	
    	log.debug("Erros::" + errors.getAllErrors());
    	
    	if (errors.hasErrors()) {
    		return "redirect:/books/newbook";
    	}
    	
    	bookService.add(newBookVO);
    	
    	return "redirect:/books/available";
    }
    
    @GetMapping("/newbook")
    public String addNewBook() {
    	return "newbook";
    }  
    
}
