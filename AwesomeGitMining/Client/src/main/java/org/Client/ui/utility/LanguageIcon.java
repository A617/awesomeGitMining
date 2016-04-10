package org.Client.ui.utility;

import java.util.ArrayList;

public class LanguageIcon {
	private ArrayList<String> list;
	private static LanguageIcon instance;
	
	public LanguageIcon() {
		list = new ArrayList<String>();
		list.add("Java");
		list.add("Python");
		list.add("Ruby");
		list.add("JavaScript");	
		list.add("C");
		list.add("C++");
		list.add("C#");
		list.add("HTML");
		list.add("CSS");
		list.add("PHP");
		list.add("Erlang");
		list.add("Lua");
		list.add("Objective-C");
		list.add("Shell");
		list.add("Go");
		list.add("Clojure");
	}
	
	public static LanguageIcon getInstance() {
		if(instance == null){
			instance = new LanguageIcon();
		}
		return instance;
	}
	
	public int hasLanguage(String name) {
		return list.indexOf(name);
	}
}
