package main.dao.po;

import java.util.ArrayList;
import java.util.List;

/**
 * UserPO
 * @author Dora
 */
public class UserPO {

	private String login;	//登录名
	private String name;	//昵称
	private String html_url;	//github主页
	private String location;	//所在地
	private String email;
	private String blog;
	private List<String> followers_name;
	private List<String> following_name;
	private List<String> repos_fullname;
	private int public_repos;
	private int followers;
	private int following;
	private String created_at;
	private String updated_at;
	
	public UserPO(String login, String name, String html_url, String location, String email, String blog,
			ArrayList<String> followers_name, ArrayList<String> following_name, ArrayList<String> repos_fullname,
			int public_repos, int followers, int following, String created_at, String updated_at) {
		super();
		this.login = login;
		this.name = name;
		this.html_url = html_url;
		this.location = location;
		this.email = email;
		this.blog = blog;
		this.followers_name = followers_name;
		this.following_name = following_name;
		this.repos_fullname = repos_fullname;
		this.public_repos = public_repos;
		this.followers = followers;
		this.following = following;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public UserPO() {
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
	@Override
	public String toString() {
		return "UserPO [login=" + login + ", name=" + name + ", html_url=" + html_url + ", location=" + location
				+ ", email=" + email + ", blog=" + blog + ", followers_name=" + followers_name + ", following_name="
				+ following_name + ", repos_fullname=" + repos_fullname + ", public_repos=" + public_repos
				+ ", followers=" + followers + ", following=" + following + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}
	
	
	
	
	
	
	
}
