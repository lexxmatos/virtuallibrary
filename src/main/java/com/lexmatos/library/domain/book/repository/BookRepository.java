package com.lexmatos.library.domain.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lexmatos.library.domain.book.entity.Book;

public interface BookRepository  extends JpaRepository <Book, Long>{
    
}