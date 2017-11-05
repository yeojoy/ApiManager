package me.yeojoy.apimanager.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yeojoy on 2017. 11. 4..
 */

public class Instagram {
    @SerializedName("id")
    public String id;
    @SerializedName("code")
    public String code;
    @SerializedName("user")
    public InstaUser user;
    @SerializedName("images")
    public InstaImages images;
    @SerializedName("created_time")
    public String createdTime;
    @SerializedName("caption")
    public InstaCaption caption;
    @SerializedName("likes")
    public InstaLikes likes;
    @SerializedName("comments")
    public InstaComments comments;
    @SerializedName("can_view_comments")
    public boolean canViewComments;
    @SerializedName("can_delete_comments")
    public boolean canDeleteComments;
    @SerializedName("type")
    public String type;
    @SerializedName("link")
    public String link;
}
