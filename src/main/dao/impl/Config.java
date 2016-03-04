package main.dao.impl;

import java.util.HashMap;
import java.util.Map;

import main.dao.entity.Branch;
import main.dao.entity.Collaborator;
import main.dao.entity.Contributor;
import main.dao.entity.Fork;
import main.dao.entity.Owner;
import main.dao.entity.Repository;
import main.dao.entity.User;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class Config {
	
	
	
	public static JsonConfig getConfig(Class<?> cls){
		JsonConfig config = new JsonConfig();    
        config.setIgnoreDefaultExcludes(false);       
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        config.setExcludes(map.get(cls));
        return config;
	}
	
	private final static String[] userExcludes = {
			"id",
			"avatar_url",
		    "gravatar_id",
		    "url",
		    "followers_url",
		    "following_url",
		    "gists_url",
		    "starred_url",
		    "subscriptions_url",
		    "organizations_url",
		    "repos_url",
		    "events_url",
		    "received_events_url"
	};
	
	private final static String[] repoExcludes = {//只要设置这个数组，指定过滤哪些字段。    
    		"id",
    	    "owner",
    	    "private",
    	    "url",
    	    "forks_url",
    	    "keys_url",
    	    "collaborators_url",
    	    "teams_url",
    	    "hooks_url",
    	    "issue_events_url",
    	    "events_url",
    	    "assignees_url",
    	    "branches_url",
    	    "tags_url",
    	    "blobs_url",
    	    "git_tags_url",
    	    "git_refs_url",
    	    "trees_url",
    	    "statuses_url",
    	    "languages_url",
    	    "stargazers_url",
    	    "contributors_url",
    	    "subscribers_url",
    	    "subscription_url",
    	    "commits_url",
    	    "git_commits_url",
    	    "comments_url",
    	    "issue_comment_url",
    	    "contents_url",
    	    "compare_url",
    	    "merges_url",
    	    "archive_url",
    	    "downloads_url",
    	    "issues_url",
    	    "pulls_url",
    	    "milestones_url",
    	    "notifications_url",
    	    "labels_url",
    	    "releases_url",
    	    "ssh_url",
    	    "svn_url",
    	    "forks",
    	    "open_issues",
    	    "watchers",
    	    "permissions",
    	    "network_count",
    	    "git_url",
    	    "html_url",
    	    
    	    //以下出现在官方api中
    	    "deployments_url",
    	    "mirror_url"
    };
	
	
	private final static String[] contributorExcludes={
			"_id",
			"id",
			"avatar_url",
		    "gravatar_id",
		    "url",
		    "followers_url",
		    "following_url",
		    "gists_url",
		    "starred_url",
		    "subscriptions_url",
		    "organizations_url",
		    "repos_url",
		    "events_url",
		    "received_events_url"
	};
	
	private final static String[] collaboratorExcludes={
			"_id",
			"id",
			"avatar_url",
		    "gravatar_id",
		    "url",
		    "followers_url",
		    "following_url",
		    "gists_url",
		    "starred_url",
		    "subscriptions_url",
		    "organizations_url",
		    "repos_url",
		    "events_url",
		    "received_events_url"
	};
	
	private final static String[] ownerExcludes={
			"id",
	        "avatar_url",
	        "gravatar_id",
	        "url",
	        "followers_url",
	        "following_url",
	        "gists_url",
	        "starred_url",
	        "subscriptions_url",
	        "organizations_url",
	        "repos_url",
	        "events_url",
	        "received_events_url"
	};
	
	
	private final static String[] branchExcludes={
			"commit",
			"_id"
	};
	
	private final static String[] forkExcludes={
			"_id",
			"id",
    	    "owner",
    	    "private",
    	    "url",
    	    "forks_url",
    	    "keys_url",
    	    "collaborators_url",
    	    "teams_url",
    	    "hooks_url",
    	    "issue_events_url",
    	    "events_url",
    	    "assignees_url",
    	    "branches_url",
    	    "tags_url",
    	    "blobs_url",
    	    "git_tags_url",
    	    "git_refs_url",
    	    "trees_url",
    	    "statuses_url",
    	    "languages_url",
    	    "stargazers_url",
    	    "contributors_url",
    	    "subscribers_url",
    	    "subscription_url",
    	    "commits_url",
    	    "git_commits_url",
    	    "comments_url",
    	    "issue_comment_url",
    	    "contents_url",
    	    "compare_url",
    	    "merges_url",
    	    "archive_url",
    	    "downloads_url",
    	    "issues_url",
    	    "pulls_url",
    	    "milestones_url",
    	    "notifications_url",
    	    "labels_url",
    	    "releases_url",
    	    "ssh_url",
    	    "svn_url",
    	    "forks",
    	    "open_issues",
    	    "watchers",
    	    "permissions",
    	    "network_count",
    	    "git_url",
    	    "html_url",
    	    
    	    "deployments_url",
    	    "mirror_url"
    };
	
	
	private static Map<Class<?>, String[]> map = new HashMap<Class<?>, String[]>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
		put(Repository.class, repoExcludes);
		put(User.class,userExcludes);
		put(Contributor.class,contributorExcludes);
		put(Collaborator.class,collaboratorExcludes);
		put(Branch.class,branchExcludes);
		put(Fork.class,forkExcludes);
		put(Owner.class,ownerExcludes);
		}
	};
	
}
