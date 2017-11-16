package me.yeojoy.apimanager.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yeojoy on 2017. 11. 16..
 */

public class GithubUser {
    @SerializedName("login")
    public String login; // : "yeojoy",
    @SerializedName("id")
    public long id; // : 1597860,
    @SerializedName("avatar_url")
    public String avatarUrl; // : "https://avatars3.githubusercontent.com/u/1597860?v=4",
    @SerializedName("gravatar_id")
    public String gravatarId; // : "",
    @SerializedName("url")
    public String url; // : "https://api.github.com/users/yeojoy",
    @SerializedName("html_url")
    public String htmlUrl; // : "https://github.com/yeojoy",
    @SerializedName("followers_url")
    public String followersUrl; // : "https://api.github.com/users/yeojoy/followers",
    @SerializedName("following_url")
    public String followingUrl; // : "https://api.github.com/users/yeojoy/following{/other_user}",
    @SerializedName("gists_url")
    public String gistsUrl; // : "https://api.github.com/users/yeojoy/gists{/gist_id}",
    @SerializedName("starred_url")
    public String starredUrl; // : "https://api.github.com/users/yeojoy/starred{/owner}{/repo}",
    @SerializedName("subscriptions_url")
    public String subscriptionsUrl; // : "https://api.github.com/users/yeojoy/subscriptions",
    @SerializedName("organizations_url")
    public String organizationsUrl; // : "https://api.github.com/users/yeojoy/orgs",
    @SerializedName("repos_url")
    public String reposUrl; // : "https://api.github.com/users/yeojoy/repos",
    @SerializedName("events_url")
    public String eventsUrl; // : "https://api.github.com/users/yeojoy/events{/privacy}",
    @SerializedName("received_events_url")
    public String received_eventsUrl; // : "https://api.github.com/users/yeojoy/received_events",
    @SerializedName("type")
    public String type; // : "User",
    @SerializedName("site_admin")
    public boolean isSiteAdmin; // : false,
    @SerializedName("name")
    public String name; // : "Kim Yeojong",
    @SerializedName("company")
    public String company; // : null,
    @SerializedName("blog")
    public String blog; // : "",
    @SerializedName("location")
    public String location; // : "Seoul, South Korea",
    @SerializedName("email")
    public String email; // : null,
    @SerializedName("hireable")
    public Boolean hireable; // : null,
    @SerializedName("bio")
    public String bio; // : null,
    @SerializedName("public_repos")
    public int publicRepos; // : 128,
    @SerializedName("public_gists")
    public int publicGists; // : 3,
    @SerializedName("followers")
    public int followers; // : 16,
    @SerializedName("following")
    public int  following; // : 50,
    @SerializedName("created_at")
    public String createdAt; // : "2012-04-02T10:53:18Z",
    @SerializedName("updated_at")
    public String updatedAt; // : "2017-11-12T15:09:14Z"
}
