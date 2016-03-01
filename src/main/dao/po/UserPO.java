package main.dao.po;

import java.util.ArrayList;

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
	private ArrayList<UserPO> follower;
	private ArrayList<UserPO> following;
	private ArrayList<RepositoryPO> repos;
	private int public_repos;
	private int followers;
	private String created_at;
	private String updated_at;
	
	
	public UserPO(String login, String name, String html_url, String location, String email, String blog,
			ArrayList<UserPO> follower, ArrayList<UserPO> following, ArrayList<RepositoryPO> repos, int public_repos,
			int followers, String created_at, String updated_at) {
		super();
		this.login = login;
		this.name = name;
		this.html_url = html_url;
		this.location = location;
		this.email = email;
		this.blog = blog;
		this.follower = follower;
		this.following = following;
		this.repos = repos;
		this.public_repos = public_repos;
		this.followers = followers;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public UserPO() {
		super();
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
	public ArrayList<UserPO> getFollower() {
		return follower;
	}
	public void setFollower(ArrayList<UserPO> follower) {
		this.follower = follower;
	}
	public ArrayList<UserPO> getFollowing() {
		return following;
	}
	public void setFollowing(ArrayList<UserPO> following) {
		this.following = following;
	}
	public ArrayList<RepositoryPO> getRepos() {
		return repos;
	}
	public void setRepos(ArrayList<RepositoryPO> repos) {
		this.repos = repos;
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
	@Override
	public String toString() {
		return "UserPO [login=" + login + ", name=" + name + ", html_url=" + html_url + ", location=" + location
				+ ", email=" + email + ", blog=" + blog + ", follower=" + follower + ", following=" + following
				+ ", repos=" + repos + ", public_repos=" + public_repos + ", followers=" + followers + ", created_at="
				+ created_at + ", updated_at=" + updated_at + "]";
	}
	
	
	
	
}
