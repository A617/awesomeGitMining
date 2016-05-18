package edu.nju.model;

import java.util.Date;

public class Repository {
    private Integer id;

    private String fullName;

    private String name;

    private String ownerName;

    private String language;

    private Integer openIssuesCount;

    private Integer watchersCount;

    private String cloneUrl;

    private String htmlUrl;

    private String homepage;

    private Date createdAt;

    private Date pushedAt;

    private Date updatedAt;

    private Integer size;

    private Integer stargazersCount;

    private Integer forksCount;

    private Integer subscribersCount;

    private String contributorsLogin;

    private String collaboratorsLogin;

    private String languages;

    private Double sizeScore;

    private Double scaleScore;

    private Double promisingScore;

    private Double participationScore;

    private Double hotScore;

    private Integer contributorsCount;

    private Integer collaboratorsCount;

    private String description;

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

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages == null ? null : languages.trim();
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

    public Integer getContributorsCount() {
        return contributorsCount;
    }

    public void setContributorsCount(Integer contributorsCount) {
        this.contributorsCount = contributorsCount;
    }

    public Integer getCollaboratorsCount() {
        return collaboratorsCount;
    }

    public void setCollaboratorsCount(Integer collaboratorsCount) {
        this.collaboratorsCount = collaboratorsCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", name='" + name + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", language='" + language + '\'' +
                ", openIssuesCount=" + openIssuesCount +
                ", watchersCount=" + watchersCount +
                ", cloneUrl='" + cloneUrl + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", homepage='" + homepage + '\'' +
                ", createdAt=" + createdAt +
                ", pushedAt=" + pushedAt +
                ", updatedAt=" + updatedAt +
                ", size=" + size +
                ", stargazersCount=" + stargazersCount +
                ", forksCount=" + forksCount +
                ", subscribersCount=" + subscribersCount +
                ", contributorsLogin='" + contributorsLogin + '\'' +
                ", collaboratorsLogin='" + collaboratorsLogin + '\'' +
                ", languages='" + languages + '\'' +
                ", sizeScore=" + sizeScore +
                ", scaleScore=" + scaleScore +
                ", promisingScore=" + promisingScore +
                ", participationScore=" + participationScore +
                ", hotScore=" + hotScore +
                ", contributorsCount=" + contributorsCount +
                ", collaboratorsCount=" + collaboratorsCount +
                ", description='" + description + '\'' +
                '}';
    }
}