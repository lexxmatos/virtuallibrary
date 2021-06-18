package com.lexmatos.library.domain.user.enums;

public enum Action {

	E("Enable"),
	D("Disable");
		
	private String name;
		
	private Action(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
