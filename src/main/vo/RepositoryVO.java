package main.vo;

import java.util.ArrayList;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class RepositoryVO extends VO {
	private String name;//项目名
	private int subscribers_count;// 点赞人数
	private ArrayList<String> languages;// 项目使用的语言
	private ArrayList<String> contributors;// 项目贡献者
	private ArrayList<String> collaborators;// 项目合作者
	private ArrayList<String> forks;// fork的项目
	private int forks_count;// 被fork的次数
	private String clone_url;// 项目主页
	private String description;// 项目描述信息

	public RepositoryVO() {

	}

	public RepositoryVO(String name, int subscribers_count, ArrayList<String> languages, ArrayList<String> contributors,
			ArrayList<String> collaborators, ArrayList<String> forks, int forks_count, String clone_url,
			String description) {
		super();
		this.name = name;
		this.subscribers_count = subscribers_count;
		this.languages = languages;
		this.contributors = contributors;
		this.collaborators = collaborators;
		this.forks = forks;
		this.forks_count = forks_count;
		this.clone_url = clone_url;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubscribers_count() {
		return subscribers_count;
	}

	public void setSubscribers_count(int subscribers_count) {
		this.subscribers_count = subscribers_count;
	}

	public ArrayList<String> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}

	public ArrayList<String> getContributors() {
		return contributors;
	}

	public void setContributors(ArrayList<String> contributors) {
		this.contributors = contributors;
	}

	public ArrayList<String> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(ArrayList<String> collaborators) {
		this.collaborators = collaborators;
	}

	public ArrayList<String> getForks() {
		return forks;
	}

	public void setForks(ArrayList<String> forks) {
		this.forks = forks;
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

}
