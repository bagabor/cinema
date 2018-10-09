package com.example.cinema.common;

public enum MovieType {

	ACTION("ACTION"), DOCUMENT("DOCUMENT"), HORROR("HORROR");

	private MovieType(final String name) {
		this.value = name;
	}

	public String value;

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
