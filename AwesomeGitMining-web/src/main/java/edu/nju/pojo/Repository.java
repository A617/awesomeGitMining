package main.java.edu.nju.pojo;

import java.util.Date;

public class Repository {
    private Integer id;

    private String fullName;

    private String name;

    private String ownerName;

    private String language;

    private Boolean fork;

    private Boolean hasIssues;

    private Boolean hasDownloads;

    private Boolean hasWiki;

    private Boolean hasPages;

    private Integer openIssuesCount;

    private Integer watchersCount;

    private String cloneUrl;

    private String htmlUrl;

    private String homepage;

    private String description;

    private Date createdAt;

    private Date pushedAt;

    private Date updatedAt;

    private Integer size;

    private String defaultBranch;

    private Integer stargazersCount;

    private Integer forksCount;

    private Integer subscribersCount;

    private String contributorsLogin;

    private String collaboratorsLogin;

    private String languagesNames;

    private String languagesCounts;

    private Double sizeScore;

    private Double scaleScore;

    private Double promisingScore;

    private Double participationScore;

    private Double hotScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public Boolean getHasIssues() {
        return hasIssues;
    }

    public void setHasIssues(Boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    public Boolean getHasDownloads() {
        return hasDownloads;
    }

    public void setHasDownloads(Boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    public Boolean getHasWiki() {
        return hasWiki;
    }

    public void setHasWiki(Boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    public Boolean getHasPages() {
        return hasPages;
    }

    public void setHasPages(Boolean hasPages) {
        this.hasPages = hasPages;
    }

    public Integer getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(Integer openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    public Integer getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(Integer watchersCount) {
        this.watchersCount = watchersCount;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl == null ? null : cloneUrl.trim();
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl == null ? null : htmlUrl.trim();
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage == null ? null : homepage.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(Date pushedAt) {
        this.pushedAt = pushedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch == null ? null : defaultBranch.trim();
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    public Integer getSubscribersCount() {
        return subscribersCount;
    }

    public void setSubscribersCount(Integer subscribersCount) {
        this.subscribersCount = subscribersCount;
    }

    public String getContributorsLogin() {
        return contributorsLogin;
    }

    public void setContributorsLogin(String contributorsLogin) {
        this.contributorsLogin = contributorsLogin == null ? null : contributorsLogin.trim();
    }

    public String getCollaboratorsLogin() {
        return collaboratorsLogin;
    }

    public void setCollaboratorsLogin(String collaboratorsLogin) {
        this.collaboratorsLogin = collaboratorsLogin == null ? null : collaboratorsLogin.trim();
    }

    public String getLanguagesNames() {
        return languagesNames;
    }

    public void setLanguagesNames(String languagesNames) {
        this.languagesNames = languagesNames == null ? null : languagesNames.trim();
    }

    public String getLanguagesCounts() {
        return languagesCounts;
    }

    public void setLanguagesCounts(String languagesCounts) {
        this.languagesCounts = languagesCounts == null ? null : languagesCounts.trim();
    }

    public Double getSizeScore() {
        return sizeScore;
    }

    public void setSizeScore(Double sizeScore) {
        this.sizeScore = sizeScore;
    }

    public Double getScaleScore() {
        return scaleScore;
    }

    public void setScaleScore(Double scaleScore) {
        this.scaleScore = scaleScore;
    }

    public Double getPromisingScore() {
        return promisingScore;
    }

    public void setPromisingScore(Double promisingScore) {
        this.promisingScore = promisingScore;
    }

    public Double getParticipationScore() {
        return participationScore;
    }

    public void setParticipationScore(Double participationScore) {
        this.participationScore = participationScore;
    }

    public Double getHotScore() {
        return hotScore;
    }

    public void setHotScore(Double hotScore) {
        this.hotScore = hotScore;
    }
}