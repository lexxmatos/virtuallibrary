package com.lexmatos.library.domain.book.enums;

public enum Action {
	
	R("Return"),
	M("Remove"),
	S("Shares");
		
	private String name;
		
	private Action(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
