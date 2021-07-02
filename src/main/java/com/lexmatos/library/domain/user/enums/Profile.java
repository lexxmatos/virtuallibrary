package com.lexmatos.library.domain.user.enums;

public enum Profile {
	A("Adminstrator"),
	U("User");
		
	private String name;
		
	private Profile(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
