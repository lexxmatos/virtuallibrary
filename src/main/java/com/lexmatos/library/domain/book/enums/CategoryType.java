package com.lexmatos.library.domain.book.enums;

public enum CategoryType {
	
	C("Computer"),
	S("Science");
	
	private String name;
			
	private CategoryType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
