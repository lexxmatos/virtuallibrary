package com.lexmatos.library.domain.book.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexmatos.library.domain.book.entity.Book;
import com.lexmatos.library.domain.book.entity.BookVO;
import com.lexmatos.library.domain.book.entity.Copies;
import com.lexmatos.library.domain.book.repository.BookRepository;
import com.lexmatos.library.domain.book.repository.CopiesRepository;

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
			List <Copies> copies = crepository.findByBookId(book.getId());
			bvos.add(new BookVO(book,copies.size()));
		}

		Comparator<BookVO> compareByAvailability = (BookVO b1, BookVO b2) -> b1.getAvailability().compareTo( b2.getAvailability() );
		Collections.sort(bvos, compareByAvailability.reversed());
		
		return bvos;
	}
}
