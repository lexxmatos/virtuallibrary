package com.lexmatos.library.domain.book.entity;

import javax.validation.constraints.NotBlank;

import com.lexmatos.library.domain.book.enums.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
	
    private Long id;
    
    //@NotBlank(message="Titulo é obrigatório")
	private String title;
    //@NotBlank(message="Categoria é obrigatória")
	private CategoryType category;
    
	private Integer availability;
	
	private Integer total;
	
	public BookVO(Book book, Integer availability, Integer total){
		this.id = book.getId();
		this.title = book.getTitle();
		this.category = book.getCategory();
		this.availability = availability;
		this.total = total;
	}
}
