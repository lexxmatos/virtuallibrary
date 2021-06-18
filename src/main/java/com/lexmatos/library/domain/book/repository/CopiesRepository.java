package com.lexmatos.library.domain.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lexmatos.library.domain.book.entity.Copies;


public interface CopiesRepository extends JpaRepository <Copies, Long>{
	
	@Query("select c from Copies c where c.book.id = ?1")
	List<Copies> findByBookId (Long idBook);
}
