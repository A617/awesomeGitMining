package main.ui.utility;

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
