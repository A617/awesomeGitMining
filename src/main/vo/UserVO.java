package main.vo;

import java.util.ArrayList;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class UserVO extends VO {
	private String login;// 登录名
	private String name;
	private String location;
	private String email;
	private String blog;
	private int follower;
	private String html_url; // github主页
	private ArrayList<String> pProjects;// 用户参与的项目
	private ArrayList<String> cProjects;// 用户创建的项目

	public UserVO() {

	}

	public UserVO(String login, String name, String location, String email, String blog, int follower,
			String html_url, ArrayList<String> pProjects, ArrayList<String> cProjects) {
		super();
		this.login = login;
		this.name = name;
		this.location = location;
		this.email = email;
		this.blog = blog;
		this.follower = follower;
		this.html_url = html_url;
		this.pProjects = pProjects;
		this.cProjects = cProjects;
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

	public int getFollower() {
		return follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public ArrayList<String> getpProjects() {
		return pProjects;
	}

	public void setpProjects(ArrayList<String> pProjects) {
		this.pProjects = pProjects;
	}

	public ArrayList<String> getcProjects() {
		return cProjects;
	}

	public void setcProjects(ArrayList<String> cProjects) {
		this.cProjects = cProjects;
	}

}
