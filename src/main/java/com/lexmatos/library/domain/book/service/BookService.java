package com.lexmatos.library.domain.book.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lexmatos.library.domain.book.entity.Book;
import com.lexmatos.library.domain.book.entity.BookVO;
import com.lexmatos.library.domain.book.entity.Copies;
import com.lexmatos.library.domain.book.enums.YesOuFalse;
import com.lexmatos.library.domain.book.repository.BookRepository;
import com.lexmatos.library.domain.book.repository.CopiesRepository;
import com.lexmatos.library.domain.user.entity.User;
import com.lexmatos.library.domain.book.enums.Action;

import lombok.extern.slf4j.Slf4j;

@Service
public class BookService {
	
    @Autowired
    private CopiesRepository crepository;

    @Autowired
    private BookRepository brepository;

    
	public List<BookVO> findAllByAvailability (){
		
		List <BookVO> bvos = new ArrayList<BookVO>();
		
		List <Book> books = brepository.findAll();
		
		for (Book book : books) {
			List <Copies> total = crepository.findByBookId(book.getId());
			List <Copies> availability = crepository.findByAvailabilityBookId(book.getId());
			if (availability.size() > 0) {
				bvos.add(new BookVO(book,availability.size(),total.size()));
			}
		}

		Comparator<BookVO> compareByAvailability = (BookVO b1, BookVO b2) -> b1.getAvailability().compareTo( b2.getAvailability() );
		Collections.sort(bvos, compareByAvailability.reversed());
		
		return bvos;
	}
	
			
	public BookVO add(BookVO bvo) {
		
    	Book b = new Book();
    	b.setTitle(bvo.getTitle());
    	b.setCategory(bvo.getCategory());
    	Example <Book> example = Example.of(b);
    	Optional<Book> book = brepository.findOne(example);
    	
    	BookVO newbvo = new BookVO(); 
    	
    	if (book.isPresent()){
    		for (int i = 0; i < bvo.getTotal(); i++) {
        		Copies newCopies = new Copies();
        		newCopies.setAction(Action.R);
        		newCopies.setBook(book.get());
        		crepository.save(newCopies);
			}
    		
    		newbvo = new BookVO(book.get(),0,0);
    		
    	} else {
    		Book newBook = new Book();
    		newBook.setTitle(bvo.getTitle());
    		newBook.setCategory(bvo.getCategory());
    		newBook = brepository.save(newBook);

    		for (int i = 0; i < bvo.getTotal(); i++) {
    			Copies newCopies = new Copies();
    			newCopies.setAction(Action.R);
    			newCopies.setBook(book.get());
    			newCopies.setBook(newBook);
    			crepository.save(newCopies);
    		}   
    		
    		newbvo = new BookVO(newBook,0,0);
    	}
    	
    	newbvo.setAvailability(crepository.findByAvailabilityBookId(newbvo.getId()).size());
    	newbvo.setTotal(crepository.findByBookId(newbvo.getId()).size());
    	
    	return newbvo;
	}
	
	public void remove(Long id) {
		Book book = brepository.findById(id).get();
		List <Copies> copies = crepository.findByAvailabilityBookId(id);
		
		if (!copies.isEmpty()) {
			copies.get(0).setAction(Action.M);
			crepository.save(copies.get(0));
		}
	}
	
	public void share(Long id) {
		Book book = brepository.findById(id).get();
		List <Copies> copies = crepository.findByAvailabilityBookId(id);
		
		if (!copies.isEmpty()) {
			copies.get(0).setAction(Action.S);
			crepository.save(copies.get(0));
		}
	}	

	public void returnBook(Long id) {
		Book book = brepository.findById(id).get();
		List <Copies> copies = crepository.findByNotAvailabilityBookId(id);
		
		if (!copies.isEmpty()) {
			copies.get(0).setAction(Action.R);
			crepository.save(copies.get(0));
		}
	}	
}
