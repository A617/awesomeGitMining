package main.dao.entity;

public class Fork extends Repository{

	protected String fn;

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	@Override
	public String toString() {
		return "Fork [fn=" + fn + ", name=" + name + ", full_name=" + full_name + ", language=" + language + ", fork="
				+ fork + ", has_issues=" + has_issues + ", has_downloads=" + has_downloads + ", has_wiki=" + has_wiki
				+ ", has_pages=" + has_pages + ", open_issues_count=" + open_issues_count + ", watchers_count="
				+ watchers_count + ", clone_url=" + clone_url + ", home_url=" + home_url + ", homepage=" + homepage
				+ ", description=" + description + ", created_at=" + created_at + ", pushed_at=" + pushed_at
				+ ", updated_at=" + updated_at + ", size=" + size + ", default_branch=" + default_branch
				+ ", stargazers_count=" + stargazers_count + ", forks_count=" + forks_count + ", open_issues="
				+ open_issues + ", subscribers_count=" + subscribers_count + "]";
	}


}
