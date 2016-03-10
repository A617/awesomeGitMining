package main.vo;

import java.util.List;
import java.util.Map;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class RepositoryVO extends VO {
	private String full_name;// 项目名
	private int subscribers_count;// 点赞人数
	private Map<String, Integer> languages;// 项目使用的语言
	private List<String> contributors_login;// 项目贡献者
	private List<String> collaborators_login;// 项目合作者
	private List<String> forks_fullname;// fork的项目
	private int forks_count;// 被fork的次数
	private String clone_url;// 项目主页
	private String description;// 项目描述信息
	private String updated_at;
	private int open_issues;

	public RepositoryVO() {

	}

	public RepositoryVO(String full_name, int subscribers_count, Map<String, Integer> languages,
			List<String> contributors_login, List<String> collaborators_login, List<String> forks_fullname,
			int forks_count, String clone_url, String description, String updated_at,int open_issues) {
		super();
		this.full_name = full_name;
		this.subscribers_count = subscribers_count;
		this.languages = languages;
		this.contributors_login = contributors_login;
		this.collaborators_login = collaborators_login;
		this.forks_fullname = forks_fullname;
		this.forks_count = forks_count;
		this.clone_url = clone_url;
		this.description = description;
		this.updated_at = updated_at;
		this.open_issues = open_issues;
	}

	public int getOpen_issues() {
		return open_issues;
	}

	public void setOpen_issues(int open_issues) {
		this.open_issues = open_issues;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public int getSubscribers_count() {
		return subscribers_count;
	}

	public void setSubscribers_count(int subscribers_count) {
		this.subscribers_count = subscribers_count;
	}

	public Map<String, Integer> getLanguages() {
		return languages;
	}

	public void setLanguages(Map<String, Integer> languages) {
		this.languages = languages;
	}

	public List<String> getContributors_login() {
		return contributors_login;
	}

	public void setContributors_login(List<String> contributors_login) {
		this.contributors_login = contributors_login;
	}

	public List<String> getCollaborators_login() {
		return collaborators_login;
	}

	public void setCollaborators_login(List<String> collaborators_login) {
		this.collaborators_login = collaborators_login;
	}

	public List<String> getForks_fullname() {
		return forks_fullname;
	}

	public void setForks_fullname(List<String> forks_fullname) {
		this.forks_fullname = forks_fullname;
	}

	public int getForks_count() {
		return forks_count;
	}

	public void setForks_count(int forks_count) {
		this.forks_count = forks_count;
	}

	public String getClone_url() {
		return clone_url;
	}

	public void setClone_url(String clone_url) {
		this.clone_url = clone_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdated_at() {
		String str = updated_at;
		if (updated_at.contains("T")) {
			str = str.replace("T", " ");
		}
		if (updated_at.contains("Z")) {
			str = str.replace("Z", " ");
		}
		return str;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

}
