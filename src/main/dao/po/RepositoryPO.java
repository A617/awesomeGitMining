package main.dao.po;

import java.util.List;
import java.util.Map;

/**
 * @author tj
 */
public class RepositoryPO {
	private String name;	//项目名
	private String owner_name;	//项目主人
	private String language;	//编程语言
	private List<String> contributors_login;	//该项目所有贡献者
	private List<String> collaborators_login;
	private List<String> branches_name;	//该项目所有版本名
	private List<String> forks_fullname;	//该项目所有fork项目
	private Map<String, Integer> languages;
	private String clone_url;	//git地址
	private String description;
	private String created_at;	//创建时间
	private String pushed_at;	//最后push时间
	private String updated_at;	//最后更新时间
	private int size;
	private int stargazers_count;
	private int forks_count;
	private int open_issues;
	private int subscribers_count;

	public RepositoryPO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	
	


	public List<String> getContributors_login() {
		return contributors_login;
	}

	public void setContributors_login(List<String> contributors_login) {
		this.contributors_login = contributors_login;
	}

	public List<String> getBranches_name() {
		return branches_name;
	}

	public void setBranches_name(List<String> branches_name) {
		this.branches_name = branches_name;
	}

	public List<String> getForks_fullname() {
		return forks_fullname;
	}

	public void setForks_fullname(List<String> forks_fullname) {
		this.forks_fullname = forks_fullname;
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

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getPushed_at() {
		return pushed_at;
	}

	public void setPushed_at(String pushed_at) {
		this.pushed_at = pushed_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStargazers_count() {
		return stargazers_count;
	}

	public void setStargazers_count(int stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public int getForks_count() {
		return forks_count;
	}

	public void setForks_count(int forks_count) {
		this.forks_count = forks_count;
	}

	public int getOpen_issues() {
		return open_issues;
	}

	public void setOpen_issues(int open_issues) {
		this.open_issues = open_issues;
	}

	public int getSubscribers_count() {
		return subscribers_count;
	}

	public void setSubscribers_count(int subscribers_count) {
		this.subscribers_count = subscribers_count;
	}

	
	public List<String> getCollaborators_login() {
		return collaborators_login;
	}

	public void setCollaborators_login(List<String> collaborators_login) {
		this.collaborators_login = collaborators_login;
	}
	
	

	public Map<String, Integer> getLanguages() {
		return languages;
	}

	public void setLanguages(Map<String, Integer> languages) {
		this.languages = languages;
	}

	@Override
	public String toString() {
		return "RepositoryPO [name=" + name + ", owner_name=" + owner_name + ", language=" + language
				+ ", contributors_login=" + contributors_login + ", collaborators_login=" + collaborators_login
				+ ", branches_name=" + branches_name + ", forks_fullname=" + forks_fullname + ", languages=" + languages
				+ ", clone_url=" + clone_url + ", description=" + description + ", created_at=" + created_at
				+ ", pushed_at=" + pushed_at + ", updated_at=" + updated_at + ", size=" + size + ", stargazers_count="
				+ stargazers_count + ", forks_count=" + forks_count + ", open_issues=" + open_issues
				+ ", subscribers_count=" + subscribers_count + "]";
	}

	

	
	
}

