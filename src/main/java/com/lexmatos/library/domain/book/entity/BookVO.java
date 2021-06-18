package com.lexmatos.library.domain.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
	
    private Long id;
    
	private String title;
	private String category;
	private Integer availability;
	
	public BookVO(Book book, Integer availability){
		this.id = book.getId();
		this.title = book.getTitle();
		this.category = book.getCategory();
		this.availability = availability;
	}
}
