package org.Common.vo;

public class SimpleUserVO extends VO{
	private String login;
	private String location;
	private String company;
	private int followers;
	
	public SimpleUserVO(){
	}
	
	public SimpleUserVO(String login, String location,String company, int followers) {
		super();
		this.login = login;
		this.location = location;
		this.company = company;
		this.followers = followers;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}
}
