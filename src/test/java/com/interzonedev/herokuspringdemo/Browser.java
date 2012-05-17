package com.interzonedev.herokuspringdemo;

import java.util.LinkedList;
import java.util.List;

public enum Browser {
	IE("iexplorer"), FIREFOX("firefox"), CHROME("chrome"), HTMLUNIT("htmlUnit");

	private final static List<String> ids = new LinkedList<String>();

	static {
		for (Browser b : values()) {
			ids.add(b.id());
		}
	}

	private final String id;

	private Browser(String id) {
		this.id = id;
	}

	public String id() {
		return id;
	}

	public static List<String> allIds() {
		return ids;
	}

	public static Browser getById(String id) {
		for (Browser b : values()) {
			if (b.id().equals(id)) {
				return b;
			}
		}
		return null;
	}
}
