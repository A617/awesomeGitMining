package main.dao.entity;

public class Owner extends User {

	@Override
	public String toString() {
		return "Owner [login=" + login + ", html_url=" + html_url + ", type=" + type + ", site_admin=" + site_admin
				+ "]";
	}

	
}
