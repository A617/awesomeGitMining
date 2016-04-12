package org.Common.po;

public class Statistics {

	/*
	 * private static Statistics instance;
	 * 
	 * private Statistics(){}
	 * 
	 * public static Statistics getInstance(){ return instance==null?new
	 * Statistics():instance; }
	 */

	public static final String[] language = { "Ruby", "Python", "Java", "JavaScript", "C", "Perl", "PHP", "C++", "HTML",
			"Shell", "C#", "Prolog", "Scala", "Viml", "CSS", "Go", "Clojure", "CoffeeScript", "Haskell", "Lua",
			"Objective-C", "others" };

	public static final String[] year = { "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015" };

	public static final String[] rank = { "star", "fork", "watchers", "subscribers", "issues", "contributors",
			"collabotators" };

	public static final String[] company = { "Shopify", "Google","Github", "Twitter", "Red Hat", "Mozilla", "Xamarin", "Heroku", "Facebook",
			"Microsoft" };
	public static final String[] categories ={"API","Django","gem","jQuery","Web","IRC","Plugin","database","Android","Git",
	"Emacs","Linux","JSON","toolkit",".NET","OS","XML","iOS","MVC","vim","Apache","Maven","MySQL"};
	
	public static String[] getLanguage() {
		String[] language = { "Ruby", "Python", "Java", "JavaScript", "C", "Perl", "PHP", "C++", "HTML", "Shell", "C#",
				"Prolog", "Scala", "Viml", "CSS", "Go", "Clojure", "CoffeeScript", "Haskell", "Lua", "Objective-C",
				"others" };
		return language;
	}

	public static String[] getYear() {
		String[] year = { "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015" };
		return year;
	}

	public static String[] getRank() {
		String[] rank = { "star", "fork", "watchers", "subscribers", "issues", "contributors", "collabotators" };
		return rank;
	}

	public static String[] getCompany() {
		String[] company = { "Shopify", "Github", "Google", "Twitter", "Facebook", "Xamarin", "Red Hat", "Heroku",
				"Microsoft", "Mozilla" };
		return company;
	}
	
	public static int getLanguageIndex(String name) {
		for (int i = 0; i < language.length; i++) {
			if(language[i].equals(name)){
				return i;
			}
		}
		return -1;
	}
	
	public static int getCompanyIndex(String name) {
		for (int i = 0; i < company.length; i++) {
			if(company[i].equals(name)){
				return i;
			}
		}
		return -1;
	}

}
