package main.dao.entity;

import java.util.List;

/**
 * UserPO
 * @author Dora
 */
public class User {

	protected String login;	//登录名
	protected String html_url;	//github主页
	protected String type;
	protected boolean site_admin;
	
	private String name;	//昵称
	private String location;	//所在地
	private String email;
	private String blog;
	private List<String> followers_name;
	private List<String> following_name;
	private List<String> repos_fullname;
	private int public_repos;
	private int public_gists;
	private int followers;
	private int following;
	private String created_at;
	private String updated_at;

	
	public User() {
		super();
	}
	
	public int getFollowing() {
		return following;
	}
	public void setFollowing(int following) {
		this.following = following;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public List<String> getFollowers_name() {
		return followers_name;
	}
	public void setFollowers_name(List<String> followers_name) {
		this.followers_name = followers_name;
	}
	public List<String> getFollowing_name() {
		return following_name;
	}
	public void setFollowing_name(List<String> following_name) {
		this.following_name = following_name;
	}
	public List<String> getRepos_fullname() {
		return repos_fullname;
	}
	public void setRepos_fullname(List<String> repos_fullname) {
		this.repos_fullname = repos_fullname;
	}
	public int getPublic_repos() {
		return public_repos;
	}
	public void setPublic_repos(int public_repos) {
		this.public_repos = public_repos;
	}
	public int getFollowers() {
		return followers;
	}
	public void setFollowers(int followers) {
		this.followers = followers;
	}

	public int getPublic_gists() {
		return public_gists;
	}

	public void setPublic_gists(int public_gists) {
		this.public_gists = public_gists;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSite_admin() {
		return site_admin;
	}

	public void setSite_admin(boolean site_admin) {
		this.site_admin = site_admin;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", name=" + name + ", html_url=" + html_url + ", location=" + location
				+ ", email=" + email + ", blog=" + blog + ", followers_name=" + followers_name + ", following_name="
				+ following_name + ", repos_fullname=" + repos_fullname + ", public_repos=" + public_repos
				+ ", public_gists=" + public_gists + ", followers=" + followers + ", following=" + following
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + ", type=" + type + ", site_admin="
				+ site_admin + "]";
	}
	
	
	
	
	
	
	
}
