package com.lexmatos.library.domain.book.enums;

public enum YesOuFalse {
	
	Y("Yes"),
	F("False");
		
	private String name;
		
	private YesOuFalse(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
