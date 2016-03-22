package main.dao.entity;

public class Statistics {
	
	private static Statistics instance;
	
	private Statistics(){}
	
	public static Statistics getInstance(){
		return instance==null?new Statistics():instance;
	}

	private final String[] language = { "Ruby", "Python", "Java", "JavaScript", "C", "Perl", "PHP", "C++", "HTML",
			"Shell", "C#", "Prolog", "Scala", "Viml", "CSS", "Go", "Clojure", "CoffeeScript", "Haskell", "Lua",
			"Objective-C", "others" };

	private final String[] year = { "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015" };

	private final String[] rank = { "star", "fork", "watchers", "subscribers", "issues", "contributors", "collabotators" };

	private final String[] company = { "Shopify", "Github", "Google", "Twitter", "Facebook", "Xamarin", "Red Hat",
			"Heroku", "Microsoft", "Mozilla" };

	public String[] getLanguage() {
		return language;
	}

	public String[] getYear() {
		return year;
	}

	public String[] getRank() {
		return rank;
	}

	public String[] getCompany() {
		return company;
	}
	
	
}
