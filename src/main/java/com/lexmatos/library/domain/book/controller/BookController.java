package com.lexmatos.library.domain.book.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexmatos.library.domain.book.entity.Book;
import com.lexmatos.library.domain.book.entity.BookVO;
import com.lexmatos.library.domain.book.repository.BookRepository;
import com.lexmatos.library.domain.book.service.BookService;
import com.lexmatos.library.domain.user.entity.User;
import com.lexmatos.library.domain.user.enums.Action;

import lombok.extern.slf4j.Slf4j;

/**
 * Cenário A.1:
Como um utilizador com perfil Administrador / Anónimo ou Utilizador, quando
eu aceder à página de listagem de Livros, eu gostaria de visualizar a lista dos Livros
da aplicação ordenados pela disponibilidade.
Cenário A.2:
Como um utilizador com perfil Administrador / Anónimo ou Utilizador, quando
eu aceder à página de listagem de Livros, caso nenhum utilizador esteja preenchido
eu visualizar uma mensagem informando que não foi encontrado nenhum livro.
Cenário A.3:
Como um utilizador com perfil Administrador, quando eu aceder à página de
listagem de Livros, devo visualizar os botões: Alugar / Devolver / Adicionar e
Remover.
Cenário A.4:
Como um utilizador com perfil Anónimo ou Utilizador, quando eu aceder à
página de listagem de Livros, devo visualizar os botões: Alugar e Devolver.
 *
 */

@Slf4j
@RestController
@RequestMapping(value = "/restbooks")
public class BookController {
	
    @Autowired
    private BookRepository repository;
    
    @Autowired
    private BookService bookService;


    @GetMapping
    public ResponseEntity <List<Book>> findAll(){
        
    	List <Book> list = repository.findAll();
        
        log.debug("Lista de livros:" + list);
        
        return ResponseEntity.ok(list);
    }
    
    @GetMapping(value="/available")
    public ResponseEntity <List<BookVO>> findAllByavailability(){
    	
    	List <BookVO> bkvs = bookService.findAllByAvailability();
    	
    	if (bkvs.isEmpty()) {
    		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    	}
    	
    	log.debug("Lista de livros:" + bkvs);
        
        return ResponseEntity.ok(bkvs);
    } 
    
    @PostMapping("/add")
    public BookVO newEmployee(@RequestBody BookVO newBookVO) {
    	return bookService.add(newBookVO);
    }    
}
