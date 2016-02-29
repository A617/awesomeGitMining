package main.vo;

import java.util.ArrayList;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class RepositoryVO {
	private String id;
	private int stars;// 点赞人数
	private ArrayList<String> languages;// 项目使用的语言
	private ArrayList<String> contributers;// 项目贡献者
	private ArrayList<String> co_workers;// 项目合作者
	private ArrayList<String> forkNames;// fork的项目
	private int forks;// 被fork的次数
	private String url;// 项目主页
	private String description;// 项目描述信息

	public RepositoryVO(String id, int stars, ArrayList<String> languages, ArrayList<String> contributers,
			ArrayList<String> co_workers, ArrayList<String> forkNames, int forks, String url, String description) {
		super();
		this.id = id;
		this.stars = stars;
		this.languages = languages;
		this.contributers = contributers;
		this.co_workers = co_workers;
		this.forkNames = forkNames;
		this.forks = forks;
		this.url = url;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public ArrayList<String> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}

	public ArrayList<String> getContributers() {
		return contributers;
	}

	public void setContributers(ArrayList<String> contributers) {
		this.contributers = contributers;
	}

	public ArrayList<String> getCo_workers() {
		return co_workers;
	}

	public void setCo_workers(ArrayList<String> co_workers) {
		this.co_workers = co_workers;
	}

	public ArrayList<String> getForkNames() {
		return forkNames;
	}

	public void setForkNames(ArrayList<String> forkNames) {
		this.forkNames = forkNames;
	}

	public int getForks() {
		return forks;
	}

	public void setForks(int forks) {
		this.forks = forks;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
